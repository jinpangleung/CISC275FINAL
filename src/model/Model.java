package model;

import java.awt.Graphics;

import controller.Controller;
import model.drawing.Animation;
import model.grid.Grid;
import model.grid.griditem.tower.RedTower;
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
 * @author Roy
 * @attributes defaultComponent - 
 * @attributes componentMapping - 
 * @attributes touch - an instance of touch
 * @attributes grid - makes model contains a grid
 * @attributes player - an instance of the player
 * @attributes inventory - an instance of the inventory
 * @attributes instance - the instance of the class
 * @attributes screenWidth - width of the screen
 * @attributes screenHeight - height of the screen
 * @attributes storm - makes model contains a storm
 * @attributes isStorming - check if storm is happening
 * @attributes timeToStorm - the time when storm comes
 * @attributes red - int of the red color component
 * @attributes blue - int of the blue color component
 * @attributes green - int of the green color component
 * @attributes isEnd - a boolean that checks whether the game ended yet
 * @attributes ts - an instance of the title screen
 * @attributes titleScreen - a boolean that checks if we are in the titlescreen
 * @attributes GRID_HEIGHT - ratio of the height of the grid from screen 
 * @attributes GRID_WIDTH - ratio of the width of the grid from screen 
 * @attributes Y_PADDING - ratio of the y component we have of the screen
 * @attributes X_PADDING - ratio of the x component we have of the screen
 * @attributes TIME_BETWEEN_STORMS - the number of time between each storm
 * @attributes END_TIME - the amount of time for the game
 * 
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
	private boolean isStorming;
	private long timeToStorm;
	private double red;
	private double blue;
	private double green;
	private boolean isEnd;
	
	private TitleScreen ts;
	private boolean titleScreen;
	
	public static final double GRID_HEIGHT = .8;
	public static final double GRID_WIDTH = .7;
	public static final double Y_PADDING = .1;
	public static final double X_PADDING = .05;
	private static final long TIME_BETWEEN_STORMS = 120 * Time.nanosecond; // 2 minutes
	private static final long END_TIME = 300 * Time.nanosecond; // end game after 5 minutes
	
	
	/**
	 * initialize
	 * It initializes the model and set all the attributes as it is below
	 * @author Roy
	 * @param int screenWidth
	 * @param int screenHeight
	 * @return none
	 * 
	 */
	
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
		
		titleScreen = true;
		ts = new TitleScreen();
		
		grid.initReadyButton();
		
		isStorming = false;
		
		timeToStorm = TIME_BETWEEN_STORMS;
		
		isEnd = false;
		
		System.out.println("\tModel has been initialized");
	}
	
	/**
	 * getinstance()
	 * It gets the instance of the model, so later we could use this to get the instance of it and work
	 * with it
	 * @author Roy
	 * @return Model.instance
	 *
	 */
	
	
	public static Model getInstance(){
		return Model.instance;
	}
	
	
	/**
	 * getScreenWidth()
	 * A getter that returns the width of the screen
	 * @author Roy
	 * @return screen width 
	 */
	
	public int getScreenWidth(){
		return this.screenWidth;
	}
	
	/**
	 * getScreenHeight()
	 * A getter that returns the height of the screen
	 * @author Roy
	 * @return screen height
	 */
	
	public int getScreenHeight(){
		return this.screenHeight;
	}
	
	/**
	 * endTitleScreen()
	 * It changes the attributes titleScreen into false and that helps us to end the title screen later
	 * @author Roy
	 * @return none
	 *
	 */
	
	public void endTitleScreen(){
		this.titleScreen = false;
	}
	
	/**
	 * getTitleScreen()
	 * A getter that returns titleScreen, which tells us if we are still in the titleScreen
	 * @author Roy
	 * @return none
	 *
	 */
	
	public boolean getTitleScreen(){
		return this.titleScreen;
	}
	
	/**
	 * update()
	 * It updates the model
	 * It first checks if the game ended then checks we are in title screen or having a storm
	 * Then, it updates everything wihtin it, basically the whole game
	 * @author Roy
	 * @param long timeElapsed
	 * @return none
	 *
	 */
	
	public void update(long timeElapsed){
		if (!isEnd){
				if(Controller.getTime() <= END_TIME){ // While game is under 5 minutes
					if(!titleScreen){
						grid.update(timeElapsed);
						if(Grid.getInstance().getReadyToGo()){
							timeToStorm -= timeElapsed;
							if (timeToStorm <= 0){
								isStorming = true;
								timeToStorm = TIME_BETWEEN_STORMS;
								storm = new Storm();
							}
						}
						if(isStorming){
							if(storm.update(timeElapsed)){ // if you update storm and find that it is done
								isStorming = false;
								storm = null;
							}
						}
					}
					if (Player.getInstance().getHappiness()<= 0){
						//end game
						isEnd = true;
					}
				}
		}
	}
	
	/**
	 * draw()
	 * It checks condition of the game and draws stuff onto player's screen accordingly
	 * It contains everything that needs to draw on here
	 * @author Roy
	 * @param Grpahic g
	 * @return none
	 *
	 */
	
	public void draw(Graphics g){
		if(Controller.getTime() <= END_TIME){
			if(!titleScreen){
				g.drawImage(Animation.getImage("bcg"), 0, 0, null);
				player.draw(g);
				inventory.draw(g);
				grid.draw(g);
				touch.draw(g);
				if (isStorming){
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
				if(timeToStorm <= 5 * Time.nanosecond){
					g.setColor(Color.RED);
					g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
					g.drawString("Storm Soon", 10, 23);
				} else if(isStorming){
					g.setColor(Color.RED);
					g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
					g.drawString("Storming", 10, 23);
				} else {
					g.setColor(Color.BLACK);
					g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
					g.drawString("Time " + Long.toString(minutes) + ":" + sec, 10, 23);
				}
				
				g.setColor(Color.BLACK);
				g.setFont(new Font("TimesRoman", Font.PLAIN, 30)); 
				g.drawString("0", Model.getInstance().getScreenWidth()-180, 65);
				g.setFont(new Font("TimesRoman", Font.PLAIN, 30)); 
				g.drawString(Integer.toString(Player.getInstance().getHappiness()), Model.getInstance().getScreenWidth() - 110, 65);
				g.drawString("100", Model.getInstance().getScreenWidth()-50, 65);
				for (int i=100; i>=0; i--){
					red = 255 - i * 2.3;
					blue = 25 + i;
					green = 0 + i;
					Color c = new Color((int) red,(int) green,(int) blue, 100);
					g.setColor(c);
					g.fillRect(Model.getInstance().getScreenWidth()-200+2*i, 10, 2, 25);
				}
			} else {
				ts.draw(g);
			}
			if (Player.getInstance().getHappiness()<= 0){
				//end game
				g.drawImage(Animation.getImage("LosingScreen"), 0, 0, null);
			}
		} else {
			g.drawImage(Animation.getImage("end"), 0, 0, null);
		}
	}
	
	/**
	 * mouseClicked()
	 * reacts to the mouse click
	 * @author Roy
	 * @param int mouseX
	 * @param int mouseY
	 * @return none
	 *
	 */
	public void mouseClicked(int mouseX, int mouseY){
		if(Controller.getTime() <= END_TIME){
			if(!titleScreen){
				if(Grid.getInstance().getReadyButton().isWithin(mouseX, mouseY)){
					Grid.getInstance().getReadyButton().mouseClicked(mouseX, mouseY);
				}
				componentMapping.mouseClicked(mouseX, mouseY);
			} else {
				ts.click(mouseX, mouseY);
			}
		}
	}
	
	/**
	 * mouseReleased()
	 * reacts to the mouse click
	 * @author Roy
	 * @param int mouseX
	 * @param int mouseY
	 * @return none
	 *
	 */
	public void mouseReleased(int mouseX, int mouseY){
		if(Controller.getTime() <= END_TIME){
		if(!titleScreen){
			for(Tower t : Grid.getInstance().getTowers()){
				if(t instanceof RedTower){
					RedTower rt = (RedTower) t;
					rt.close();
				}
			}
			componentMapping.mouseReleased(mouseX, mouseY);
		}
		}
	}
	
	/**
	 * mouseDragged()
	 * reacts to the mouse click
	 * @author Roy
	 * @param int mouseX
	 * @param int mouseY
	 * @return none
	 *
	 */
	public void mouseDragged(int mouseX, int mouseY){
		if(Controller.getTime() <= END_TIME){
		if(!titleScreen){
			touch.mouseDragged(mouseX, mouseY);
		}
		}
	}
	
	/**
	 * updateTutorial()
	 * a seperate update just for the tutorial
	 * @author Roy
	 * @param long elapsedTime
	 * @return none
	 *
	 */
	public void updateTutorial(long elapsedTime) {
		grid.updateTutorial(elapsedTime);
	}

}