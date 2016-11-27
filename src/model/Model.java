package model;

import java.awt.Graphics;

import model.drawing.Animation;
import model.grid.Grid;
import model.grid.GridColor;
import model.grid.griditem.gabion.GabionType;
import model.grid.griditem.trailitem.TrailItem;
import model.gui.component.*;
import model.gui.touch.Touch;
import model.inventory.Inventory;
import model.inventory.factory.TowerFactory;

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
	private int screenWidth;
	private int screenHeight;
	private Grid grid;
	private Inventory inventory;
	private Player player;
	private Storm storm;
	private int stormNum;
	
	public static final double GRID_HEIGHT = .8;
	public static final double GRID_WIDTH = .7;
	public static final double Y_PADDING = .1;
	public static final double X_PADDING = .05;
	
	
	public void initialize(int screenWidth, int screenHeight){
		System.out.println("\tModel is being initialized");
		// Initialize component mapping
		defaultComponent = new DefaultComponent(0, 0, screenWidth, screenHeight);
		componentMapping = new ComponentMapping(defaultComponent, screenWidth, screenHeight);
		// Initialize Animation
		Animation.initialize();
		// Initialize Touch
		touch = Touch.getInstance();
		// Initialize Grid component
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		this.grid = new Grid(
				new ComponentPosition((int) (screenWidth * X_PADDING), (int) (screenHeight * Y_PADDING)),
				(int) (screenWidth * GRID_WIDTH), (int) (screenHeight * GRID_HEIGHT));
		this.componentMapping.addComponent(grid);
		// Initialize Inventory and its components
		inventory = Inventory.getInstance();
		inventory.initialize(screenWidth, screenHeight);
		this.componentMapping.addComponent(inventory.getTowerFactory(GridColor.RED));
		this.componentMapping.addComponent(inventory.getTowerFactory(GridColor.BLUE));
		this.componentMapping.addComponent(inventory.getTowerFactory(GridColor.GREEN));
		this.componentMapping.addComponent(inventory.getGabionFactory(GabionType.CONCRETE));
		this.componentMapping.addComponent(inventory.getGabionFactory(GabionType.OYSTER));
		
		// Initialize Player
		this.player = Player.getInstance();
		
		// Initialize Storm
		this.storm = new Storm();
		this.stormNum = 0;
		System.out.println("\tModel has been initialized");
	}
	
	public void update(long timeElapsed){
		grid.update(timeElapsed);
		player.update(timeElapsed);
		if (player.getGameTime()/1000000000 - stormNum == 1){
			storm.dealDamage();
			stormNum++;
		}
	}
	
	public void draw(Graphics g){
		inventory.draw(g);
		grid.draw(g);
		touch.draw(g);
		if(touch.isHolding()){
			g.fillRect(0, 0, 10, 10);
		}
		g.drawImage(Animation.getImage("EH1"), 0, 0, null);
	}
	
	public void mouseClicked(int mouseX, int mouseY){
		System.out.println("Mouse Clicked");
		componentMapping.mouseClicked(mouseX, mouseY);
	}
	
	public void mouseReleased(int mouseX, int mouseY){
		System.out.println("Mouse released");
		componentMapping.mouseReleased(mouseX, mouseY);
	}
	
	public void mouseDragged(int mouseX, int mouseY){
		touch.mouseDragged(mouseX, mouseY);
	}

}
