package model;

import controller.Controller;
import model.drawing.Animation;

public class StartButton extends Button {

	public StartButton(int x, int y, int width, int height) {
		super(x, y, width, height);
		this.ani = new Animation("ExperiencedPlayerButton");
		this.ani.setxOffset(0);
		this.ani.setyOffset(0);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void mouseClicked(int mouseX, int mouseY) {
		Model.getInstance().endTitleScreen();
		Controller.setRunningTutorial(false);
	}

	@Override
	public void mouseReleased(int mouseX, int mouseY) {
		// TODO Auto-generated method stub
		
	}

}
