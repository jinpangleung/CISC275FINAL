package model.inventory.factory;

import controller.Controller;
import model.TutorialStep;
import model.drawing.Coord;
import model.grid.Grid;
import model.grid.griditem.gabion.ConcreteGabion;
import model.gui.component.ComponentPosition;
import model.gui.touch.Touch;

/**
 * ConcreteGabionFactory
 * a factory that creates ConcreteGabion
 * 
 * @author Eric
 *
 */

public class ConcreteGabionFactory extends TowerFactory {

	public ConcreteGabionFactory(ComponentPosition topLeft, int width, int height) {
		super(topLeft, width, height);
		setCurrency(3);
	}
	
	public ConcreteGabionFactory(int x, int y, int width, int height){
		this(new ComponentPosition(x, y), width, height);
	}
	
	//@Override
	public void mouseClicked(int mouseX, int mouseY){
		// If tutorial is running then tell grid to perform correct step
		if(Controller.isRunningTutorial() && Grid.getInstance().getStep() == TutorialStep.CLICK_GABBION){
			Grid.getInstance().doneClickGabbion();
		}
		
		// If applicable, clamp new tower to touch
		if(getCurrency() >= getCostPer()){
			reduceCurrency(getCostPer());
			Touch.getInstance().clamp(new ConcreteGabion(new Coord(topLeft.getX(), topLeft.getY())));
		}
	}

}
