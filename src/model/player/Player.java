package model.player;

/**
 * Player
 * Controls game time elapsed, estuary happiness
 * 
 * @author Eric
 *
 */

public class Player {
	
	private long time;
	private Happiness happiness;
	private static Player instance;
	
	public Player(){
		happiness = new Happiness();
		time = 0;
		instance = this;
	}
	
	public void update(long timeElapsed){
		this.time += timeElapsed;
	}
	
	public static Player getInstance(){
		return instance;
	}
	
	public boolean increaseHappiness(int h){
		return happiness.increaseHappiness(h);
	}
	
	public boolean decreaseHappiness(int h){
		return happiness.decreaseHappiness(h);
	}
	
	public long getElapsedTime(){
		return time;
	}

}
