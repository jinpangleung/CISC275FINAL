package main;

import controller.Controller;
import model.Model;
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
			
		long previousTime = System.nanoTime();
    	while(true){
    		long currentTime = System.nanoTime();
    		long elapsedTime = currentTime - previousTime;
    		previousTime = currentTime;
    		controller.run(elapsedTime);
    		elapsedTime = System.nanoTime() - previousTime;
    		// Put a cap on game loop, otherwise CPU runs relentlessly
    		// At the moment it is put at an absolute maximum of 100FPS
    		/*
    		if(elapsedTime < 10){
    			try{
    				Thread.sleep(10 - (elapsedTime/1000000));
    			} catch (Exception e){
    				
    			}
    		}
    		*/
    	}
	}
}