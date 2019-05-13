package physics;

import java.util.HashMap;
import java.util.Set;

/**
 * <b>NetForce</b>
 * <p>A collection of forces organized with a hash map</p>
 * @author abori
 * 5/11/2019
 */
public class NetForce {
	//variable declarations
	private HashMap<String, Force> forces;
	
	//*Constructors*//
	/**
	 * <b>default constructor</b>
	 * <p>initializes the forces hash map in preparation for forces to be added</p>
	 */
	public NetForce() {
		this.forces = new HashMap<String, Force>();
	}
	
	//*Other Methods*//
	/**
	 * <b>addForce</b>
	 * <p>places a new force under a given label into the hash map of forces considered when returning a net force</p>
	 * @param label the label for the force to be added
	 * @param f the force to be added
	 */
	public void addForce(String label, Force f) {
		this.forces.put(label, f);
	}
	
	/**
	 * <b>getNetForce</b>
	 * <p>returns the net force considering all the forces in the hash table (does not update)</p>
	 * @param currentTime the current time (level time)
	 * @return the net force
	 */
	public Force getNetForce(double currentTime) {
		//declaring and initializing necessary variables
		double xComp = 0, yComp = 0, duration = Double.MAX_VALUE;
		Set<String> keys = this.forces.keySet();
		
		//Going through the forces in the hash map
		for (String key: keys) {
			Force f = this.forces.get(key);
			
			//accumulating x and y components
			xComp += f.getVector().getxComp();
			yComp += f.getVector().getyComp();
			
			//finding the time left this net force is accurate
			if ((f.getDuration() + f.getBirthTime() - currentTime) < duration) {
				duration = f.getDuration() + f.getBirthTime() - currentTime;
			}
		}
		
		//returning the new Force 
		return new Force(xComp, yComp, duration, currentTime);
	}
	
	/**
	 * <b>update</b>
	 * <p>updates the forces in the hashMap and removes them if they are no longer viable</p>
	 * @param currentTime
	 */
	public void update(double currentTime) {
		//getting all the keys
		Set<String> keys = this.forces.keySet();
		
		//going through the forces in the hash map
		for (String key: keys) {
			//removing forces if they are no longer viable
			if (!this.forces.get(key).update(currentTime)) {
				this.forces.remove(key);
			}
		}
	}
	
	/**
	 * <b>removeForce</b>
	 * <p>the key of the force to be removed</p>
	 * @param key
	 */
	public void removeForce(String key) {
		this.forces.remove(key);
	}
	
	/**
	 * <b>getForce</b>
	 * <p>returns the force that corresponds to the given key</p>
	 * @param key the key for the corresponding force
	 * @return the force that corresponds to the given key
	 */
	public Force getForce(String key) {
		return this.forces.get(key);
	}
}
