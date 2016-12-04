package model.storm;

import java.awt.Graphics;

import model.Model;
import model.Time;
import model.grid.Grid;

/**
 * Wave
 * 
 * @author Eric
 *
 */

public class Wave {
	
	private static final int DAMAGE = 100;
	
	private WaveState state;
	private int x;
	private double y;
	private double startY;
	private double speed;
	private boolean hasHitGabion;
	private double gabionY;
	
	public Wave(int x){
		this.state = WaveState.ADVANCING;
		this.x = x;
		this.y = Model.getInstance().getScreenHeight();
		this.startY = this.y;
		this.speed = this.y * 10 / Time.nanosecond; // Move the entire screen in 10 seconds
		this.hasHitGabion = false;
		this.gabionY = Grid.getInstance().getBottomRight().getY();
	}
	
	public boolean update(long elapsedTime){
		switch(this.state){
		case ADVANCING: return advancingUpdate(elapsedTime);
		case RETREATING: return retreatingUpdate(elapsedTime);
		case BREAKING: return breakingUpdate(elapsedTime);
		default: throw new NoWaveStateException();
		}
	}
	
	private boolean advancingUpdate(long elapsedTime){
		this.y -= this.speed * elapsedTime;
		if(this.y <= 0){
			flood();
		}
		if(!this.hasHitGabion){
			// meaning that wave has not hit the point where it checked for a gabion
			if(this.y <= this.gabionY){
				// hit gabions, keep going if not enough
				if(damageGabions()){
					crash();
				}
				this.hasHitGabion = true;
			}
		}
		return false;
	}
	
	private boolean retreatingUpdate(long elapsedTime){
		this.y += this.speed * elapsedTime;
		return this.y >= this.startY;
	}
	
	private boolean breakingUpdate(long elapsedTime){
		// UPDATE BREAKING ANIATION
		// TODO
		return false;
	}
	
	public void draw(Graphics g){
		// TODO
	}
	
	private void crash(){
		// TODO
	}
	
	// Return true if the gabions were enough to block it
	private boolean damageGabions(){
		
		return false;
	}
	
	private void flood(){
		// TODO
	}

}
