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
	
	private Wave wave;
	
	public Storm(){
		wave = new Wave();
		// TODO
	}
	
	public boolean update(long elapsedTime){
		if(wave.update(elapsedTime)){
			return true;
		} else {
			return false;
		}
	}
	
	public void draw(Graphics g){
		wave.draw(g);
	}

}
