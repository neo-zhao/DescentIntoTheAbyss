package framework;

import java.io.FileNotFoundException;

import javafx.scene.image.ImageView;
import managers.TextureManager;
import physics.Calculations;
import physics.Force;

/**
 * <b>GameObject</b>
 * <p>The most basic representation of a game object containing texture, position, and dimensions</p>
 * @author abori
 * 5/11/2019
 */
public abstract class GameObject {
	//variable declarations
	private ImageView image;
	private double posX;
	private double posY;
	private double width;
	private double height;
	private boolean permeable;
	
	//*Constructors*//
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
	public GameObject(String textureFileName, double posX, double posY, double width, double height, boolean permeable) throws FileNotFoundException {
		//initializes texture
		this.image = new ImageView(TextureManager.getTexture(textureFileName));
		this.image.setX(posX);
		this.image.setY(posY);
		this.image.setFitWidth(width);
		this.image.setFitHeight(height);
		
		//initializes other stuff
		this.posX = posX;
		this.posY = posY;
		this.width = width;
		this.height = height;
		this.permeable = permeable;
	}
	
	//*Getters and Setters*//
	public ImageView getImage() {return image;}
	public double getPosX() {return posX;}
	public void setPosX(double posX) {
		this.image.setX(this.image.getX()+ posX - this.posX);
		this.posX = posX;
	}
	public double getPosY() {return posY;}
	public void setPosY(double posY) {
		this.image.setY(this.image.getY()+ posY - this.posY);
		this.posY = posY;
	}
	public double getWidth() {return width;}
	public double getHeight() {return height;}
	public boolean getPermeable() {return permeable;}
	
	//*Other Methods*//
	/**
	 * <b>displaceImage</b>
	 * <p>moves the image of the object relative to the position of the camera (from level)</p>
	 * @param viewDisplacementX the x value for position of the camera
	 * @param viewDisplacementY the y value for position of the camera
	 */
	public void displaceImage(double viewDisplacementX, double viewDisplacementY) {
		this.image.setX(posX - viewDisplacementX);
		this.image.setY(posY - viewDisplacementY);
	}
	
	/**
	 * <b>handleCollision</b>
	 * <p>handles the collision (or lack thereof) of a mobile game object with this object</p>
	 * @param g the mobile game object whose collision is being handled
	 * @param currentTime
	 */
	public void handleCollision(MobileGameObject g, double currentTime) {	
		//only handle collisions if this object is not permeable
		if (!this.permeable && this.hasCollision(g, currentTime)) {
			//checks for collision with top of this object
			if (g.getPosY() + g.getHeight() < this.posY) {
				//update velocity
				g.getVelocity().setyComp(0);
				
				//update position
				g.setPosY(this.posY - g.getHeight() - 1);
				
				//update move state
				g.setMoveState(MobileGameObject.MoveState.onGround);
			}
			//checks for collision with bottom of this object
			if (g.getPosY() > this.posY + this.height) {
				//update velocity
				g.getVelocity().setyComp(0);
				
				//update position
				g.setPosY(this.posY + 1);
			}
			//check for collision with left of this object
			if (g.getPosX() + g.getWidth() < this.posX) {
				//update velocity
				g.getVelocity().setxComp(0);
				
				//update position
				g.setPosY(this.posX - g.getWidth() - 1);
			}
			//check for collision with right of this object
			if (g.getPosX() > this.posX + this.height) {
				//update velocity
				g.getVelocity().setxComp(0);
				
				//update position
				g.setPosY(this.posX + 1);
			}
		}
	}
	
	/**
	 * <b>has Collision</b>
	 * <p>returns true if g will collide with this game object</p>
	 * @param g the mobile game object that is being tested
	 * @param currentTime the current time (level time)
	 * @return true if there is a collision
	 */
	private boolean hasCollision(MobileGameObject g, double currentTime) {
		//calculate elapsedTime
		double elapsedTime = currentTime - g.getLastTime();
		
		//checks for horizontal overlap
		if (Calculations.hasOverlap(this.posX, this.posX + this.height, 
				g.getPosX() + g.getVelocity().getxComp()*elapsedTime, 
				g.getPosX() + g.getVelocity().getxComp()*elapsedTime + g.getHeight())) {
			//System.out.println("horizontal overlap");
			//checks for vertical overlap
			if (Calculations.hasOverlap(this.posY, this.posY + this.width, 
					g.getPosY() + g.getVelocity().getyComp()*elapsedTime, 
					g.getPosY() + g.getVelocity().getyComp()*elapsedTime + g.getWidth())) {
				System.out.println("vertical overlap");
				return true;
			}
		}
		return false;
	}
}
