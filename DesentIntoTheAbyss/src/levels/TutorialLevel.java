package levels;

import java.io.FileNotFoundException;

import framework.Level;
import gameObjects.Platform;
import gameObjects.Player;
import gameObjects.SignPost;
import gameObjects.Spike;
import javafx.stage.Stage;
import menus.MenuMain;

public class TutorialLevel extends Level{
	//*Constructors*//
		/**
		 * <b>Constructor</b>
		 * <p>Adds in all the things required for tutorial level</p>
		 * @param stage the stage the level will be displayed on
		 * @throws FileNotFoundException 
		 */
		public TutorialLevel(Stage stage) throws FileNotFoundException {
			super(stage);
			this.setMinY(-5000);
			this.setMaxX(10000);
			this.setMaxY(800);
			//creating player
			super.setPlayer(new Player("./res/Grey.png", -100, 659, 40, 40));
			
			//creating game objects
			
			//initial bottom platform
			super.addGameObject(new Platform(-200, 700, 700, 50)); super.addGameObject(new SignPost("./res/rightarrow.png", -100, 400, 100,100));
			//first spike
			super.addGameObject(new Spike(300, 650, 50, 50));
			
			//spikes after the first platform
			for(int x = 0; x < 8; x++) super.addGameObject(new Spike(500 + 50 *x, 700, 50,50));
			
			//platform after spikes
			super.addGameObject(new Platform(900, 700, 400, 50));
			
			//high wall
			super.addGameObject(new Platform (1300, 200, 50, 500));
			
			//long track leading to wall jump
			super.addGameObject(new Platform(1300, 200, 750, 50));
			
			//wall jump section
			super.addGameObject(new Platform(2050, -700, 50, 900));
			super.addGameObject(new Platform(1800, -800, 50, 730));
			
			//track leading to wall climb section
			super.addGameObject(new Platform(2050, -700, 750, 50));
			
			//tall wall to climb up
			super.addGameObject(new Platform(2800, -1320, 50, 620));
			
			//keep walking to reach the end
			super.addGameObject(new Platform(2800, -1320, 400, 50));
		}

		@Override
		public boolean levelComplete() {
			if(spikeDeath || this.getPlayer().getPosY() > 710) {
				new MenuMain(this.getStage()).start();
				System.out.println("You died.");
				spikeDeath = false;
				return true;
			}
			if (this.getPlayer().getPosX() > 3010) {
				new MenuMain(this.getStage()).start();
				System.out.println("tutorial level over.");
				return true;
			}
			return false;
		}
}
