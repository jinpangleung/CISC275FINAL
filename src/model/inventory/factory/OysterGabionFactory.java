package model.inventory.factory;
	
import model.drawing.Animation;
import model.drawing.Coord;
import model.grid.griditem.gabion.OysterGabion;
import model.gui.component.ComponentPosition;
import model.gui.touch.Touch;

/**
 * OysterGabionFactory
 * a factory that creates OysterGabion
 * 
 * @author eric
 *
 */

public class OysterGabionFactory extends TowerFactory {
	
	private final int OYSTERS_PER_GABION = 4;

	public OysterGabionFactory(ComponentPosition topLeft, int width, int height) {
		super(topLeft, width, height);
		this.animation = new Animation("OysterGabion");
		setCurrency(0);
		setCostPer(OYSTERS_PER_GABION);
	}
	
	public OysterGabionFactory(int x, int y, int width, int height){
		this(new ComponentPosition(x, y), width, height);
	}
	
	//@Override
	public void mouseClicked(int mouseX, int mouseY){
		// If applicable, clamp new tower to touch
		if(getCurrency() >= getCostPer()){
			reduceCurrency(getCostPer());
			Touch.getInstance().clamp(new OysterGabion(new Coord(topLeft.getX(), topLeft.getY())));
		}
	}

}
