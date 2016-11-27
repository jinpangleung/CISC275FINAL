package model.drawing;

import java.awt.Graphics;

/**
 * DrawableObject
 * Any object that can be drawn
 * Drawable object contains a Coord
 * 
 * @see Coord
 * @author Eric
 *
 */

public abstract class DrawableObject {
	
	protected Coord coord;
	protected Animation animation;
	
	public DrawableObject(Coord coord, Animation animation){
		this.coord = coord;
		this.animation = animation;
	}
	
	public void draw(Graphics g){
		animation.draw(g, coord);
	}
	
	public void drawStatic(Graphics g){
		animation.drawStatic(g, coord);
	}
	
	public Coord getCoord(){
		return coord;
	}
	
	public void setCoord(Coord c){
		this.coord = c;
	}
	
	public Animation getAnimation(){
		return animation;
	}
	
	public void setAnimation(Animation animation){
		this.animation = animation;
	}
	
	public void setCoord(int x, int y){
		this.coord.setX(x);
		this.coord.setY(y);
	}
	
	public boolean isWithin(double x, double y){
		int halfWidth = this.getAnimation().getImageWidth() / 2;
		int halfHeight = this.getAnimation().getImageHeight() / 2;
		double left = this.getCoord().getX() - halfWidth;
		double right = this.getCoord().getX() + halfWidth;
		double top = this.getCoord().getY() - halfHeight;
		double bottom = this.getCoord().getY() + halfHeight;
		return left <= x && right >= x && top <= y && bottom >= y;
	}
	
	public boolean isWithin(Coord coord){
		return isWithin(coord.getX(), coord.getY());
	}
	
	/* Example String
	 * DrawableObject
	 * (11.21, 198.14)
	 * [oyster_1, oyster_2, oyster_3, oyster_4]
	 * Length : 4
	 * Current Index : 2 : oyster_3
	 * Size : 128x128
	 * Offset : 63, 63
	 */
	public String toString(){
		String str = "";
		str += "DrawableObject\n";
		str += coord.toString();
		str += "\n";
		str += animation.toString();
		return str;
	}

}