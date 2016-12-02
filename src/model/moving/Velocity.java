package model.moving;

/**
 * Velocity
 * Contains the x and y component of a MovableObject's velocity
 * x and y doubles for precision
 * 
 * @author Eric
 *
 */

public class Velocity {
	
	private double x;
	private double y;
	
	public Velocity(double d, double e) {
		this.x = d;
		this.y = e;
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	
	public String toString(){
		String str = "";
		str += "(";
		str += Double.toString(x);
		str += ", ";
		str += Double.toString(y);
		str += ")";
		return str;
	}

}
