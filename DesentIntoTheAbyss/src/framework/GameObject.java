package framework;

import java.io.FileNotFoundException;

import javafx.scene.image.ImageView;
import managers.TextureManager;
import physics.Calculations;

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
		if (!this.permeable) {
			//if horizontal motion of the object results in a possible collision
			if (Calculations.hasOverlap(posY, posY + height, g.getPosY(), g.getPosY() + g.getHeight())) {
				//if the target is to the left of this object
				if (g.getPosX() <= posX && g.getUpperBoundX() > posX - GameConstants.HITBOX_BUFFER) {
					g.setUpperBoundX(posX - GameConstants.HITBOX_BUFFER);
				}
				//if the target is to the right of this object
				if (g.getPosX() > posX + width && g.getLowerBoundX() < posX + width + GameConstants.HITBOX_BUFFER) {
					g.setLowerBoundX(posX + width + GameConstants.HITBOX_BUFFER);
				}
			}
			//if vertical motion of the object results in a possible collision
			if (Calculations.hasOverlap(posX, posX + width, g.getPosX(), g.getPosX() + g.getWidth())) {
				//if the target is above this object
				if (g.getPosY() <= posY && g.getUpperBoundY() > posY - GameConstants.HITBOX_BUFFER) {
					g.setUpperBoundY(posY - GameConstants.HITBOX_BUFFER);
				}
				//if the target is below this object
				if (g.getPosY() > posY + height && g.getLowerBoundY() < posY + height + GameConstants.HITBOX_BUFFER) {
					g.setLowerBoundY(posY + height + GameConstants.HITBOX_BUFFER);
					//System.out.println("lower bound: " + (posY + height + GameConstants.HITBOX_BUFFER));
				}
			}
			
			//calculate elapsed time
			double elapsedTime = currentTime - g.getLastTime();
			
			/*corner cases*/
			
			//checks if top left corner is within this object after motion
			if (Calculations.isBetween(posX, posX+width,g.getPosX() + g.getVelocity().getxComp()*elapsedTime) && 
					Calculations.isBetween(posY, posY+height, g.getPosY() + g.getVelocity().getyComp()*elapsedTime)) {
				if (g.getLowerBoundX() < g.getPosX()) {g.setLowerBoundX(g.getPosX());}
				if (g.getLowerBoundY() < g.getPosY()) {g.setLowerBoundY(g.getPosY());}

			}
			//checks if top right corner is within this object after motion
			if (Calculations.isBetween(posX, posX+width, g.getPosX() + g.getWidth() + g.getVelocity().getxComp()*elapsedTime) &&
					Calculations.isBetween(posY, posY+height, g.getPosY() + g.getVelocity().getyComp()*elapsedTime)) {
				if (g.getUpperBoundX() > g.getPosX() + g.getWidth()) {g.setUpperBoundX(g.getPosX() + g.getWidth());}
				if (g.getLowerBoundY() < g.getPosY()) {g.setLowerBoundY(g.getPosY());}
			}
		}
	}
}
