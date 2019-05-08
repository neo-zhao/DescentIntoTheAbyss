package framework;

import java.util.ArrayList;

//imports
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import menus.MenuMain;

/**
 * <b>GameMain<\b>
 * <p>I'm honestly not sure what this is for yet...</p>
 * @author Neo Zhao
 * 5/6/2019
 */
public class GameMain extends Application{
	//variable declaration
	public static ArrayList<String> keyInput = new ArrayList<String>();
	
	/**
	 * It has to start somewhere...
	 * @param args ignore this
	 */
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		//Configuring stage
		primaryStage.setTitle("Descent Into The Abyss");
		primaryStage.setWidth(GameConstants.STAGE_WIDTH);
		primaryStage.setHeight(GameConstants.STAGE_HEIGHT);
		
		//setting up group and scene
		Group root = new Group();
	    Scene theScene = new Scene( root );
	    primaryStage.setScene( theScene );
	    
	    //handling key input using scene
	    theScene.setOnKeyPressed(e -> {
	    	if (!keyInput.contains(e.getCode().toString())) {
	    		keyInput.add(e.getCode().toString());
	    		System.out.println(e.getCode().toString() + " has been added");
	    	}
	    });
	    theScene.setOnKeyReleased(e -> {
	    	keyInput.remove(e.getCode().toString());
    		System.out.println(e.getCode().toString() + " has been removed");
	    });
	    
	    //start game at the main menu
		new MenuMain(root).start();
		
		//Displaying stage
		primaryStage.show();
	}

}