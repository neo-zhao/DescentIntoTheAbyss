package levels;

import java.io.FileNotFoundException;

//imports
import framework.Level;
import gameElements.GameObject;
import gameElements.Player;
import gameElements.TotemEnemy;
import javafx.scene.Group;
import menus.MenuMain;

public class LevelOne extends Level{

	//*Constructors*//
	/**
	 * <b>Constructor</b>
	 * <p>Adds in all the things required for level one</p>
	 * @param stage the stage the level will be displayed on
	 * @throws FileNotFoundException 
	 */
	public LevelOne(Group root) throws FileNotFoundException {
		super(root);
		
		//creating static objects
		super.addStaticObject(new GameObject("./res/DarkGrey.png", 0, 700, 1000, 50));
		super.addStaticObject(new GameObject("./res/DarkGrey.png", 300, 400, 200, 50));
		super.addStaticObject(new GameObject("./res/DarkGrey.png", 500, 200, 200, 50));
		//creating dynamic objects
		
		//creating player
		super.setPlayer(new Player("./res/Grey.png", 10, 150, 50, 50));
		super.addMobileObject(super.player);
		super.addMobileObject(new TotemEnemy("./res/Totem.png", 600, 600, 150, 450, false));
	}

	@Override
	public boolean levelComplete() {
		if (super.player.getPosX() > 1000) {
			new MenuMain(root).start();
			System.out.println("level one over.");
			return true;
		}
		return false;
	}
}