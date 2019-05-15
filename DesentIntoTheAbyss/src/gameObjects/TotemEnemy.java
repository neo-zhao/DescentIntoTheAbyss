package gameObjects;

import java.io.FileNotFoundException;

import framework.GameConstants;
import framework.GameObject;
import framework.MobileGameObject;
import physics.Calculations;

public class TotemEnemy extends GameObject{
	private int health = 100;
	
	public TotemEnemy(double posX, double posY)
			throws FileNotFoundException {
		super("./res/totem.png", posX, posY, 150, 450, false);
		// TODO Auto-generated constructor stub
	}
	
	//Getters and setters
	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
	
	//Other Methods
	public void handleCollision(Bullet b) {	
		//TODO handle when collides with bullet	
	}
}
