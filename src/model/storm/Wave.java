package model.storm;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Collection;
import java.util.Iterator;

import model.Model;
import model.Time;
import model.drawing.Animation;
import model.grid.Grid;
import model.grid.griditem.gabion.Gabion;

/**
 * Wave
 * 
 * @author Eric
 *
 */

public class Wave {
	
	private static final int DAMAGE = 10;
	
	private WaveState state;
	private double y;
	private double startY;
	private double speed;
	private boolean hasHitGabion;
	private double gabionY;
	protected Animation waveAnimation;
	
	private static final double TIME = 4.0; // 10 seconds
	private static final double TOP = 0; // half screen height
	
	public Wave(){
		this.state = WaveState.ADVANCING;
		this.y = Model.getInstance().getScreenHeight();
		System.out.println(y);
		this.startY = this.y;
		this.speed = (double) (this.y - TOP)  / (TIME * Time.nanosecond); // Move the en= new Animation("wave", 13);tire screen in 10 seconds
		this.hasHitGabion = false;
		this.gabionY = Grid.getInstance().getBottomRight().getY() - 180;
		waveAnimation = new Animation("wave", 13);
		waveAnimation.setxOffset(0);
		waveAnimation.setyOffset(0);
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
				System.out.println("Hitting gabions");
				if(damageGabions()){
					System.out.println("Flood");
					flood();
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
		waveAnimation.update(elapsedTime);
		if(waveAnimation.getIndex() == waveAnimation.getLength() - 1){
			this.state = WaveState.RETREATING;
		}
		// TODO
		return false;
	}
	
	public void draw(Graphics g){
		// System.out.println(Integer.toString(x) + " " + Integer.toString(y) + " " + Integer.toString(width) + " " + Integer.toString(height));
		if(this.hasHitGabion){
			waveAnimation.draw(g, 0, y);
		}else{
			waveAnimation.draw(g, 0, y);
		}
	}
	
	// Return true if the gabions were enough to block it
	private boolean damageGabions(){
		Collection<Gabion> gabion = Grid.getInstance().getGabions();
		int damageDealt = 0;
		for(Iterator<Gabion> gLoop = gabion.iterator(); gLoop.hasNext();){
			Gabion gRandom = gLoop.next();
			if (damageDealt != DAMAGE){
				gRandom.takeDamage();
				damageDealt = damageDealt + 10;
				if(damageDealt == DAMAGE){
					return true;
				}
			}
			else {
				return true;
			}
		}
		return false;
	}
	
	private void flood(){
		// TODO
		this.state = WaveState.BREAKING;
	}

}
