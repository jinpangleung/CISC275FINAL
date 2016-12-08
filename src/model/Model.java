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

import java.awt.Color;
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
	private Storm storm2;
	private Storm storm3;
	private Storm storm4;
	//private long timeToStorm = 120000000000L;
	private long timeToStorm = 120000000000L; //test
	private long timeToStorm2 = 240000000000L;
	private long timeToStorm3 = 360000000000L;
	private long timeToStorm4 = 480000000000L;
	private int numOfStorm;
	private double red;
	private double blue;
	private double green;
	
	private TitleScreen ts;
	private boolean titleScreen;
	
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
		this.storm2 = new Storm();
		this.storm3 = new Storm();
		this.storm4 = new Storm();
		
		titleScreen = true;
		ts = new TitleScreen();
		
		grid.initReadyButton();
		
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
	
	public void endTitleScreen(){
		this.titleScreen = false;
	}
	
	public void update(long timeElapsed){
		if(!titleScreen){
			//int numOfStorm = (int) (timeElapsed/timeToStorm);
			grid.update(timeElapsed);
			if(Grid.getInstance().getReadyToGo()){
				timeToStorm -= timeElapsed;
				if (timeToStorm <= 0){
					storm.update(timeElapsed);
				}
			}
	//		if(numOfStorm == 1){
	//			storm.update(timeElapsed);
	//			timeToStorm = timeToStorm + timeToStorm;
	//		}
		}
		if (timeToStorm2 <= 0){
			storm2.update(timeElapsed);
		}
		if (timeToStorm3 <= 0){
			storm3.update(timeElapsed);
		}
		if (timeToStorm4 <= 0){
			storm4.update(timeElapsed);
		}
//		if(numOfStorm == 1){
//			storm.update(timeElapsed);
//			timeToStorm = timeToStorm + timeToStorm;
//		}
	}
	
	public void draw(Graphics g){
		if(!titleScreen){
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
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, 150, 30);
			g.setColor(Color.BLACK);
			g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
			g.drawString("Time " + Long.toString(minutes) + ":" + sec, 10, 23);
			
			g.setFont(new Font("TimesRoman", Font.PLAIN, 30)); 
			g.drawString("100", Model.getInstance().getScreenWidth()-150, 65);
			g.setFont(new Font("TimesRoman", Font.PLAIN, 30)); 
			g.drawString(Integer.toString(Player.getInstance().getHappiness()), Model.getInstance().getScreenWidth() - 75, 65);
			g.drawString("0", Model.getInstance().getScreenWidth()-20, 65);
			for (int i=100; i>=0; i--){
				red = 255 - i * 2.3;
				blue = 25 + i;
				green = 0 + i;
				Color c = new Color((int) red,(int) green,(int) blue, 6);
				g.setColor(c);
				g.fillRect(Model.getInstance().getScreenWidth()-(i+1)-50, 10, Model.getInstance().getScreenWidth()-i-50, 25);
			
//				if (minutes >= 5){
//					if (player.getInstance().getHappiness() >= 50){
//						Color c2 = new Color(255, 255, 255, 255);
//						g.setColor(c2);
//						g.fillRect(0, 0, Model.getInstance().getScreenWidth(), Model.getInstance().getScreenHeight());
//						g.setFont(new Font("TimesRoman", Font.PLAIN, 50));
//						g.drawString("Score: " + player.getInstance().getHappiness() + "Good Job! You saved the estuary!", Model.getInstance().getScreenWidth()/2, Model.getInstance().getScreenHeight()/2);
//					}
//					else {
//						Color c2 = new Color(0, 0, 0, 255);
//						g.setColor(c2); 
//						g.fillRect(0, 0, Model.getInstance().getScreenWidth(), Model.getInstance().getScreenHeight());
//						g.setFont(new Font("TimesRoman", Font.PLAIN, 50));
//						g.drawString("Score: " + player.getInstance().getHappiness() + "The estuary is sick, try again next time.", Model.getInstance().getScreenWidth()/2, Model.getInstance().getScreenHeight()/2);;
//					}
//				}
			}
		} else {
			ts.draw(g);
		}
	}
	
	/*public void draw(Graphics g){
		g.drawImage(Animation.getImage("bcg"), 0, 0, null);
		player.draw(g);
		inventory.draw(g);
		grid.draw(g);
		touch.draw(g);
		if (timeToStorm <= 0){
			storm.draw(g);
		}
		if (timeToStorm2 <= 0){
			storm2.draw(g);
		}
		if (timeToStorm3 <= 0){
			storm3.draw(g);
		}
		if (timeToStorm4 <= 0){
			storm4.draw(g);
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
		
		g.setFont(new Font("TimesRoman", Font.PLAIN, 30)); 
		g.drawString("100", Model.getInstance().getScreenWidth()-150, 65);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 30)); 
		g.drawString("0", Model.getInstance().getScreenWidth()-20, 65);
		
		for (int i=100; i>=0; i--){
			red = 255 - i * 2.3;
			blue = 25 + i;
			green = 0 + i;
			
			Color c = new Color((int) red,(int) green,(int) blue, 6);
			g.setColor(c);
			g.fillRect(Model.getInstance().getScreenWidth()-(i+1)-50, 10, Model.getInstance().getScreenWidth()-i-50, 25);
//			g.setFont(new Font("TimesRoman", Font.PLAIN, 10));
//			g.drawString("100", Model.getInstance().getScreenWidth()-50, 35);
//			g.setFont(new Font("TimesRoman", Font.PLAIN, 10));
//			g.drawString("0", Model.getInstance().getScreenWidth(), 35);
		}}*/
	
	public void mouseClicked(int mouseX, int mouseY){
		if(!titleScreen){
			if(Grid.getInstance().getReadyButton().isWithin(mouseX, mouseY)){
				Grid.getInstance().getReadyButton().mouseClicked(mouseX, mouseY);
			}
			componentMapping.mouseClicked(mouseX, mouseY);
		} else {
			ts.click(mouseX, mouseY);
		}
	}
	
	public void mouseReleased(int mouseX, int mouseY){
		if(!titleScreen){
			componentMapping.mouseReleased(mouseX, mouseY);
		}
	}
	
	public void mouseDragged(int mouseX, int mouseY){
		if(!titleScreen){
			touch.mouseDragged(mouseX, mouseY);
		}
	}

	public void updateTutorial(long elapsedTime) {
		grid.updateTutorial(elapsedTime);
	}

}