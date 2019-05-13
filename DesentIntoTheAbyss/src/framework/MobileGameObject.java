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
	private double lastTime, lowerBoundY, upperBoundY, lowerBoundX, upperBoundX;
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
		
		//physics stuff
		this.forces = new NetForce();
		this.acceleration = new Vector(0,0);
		this.velocity = new Vector(0,0);
		this.lastTime = 0;
		
		//other stuff
		this.moveState = MoveState.inAir;
		this.lowerBoundX = 0;
		this.lowerBoundY = 0;
		this.upperBoundX = GameConstants.STAGE_WIDTH;
		this.upperBoundY = GameConstants.STAGE_HEIGHT;
	}

	//*Getters and Setters*//
	public NetForce getForces() {return forces;}
	public Vector getAcceleration() {return acceleration;}
	public Vector getVelocity() {return velocity;}
	public double getLastTime() {return lastTime;}
	public double getLowerBoundX() {return lowerBoundX;}
	public void setLowerBoundX(double lowerBoundX) {this.lowerBoundX = lowerBoundX;}
	public double getUpperBoundX() {return upperBoundX;}
	public void setUpperBoundX(double upperBoundX) {this.upperBoundX = upperBoundX;}
	public double getLowerBoundY() {return lowerBoundY;}
	public void setLowerBoundY(double lowerBoundY) {this.lowerBoundY = lowerBoundY;}
	public double getUpperBoundY() {return upperBoundY;}
	public void setUpperBoundY(double upperBoundY) {this.upperBoundY = upperBoundY;}
	public MoveState getMoveState() {return moveState;}
	public void setMoveState(MoveState moveState) {this.moveState = moveState;}
	public void setBounds(double xLower, double xUpper, double yLower, double yUpper) {
		this.lowerBoundX = xLower;
		this.upperBoundX = xUpper;
		this.lowerBoundY = yLower;
		this.upperBoundY = yUpper;
	}
	
	//*Other Methods*//
	/**
	 * <b>update</b>
	 * <p>updates the game object based on  the current time</p>
	 * @param currentTime the current time (level time)
	 */
	public void update(double currentTime) {
		//calculating elapsedTime
		double elapsedTime = currentTime - this.lastTime;

		/*updating position*/
		//horizontal motion
		//if the movement moves the object past its lower bound for x, limit the movement
		if (this.getPosX() + velocity.getxComp()*elapsedTime < lowerBoundX) {
			this.setPosX(lowerBoundX + 1);
			this.getVelocity().setxComp(0);
		} 
		//if the movement moves the object past its upper bound for x, limit the movement
		else if (this.getPosX() + this.getWidth() + velocity.getxComp()*elapsedTime > upperBoundX) {
			this.setPosX(upperBoundX - this.getWidth() - 1);
			this.getVelocity().setxComp(0);
		}
		//otherwise, allow the movement
		else {
			this.setPosX(this.getPosX() + velocity.getxComp()*elapsedTime);
		}
		//vertical motion
		//if the movement moves the object past its lower bound for y, limit the movement
		if (this.getPosY() + velocity.getyComp()*elapsedTime < lowerBoundY) {
			this.setPosY(lowerBoundY + 1);
			this.getVelocity().setyComp(0);
		} 
		//if the movement moves the object past its upper bound for y, limit the movement
		else if (this.getPosY() + this.getHeight() + velocity.getyComp()*elapsedTime > upperBoundY) {
			this.setPosY(upperBoundY - this.getHeight() - 1);
			this.getVelocity().setyComp(0);
			this.setMoveState(MoveState.onGround);
		}
		//otherwise, allow the movement
		else {
			this.setPosY((int)(this.getPosY() + velocity.getyComp()*elapsedTime));
			this.setMoveState(MoveState.inAir);
		}	
		
		//updating velocity
		this.velocity.addX(this.acceleration.getxComp()*elapsedTime);
		this.velocity.addY(this.acceleration.getyComp()*elapsedTime);
		
		//updating acceleration
		Force netForce = this.forces.getNetForce(currentTime);
		this.acceleration.setxComp(netForce.getVector().getxComp());
		this.acceleration.setyComp(netForce.getVector().getyComp());
		
		//updating forces
		this.forces.update(currentTime);
		if (this.moveState == MoveState.inAir) {
			this.forces.addForce("Gravity", new Force(0, GameConstants.GRAVITY, 0, currentTime));
		}
		
		//updating last time
		this.lastTime = currentTime;
	}
	
}
