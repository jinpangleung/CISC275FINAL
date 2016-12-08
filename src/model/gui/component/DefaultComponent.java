package model.gui.component;

import controller.Controller;
import model.TutorialStep;
import model.grid.Grid;
import model.gui.path.Path;
import model.gui.touch.Touch;

public class DefaultComponent extends Component {

	public DefaultComponent(ComponentPosition topLeft, int width, int height) {
		super(topLeft, width, height);
	}
	
	public DefaultComponent(int x, int y, int width, int height){
		this(new ComponentPosition(x, y), width, height);
	}

	//@Override
	public void mouseClicked(int mouseX, int mouseY) {
		// Ignore mouse clicks
		
	}

	//@Override
	public void mouseReleased(int mouseX, int mouseY) {
		if(Touch.getInstance().isHolding()){
			// If tutorial is running and player is holding tower then call correct action
			if(Controller.isRunningTutorial() && Grid.getInstance().getStep() == TutorialStep.PLACE_TOWER){
				Grid.getInstance().doneClickTowerUndo();
			}
			if(Controller.isRunningTutorial() && Grid.getInstance().getStep() == TutorialStep.PLACE_GABBION){
				Grid.getInstance().doneClickGabbionUndo();
			}
			
			Path.snap();
		}
		
	}

}
