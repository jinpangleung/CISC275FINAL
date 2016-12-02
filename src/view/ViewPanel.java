package view;

import java.awt.Graphics;

import javax.swing.JPanel;

import controller.MouseController;
import model.Model;

/**
 * ViewPanel
 * Intended to be the only Panel in the View
 * Intended to cover the entire View
 * @author Eric
 *
 */

public class ViewPanel extends JPanel{
	
	private static final long serialVersionUID = -6692584106492189311L;
	
	private Model model;
	
	public ViewPanel(Model model){
		this.model = model;
	}
	
	public void initialize(MouseController mc){
		this.addMouseListener(mc);
		this.addMouseMotionListener(mc);
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		model.draw(g);
	}

}
