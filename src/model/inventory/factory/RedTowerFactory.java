package model.inventory.factory;

import model.drawing.Coord;
import model.grid.griditem.tower.RedTower;
import model.gui.component.ComponentPosition;
import model.gui.touch.Touch;

/**
 * RedTowerFactory
 * a factory that creates redtower
 * 
 * @author eric
 *
 */


public class RedTowerFactory extends TowerFactory {

	public RedTowerFactory(ComponentPosition topLeft, int width, int height) {
		super(topLeft, width, height);
	}
	
	public RedTowerFactory(int x, int y, int width, int height){
		this(new ComponentPosition(x, y), width, height);
	}
	
	//@Override
	public void mouseClicked(int mouseX, int mouseY){
		// If applicable, clamp new tower to touch
		if(getCurrency() >= getCostPer()){
			reduceCurrency(getCostPer());
			Touch.getInstance().clamp(new RedTower(new Coord(topLeft.getX(), topLeft.getY())));
		}
	}

}
