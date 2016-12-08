package model.inventory.factory;

import com.sun.prism.Graphics;

import controller.Controller;
import model.TutorialStep;
import model.drawing.Animation;
import model.drawing.Coord;
import model.grid.Grid;
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
		this.animation = new Animation("RedTowerIcon");
	}
	
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

