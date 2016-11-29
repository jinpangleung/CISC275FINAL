package model.gui.component;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Component
 * Component is a static "frame" that exists within a component mapping
 * It has dimensions and can handle mouse clicks and mouse releases within it
 * 
 * @see ComponentMapping
 * @author Eric
 *
 */

public abstract class Component {
	
	protected ComponentPosition topLeft;
	protected ComponentPosition bottomRight;
	
	public Component(ComponentPosition topLeft, int width, int height){
		this.topLeft = topLeft;
		this.bottomRight = new ComponentPosition(topLeft.getX() + width - 1, topLeft.getY() + height - 1);
	}
	
	public Component(int x, int y, int width, int height){
		this.topLeft = new ComponentPosition(x, y);
		this.bottomRight = new ComponentPosition(x + width - 1, y + height - 1);
	}
	
	public boolean isWithin(int x, int y){
		return topLeft.getX() <= x && bottomRight.getX() >= x &&
				topLeft.getY() <= y && bottomRight.getY() >= y;
	}
	
	public void mouseClicked(int mouseX, int mouseY){
		// Do nothing
	}
	
	public void mouseReleased(int mouseX, int mouseY){
		// Do nothing
	}

	public ComponentPosition getTopLeft() {
		return topLeft;
	}

	public void setTopLeft(ComponentPosition topLeft) {
		this.topLeft = topLeft;
	}

	public ComponentPosition getBottomRight() {
		return bottomRight;
	}

	public void setBottomRight(ComponentPosition bottomRight) {
		this.bottomRight = bottomRight;
	}
	
	/* Example String
	 * Component
	 * Top Left : (10, 10)
	 * Bottom Right : (100, 110)
	 */
	public String toString(){
		String str = "";
		str += "Component\n";
		str += "Top Left : " + topLeft.toString() + "\n";
		str += "Bottom Right : " + bottomRight.toString();
		return str;
	}
	
	public void draw(Graphics g){
		g.setColor(Color.CYAN);
		g.fillRect(this.getTopLeft().getX(), this.getTopLeft().getY(),
				this.getBottomRight().getX() - this.getTopLeft().getX(),
				this.getBottomRight().getY() - this.getTopLeft().getY());
	}

}
