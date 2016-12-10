package model.grid;

import model.Button;
import model.drawing.Animation;

/**
 * Ready Button
 * handles the ready button
 * @author Roy
 * 
 */
public class ReadyButton extends Button {
	/**
	 * Ready Button constructor
	 * @author Roy
	 * @param int x
	 * @param int y
	 * @param int w
	 * @param int h
	 */
	 
	public ReadyButton(int x, int y, int w, int h){
		super(x, y, w, h);
		this.ani = new Animation("play");
		this.ani.setxOffset(0);
		this.ani.setyOffset(0);
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
	 
	@Override
	public void mouseClicked(int mouseX, int mouseY) {
		// TODO Auto-generated method stub
		Grid.getInstance().ready();
		
	}
	
	/**
	 * mousReleased()
	 * reacts to the mouse click
	 * @author Roy
	 * @param int mouseX
	 * @param int mouseY
	 * @return none
	 *
	 */
	@Override
	public void mouseReleased(int mouseX, int mouseY) {
		// TODO Auto-generated method stub
		
	}
	

}
