package model;

import java.awt.Graphics;

import model.drawing.Animation;
import model.gui.component.Component;

public abstract class Button extends Component{
	
	protected Animation ani;

	public Button(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void draw(Graphics g){
		ani.draw(g, this.getTopLeft().getX(), this.getTopLeft().getY());
	}
	
}
