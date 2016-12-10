package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import model.Model;

/**
 * MouseController
 * Attached to ViewPanel
 * Let's model handle mouse clicks, drags, and releases
 * @author Eric
 * @attributes model - holds the instance of the model
 *
 */

public class MouseController implements MouseListener, MouseMotionListener {
	
	private Model model;
	
	
	/**
	 * MouseController
	 * Constructor of the class
	 * @author Roy
	 * @param model - the model of the game
	 *
	 */
	public MouseController(Model model){
		this.model = model;
	}
	
	/**
	 * mouseClicked()
	 * reacts to the mouse click
	 * @author Roy
	 * @param MouseEvent me
	 * @return none
	 *
	 */
	//@Override
	public void mouseClicked(MouseEvent me) {
		//model.mouseClicked(me.getX(), me.getY());
		
	}
	
	/**
	 * mouseReleased()
	 * reacts to the mouse click
	 * @author Roy
	 * @param MouseEvent me
	 * @return none
	 *
	 */
	//@Override
	public void mouseReleased(MouseEvent me) {
		model.mouseReleased(me.getX(), me.getY());
		
	}
	
	/**
	 * mouseDragged()
	 * reacts to the mouse click
	 * @author Roy
	 * @param MouseEvent me
	 * @return none
	 *
	 */
	//@Override
	public void mouseDragged(MouseEvent me) {
		model.mouseDragged(me.getX(), me.getY());
		
	}

	/**
	 * mouseEntered()
	 * reacts to the mouse click
	 * @author Roy
	 * @param MouseEvent arg0
	 * @return none
	 *
	 */
	//@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * mouseExited()
	 * reacts to the mouse click
	 * @author Roy
	 * @param MouseEvent arg0
	 * @return none
	 *
	 */
	//@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * mousePressed()
	 * reacts to the mouse click
	 * @author Roy
	 * @param MouseEvent me
	 * @return none
	 *
	 */
	//@Override
	public void mousePressed(MouseEvent me) {
		// TODO Auto-generated method stub
		model.mouseClicked(me.getX(), me.getY());
		
	}
	
	/**
	 * mouseMoved()
	 * reacts to the mouse click
	 * @author Roy
	 * @param MouseEvent arg0
	 * @return none
	 *
	 */
	//@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
