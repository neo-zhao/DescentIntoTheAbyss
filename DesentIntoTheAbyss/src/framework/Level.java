package framework;

//imports
import java.util.ArrayList;
import java.util.List;

import gameElements.GameObject;
import gameElements.MobileGameObject;
import gameElements.Player;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;

/**
 * <b>Level</b>
 * <p>General properties required in a level</p>
 * @author Neo Zhao
 * 5/6/2019
 */
public abstract class Level {
	//variable declarations
	protected List<GameObject> staticLevelObjects;
	protected List<MobileGameObject> mobileLevelObjects;
	protected Group root;
	protected Player player;
	
	//*Constructors*//
	/**
	 * <b>Constructor</b>
	 * <p>Initializes the levelObjects ArrayList</p>
	 */
	public Level(Group root) {
		this.staticLevelObjects = new ArrayList<GameObject>();
		this.mobileLevelObjects = new ArrayList<MobileGameObject>();
		this.root = root;
		root.getChildren().clear();
	}
	
	//*Getters and Setters*//
	public void addStaticObject(GameObject g) {staticLevelObjects.add(g);}
	public void removeStaticObject(GameObject g) {staticLevelObjects.remove(g);}
	public void addMobileObject(MobileGameObject g) {mobileLevelObjects.add(g);}
	public void removeMobileObject(MobileGameObject g) {mobileLevelObjects.remove(g);}
	public List<GameObject> getLevelObjects() {return staticLevelObjects;}
	public void setPlayer(Player p) {this.player = p;}
	
	//*Other Methods*//
	/**
	 * <b>start</b>
	 * <p>Handles the game loop and rendering for the level</p>
	 * @param stage The stage object on which the level will be displayed
	 */
	public void start() {
	    //add static level objects to the scene
    	for(GameObject g: staticLevelObjects) {
    		root.getChildren().add(g.getTexture());
    	}
    	
    	//add mobile level objects to the scene
    	for (MobileGameObject g: mobileLevelObjects) {
    		root.getChildren().add(g.getTexture());
    	}
    	
	    final long startNanoTime = System.nanoTime();
	 
	    //the game loop
	    new AnimationTimer()
	    {
	        public void handle(long currentNanoTime)
	        {
	        	//calculate level time
	        	double t = (currentNanoTime - startNanoTime) / 1000000000.0;
	        	//System.out.println(t);
	        	
	        	//update mobile level objects
	        	for (MobileGameObject g: mobileLevelObjects) {
	        		//handles collisions with static objects
	        		for (GameObject o: staticLevelObjects) {
	        			o.handleCollision(g, t);
	        		}
	        		
	        		//handles collisions with mobile objects
	        		for (MobileGameObject o: mobileLevelObjects) {
	        			if(!(o.equals(g)))
	        				o.handleCollision(g, t);
	        		}
	        		g.update(t);
	        	}
	        	
	        	//update static level objects
	        	for (GameObject g: staticLevelObjects) {
	        		g.update(t);
	        	}
	        	
	        	//player.getVelocity().setxComp(.5);
	        	
	        	//exit game loop once level is complete
	        	if(levelComplete()) {
	        		this.stop();
	        	}
	        }
	    }.start();
	}
	
	/**
	 * <b>levelComplete</b>
	 * <p>Determines level completion and handles associated events</p>
	 * @return whether the level has been completed or not
	 */
	public abstract boolean levelComplete();
}
