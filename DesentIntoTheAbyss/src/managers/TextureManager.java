package managers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.image.Image;

/**
 * <b>TextureManager</b>
 * <p>This class keeps track of all the images used in the game so that image loading only occurs once.</p>
 * @author Neo Zhao
 * 5/6/2019
 */
public class TextureManager {
	//the collection that will contain all the images to be used in the game
	private static Map<String, Image> textures = new HashMap<String, Image>();
	
	/**
	 * <b>getTexture</b>
	 * <p>Returns the image given the file name; adds the image to the HashMap if it's the first time.</p>
	 * @param fileName the name of the image file (the entire file name; including .png, etc.)
	 * @return the requested javafx.scene.image object
	 * @throws FileNotFoundException 
	 */
	public static Image getTexture(String fileName) throws FileNotFoundException {
		//if the requested image has already been loaded, return it
		if(textures.get(fileName) != null) {
			return textures.get(fileName);
		}
		
		//otherwise, load the image and return it
		Image image = new Image(new FileInputStream(fileName));
		System.out.println(fileName + " loaded");
		textures.put(fileName, image);
		return image;
	}
	
}
