package gameObjects;

import java.io.FileNotFoundException;

import framework.GameObject;
import physics.Calculations;

public class Spike extends GameObject{

	public Spike(double posX, double posY, double width, double height)
			throws FileNotFoundException {
		super("./res/spike.png", posX, posY, width, height, true);
		// TODO Auto-generated constructor stub
	}
	
	//determine if player dies
	public boolean isTouchingPlayer(Player p) {
//		System.out.println("Player x: "  );
//		System.out.println("Player y: "  );
//		System.out.println("Spike x: "  );
//		System.out.println("Spike x: "  );
		return Calculations.hasOverlap(this.getPosY(), this.getPosY()+ this.getHeight(), p.getPosY(), p.getPosY() + p.getHeight())
				&&
				Calculations.hasOverlap(this.getPosX(), this.getPosX()+ this.getWidth(), p.getPosX(), p.getPosX() + p.getWidth());
	}
	
	
}
