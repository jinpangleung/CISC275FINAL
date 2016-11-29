package model.gui.component;

/**
 * ComponentPosition
 * An x,y coordinate used exclusively for describing position of components
 * @author Eric
 *
 */

public class ComponentPosition {
	
	private int x;
	private int y;
	
	public ComponentPosition(int x, int y){
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public String toString(){
		String str = "";
		str += "(";
		str += Integer.toString(x);
		str += ", ";
		str += Integer.toString(y);
		str += ")";
		return str;
	}
	

}
