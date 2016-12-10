package model;

import java.awt.Graphics;

import model.drawing.Animation;

/**
 * TitleScreen
 * The screen that we have in the beginning before player starts the game
 * @author Roy
 * @attributes screen - gets the animation for the screen
 * @attributes buttonTuturial - the tutorial button
 * @attributes buttonStart - the start button
 * @attributes width - the width ratio for the button
 * @attributes height - the height ratio for the button
 * 
 * 
 */
public class TitleScreen {
	
	private Animation screen;
	
	private Button buttonTutorial;
	private Button buttonStart;
	
	private static final double WIDTH = 0.3;
	private static final double HEIGHT = 0.3;
	
	/**
	 * Constructor
	 * creates the title screen
	 * @author Roy
	 *
	 */
	public TitleScreen(){
		int buttonWidth =(int) (Model.getInstance().getScreenWidth() * WIDTH);
		int buttonHeight =(int) (Model.getInstance().getScreenHeight() * HEIGHT);
		
		int y = Model.getInstance().getScreenHeight() / 2;
		
		int x1 = (Model.getInstance().getScreenWidth() / 2) - (buttonWidth) - (buttonWidth/4);
		int x2 = (Model.getInstance().getScreenWidth() / 2) +(buttonWidth/2)  - (buttonWidth/4);
		
		//System.out.println(Integer.toString(buttonWidth) + " " + Integer.toString(buttonHeight) + " " + 
			//	Integer.toString(x) + " " + Integer.toString(y1) + " " + Integer.toString(y2));
		
		buttonTutorial = new TutorialButton(x1, y, buttonWidth, buttonHeight);
		buttonStart = new StartButton(x2, y , buttonWidth, buttonHeight);
		screen = new Animation("title");
		screen.setxOffset(0);
		screen.setyOffset(0);
	}
	
	/**
	 * click
	 * reacts to the mouse click
	 * @author Roy
	 * @param int mouseX
	 * @param int mouseY
	 * @return none
	 *
	 */
	public void click(int mouseX, int mouseY){
		if(buttonTutorial.isWithin(mouseX, mouseY)){
			buttonTutorial.mouseClicked(mouseX, mouseY);
		} else if (buttonStart.isWithin(mouseX, mouseY)){
			buttonStart.mouseClicked(mouseX, mouseY);
		}
	}
	
	/**
	 * draw
	 * draws the button
	 * @author Roy
	 * @param Graphics g
	 * @return none
	 *
	 */
	public void draw(Graphics g){
		screen.draw(g, 0, 0);
		buttonTutorial.draw(g);
		buttonStart.draw(g);
	}
	
	

}
