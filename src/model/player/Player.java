package model.player;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import model.Model;
import model.grid.Grid;
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
	
	private Happiness happiness;
	private static Player instance;
	private double red;
	private double green;
	private double blue;
	private final int alpha;
	
	public Player(){
		happiness = new Happiness();
		instance = this;
		alpha = 100;
	}
	
	
	public void draw(Graphics g){
//		red = 255 - happiness.getHappiness() * 0;
//		blue = 235 + happiness.getHappiness() * .2;
//		green = 55 + happiness.getHappiness() * 2;
		red = 255 - happiness.getHappiness() * 2.3;
		blue = 25 + happiness.getHappiness();
		green = 0 + happiness.getHappiness();
		Color c = new Color((int) red,(int) green,(int) blue, alpha);
		g.setColor(c);
		g.fillRect(0, 0, Model.getInstance().getScreenWidth(), Model.getInstance().getScreenHeight());
		
		//draw numerical estuarary happiness
		int x = (int) (Grid.getInstance().getWidth());
		int y = (int) (Grid.getInstance().getHeight()*.25);

		g.setFont(new Font("TimesRoman", Font.PLAIN, 100)); 
		g.drawString(Integer.toString(getHappiness()), x, y);
	}
	
	
	public void update(long timeElapsed){
//		Random rand = new Random();
//		int  n = rand.nextInt(1000) + 1;
		
//		this.time += timeElapsed;
//		System.out.println("red = " + red);
//		System.out.println("green = " + green);
//		System.out.println("blue = " + blue);
//		System.out.println(happiness.getHappiness());
//		if (n == 1){
//			happiness.decreaseHappiness(1);
//		}
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

	public int getHappiness(){
		return happiness.getHappiness();
	}
}
