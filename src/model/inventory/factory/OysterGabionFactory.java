package model.inventory.factory;
	
import model.drawing.Coord;
import model.grid.gridcell.GridPosition;
import model.grid.griditem.gabion.OysterGabion;
import model.gui.component.ComponentPosition;
import model.gui.path.DestroyBehavior;
import model.gui.path.Path;
import model.gui.touch.Touch;

/**
 * OysterGabionFactory
 * a factory that creates OysterGabion
 * 
 * @author eric
 *
 */

public class OysterGabionFactory extends TowerFactory {
	
	private int oysters;
	private final int OYSTERS_PER_GABION = 4;

	public OysterGabionFactory(ComponentPosition topLeft, int width, int height) {
		super(topLeft, width, height);
		oysters = 100;
	}
	
	public OysterGabionFactory(int x, int y, int width, int height){
		super(x, y, width, height);
		oysters = 100;
	}
	
	@Override
	public void mouseClicked(int mouseX, int mouseY){
		// If applicable, clamp new tower to touch
		if(oysters >= OYSTERS_PER_GABION){
			oysters -= OYSTERS_PER_GABION;
			Touch.getInstance().clamp(new OysterGabion(new Coord(topLeft.getX(), topLeft.getY()),
					new GridPosition(0, 0)));
		}
	}

}
