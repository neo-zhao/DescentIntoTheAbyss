package gameElements;

import java.io.FileNotFoundException;

//imports
import framework.GameConstants;

/**
 * <b>MobileGameObject</b>
 * <p>General representation of a game object that moves</p>
 * @author Neo Zhao
 * 5/7/2019
 */
public class MobileGameObject extends GameObject{
	//variable declarations
	protected double lastTime;
	protected Vector velocity, acceleration;
	protected double lowerBoundY, upperBoundY, lowerBoundX, upperBoundX;
	
	//*Constructors*//
	/**
	 * <b>Complete constructor</b>
	 * <p>Initializes the texture, position, permeability, velocity, acceleration, and acting forces of the new mobile game object</p>
	 * @param t the file name of the texture (complete filename; including .png, etc)
	 * @param x the x position of the object on the canvas
	 * @param y the y position of the object on the canvas
	 * @param w the width of the object
	 * @param h the height of the object
	 * @param p whether or not other game objects can pass through this game object or not
	 * @param v the starting velocity of the mobile game object
	 * @param a the starting acceleration of the mobile game object
	 * @throws FileNotFoundException 
	 */
	public MobileGameObject(String t, int x, int y, int w, int h, boolean p, Vector v, Vector a) throws FileNotFoundException {
		super(t, x, y, w, h, p);
		this.velocity = v;
		this.acceleration = a;
		this.lastTime = 0;
		this.lowerBoundX = 0;
		this.lowerBoundY = 0;
		this.upperBoundX = GameConstants.STAGE_WIDTH;
		this.upperBoundY = GameConstants.STAGE_HEIGHT;
		
	}

	/**
	 * <b>Semi-complete constructor</b>
	 * <p>Initializes the texture, position, permeability, velocity, acceleration, and acting forces of the new mobile game object</p>
	 * @param t the file name of the texture (complete filename; including .png, etc)
	 * @param x the x position of the object on the canvas
	 * @param y the y position of the object on the canvas
	 * @param w the width of the object
	 * @param h the height of the object
	 * @param p whether or not other game objects can pass through this game object or not
	 * @throws FileNotFoundException 
	 */
	public MobileGameObject(String t, int x, int y, int w, int h, boolean p) throws FileNotFoundException {
		this(t, x, y, w, h, p, new Vector(0,0), new Vector(0, GameConstants.GRAVITY));
		this.lastTime = 0;
	}
	
	//*Getters and Setters*//
	public Vector getVelocity() {return velocity;}
	public void setVelocity(Vector velocity) {this.velocity = velocity;}
	public Vector getAcceleration() {return acceleration;}
	public void setAcceleration(Vector acceleration) {this.acceleration = acceleration;}
	public double getLastTime() {return lastTime;}
	public void setLastTime(double lastTime) {this.lastTime = lastTime;}
	public double getLowerBoundX() {return lowerBoundX;}
	public void setLowerBoundX(double d) {this.lowerBoundX = d;}
	public double getLowerBoundY() {return lowerBoundY;}
	public void setLowerBoundY(double d) {this.lowerBoundY = d;}
	public double getUpperBoundX() {return upperBoundX;}
	public void setUpperBoundX(double posX) {this.upperBoundX = posX;}
	public double getUpperBoundY() {return upperBoundY;}
	public void setUpperBoundY(double posY) {this.upperBoundY = posY;}
	
	//*Other Methods*//
	/**
	 * <b>update</b>
	 * <p>updates the position and velocity</p>
	 * @param t the current time
	 */
	public void update(double t) {
		//calculating elapsedTime
		double elapsedTime = t - lastTime;
		
		/*updating position*/
		//horizontal motion
		//if the movement moves the object past its lower bound for x, limit the movement
		if (super.posX + velocity.getxComp()*elapsedTime < lowerBoundX) {
			this.setPosX(lowerBoundX + 1);
			this.getVelocity().setxComp(0);
		} 
		//if the movement moves the object past its upper bound for x, limit the movement
		else if (super.posX + super.getWidth() + velocity.getxComp()*elapsedTime > upperBoundX) {
			this.setPosX(upperBoundX - super.getWidth() - 1);
			this.getVelocity().setxComp(0);
		}
		//otherwise, allow the movement
		else {
			this.setPosX(super.posX + velocity.getxComp()*elapsedTime);
		}
		//vertical motion
		//if the movement moves the object past its lower bound for y, limit the movement
		if (super.posY + velocity.getyComp()*elapsedTime < lowerBoundY) {
			this.setPosY(lowerBoundY + 1);
			this.getVelocity().setyComp(0);
		} 
		//if the movement moves the object past its upper bound for x, limit the movement
		else if (super.posY + super.getHeight() + velocity.getyComp()*elapsedTime > upperBoundY) {
			this.setPosY(upperBoundY - super.getHeight() - 1);
			this.getVelocity().setyComp(0);
			
			//if target is player, change player state to onGround
			if (this instanceof Player) {
				((Player)this).setPlayerState(PlayerState.OnGround);
			}
		}
		//otherwise, allow the movement
		else {
			this.setPosY((int)(super.posY + velocity.getyComp()*elapsedTime));
		}	
		
		//updating velocity
		velocity.setxComp(velocity.getxComp() + acceleration.getxComp()*elapsedTime);
		velocity.setyComp(velocity.getyComp() + acceleration.getyComp()*elapsedTime);
		
		//updating last time
		lastTime = t;
		
		//updating moveBounds
		this.lowerBoundX = 0;
		this.lowerBoundY = 0;
		this.upperBoundX = GameConstants.STAGE_WIDTH;
		this.upperBoundY = GameConstants.STAGE_HEIGHT;
	}
	
}
