package gameObjects;

import java.io.FileNotFoundException;

import framework.MobileGameObject;

/**
 * <b>Camera</b>
 * <p>Used by level to keep track of level roll</p>
 * @author abori
 * 5/12/1029
 */
public class Camera extends MobileGameObject{
	//*Constructors*//
	/**
	 * <b>Constructor</b>
	 * <p>initializes all the values required for the creation of a camera object</p>
	 * @throws FileNotFoundException
	 */
	public Camera() throws FileNotFoundException {
		super("./res/White.png", 0, 0, 0, 0, true);
	}
	
}
