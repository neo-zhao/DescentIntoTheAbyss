package gameObjects;

import java.io.FileNotFoundException;

import framework.GameObject;

/**
 * Signposts
 * These are simply instructions that tell the player what to do
 */
public class SignPost extends GameObject{

	public SignPost(String textureFileName, double posX, double posY, double width, double height )
			throws FileNotFoundException {
		super(textureFileName, posX, posY, width, height, true);
		// TODO Auto-generated constructor stub
	}

}
