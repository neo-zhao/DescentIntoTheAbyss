package framework;

//imports
import java.util.ArrayList;
import java.util.List;

import javafx.scene.Group;
import javafx.scene.Node;

/**
 * <b>Menu</b>
 * <p>general properties required in a menu</p>
 * @author Neo Zhao
 * 5/7/2019
 */
public abstract class Menu {
	//variable declarations
	protected List<Node> menuObjects;
	protected Group root;
	
	//*Constructors*//
	/**
	 * <b>Constructor</b>
	 * <p>initializes the list of menu objects</p>
	 */
	public Menu(Group root) {
		this.menuObjects = new ArrayList<Node>();
		this.root = root;
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
