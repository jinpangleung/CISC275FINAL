package controller;

import model.Model;
import model.Time;
import model.grid.Grid;
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
	private static long time;
	private static boolean runningTutorial;
	private static boolean isPaused;
	
	public Controller(Model model, View view){
		this.model = model;
		this.view = view;
		runningTutorial = true;
		isPaused = true;
	}
	
	public void run(long elapsedTime){
		if(runningTutorial){
			model.updateTutorial(elapsedTime);
			if(!isPaused){
				this.time += elapsedTime;
			}	
		}
		else{
			this.model.update(elapsedTime);
			if(Grid.getInstance().getReadyToGo()){
				this.time += elapsedTime;
			}
		}

		this.view.draw();
	}
	
	public static long getTime(){
		return time;
	}

	public static boolean isRunningTutorial() {
		return runningTutorial;
	}

	public static void setRunningTutorial(boolean runningTutorial) {
		Controller.runningTutorial = runningTutorial;
	}

	public static boolean isPaused() {
		return isPaused;
	}

	public static void setPaused(boolean isPaused) {
		Controller.isPaused = isPaused;
	}
}
