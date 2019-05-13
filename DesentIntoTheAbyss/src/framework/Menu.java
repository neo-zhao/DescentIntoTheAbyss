package framework;

//imports
import java.util.ArrayList;
import java.util.List;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.stage.Stage;

/**
 * <b>Menu</b>
 * <p>general properties required in a menu</p>
 * @author Neo Zhao
 * 5/7/2019
 */
public abstract class Menu {
	//variable declarations
	private List<Node> menuObjects;
	private Group root;
	
	//*Constructors*//
	/**
	 * <b>Constructor</b>
	 * <p>initializes the list of menu objects</p>
	 */
	public Menu(Stage stage) {
		this.menuObjects = new ArrayList<Node>();
		this.root = (Group) stage.getScene().getRoot();
		root.getChildren().clear();
	}
	
	//*Getters and Setters*//
	public void add(Node n) {menuObjects.add(n);}
	public void remove(Node n) {menuObjects.remove(n);}
	
	//*Other Methods*//
	/**
	 * <b>start</b>
	 * <p>displays the menu</p>
	 * @param stage
	 */
	public void start() {
	    //add all the nodes to the group
	    for(Node n: menuObjects) {
	    	root.getChildren().add(n);
	    }
	}
}
