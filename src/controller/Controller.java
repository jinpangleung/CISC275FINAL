package controller;

import model.Model;
import view.View;

/**
 * Controller
 * Holds the View and Model
 * Negotiates between the two
 * 
 * @author Eric
 *
 */

public class Controller {
	
	private Model model;
	private View view;
	
	public Controller(Model model, View view){
		this.model = model;
		this.view = view;
	}
	
	public void run(long elapsedTime){
		this.model.update(elapsedTime);
		this.view.draw();
	}

}
