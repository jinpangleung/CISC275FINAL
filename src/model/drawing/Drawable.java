package model.drawing;

import java.awt.Graphics;

/**
 * The Drawable Interface can be implemented to any class that needs the ability to draw itself.
 * This includes DrawableObject and any GUI elements
 * 
 * @author Eric
 *
 */

public interface Drawable {
	
	/**
	 * Draw on given Graphics
	 * 
	 * @param Graphics g
	 * @return void
	 */
	public void draw(Graphics g);

}
