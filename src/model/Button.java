package model;

import java.awt.Graphics;

import model.drawing.Animation;
import model.gui.component.Component;

/**
 * Button
 * AN abstract class that handles all the button controls
 * @author Roy
 *@attributes ani - gets the animation for the button
 */
public abstract class Button extends Component{
	
	protected Animation ani;
	
	/**
	 * Constructor
	 * @author Roy
	 * @param int x
	 * @param int y
	 * @param int w
	 * @param int h
	 *
	 */
	public Button(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * draw
	 * draws the button
	 * @author Roy
	 * @param Graphics g
	 * @return none
	 *
	 */
	//@Override
	public void draw(Graphics g){
		ani.draw(g, this.getTopLeft().getX(), this.getTopLeft().getY());
	}
	
}
