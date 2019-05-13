package menus;

import java.io.FileNotFoundException;

import framework.Menu;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import levels.LevelOne;

public class MenuMain extends Menu{
	/**
	 * <b>Constructor</b>
	 * <p>implements all the stuff needed for the main menu</p>
	 */
	public MenuMain(Stage stage) {
		super(stage);
	
	    //adding button
	    Button play = new Button();
	    play.setLayoutX(100);
	    play.setLayoutY(100);
	    play.setText("Play");
	    play.setOnAction(e -> {
	    	try {
				new LevelOne(stage).start((Group) stage.getScene().getRoot());
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	    });
	    super.add(play);
	}
}
