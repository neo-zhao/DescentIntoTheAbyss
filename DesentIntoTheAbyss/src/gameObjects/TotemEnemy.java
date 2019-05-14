package gameObjects;

import java.io.FileNotFoundException;

import framework.GameObject;

public class TotemEnemy extends GameObject{

	public TotemEnemy(double posX, double posY)
			throws FileNotFoundException {
		super("./res/totem.png", posX, posY, 150, 450, false);
		// TODO Auto-generated constructor stub
	}

}
