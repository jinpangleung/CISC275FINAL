package model.storm;

import java.awt.Graphics;

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
	
	public Storm(){
		wave1 = new Wave(0);
		wave2 = new Wave(0);
		// TODO
	}
	
	public boolean update(long elapsedTime){
		if(wave1.update(elapsedTime) && wave2.update(elapsedTime)){
			return true;
		} else {
			return false;
		}
	}
	
	public void draw(Graphics g){
		wave1.draw(g);
		wave2.draw(g);
	}

}
