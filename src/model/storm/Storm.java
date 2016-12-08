package model.storm;

import java.awt.Color;
import java.awt.Graphics;

import model.Model;
import model.drawing.Animation;
import model.drawing.Coord;

/**
 * Storm
 * 
 * @author Eric
 *
 */

/*
 * Things a storm must do:
 * 		Draw two waves the crash when they hit gabions, keep going and stun the towers otherwise
 */

public class Storm {
	
	private Wave wave1;
	private Wave wave2;
	private int alpha;
	protected Coord coord;
	protected Animation animation;
	protected Animation cloudAnimation = new Animation("cloud", 52);
	protected Animation rainAnimation = new Animation("rain", 10);
	
	private long timeLeft = 2000000000L;
	
	public Storm(){
		wave1 = new Wave();
		wave2 = new Wave();
		alpha = 0;
		// TODO
	}
	
	public boolean update(long elapsedTime){
		timeLeft -= elapsedTime;
		cloudAnimation.update(elapsedTime);
		if(wave1.update(elapsedTime) && wave2.update(elapsedTime)){
			return true;
		} else {
			return false;
		}
	}
	
	public void draw(Graphics g){
		//while time is less than 2 sec
		while (timeLeft > 0){
			Color c = new Color(0, 0, 0, alpha);
			alpha = alpha + 1;
			g.setColor(c);
			g.fillRect(0, 0, Model.getInstance().getScreenWidth(), Model.getInstance().getScreenHeight());
		}
		cloudAnimation.draw(g, 0, 0);
		rainAnimation.draw(g, 0, Model.getInstance().getScreenHeight()*0.2);
	}
	
	public void drawwave(Graphics g){
		wave1.draw(g);
		wave2.draw(g);
	}

}
