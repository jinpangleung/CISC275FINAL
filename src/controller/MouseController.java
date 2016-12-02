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
 *
 */

public class MouseController implements MouseListener, MouseMotionListener {
	
	private Model model;
	
	public MouseController(Model model){
		this.model = model;
	}

	@Override
	public void mouseClicked(MouseEvent me) {
		//model.mouseClicked(me.getX(), me.getY());
		
	}
	
	@Override
	public void mouseReleased(MouseEvent me) {
		model.mouseReleased(me.getX(), me.getY());
		
	}
	
	@Override
	public void mouseDragged(MouseEvent me) {
		model.mouseDragged(me.getX(), me.getY());
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent me) {
		// TODO Auto-generated method stub
		model.mouseClicked(me.getX(), me.getY());
		
	}
	
	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
