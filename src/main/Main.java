package main;

import controller.Controller;
import model.Model;
import model.Time;
import model.player.Player;
import view.View;

/**
 * Main
 * Calls main method
 * @author Eric
 *
 */

public class Main {
	
	public static void main(String[] args){
			System.out.println("Starting up program...");
			System.out.println("Creating Model...");
		Model model = new Model();
			System.out.println("Base Model Created...");
			System.out.println("Creating View...");
		View view = new View(model, "CISC275");
			System.out.println("View Created...");
			System.out.println("Creating Controller...");
		Controller controller = new Controller(model, view);
			System.out.println("Controller Created...");
		
			System.out.println("Preparing to start Game Loop...");
			
		long minimumTime = 10000000; // 1/100 seconds
			
		long previousTime = System.nanoTime();
		
		boolean isGameDone = false;
    	while(!isGameDone){
    		long currentTime = System.nanoTime();
    		long elapsedTime = currentTime - previousTime;
    		previousTime = currentTime;
    		controller.run(elapsedTime);
    		// Put a cap on game loop, otherwise CPU runs relentlessly
    		// At the moment it is put at an absolute maximum of 100FPS
    		if(elapsedTime < minimumTime){ // one second
    			try{
    				Thread.sleep(0, (int) (minimumTime - elapsedTime)); // 0 milliseconds + some nanoseconds
    			} catch (Exception e){
    				
    			}
    		}
    		
    		if(controller.getTime() > Time.nanosecond * 60){
    			isGameDone = true;
    		}
    	}
    	
    	System.out.println("Game Done. Your score is: " + Player.getInstance().getHappiness());
	}
}