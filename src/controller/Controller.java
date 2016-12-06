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
	private long time;
	
	public Controller(Model model, View view){
		this.model = model;
		this.view = view;
	}
	
	public void run(long elapsedTime){
		this.time += elapsedTime;
		this.model.update(elapsedTime);
		this.view.draw();
	}
	
	public long getTime(){
		return time;
	}
}
