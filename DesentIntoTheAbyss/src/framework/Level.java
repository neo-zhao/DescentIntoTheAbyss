package framework;

import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Set;

import gameObjects.Camera;
import gameObjects.Player;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.stage.Stage;

/**
 * <b>Level</b>
 * <p>Handles the game loop and level completion requirements</p>
 * @author abori
 * 5/12/2019
 */
public abstract class Level {
	//variable declarations
	private Set<GameObject> inLevel;
	private Camera camera;
	private Player player;
	private Stage stage;
	private double bufferX, bufferY, minX, maxX, minY, maxY;
	
	//*Constructors*//
	public Level(Stage stage) throws FileNotFoundException {
		this.inLevel = new HashSet<GameObject>();
		this.camera = new Camera();
		this.player = null;
		this.stage = stage;
		this.bufferX = GameConstants.BUFFERX;
		this.bufferY = GameConstants.BUFFERY;
		((Group)stage.getScene().getRoot()).getChildren().clear();
	}
	
	//*Getters and Setters*//
	public void addGameObject(GameObject g) {
		this.inLevel.add(g);
		if (g.getPosX() < this.minX) {minX = g.getPosX();}
		if (g.getPosX() + g.getWidth() > this.maxX) {maxX = g.getPosX() + g.getWidth();}
		if (g.getPosY() < this.minY) {minY = g.getPosY();}
		if (g.getPosY() + g.getHeight() > this.maxY) {maxY = g.getPosY() + g.getHeight();}
	}
	public Player getPlayer() {return player;}
	public void setPlayer(Player player) {
		this.player = player;
		this.inLevel.add(player);
	}
	public Stage getStage() {return stage;}
	
	//*Other Methods*//
	public void start(Group root) {	
		//adds everything to the stage
		for (GameObject g: inLevel) {
			root.getChildren().add(g.getImage());
		}
		
    	//get current time to calculate level time
	    final long startNanoTime = System.nanoTime();
	    
	    //the game loop
	    new AnimationTimer()
	    {
	        public void handle(long currentNanoTime)
	        {
	        	//calculate level time
	        	double t = (currentNanoTime - startNanoTime) / 1000000000.0;
	        	
	        	//update mobile level objects
	        	for (GameObject g: inLevel) {
	        		//update only the mobile level objects
	        		if (g instanceof MobileGameObject) {
	        			//reset move bounds
	        			((MobileGameObject) g).setBounds(minX, maxX, minY, maxY);
	        			
	        			//handle input if g takes user input
	        			if (g instanceof UserAffectedGameObject) {
	        				((UserAffectedGameObject) g).handleInput(t);
	        			}
	        			
	        			//handle collisions with other objects
	        			for (GameObject s: inLevel) {
	        				if (!g.equals(s)) {
		        				s.handleCollision((MobileGameObject)g, t);
	        				}
	        			}
	        			
	        			//update g
	        			((MobileGameObject) g).update(t);
	        		}
	        	}
	        	
	        	//moves camera if necessary
        		if (player.getPosX() > camera.getPosX() + stage.getWidth() - bufferX) {
        			camera.setPosX(player.getPosX() - stage.getWidth() + bufferX);
        		}
        		if (player.getPosX() < camera.getPosX() + bufferX) {
        			camera.setPosX(player.getPosX() - bufferX);
        		}
        		if (player.getPosY() > camera.getPosY() + stage.getHeight() - bufferY) {
        			camera.setPosY(player.getPosY() - stage.getHeight() + bufferY);
        		}
        		if (player.getPosY() < camera.getPosY() + bufferY) {
        			camera.setPosY(player.getPosY() - bufferY);
        		}
        		
        		//adjusts display of all game objects according to the camera
        		for (GameObject g: inLevel) {
        			g.displaceImage(camera.getPosX(), camera.getPosY());
        		}
	        	
	        	//exit game loop once level is complete
	        	if(levelComplete()) {
	        		this.stop();
	        	}
	        }
	    }.start();
	}
	
	/**
	 * <b>levelComplete</b>
	 * <p>used to control when the level ends and what happens when the level ends</p>
	 * @return a boolean that controls whether the level ends
	 */
	public abstract boolean levelComplete();
}
