package model.grid;

import model.Button;
import model.drawing.Animation;

public class ReadyButton extends Button {
	
	public ReadyButton(int x, int y, int w, int h){
		super(x, y, w, h);
		this.ani = new Animation("play");
		this.ani.setxOffset(0);
		this.ani.setyOffset(0);
	}

	@Override
	public void mouseClicked(int mouseX, int mouseY) {
		// TODO Auto-generated method stub
		Grid.getInstance().ready();
		
	}

	@Override
	public void mouseReleased(int mouseX, int mouseY) {
		// TODO Auto-generated method stub
		
	}

}
