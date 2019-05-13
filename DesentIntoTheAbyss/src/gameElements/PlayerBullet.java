package gameElements;

import java.io.FileNotFoundException;

import framework.GameConstants;

public class PlayerBullet extends MobileGameObject{
	
	public PlayerBullet(int x, int y) throws FileNotFoundException {
		super("./res/Bullet.png", x, y, 5, 5, true);
		this.setVelocity(new Vector(5,0));
		this.setAcceleration(new Vector(0,0));
		
		// TODO Auto-generated constructor stub
	}

	public boolean outOfBounds() {
		return this.getPosX() > GameConstants.STAGE_WIDTH || this.getPosY() > GameConstants.STAGE_HEIGHT; 
	}
	
	
}
