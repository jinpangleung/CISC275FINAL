package model;

import controller.Controller;
import model.drawing.Animation;
import model.grid.Grid;

/**
 * StartButton
 * a specific button which it inherits button class
 * @author Roy
 */
public class TutorialButton extends Button {

	/**
	 * Constructor
	 * @author Roy
	 * @param int x
	 * @param int y
	 * @param int width
	 * @param int height
	 *
	 */
	public TutorialButton(int x, int y, int width, int height) {
		super(x, y, width, height);
		this.ani = new Animation("NewPlayerButton");
		this.ani.setxOffset(0);
		this.ani.setyOffset(0);
		// TODO Auto-generated constructor stub
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
	//@Override
	public void mouseClicked(int mouseX, int mouseY) {
		// TODO Auto-generated method stub
		Model.getInstance().endTitleScreen();
		Controller.setRunningTutorial(true);
		Grid.getInstance().ready();
		
	}

	/**
	 * mouseReleased()
	 * reacts to the mouse click
	 * @author Roy
	 * @param int mouseX
	 * @param int mouseY
	 * @return none
	 *
	 */
	//@Override
	public void mouseReleased(int mouseX, int mouseY) {
		// TODO Auto-generated method stub
		
	}

}
