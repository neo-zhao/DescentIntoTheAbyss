package levels;

import java.io.FileNotFoundException;

import framework.Level;
import gameObjects.Platform;
import gameObjects.Player;
import gameObjects.TotemEnemy;
import javafx.stage.Stage;
import menus.MenuMain;

public class TotemLevel extends Level{
	//*Constructors*//
		/**
		 * <b>Constructor</b>
		 * <p>Adds in all the things required for level one</p>
		 * @param stage the stage the level will be displayed on
		 * @throws FileNotFoundException 
		 */
		public TotemLevel(Stage stage) throws FileNotFoundException {
			super(stage);
			
			//creating player
			super.setPlayer(new Player("./res/Grey.png", 150, 150, 50, 50));
			super.setMaxX(1200);
			super.setMaxY(900);
			super.setMinX(-400);
			super.setMinY(-400);
			super.setMovableCamera(false);
			//creating game objects
			super.addGameObject(new Platform(100, 375, 100, 100));
			super.addGameObject(new Platform(290, 600, 100, 100));
			super.addGameObject(new Platform(290, 150, 100, 100));
			
			super.addGameObject(new TotemEnemy(1000, 150));
		}

		@Override
		public boolean levelComplete() {
			if (this.getPlayer().getPosY() >= 849) {
				new MenuMain(this.getStage()).start();
				return true;
			}
			return false;
		}
}
