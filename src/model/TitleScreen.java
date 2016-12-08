package model;

import java.awt.Graphics;

import model.drawing.Animation;

public class TitleScreen {
	
	private Animation screen;
	
	private Button buttonTutorial;
	private Button buttonStart;
	
	private static final double WIDTH = 0.3;
	private static final double HEIGHT = 0.3;
	
	public TitleScreen(){
		int buttonWidth =(int) (Model.getInstance().getScreenWidth() * WIDTH);
		int buttonHeight =(int) (Model.getInstance().getScreenHeight() * HEIGHT);
		
		int x = Model.getInstance().getScreenWidth() / 2;
		
		int y1 = (Model.getInstance().getScreenHeight() / 2) - (buttonHeight);
		int y2 = (Model.getInstance().getScreenHeight() / 2);
		
		System.out.println(Integer.toString(buttonWidth) + " " + Integer.toString(buttonHeight) + " " + 
				Integer.toString(x) + " " + Integer.toString(y1) + " " + Integer.toString(y2));
		
		buttonTutorial = new TutorialButton(x - (buttonWidth / 2), y1, buttonWidth, buttonHeight);
		buttonStart = new StartButton(x - (buttonWidth / 2), y2, buttonWidth, buttonHeight);
		screen = new Animation("title");
		screen.setxOffset(0);
		screen.setyOffset(0);
	}
	
	public void click(int mouseX, int mouseY){
		if(buttonTutorial.isWithin(mouseX, mouseY)){
			buttonTutorial.mouseClicked(mouseX, mouseY);
		} else if (buttonStart.isWithin(mouseX, mouseY)){
			buttonStart.mouseClicked(mouseX, mouseY);
		}
	}
	
	public void draw(Graphics g){
		screen.draw(g, 0, 0);
		buttonTutorial.draw(g);
		buttonStart.draw(g);
	}
	
	
	
	

}
