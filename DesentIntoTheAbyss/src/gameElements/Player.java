package gameElements;

import java.io.FileNotFoundException;

import framework.GameConstants;
import framework.GameMain;

/**
 * <b>Player</b>
 * <p>Representation of the player object</p>
 * @author Neo Zhao
 * 5/7/2019
 */
public class Player extends MobileGameObject{
	PlayerState playerState;
	boolean hasDash;
	double lastDash = 0;
	
	//*Constructors*//
	/**
	 * <b>Complete constructor</b>
	 * <p>Initializes the texture, position, permeability, velocity, acceleration, and acting forces of the new player object</p>
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
	public Player(String t, int x, int y, int w, int h, Vector v, Vector a) throws FileNotFoundException {
		super(t, x, y, w, h, false, v, a);
	}
	
	/**
	 * <b>Semi-complete constructor</b>
	 * <p>Initializes the texture, position, permeability, velocity, acceleration, and acting forces of the new player object</p>
	 * @param t the file name of the texture (complete filename; including .png, etc)
	 * @param x the x position of the object on the canvas
	 * @param y the y position of the object on the canvas
	 * @param w the width of the object
	 * @param h the height of the object
	 * @param p whether or not other game objects can pass through this game object or not
	 * @throws FileNotFoundException 
	 */
	public Player(String t, int x, int y, int w, int h) throws FileNotFoundException {
		super(t, x, y, w, h, false);
	}
	
	//*Getters and Setters*//
	public PlayerState getPlayerState() {return playerState;}
	public void setPlayerState(PlayerState playerState) {
		this.playerState = playerState;
		if (playerState == PlayerState.OnGround) {
			hasDash = true;
		}
	}
	
	//*Other Methods*//
	@Override
	public void update(double t) {
		//handles shooting bullets
		//TODO how to add bullets onto the screen
		//TODO "Aim" bullets
		if(GameMain.keyInput.contains("SPACE")) {
			try {
				PlayerBullet p = new PlayerBullet((int)this.getPosX(), (int)this.getPosY());
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//handles moving right
		if (GameMain.keyInput.contains("RIGHT")) {
			super.getVelocity().setxComp(GameConstants.MOVE_SPEED);
		}
		if (!GameMain.keyInput.contains("RIGHT") && super.getVelocity().getxComp() > 0) {
			super.getVelocity().setxComp(0);
		}
		
		//handles moving left
		if (GameMain.keyInput.contains("LEFT")) {
			super.getVelocity().setxComp(-GameConstants.MOVE_SPEED);
		}
		if (!GameMain.keyInput.contains("LEFT")  && super.getVelocity().getxComp() < 0) {
			super.getVelocity().setxComp(0);
		}
		
		//handles jumping
		if (GameMain.keyInput.contains("UP") && playerState == PlayerState.OnGround) {
			super.getVelocity().setyComp(GameConstants.JUMP_VELO);
			playerState = PlayerState.InAir;
		}
		
		//handles dashing: controlled by has dash (regain by being on ground) and dash buffer
		if (hasDash == true && GameMain.keyInput.contains("D") && t - lastDash > GameConstants.DASH_BUFFER) {
			DashState dashState = DashState.None;

			//checks for dash down
			if(GameMain.keyInput.contains("DOWN")) {dashState = DashState.Down;}
			//check for dash left
			if(GameMain.keyInput.contains("LEFT")) {dashState = DashState.Left;}
			//checks for dash up
			if(GameMain.keyInput.contains("UP")) {dashState = DashState.Up;}
			//checks for dash right
			if(GameMain.keyInput.contains("RIGHT")) {dashState = DashState.Right;}
			//checks for dash down left
			if(GameMain.keyInput.contains("DOWN") && GameMain.keyInput.contains("LEFT")) {dashState = DashState.DownLeft;}
			//checks for dash left up
			if(GameMain.keyInput.contains("UP") && GameMain.keyInput.contains("LEFT")) {dashState = DashState.LeftUp;}
			//checks for dash right down
			if(GameMain.keyInput.contains("RIGHT") && GameMain.keyInput.contains("DOWN")) {dashState = DashState.RightDown;}
			//checks for dash right up
			if(GameMain.keyInput.contains("RIGHT") && GameMain.keyInput.contains("UP")) {dashState = DashState.UpRight;}
			
			
			//handles dash according to dash state (if not none)
			if(dashState != DashState.None) {
				switch (dashState) {
				case Down:
					super.setPosY(posY + GameConstants.DASH_LENGTH);
					super.getVelocity().setyComp(0);
					break;
				case DownLeft:
					super.setPosY(posY + GameConstants.DASH_LENGTH *Math.sqrt(2)/2);
					super.setPosX(posX - GameConstants.DASH_LENGTH *Math.sqrt(2)/2);
					super.getVelocity().setyComp(0);
					super.getVelocity().setxComp(0);
					break;
				case Left:
					super.setPosX(posX - GameConstants.DASH_LENGTH);
					super.getVelocity().setxComp(0);
					break;
				case LeftUp:
					super.setPosY(posY - GameConstants.DASH_LENGTH *Math.sqrt(2)/2);
					super.setPosX(posX - GameConstants.DASH_LENGTH *Math.sqrt(2)/2);
					super.getVelocity().setyComp(0);
					super.getVelocity().setxComp(0);
					break;
				case Right:
					super.setPosX(posX + GameConstants.DASH_LENGTH);
					super.getVelocity().setxComp(0);
					break;
				case RightDown:
					super.setPosY(posY + GameConstants.DASH_LENGTH *Math.sqrt(2)/2);
					super.setPosX(posX + GameConstants.DASH_LENGTH *Math.sqrt(2)/2);
					super.getVelocity().setyComp(0);
					super.getVelocity().setxComp(0);
					break;
				case Up:
					super.setPosY(posY - GameConstants.DASH_LENGTH);
					super.getVelocity().setyComp(0);
					playerState = PlayerState.InAir;
					break;
				case UpRight:
					super.setPosY(posY - GameConstants.DASH_LENGTH *Math.sqrt(2)/2);
					super.setPosX(posX + GameConstants.DASH_LENGTH *Math.sqrt(2)/2);
					super.getVelocity().setyComp(0);
					super.getVelocity().setxComp(0);
					break;
				default:
					break;
				}	
				
				//updates dash control variables
				hasDash = false;
				lastDash = t;
			}			
		}
		//TODO
			
		//updates movement in accordance to mobile object update
		super.update(t);
	}
	

}
