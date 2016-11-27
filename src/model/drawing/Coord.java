package model.drawing;

/**
 * Coord
 * A Coord is specifically for the pixel coordinates of a drawable object
 * If an object can be drawn it needs to know what pixel it is at
 * Coord correlates to a drawable object's physical position on the ViewPanel
 * @author Eric
 *
 */

public class Coord {
	
	private double x;
	private double y;
	
	public Coord(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	public Coord(int x, int y){
		this.x = (double) x;
		this.y = (double) y;
	}
	
	public void addX(double x){
		this.x += x;
	}
	
	public void addY(double y){
		this.y += y;
	}
	
	public void addX(int x){
		this.x += (double) x;
	}
	
	public void addY(int y){
		this.y += (double) y;
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
	
	/* Example String
	 * (10.0, 1024.0021)
	 */
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
