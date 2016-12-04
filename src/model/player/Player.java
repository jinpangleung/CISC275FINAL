package model.player;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import model.Model;
import model.grid.griditem.GridItem;
import model.gui.path.Path;

/**
 * Player
 * Controls game time elapsed, estuary happiness
 * 
 * @author Eric
 *
 */

public class Player {
	
	private long time;
	private Happiness happiness;
	private static Player instance;
	private double red = 25 + happiness.getHappiness()*2.3;
	private double green = 25 + Math.sqrt(1-happiness.getHappiness()*happiness.getHappiness());
	private double blue = 25 + happiness.getHappiness()*2.3;
	private final int alpha;
	
	public Player(){
		happiness = new Happiness();
		time = 0;
		instance = this;
		alpha = 30;
	}
	
	
	public void draw(Graphics g){
		Color c = new Color((int) red,(int) green,(int) blue, alpha);
		BufferedImage image = new BufferedImage(300, 200, BufferedImage.TYPE_INT_ARGB);
		Graphics graphics = image.getGraphics(); 
		graphics.setColor(c);
		graphics.fillRect(0, 0, Model.getInstance().getScreenWidth(), Model.getInstance().getScreenHeight());
		graphics.dispose();
		
//		super.draw(g);
//		for(GridItem gi : items){
//			gi.draw(g);
//		}
//		for(Path p : paths){
//			p.getGridItem().draw(g);
//		}
	}
	
	
	public void update(long timeElapsed){
		this.time += timeElapsed;
	}
	
	public static Player getInstance(){
		return instance;
	}
	
	public boolean increaseHappiness(int h){
		return happiness.increaseHappiness(h);
	}
	
	public boolean decreaseHappiness(int h){
		return happiness.decreaseHappiness(h);
	}
	
	public long getElapsedTime(){
		return time;
	}

}
