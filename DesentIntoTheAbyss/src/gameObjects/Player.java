package gameObjects;

import java.io.FileNotFoundException;

import framework.GameConstants;
import framework.GameMain;
import framework.MobileGameObject;
import framework.UserAffectedGameObject;

public class Player extends MobileGameObject implements UserAffectedGameObject{
	//variable declarations
	
	//*Constructors*//
	/**
	 * <b>Complete Constructor</b>
	 * <p>initializes the texture, position, dimensions, and physics for this game object</p>
	 * @param textureFileName the file name of the image file used for this texture (complete filename; including .png, etc)
	 * @param posX the x position of the object 
	 * @param posY the y position of the object
	 * @param width the width of the object
	 * @param height the height of the object
	 * @throws FileNotFoundException when the file for the texture is not found
	 */
	public Player(String textureFileName, double posX, double posY, double width, double height) throws FileNotFoundException {
		super(textureFileName, posX, posY, width, height, false);
	}

	//*Getters and Setters*//
	
	//*Other Methods*//
	@Override
	public void handleInput(double currentTime) {
		
		//handles left and right motion
		this.handleMoveLeft();
		this.handleMoveRight();
		
		
		
		//handles jumping
		if (GameMain.keyInput.contains("UP") && this.getMoveState() == MoveState.onGround) {
			super.getVelocity().setyComp(GameConstants.JUMP_VELO);
			this.setMoveState(MoveState.inAir);
		}
		
//		//handles dashing: controlled by has dash (regain by being on ground) and dash buffer
//		if (hasDash == true && GameMain.keyInput.contains("D") && t - lastDash > GameConstants.DASH_BUFFER) {
//			DashState dashState = DashState.None;
//			
//			//checks for dash down
//			if(GameMain.keyInput.contains("DOWN")) {dashState = DashState.Down;}
//			//check for dash left
//			if(GameMain.keyInput.contains("LEFT")) {dashState = DashState.Left;}
//			//checks for dash up
//			if(GameMain.keyInput.contains("UP")) {dashState = DashState.Up;}
//			//checks for dash right
//			if(GameMain.keyInput.contains("RIGHT")) {dashState = DashState.Right;}
//			//checks for dash down left
//			if(GameMain.keyInput.contains("DOWN") && GameMain.keyInput.contains("LEFT")) {dashState = DashState.DownLeft;}
//			//checks for dash left up
//			if(GameMain.keyInput.contains("UP") && GameMain.keyInput.contains("LEFT")) {dashState = DashState.LeftUp;}
//			//checks for dash right down
//			if(GameMain.keyInput.contains("RIGHT") && GameMain.keyInput.contains("DOWN")) {dashState = DashState.RightDown;}
//			//checks for dash right up
//			if(GameMain.keyInput.contains("RIGHT") && GameMain.keyInput.contains("UP")) {dashState = DashState.UpRight;}
//			
//			
//			//handles dash according to dash state (if not none)
//			if(dashState != DashState.None) {
//				switch (dashState) {
//				case Down:
//					super.setPosY(posY + GameConstants.DASH_LENGTH);
//					super.getVelocity().setyComp(0);
//					break;
//				case DownLeft:
//					super.setPosY(posY + GameConstants.DASH_LENGTH *Math.sqrt(2)/2);
//					super.setPosX(posX - GameConstants.DASH_LENGTH *Math.sqrt(2)/2);
//					super.getVelocity().setyComp(0);
//					super.getVelocity().setxComp(0);
//					break;
//				case Left:
//					super.setPosX(posX - GameConstants.DASH_LENGTH);
//					super.getVelocity().setxComp(0);
//					break;
//				case LeftUp:
//					super.setPosY(posY - GameConstants.DASH_LENGTH *Math.sqrt(2)/2);
//					super.setPosX(posX - GameConstants.DASH_LENGTH *Math.sqrt(2)/2);
//					super.getVelocity().setyComp(0);
//					super.getVelocity().setxComp(0);
//					break;
//				case Right:
//					super.setPosX(posX + GameConstants.DASH_LENGTH);
//					super.getVelocity().setxComp(0);
//					break;
//				case RightDown:
//					super.setPosY(posY + GameConstants.DASH_LENGTH *Math.sqrt(2)/2);
//					super.setPosX(posX + GameConstants.DASH_LENGTH *Math.sqrt(2)/2);
//					super.getVelocity().setyComp(0);
//					super.getVelocity().setxComp(0);
//					break;
//				case Up:
//					super.setPosY(posY - GameConstants.DASH_LENGTH);
//					super.getVelocity().setyComp(0);
//					playerState = PlayerState.InAir;
//					break;
//				case UpRight:
//					super.setPosY(posY - GameConstants.DASH_LENGTH *Math.sqrt(2)/2);
//					super.setPosX(posX + GameConstants.DASH_LENGTH *Math.sqrt(2)/2);
//					super.getVelocity().setyComp(0);
//					super.getVelocity().setxComp(0);
//					break;
//				default:
//					break;
//				}	
//				
//				//updates dash control variables
//				hasDash = false;
//				lastDash = t;
//			}			
//		}
	}	
	
	/**
	 * <b>handleMoveLeft</b>
	 * <p>handles the left movement of the player object</p>
	 */
	private void handleMoveLeft() {
		//handles moving left
		if (GameMain.keyInput.contains("LEFT")) {
			this.getVelocity().setxComp(-GameConstants.MOVE_SPEED);
		}
		if (!GameMain.keyInput.contains("LEFT")  && this.getVelocity().getxComp() < 0) {
			this.getVelocity().setxComp(0);
		}
	}
	
	/**
	 * <b>handleMoveRight</b>
	 * <p>handles the right movement of the player object</p>
	 */
	private void handleMoveRight() {
		//handles moving right
		if (GameMain.keyInput.contains("RIGHT")) {
			this.getVelocity().setxComp(GameConstants.MOVE_SPEED);
		}
		if (!GameMain.keyInput.contains("RIGHT") && super.getVelocity().getxComp() > 0) {
			this.getVelocity().setxComp(0);
		}
		
	}
}	