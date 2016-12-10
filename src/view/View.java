package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import controller.MouseController;
import model.Model;

/**
 * View is a class for the view part of MVC. Essentially it is the highest level
 * class dealing with what is drawn on the player's screen and how the player
 * interacts with the game.
 * 
 * @author Eric
 * @version
 */
public class View extends JFrame{
	
	private static final long serialVersionUID = -2610325547081016786L;
	private ViewPanel panel;
	
	/**
	 * Constructs a View.
	 * 
	 * @param model a Model used only to initialize other parts of the view
	 * @param s a String representing the title of the window
	 */
	public View(Model model, String s){
		super(s);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setExtendedState(MAXIMIZED_BOTH);
		this.setUndecorated(true);
		Dimension maxScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
		model.initialize((int) maxScreenSize.getWidth(), (int) maxScreenSize.getHeight());
		panel = new ViewPanel();
		this.add(panel, BorderLayout.CENTER);
		MouseController mouseController = new MouseController(model);
		panel.initialize(mouseController);
		this.setVisible(true);
	}

	/**
	 * Repaints the ViewPanel for the game
	 */
	public void draw(){
		panel.repaint();
	}

}
