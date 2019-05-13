package gameObjects;

import java.io.FileNotFoundException;

import framework.GameConstants;
import framework.GameMain;
import framework.MobileGameObject;
import framework.UserAffectedGameObject;
import physics.Force;

public class Player extends MobileGameObject implements UserAffectedGameObject{
	//variable declarations
	boolean inDash, hasDash;
	double lastDash;
	private enum DashState {None, Down, DownLeft, Left, LeftUp, Up, UpRight, Right, RightDown;}
	
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
		this.inDash = false;
		this.hasDash = false;
		this.lastDash = 0;
	}

	//*Getters and Setters*//
	
	//*Other Methods*//
	@Override
	public void handleInput(double currentTime) {
		//updates has dash
		if (this.getMoveState() == MoveState.onGround) {
			hasDash = true;
		}
		
		//updates in dash
		if (this.getForces().getForce("DashLag") == null) {
			inDash = false;
		}
				
		//only handles user input if not currently in dash
		if (!inDash) {
			//handles basic motion
			this.handleMoveLeft();
			this.handleMoveRight();
			this.handleJump();
			
			//handles dashing: controlled by has dash (regain by being on ground) and dash buffer
			this.handleDash(currentTime);
			
		}	
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
	
	/**
	 * <b>handleJump</b>
	 * <p>handles the jump movement of the player object</p>
	 */
	private void handleJump() {
		if (GameMain.keyInput.contains("UP") && this.getMoveState() == MoveState.onGround) {
			super.getVelocity().setyComp(GameConstants.JUMP_VELO);
			this.setMoveState(MoveState.inAir);
		}
	}
	
	/**
	 * <b>handleDash</b>
	 * <p>handles the dash movement of the player object</p>
	 */
	private void handleDash(double t) {
		if (GameMain.keyInput.contains("D") && hasDash == true && t - lastDash > GameConstants.DASH_BUFFER) {
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
					this.getVelocity().setyComp(GameConstants.DASH_VELO);
					this.getForces().addForce("DashLag", new Force(0, -GameConstants.DASH_VELO, 1, t));
					break;
				case DownLeft:
					this.getVelocity().setxComp(-GameConstants.DASH_VELO*Math.sqrt(2)*4);
					this.getVelocity().setyComp(GameConstants.DASH_VELO*Math.sqrt(2)/2);
					this.getForces().addForce("DashLag", new Force(GameConstants.DASH_VELO*Math.sqrt(2)/2, -GameConstants.DASH_VELO*Math.sqrt(2)/2, 1, t));
					break;
				case Left:
					this.getVelocity().setxComp(-GameConstants.DASH_VELO*8);
					this.getForces().addForce("DashLag", new Force(GameConstants.DASH_VELO, 0, 1, t));
					break;
				case LeftUp:
					this.getVelocity().setxComp(-GameConstants.DASH_VELO*Math.sqrt(2)*4);
					this.getVelocity().setyComp(-GameConstants.DASH_VELO*Math.sqrt(2)/2);
					this.getForces().addForce("DashLag", new Force(GameConstants.DASH_VELO*Math.sqrt(2)/2, GameConstants.DASH_VELO*Math.sqrt(2)/2, 1, t));
					break;
				case Right:
					this.getVelocity().setxComp(GameConstants.DASH_VELO*8);
					this.getForces().addForce("DashLag", new Force(-GameConstants.DASH_VELO, 0, 1, t));
					break;
				case RightDown:
					this.getVelocity().setxComp(GameConstants.DASH_VELO*Math.sqrt(2)*4);
					this.getVelocity().setyComp(GameConstants.DASH_VELO*Math.sqrt(2)/2);
					this.getForces().addForce("DashLag", new Force(-GameConstants.DASH_VELO*Math.sqrt(2)/2, -GameConstants.DASH_VELO*Math.sqrt(2)/2, 1, t));
					break;
				case Up:
					this.getVelocity().setyComp(-GameConstants.DASH_VELO);
					this.getForces().addForce("DashLag", new Force(GameConstants.DASH_VELO, 0, 1, t));
					break;
				case UpRight:
					this.getVelocity().setxComp(GameConstants.DASH_VELO*Math.sqrt(2)*4);
					this.getVelocity().setyComp(-GameConstants.DASH_VELO*Math.sqrt(2)/2);
					this.getForces().addForce("DashLag", new Force(-GameConstants.DASH_VELO*Math.sqrt(2)/2, GameConstants.DASH_VELO*Math.sqrt(2)/2, 1, t));
					break;
				default:
					break;
				}	
				
				//updates dash control variables
				hasDash = false;
				lastDash = t;
			}		
		}
	}
	
	@Override
	protected void handleCollisionAddOn(CollisionState collisionState) {
		// TODO Auto-generated method stub
		
	}
}	