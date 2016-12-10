package model.inventory.factory;

import controller.Controller;
import model.TutorialStep;
import model.drawing.Animation;
import model.drawing.Coord;
import model.grid.Grid;
import model.grid.griditem.gabion.ConcreteGabion;
import model.gui.component.ComponentPosition;
import model.gui.touch.Touch;

/**
 * ConcreteGabionFactory is a factory class that constructs a ConcreteGabion.
 * See {@see TowerFactory} for more details.
 * @author Eric
 */
public class ConcreteGabionFactory extends TowerFactory {

    /**
     * Constructs a ConcreteGabionFactory object. See {@see TowerFactory} for
     * more details.
     */
	public ConcreteGabionFactory(ComponentPosition topLeft, int width, int height) {
		super(topLeft, width, height);
		this.animation = new Animation("ConcreteGabion");
		setCurrency(3);
	}
	
	/**
     * Constructs a ConcreteGabionFactory object. See {@see TowerFactory} for
     * more details.
     */
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
