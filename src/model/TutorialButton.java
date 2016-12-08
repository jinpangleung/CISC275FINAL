package model;

import controller.Controller;
import model.drawing.Animation;
import model.grid.Grid;

public class TutorialButton extends Button {

	public TutorialButton(int x, int y, int width, int height) {
		super(x, y, width, height);
		this.ani = new Animation("NewPlayerButton");
		this.ani.setxOffset(0);
		this.ani.setyOffset(0);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void mouseClicked(int mouseX, int mouseY) {
		// TODO Auto-generated method stub
		Model.getInstance().endTitleScreen();
		Controller.setRunningTutorial(true);
		Grid.getInstance().ready();
		
	}

	@Override
	public void mouseReleased(int mouseX, int mouseY) {
		// TODO Auto-generated method stub
		
	}

}
