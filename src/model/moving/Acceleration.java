package model.moving;

import model.OrderedPair;

/**
 * Acceleration
 * Pair of x, y that dictates acceleration
 * Used to increase velocity
 * x and y are doubles
 * 
 * @author Eric
 *
 */

public class Acceleration extends OrderedPair<Double> {
	
	public Acceleration(double x, double y){
	    super(x, y);
	}
}
