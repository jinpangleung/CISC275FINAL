package model.inventory.factory;
import model.drawing.Animation;
import model.drawing.Coord;
import model.grid.griditem.tower.BlueTower;
import model.gui.component.ComponentPosition;
import model.gui.touch.Touch;

/**
 * BlueTowerFactory
 * a factory that creates bluetower
 * 
 * @author eric
 *
 */

public class BlueTowerFactory extends TowerFactory {

	public BlueTowerFactory(ComponentPosition topLeft, int width, int height) {
		super(topLeft, width, height);
		this.animation = new Animation("BlueTowerIcon");
	}
	
	public BlueTowerFactory(int x, int y, int width, int height){
		this(new ComponentPosition(x, y), width, height);
	}
	
	//@Override
	public void mouseClicked(int mouseX, int mouseY){
		// If applicable, clamp new tower to touch
		if(getCurrency() >= getCostPer()){
			reduceCurrency(getCostPer());
			Touch.getInstance().clamp(new BlueTower(new Coord(topLeft.getX(), topLeft.getY())));
		}
	}

}
