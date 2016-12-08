package model.inventory.factory;
	
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import model.drawing.Animation;
import model.drawing.Coord;
import model.grid.griditem.gabion.OysterGabion;
import model.gui.component.ComponentPosition;
import model.gui.touch.Touch;

/**
 * OysterGabionFactory
 * a factory that creates OysterGabion
 * 
 * @author eric
 *
 */

public class OysterGabionFactory extends TowerFactory {
	
	private final int OYSTERS_PER_GABION = 4;

	public OysterGabionFactory(ComponentPosition topLeft, int width, int height) {
		super(topLeft, width, height);
		this.animation = new Animation("OysterGabion");
		setCurrency(0);
		setCostPer(OYSTERS_PER_GABION);
	}
	
	public OysterGabionFactory(int x, int y, int width, int height){
		this(new ComponentPosition(x, y), width, height);
	}
	
	//@Override
	public void mouseClicked(int mouseX, int mouseY){
		// If applicable, clamp new tower to touch
		if(getCurrency() >= getCostPer()){
			reduceCurrency(getCostPer());
			Touch.getInstance().clamp(new OysterGabion(new Coord(topLeft.getX(), topLeft.getY())));
		}
	}
	
	@Override
	public void draw(Graphics g){
		g.setColor(Color.WHITE);
		g.setFont(new Font("Times Roman", Font.PLAIN, 20));
		int x = this.getBottomRight().getX() - ((this.getBottomRight().getX() - this.getTopLeft().getX()) / 2);
		int y = this.getBottomRight().getY() - ((this.getBottomRight().getY() - this.getTopLeft().getY()) / 2);
		animation.draw(g, x, y);
		g.drawString(Integer.toString(getCurrency() / 4), this.getBottomRight().getX(), this.getBottomRight().getY());
	}

}
