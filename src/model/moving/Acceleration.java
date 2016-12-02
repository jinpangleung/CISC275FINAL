package model.moving;

/**
 * Acceleration
 * Pair of x, y that dictates acceleration
 * Used to increase velocity
 * x and y are doubles
 * 
 * @author Eric
 *
 */

public class Acceleration {
	
	private double x;
	private double y;
	
	public Acceleration(double x, double y){
	    this.x = x;
	    this.y = y;
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
