package physics;

/**
 * <b>Force</b>
 * <p>Representation of a Force vector with directional components and duration</p>
 * @author abori
 * 5/11/2019
 */
public class Force {
	//variable declarations
	private boolean viable;
	private Vector force;
	private double duration;
	private double birthTime;
	
	//*Constructors*//
	/**
	 * <b>Complete Constructor</b>
	 * <p>Initializes xComp. yComp, duration, and startTime of this force</p>
	 * @param xComp the x component of the force
	 * @param yComp the y component of the force
	 * @param duration the duration of the force
	 * @param birthTime the birth time of the force
	 */
	public Force(double xComp, double yComp, double duration, double birthTime) {
		//variable initializations
		this.viable = true;
		this.force = new Vector(xComp, yComp);
		this.duration = duration;
		this.birthTime = birthTime;
	}
	
	//*Getters and Setters*//
	public Vector getVector() {return force;}
	public double getDuration() {return duration;}
	public double getBirthTime() {return birthTime;}
	
	//*Other Methods*//
	/**
	 * <b>update</b>
	 * <p>updates the vector given the current time and returns true or false depending on whether the force is still viable</p>
	 * @param currentTime the current time (should be the same variable of time passed in upon force creation)
	 * @return the viability of the force (whether the force has exceeded its duration given its birth time and current time)
	 */
	public boolean update(double currentTime) {
		//check if the force is still viable
		if (currentTime > birthTime + duration) {
			//set viability to false if it isn't
			this.viable = false;
		}
		
		//return viability of this force
		return this.viable;
	}
}
