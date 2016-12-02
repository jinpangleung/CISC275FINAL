package model.grid.gridcell;

import model.OrderedPair;

/**
 * Acceleration
 * x,y pair how many pixels to accelerate every nanosecond
 * @author Eric
 *
 */

public class Acceleration extends OrderedPair<Double> {

	public Acceleration(double x, double y) {
		super(x, y);
	}
	
	public Acceleration accelerationByTime(long time){
		return new Acceleration(this.getX() * time, this.getY() * time);
	}

}
