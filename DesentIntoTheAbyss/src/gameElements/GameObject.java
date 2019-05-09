package gameElements;

import java.io.FileNotFoundException;

import framework.GameConstants;
//imports
import javafx.scene.image.ImageView;
import managers.TextureManager;

/**
 * <b>GameObject</b>
 * <p>General representation of a game object</p>
 * @author Neo Zhao
 * 5/6/2019
 */
public class GameObject {
	//variable declarations
	protected String image;
	protected ImageView texture;
	protected double posX, posY, width, height;
	protected boolean permeable;
	protected double lastTime;
	
	//*Constructors*//
	/**
	 * <b>Complete constructor</b>
	 * <p>Initializes the texture, position, and permeability of the new game object</p>
	 * @param t the file name of the texture (complete filename; including .png, etc)
	 * @param x the x position of the object on the canvas
	 * @param y the y position of the object on the canvas
	 * @param w the width of the object
	 * @param h the height of the object
	 * @param p whether or not other game objects can pass through this game object or not
	 * @throws FileNotFoundException 
	 */
	public GameObject(String t, int x, int y, int w, int h, boolean p) throws FileNotFoundException {
		this.image = t;
		this.posX = x;
		this.posY = y;
		this.width = w;
		this.height = h;
		this.permeable = p;
		
		//texture stuff
		this.texture = new ImageView(TextureManager.getTexture(image));
		this.texture.setX(x);
		this.texture.setY(y);
		this.texture.setFitWidth(w);
		this.texture.setFitHeight(h);
		
	}
	
	/**
	 * <b>Semi-complete constructor</b>
	 * <p>Initializes the texture and position of the new game object; sets permeability to false</p>
	 * @param t the file name of the texture (complete filename; including .png, etc)
	 * @param x the x position of the object on the canvas
	 * @param y the y position of the object on the canvas
	 * @param w the width of the object
	 * @param h the height of the object
	 * @throws FileNotFoundException 
	 */
	public GameObject(String t, int x, int y, int w, int h) throws FileNotFoundException {
		this(t,x,y,w,h,false);
	}
	
	//*Getters and Setters*//
	public String getImage() {return image;}
	public void setImage(String image) throws FileNotFoundException {
		this.image = image;
		this.texture = new ImageView(TextureManager.getTexture(image));
		this.texture.setX(this.posX);
		this.texture.setY(this.posY);
		this.texture.setFitWidth(this.width);
		this.texture.setFitHeight(this.height);
	}
	public double getPosX() {return posX;}
	public void setPosX(double d) {
		this.posX = d;
		this.texture.setX(d);
	}
	public double getPosY() {return posY;}
	public void setPosY(double d) {
		this.posY = d;
		this.texture.setY(this.posY);
	}
	public boolean getPermeable() {return permeable;}
	public void setPermeable(boolean permeable) {this.permeable = permeable;}
	public double getHeight() {return height;}
	public void setHeight(int height) {
		this.height = height;
		this.texture.setFitHeight(this.height);
	}
	public double getWidth() {return width;}
	public void setWidth(int width) {
		this.width = width;
		this.texture.setFitWidth(this.width);
	}
	public ImageView getTexture() {return texture;}
	
	//*Other Methods*//
	/**
	 * <b>update</b>
	 * <p>updates lastTime</p>
	 * @param t the current time
	 */
	public void update(double t) {
		lastTime = t;
	}
	
	/**
	 * <b>handleCollision</b>
	 * <p>updates the upper and lower bounds for the movement of the target mobile game object depending on its position relative to this object.</p>
	 * <p>If purely horizontal or purely vertical motion of the target does not result in a collision, nothing will be updated.</p>
	 * @param g the target mobile game object
	 */
	public void handleCollision(MobileGameObject g, double t) {
		//if horizontal motion of the object results in a possible collision
		if (within(g.getPosY(), posY, posY + height) || within(g.getPosY() + g.getHeight(), posY, posY + height)) {
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
		if (within(g.getPosX(), posX, posX + width) || within(g.getPosX() + g.getWidth(), posX, posX + width)) {
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
		double elapsedTime = t - lastTime;
		
		/*corner cases*/
		
		//checks if top left corner is within this object after motion
		if (within(g.getPosX() + g.getVelocity().getxComp()*elapsedTime, posX, posX+width) && within(g.getPosY() + g.getVelocity().getyComp()*elapsedTime, posY, posY+height)) {
			if (g.getLowerBoundX() < g.getPosX()) {g.setLowerBoundX(g.getPosX());}
			if (g.getLowerBoundY() < g.getPosY()) {g.setLowerBoundY(g.getPosY());}
			System.out.println("top left corner");
		}
		//checks if top right corner is within this object after motion
		if (within(g.getPosX() + g.getWidth() + g.getVelocity().getxComp()*elapsedTime, posX, posX+width) && within(g.getPosY() + g.getVelocity().getyComp()*elapsedTime, posY, posY+height)) {
			if (g.getUpperBoundX() > g.getPosX() + g.getWidth()) {g.setUpperBoundX(g.getPosX() + g.getWidth());}
			if (g.getLowerBoundY() < g.getPosY()) {g.setLowerBoundY(g.getPosY());}
			System.out.println("top right corner");
		}
		//checks bottom corners if in air
//		if (g instanceof Player) {
//			if (((Player)g).getPlayerState() == PlayerState.InAir) {
//				//checks if bottom left corner is within this object after motion
//				if (within(g.getPosX() + g.getVelocity().getxComp()*elapsedTime, posX, posX+width) && within(g.getPosY() + g.getHeight() + g.getVelocity().getyComp()*elapsedTime, posY, posY+height)) {
//					g.setLowerBoundX(g.getPosX());
//					g.setUpperBoundY(g.getPosY() + g.getHeight());
//					System.out.println("bottom left corner");
//				}
//				//checks if bottom right corner is within this object after motion
//				if (within(g.getPosX() + g.getWidth() + g.getVelocity().getxComp()*elapsedTime, posX, posX+width) && within(g.getPosY() + g.getHeight() + g.getVelocity().getyComp()*elapsedTime, posY, posY+height)) {
//					g.setUpperBoundX(g.getPosX() + g.getWidth());
//					g.setUpperBoundY(g.getPosY() + g.getHeight());
//					System.out.println("bottom right corner");
//				}
//			}
//		}
	}
	
	/**
	 * <b>within</b>
	 * <p>if t is greater than or equal to a and less than or equal to b, return true</p>
	 * @param t the target
	 * @param a the lower bound
	 * @param b the upper bound
	 * @return true is t is between the lower bound and upper bound
	 */
	private boolean within(double t, double a, double b) {
		return (t >= a && t <= b);
	}
}
