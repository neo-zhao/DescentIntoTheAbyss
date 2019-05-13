package framework;

/**
 * <b>UserAffectedGameObject</b>
 * <p>all the methods a user affected game object should implement</p>
 * @author abori
 * 5/12/2019
 */
public interface UserAffectedGameObject {
	/**
	 * <b>handleInput</b>
	 * <p>handles user input</p>
	 * @param currentTime the current time (level time)
	 */
	public void handleInput(double currentTime);
}
