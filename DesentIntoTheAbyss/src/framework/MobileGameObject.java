package framework;

import java.io.FileNotFoundException;

import physics.Force;
import physics.NetForce;
import physics.Vector;

/**
 * <b>MobileGameObject</b>
 * <p>A representation of a mobile game object that adds forces, acceleration, and velocity to the basic game object</p>
 * @author abori
 * 5/11/2019
 */
public abstract class MobileGameObject extends GameObject{
	//variable declarations
	private NetForce forces;
	private Vector acceleration, velocity;
	private double lastTime;
	public static enum MoveState {inAir, onGround;}
	private MoveState moveState;
	
	//*Constructor*//
	/**
	 * <b>Complete Constructor</b>
	 * <p>initializes the texture, position, dimensions, and physics for this game object</p>
	 * @param textureFileName the file name of the image file used for this texture (complete filename; including .png, etc)
	 * @param posX the x position of the object 
	 * @param posY the y position of the object
	 * @param width the width of the object
	 * @param height the height of the object
	 * @param permeable whether or not other objects can pass through this object
	 * @throws FileNotFoundException when the file for the texture is not found
	 */
	public MobileGameObject(String textureFileName, double posX, double posY, double width, double height, boolean permeable) throws FileNotFoundException {
		//super stuff
		super(textureFileName, posX, posY, width, height, permeable);
		
		//other stuff
		this.forces = new NetForce();
		this.acceleration = new Vector(0,0);
		this.velocity = new Vector(0,0);
		this.lastTime = 0;
		this.moveState = MoveState.inAir;
	}

	//*Getters and Setters*//
	public NetForce getForces() {return forces;}
	public Vector getAcceleration() {return acceleration;}
	public Vector getVelocity() {return velocity;}
	public double getLastTime() {return lastTime;}
	public MoveState getMoveState() {return moveState;}
	public void setMoveState(MoveState moveState) {this.moveState = moveState;}
	
	//*Other Methods*//
	/**
	 * <b>update</b>
	 * <p>updates the game object based on  the current time</p>
	 * @param currentTime the current time (level time)
	 */
	public void update(double currentTime) {
		//calculating elapsedTime
		double elapsedTime = currentTime - this.lastTime;
	
		//updating forces
		if (this.moveState == MoveState.inAir) {
			this.forces.addForce("Gravity", new Force(0, GameConstants.GRAVITY, 0, currentTime));
		}
		this.forces.update(currentTime);
		
		//updating acceleration
		Force netForce = this.forces.getNetForce(currentTime);
		this.acceleration.setxComp(netForce.getVector().getxComp());
		this.acceleration.setyComp(netForce.getVector().getyComp());
		
		//updating velocity
		this.velocity.addX(this.acceleration.getxComp()*elapsedTime);
		this.velocity.addY(this.acceleration.getyComp()*elapsedTime);
		
		//updating position
		this.setPosX(this.getPosX() + this.velocity.getxComp()*elapsedTime);
		this.setPosY(this.getPosY() + this.velocity.getyComp()*elapsedTime);
		
		//updating last time
		this.lastTime = currentTime;
	}
	
}
