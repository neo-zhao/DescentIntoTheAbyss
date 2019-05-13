package gameObjects;

import java.io.FileNotFoundException;

import framework.GameObject;

/**
 * <b>Platform</b>
 * <p>A block that the player can stand on</p>
 * @author abori
 * 5/12/2019
 */
public class Platform extends GameObject{

	/**
	 * <b>Constructor</b>
	 * <p>initializes necessary values</p>
	 * @param posX the x position of the platform
	 * @param posY the y position of the platform
	 * @param width the width of the platform
	 * @param height the height of the platform
	 * @throws FileNotFoundException
	 */
	public Platform(double posX, double posY, double width, double height) throws FileNotFoundException {
		super("./res/DarkGrey.png", posX, posY, width, height, false);
	}

}
