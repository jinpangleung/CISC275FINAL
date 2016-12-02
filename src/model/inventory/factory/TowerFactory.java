package model.inventory.factory;

import java.awt.Color;
import java.awt.Graphics;

import model.drawing.Coord;
import model.grid.griditem.towers.RedTower;
import model.gui.component.Component;
import model.gui.component.ComponentPosition;
import model.gui.path.Path;
import model.gui.touch.Touch;

/**
 * TowerFactory
 * an abstract class of factory that creates towers
 * 
 * @author eric
 *
 */

public abstract class TowerFactory extends Component {
	
	private int towerCount;
	
	public TowerFactory(ComponentPosition topLeft, int width, int height){
		super(topLeft, width, height);
	}
	
	public TowerFactory(int x, int y, int width, int height){
		super(x, y, width, height);
	}
	
	@Override
	public abstract void mouseClicked(int mouseX, int mouseY);
	
	public void increaseTowerCount(){
		towerCount++;
	}
	
	@Override
	public void mouseReleased(int mouseX, int mouseY){
		if(Touch.getInstance().isHolding()){
			Path.snap();
		}
	}

}
