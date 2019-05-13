package framework;

public class GameConstants {
	//physics
	public static final double GRAVITY = 4500;
	
	//Level Stuff
	public static final double BUFFERX = 300;
	public static final double BUFFERY = 300;
	
	//old (possibly useless)
	public static final int STAGE_WIDTH = 1200;
	public static final int STAGE_HEIGHT = STAGE_WIDTH*3/4;
	public static final double MOVE_SPEED = 700;
	public static final double JUMP_VELO = -0.3*GRAVITY;
	public static final double DASH_LENGTH = 250;
	public static final double DASH_BUFFER = .5;
	public static final double HITBOX_BUFFER = 0;
	public static final double SCREEN_ROLL_RATE = 0.1;
	public static final double SCREEN_ROLL_TOL_X = STAGE_WIDTH/4;
	public static final double SCREEN_ROLL_TOL_Y = STAGE_HEIGHT/4;
}