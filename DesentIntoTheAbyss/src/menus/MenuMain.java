package menus;

import java.io.FileNotFoundException;

import framework.Menu;
import javafx.scene.Group;
import javafx.scene.control.Button;
import levels.LevelOne;

public class MenuMain extends Menu{
	/**
	 * <b>Constructor</b>
	 * <p>implements all the stuff needed for the main menu</p>
	 */
	public MenuMain(Group root) {
		super(root);
	
	    //adding button
	    Button play = new Button();
	    play.setLayoutX(100);
	    play.setLayoutY(100);
	    play.setText("Play");
	    play.setOnAction(e -> {
	    	try {
				new LevelOne(root).start();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	    });
	    super.add(play);
	}
}
