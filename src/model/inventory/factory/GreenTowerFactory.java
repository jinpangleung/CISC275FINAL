package model.inventory.factory;

import model.drawing.Animation;
import model.drawing.Coord;
import model.grid.griditem.tower.GreenTower;
import model.gui.component.ComponentPosition;
import model.gui.touch.Touch;

/**
 * GreenTowerFactory is a factory class that constructs a GreenTower.
 * See {@see TowerFactory} for more details.
 * @author Eric
 */
public class GreenTowerFactory extends TowerFactory {

    /**
     * Constructs a GreenTowerFactory object. See {@see TowerFactory} for
     * more details.
     */
	public GreenTowerFactory(ComponentPosition topLeft, int width, int height) {
		super(topLeft, width, height);
		this.animation = new Animation("GreenTowerIcon");
	}
	
	/**
     * Constructs a GreenTowerFactory object. See {@see TowerFactory} for
     * more details.
     */
	public GreenTowerFactory(int x, int y, int width, int height){
		this(new ComponentPosition(x, y), width, height);
	}
	
	//@Override
	public void mouseClicked(int mouseX, int mouseY){
		// If applicable, clamp new tower to touch
		if(getCurrency() >= getCostPer()){
			reduceCurrency(getCostPer());
			Touch.getInstance().clamp(new GreenTower(new Coord(topLeft.getX(), topLeft.getY())));
		}
	}

}
