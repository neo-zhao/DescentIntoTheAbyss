package managers;

import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Set;

import framework.GameConstants;
import framework.GameMain;
import framework.PlayerAffectedGameObject;
import framework.UserAffectedGameObject;
import gameObjects.Bullet;
import gameObjects.Player;

/**
 * <b>BulletManager</b>
 * <p>Manages bullets shot by players and enemies<p>
 * @author abori
 * 5/13/2019
 */
public class BulletManager implements PlayerAffectedGameObject, UserAffectedGameObject{
	//variable declarations
	private Set<Bullet> bullets;
	private Player player;
	private double lastShot;
	
	//*Constructors*//
	public BulletManager() {
		this.bullets = new HashSet<Bullet>();
		this.player = null;
		this.lastShot = 0;
	}
	
	//*Getters and Setters*//
	public Set<Bullet> getBullets() {return bullets;}
	//*Other Methods*//
	
	@Override
	public void handleInput(double currentTime) {
		//adds new bullet if requirements are met
		if (GameMain.keyInput.contains("S") && currentTime - lastShot > GameConstants.SHOOT_BUFFER) {
			lastShot = currentTime;
			try {
				this.bullets.add(new Bullet(this.player.getPosX() + this.player.getWidth() +1, this.player.getPosY() + this.player.getHeight()/3));
				//debugging
				//System.out.println((this.player.getPosX() + this.player.getWidth() + 1) + " "+ this.player.getPosY() + " "+ this.player.getHeight()/3);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		//remove bullets that are no longer viable
		for (Bullet b: bullets) {
			if (!b.isViable()) {
				bullets.remove(b);
			}
		}
	}

	@Override
	public void handlePlayer(Player player) {
		this.player = player;
	}
}
