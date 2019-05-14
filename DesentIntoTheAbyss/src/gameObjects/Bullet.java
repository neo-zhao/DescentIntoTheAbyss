package gameObjects;

import java.io.FileNotFoundException;

import framework.GameConstants;
import framework.MobileGameObject;

/**
 * <b>Bullet</b>
 * </p>bullets that can be shot</p>
 * @author abori
 * 5/13/2019
 */
public class Bullet extends MobileGameObject{
	//variable declarations
	private boolean viable;
	
	//*Constructors*//
	public Bullet(double posX, double posY) throws FileNotFoundException {
		super("./res/Black.png", posX, posY, 10, 10, true);
		this.viable = true;
		
		//sets velocity	
		this.getVelocity().setxComp(GameConstants.BULLET_VELO);
	
		//makes gravity not affect the bullet
		this.setAffectedGravity(false);
		
	}
	
	//*Getters and Setters*//
	public boolean isViable() {
		return viable;
	}
	
	//*Other Methods*//

	@Override
	protected void handleCollisionAddOn(CollisionState collisionState) {
		this.viable = false;
	}
	
	
}
