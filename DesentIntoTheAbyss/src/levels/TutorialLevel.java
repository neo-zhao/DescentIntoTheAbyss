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
			this.setMaxY(900);
			//creating player
			//super.setPlayer(new Player("./res/Grey.png", -100, 659, 40, 40));
			super.setPlayer(new Player("./res/Grey.png", 2800, -1450, 50, 50));
			//creating game objects
			
			//initial bottom platform
			super.addGameObject(new Platform(-200, 700, 700, 50)); 
			super.addGameObject(new SignPost("./res/rightarrow.png", -100, 800, 100,100));
			
			//first spike
			super.addGameObject(new Spike(300, 650, 50, 50)); 
			super.addGameObject(new SignPost("./res/uparrow.png", 250, 800, 100,100));
			
			//spikes after the first platform
			for(int x = 0; x < 8; x++) super.addGameObject(new Spike(500 + 50 *x, 700, 50,50));
			super.addGameObject(new SignPost("./res/uparrow.png", 550, 800, 100,100));
			super.addGameObject(new SignPost("./res/plus.png", 650, 800, 100,100));
			super.addGameObject(new SignPost("./res/letterd.png", 750, 800, 100,100));
			
			
			//platform after spikes
			super.addGameObject(new Platform(900, 700, 400, 50));
			
			//high wall
			super.addGameObject(new Platform (1300, 200, 50, 500));
			super.addGameObject(new SignPost("./res/uparrow.png", 1400, 600, 100,100));
			super.addGameObject(new SignPost("./res/plus.png", 1400, 500, 100,100));
			super.addGameObject(new SignPost("./res/letterd.png", 1400, 400, 100,100));
			
			//long track leading to wall jump
			super.addGameObject(new Platform(1300, 200, 750, 50));
			
			//wall jump section
			super.addGameObject(new Platform(2050, -700, 50, 900));
			super.addGameObject(new Platform(1800, -800, 50, 730));
			super.addGameObject(new SignPost("./res/upleftarrow.png",  2100, -100, 100,100));
			super.addGameObject(new SignPost("./res/uprightarrow.png", 1700, -300, 100,100));
			super.addGameObject(new SignPost("./res/upleftarrow.png",  2100, -500, 100,100));
			super.addGameObject(new SignPost("./res/uprightarrow.png", 1700, -700, 100,100));			
			
			//track leading to wall climb section
			super.addGameObject(new Platform(2050, -700, 750, 50));
			
			//tall wall to climb up
			super.addGameObject(new Platform(2800, -1320, 50, 620));
			super.addGameObject(new SignPost("./res/uparrow.png", 2900, -800, 100,100));			
			super.addGameObject(new SignPost("./res/plus.png", 3000, -800, 100,100));	
			super.addGameObject(new SignPost("./res/letterd.png", 3100, -800, 100,100));	
			super.addGameObject(new SignPost("./res/letters.png", 2900, -1200, 100,100));	
			super.addGameObject(new SignPost("./res/plus.png", 3000, -1200, 100,100));	
			super.addGameObject(new SignPost("./res/uparrow.png", 3100, -1200, 100,100));	

			
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
