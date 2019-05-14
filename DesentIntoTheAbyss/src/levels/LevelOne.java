package levels;

import java.io.FileNotFoundException;


import framework.Level;
import gameObjects.Platform;
import gameObjects.Player;
import gameObjects.Spike;
import javafx.stage.Stage;
import menus.MenuMain;

public class LevelOne extends Level{

	//*Constructors*//
	/**
	 * <b>Constructor</b>
	 * <p>Adds in all the things required for level one</p>
	 * @param stage the stage the level will be displayed on
	 * @throws FileNotFoundException 
	 */
	public LevelOne(Stage stage) throws FileNotFoundException {
		super(stage);
		
		//creating player
		super.setPlayer(new Player("./res/Grey.png", 10, 150, 50, 50));
		

		//creating game objects
		super.addGameObject(new Platform(0, 700, 3000, 50));
		super.addGameObject(new Platform(300, 550, 200, 50));
		super.addGameObject(new Platform(500, 200, 200, 50));
		super.addGameObject(new Platform(1000, 100, 50, 600));
	}

	@Override
	public boolean levelComplete() {
		if (this.getPlayer().getPosX() > 3000) {
			new MenuMain(this.getStage()).start();
			System.out.println("level one over.");
			return true;
		}
		return false;
	}
}