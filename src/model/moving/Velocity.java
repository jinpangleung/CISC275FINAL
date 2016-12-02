package model.moving;

import model.OrderedPair;

/**
 * Velocity
 * Contains the x and y component of a MovableObject's velocity
 * x and y doubles for precision
 * 
 * @author Eric
 *
 */

public class Velocity extends OrderedPair<Double> {
	
	public Velocity(double d, double e) {
		super(d, e);
	}
	
}
