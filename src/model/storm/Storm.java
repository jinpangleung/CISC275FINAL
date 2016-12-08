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
	
	private long timeLeft = 10000000000L;
	
	public Storm(){
		wave1 = new Wave();
		wave2 = new Wave();
		alpha = 0;
		// TODO
	}
	
	public boolean update(long elapsedTime){
		timeLeft -= elapsedTime;
		cloudAnimation.update(elapsedTime);
		rainAnimation.update(elapsedTime);
		if(wave1.update(elapsedTime) && wave2.update(elapsedTime)){
			return true;
		} else {
			return false;
		}
	}
	
	public void draw(Graphics g){
		//while time is less than 5 sec
//		while (timeLeft > 0){
//			if (alpha == 255){
//				//alpha doesn't increment
//			}
//			else{
//				
//				alpha = alpha + 1;
//			}
//			Color c = new Color(0, 0, 0, alpha);
//			g.setColor(c);
//			g.fillRect(0, 0, Model.getInstance().getScreenWidth(), Model.getInstance().getScreenHeight());
//		}
		if (timeLeft > 0){
			cloudAnimation.draw(g, Model.getInstance().getScreenWidth()/2, Model.getInstance().getScreenWidth()*0.05);
			rainAnimation.draw(g, Model.getInstance().getScreenWidth()/2, Model.getInstance().getScreenHeight()*0.6);
		}
	}
	
	public void drawwave(Graphics g){
		wave1.draw(g);
		wave2.draw(g);
	}

}
