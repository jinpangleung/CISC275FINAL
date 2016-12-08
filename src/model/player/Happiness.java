package model.player;

import java.awt.Graphics;

/**
 * Happiness
 * Class for Estuary Happiness
 * 
 * @author Eric
 *
 */

public class Happiness {
	
	private int raw;
	
	private static final int BASE_HAPPINESS = 75;
	private static final int MAX_HAPPINESS = 100;
	private static final int MIN_HAPPINESS = 0;
	
	public Happiness(){
		this.raw = BASE_HAPPINESS;
	}
	
	// Returns true if the esturary reaches max happiness
	public boolean increaseHappiness(int h){
		this.raw += h;
		if (this.raw >= MAX_HAPPINESS){
			this.raw = MAX_HAPPINESS;
			return true;
		} else {
			return false;
		}
	}
	
	// Returns true if esturary reaches minimum happiness
	public boolean decreaseHappiness(int h){
		this.raw -= h;
		if(this.raw <= MIN_HAPPINESS){
			this.raw = MIN_HAPPINESS;
			return true;
		} else {
			return false;
		}
	}
	
	public void draw(Graphics g){
		// TODO
		// Draw the meter and the tint
	}
	
	public int getHappiness(){
		return this.raw;
	}

}
