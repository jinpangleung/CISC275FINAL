package model;

import java.awt.Graphics;

import controller.Controller;
import model.difficulty.Difficulty;
import model.drawing.Animation;
import model.grid.Grid;
import model.grid.griditem.tower.Tower;
import model.gui.component.*;
import model.gui.touch.Touch;
import model.inventory.Inventory;
import model.player.Player;
import model.storm.Storm;

import java.awt.Font;

/**
 * Model
 * Model holds all of the game elements
 * It should be updatable based on elapsed time since last update
 * @author Eric
 *
 */

public class Model {
	
	// Model Attributes
	private Component defaultComponent;
	private ComponentMapping componentMapping;
	private Touch touch;
	private Grid grid;
	private Player player;
	private Inventory inventory;
	private static Model instance;
	private int screenWidth;
	private int screenHeight;
	private Storm storm;
	private long timeToStorm = 7000000000L;
	private int numOfStorm;
	
	public static final double GRID_HEIGHT = .8;
	public static final double GRID_WIDTH = .7;
	public static final double Y_PADDING = .1;
	public static final double X_PADDING = .05;
	
	
	public void initialize(int screenWidth, int screenHeight){
		System.out.println("\tModel is being initialized");
		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;
		Model.instance = this;
		// Initialize component mapping
		defaultComponent = new DefaultComponent(0, 0, screenWidth, screenHeight);
		componentMapping = new ComponentMapping(defaultComponent, screenWidth, screenHeight);
		// Initialize Grid component
		this.grid = new Grid(
				new ComponentPosition((int) (screenWidth * X_PADDING), (int) (screenHeight * Y_PADDING)),
				(int) (screenWidth * GRID_WIDTH), (int) (screenHeight * GRID_HEIGHT));
		this.componentMapping.addComponent(grid);
		Animation.initialize();
		this.touch = new Touch();
		// Initialize Inventory and its components
		inventory = new Inventory();
		inventory.initialize(screenWidth, screenHeight);
		player = new Player();
		this.componentMapping.addComponent(inventory.getRtf());
		this.componentMapping.addComponent(inventory.getBtf());
		this.componentMapping.addComponent(inventory.getGtf());
		this.componentMapping.addComponent(inventory.getCgf());
		this.componentMapping.addComponent(inventory.getOgf());
		
		Tower.initialize(screenWidth, screenHeight);
		
		Model.instance = this;
		
		this.storm = new Storm();
		
		System.out.println("\tModel has been initialized");
	}
	
	public static Model getInstance(){
		return Model.instance;
	}
	
	public int getScreenWidth(){
		return this.screenWidth;
	}
	
	public int getScreenHeight(){
		return this.screenHeight;
	}
	
	public void update(long timeElapsed){
		//int numOfStorm = (int) (timeElapsed/timeToStorm);
		grid.update(timeElapsed);
		timeToStorm -= timeElapsed;
		if (timeToStorm <= 0){
			storm.update(timeElapsed);
		}
//		if(numOfStorm == 1){
//			storm.update(timeElapsed);
//			timeToStorm = timeToStorm + timeToStorm;
//		}
	}
	
	public void draw(Graphics g){
		g.drawImage(Animation.getImage("bcg"), 0, 0, null);
		player.draw(g);
		inventory.draw(g);
		grid.draw(g);
		touch.draw(g);
		if (timeToStorm <= 0){
			storm.draw(g);
		}
		long timeRemaining = Controller.getTime();
		long seconds = timeRemaining / Time.nanosecond;
		long minutes = seconds / 60;
		String sec = Long.toString(seconds%60);
		if(sec.length() == 1){
			sec = "0" + sec;
		}
		g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
		g.drawString("Time " + Long.toString(minutes) + ":" + sec, 100, 100);
	}
	
	public void mouseClicked(int mouseX, int mouseY){
		componentMapping.mouseClicked(mouseX, mouseY);
	}
	
	public void mouseReleased(int mouseX, int mouseY){
		componentMapping.mouseReleased(mouseX, mouseY);
	}
	
	public void mouseDragged(int mouseX, int mouseY){
		touch.mouseDragged(mouseX, mouseY);
	}

}
