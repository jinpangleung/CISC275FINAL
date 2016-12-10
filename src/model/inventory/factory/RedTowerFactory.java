package model.inventory.factory;

import controller.Controller;
import model.TutorialStep;
import model.drawing.Animation;
import model.drawing.Coord;
import model.grid.Grid;
import model.grid.griditem.tower.RedTower;
import model.gui.component.ComponentPosition;
import model.gui.touch.Touch;

/**
 * RedTowerFactory is a factory class that constructs a RedTower.
 * See {@see TowerFactory} for more details.
 * @author Eric
 */
public class RedTowerFactory extends TowerFactory {

    /**
     * Constructs a RedTowerFactory object. See {@see TowerFactory} for
     * more details.
     */
	public RedTowerFactory(ComponentPosition topLeft, int width, int height) {
		super(topLeft, width, height);
		this.animation = new Animation("RedTowerIcon");
	}
	
	/**
     * Constructs a RedTowerFactory object. See {@see TowerFactory} for
     * more details.
     */
	public RedTowerFactory(int x, int y, int width, int height){
		this(new ComponentPosition(x, y), width, height);
	}
	
	//@Override
	public void mouseClicked(int mouseX, int mouseY){
		// If tutorial is running then tell grid to perform correct step
		if(Controller.isRunningTutorial() && Grid.getInstance().getStep() == TutorialStep.CLICK_TOWER){
			Grid.getInstance().doneClickTower();
		}
		
		// If applicable, clamp new tower to touch
		if(getCurrency() >= getCostPer()){
			reduceCurrency(getCostPer());
			Touch.getInstance().clamp(new RedTower(new Coord(topLeft.getX(), topLeft.getY())));
		}
	}
}

