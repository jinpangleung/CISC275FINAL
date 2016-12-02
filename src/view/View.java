package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import controller.MouseController;
import model.Model;

/**
 * In charge of managing all of the JPanels
 * 
 * @author Eric
 *
 */
public class View extends JFrame{
	
	private static final long serialVersionUID = -2610325547081016786L;
	private Model model;
	private ViewPanel panel;
	
	public View(Model model, String s){
		super(s);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.model = model;
		this.setExtendedState(MAXIMIZED_BOTH);
		this.setUndecorated(true);
		Dimension maxScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
		model.initialize((int) maxScreenSize.getWidth(), (int) maxScreenSize.getHeight());
		panel = new ViewPanel(model);
		this.add(panel, BorderLayout.CENTER);
		MouseController mouseController = new MouseController(model);
		panel.initialize(mouseController);
		this.setVisible(true);
	}

	/**
	 * Have the JPanels repaint themselves
	 * 
	 * @return void
	 */
	public void draw(){
		panel.repaint();
	}

}
