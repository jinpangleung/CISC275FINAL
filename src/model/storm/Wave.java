package model.storm;

import java.awt.Color;
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
	private double y;
	private double startY;
	private double speed;
	private boolean hasHitGabion;
	private double gabionY;
	
	private static final double TIME = 10.0; // 10 seconds
	private static final double TOP = Model.getInstance().getScreenHeight() / 2; // half scrren height
	
	public Wave(){
		this.state = WaveState.ADVANCING;
		this.y = Model.getInstance().getScreenHeight();
		System.out.println(y);
		this.startY = this.y;
		this.speed = (double) TOP / (TIME * Time.nanosecond); // Move the entire screen in 10 seconds
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
		if(this.y <= TOP){
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
		int x = 0;
		int y = (int) this.y;
		int width = Model.getInstance().getScreenWidth();
		int height = Model.getInstance().getScreenHeight();
		// System.out.println(Integer.toString(x) + " " + Integer.toString(y) + " " + Integer.toString(width) + " " + Integer.toString(height));
		g.setColor(Color.BLUE);
		g.fillRect(x, y, width, height);
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
		this.state = WaveState.RETREATING;
	}

}
