package model.drawing;

import model.OrderedPair;

/**
 * A Coord is the pixel x,y coordinate of a DrawableObject
 * Coord is an OrderedPair of type Double
 * 
 * @see OrderedPair
 * @see Double
 * 
 * @author Eric
 *
 */

public class Coord extends OrderedPair<Double> {

	// Constructor
	public Coord(double x, double y) {
		super(x, y);
	}
	
	// Methods
	public void addX(double x){
		this.setX(this.getX() + x);
	}
	
	public void addY(double y){
		this.setY(this.getY() + y);
	}
	
	public void add(double x, double y){
		this.addX(x);
		this.addY(y);
	}

}
