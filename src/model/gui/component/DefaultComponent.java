package model.gui.component;

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
			Path.snap();
		}
		
	}

}
