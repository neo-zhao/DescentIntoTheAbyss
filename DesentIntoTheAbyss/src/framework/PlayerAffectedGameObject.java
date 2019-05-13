package framework;

import gameObjects.Player;

/**
 * <b>PlayerAffectedGameObject</b>
 * <p>An object that is affected by the arributes of the in game player</p>
 * @author abori
 * 5/13/2019
 */
public interface PlayerAffectedGameObject {
	/**
	 * <b>handlePlayer</b>
	 * <p>updates object parameters based on player object parameters</p>
	 * @param player the player object
	 */
	public void handlePlayer(Player player);
}
