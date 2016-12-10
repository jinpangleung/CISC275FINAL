package model.gui.touch;

import model.drawing.Animation;
import model.drawing.Coord;
import model.grid.griditem.GridColor;
import model.grid.gridcell.GridPosition;
import model.grid.griditem.GridItem;

/**
 * DefaultTouchObject is a null object class. That is,
 * Touch holds a DefaultTouchObject whenever it is not holding
 * another GridItem.
 * 
 * @author Eric
 */
public class DefaultTouchObject extends GridItem {

    /**
     *  Constructs a DefaultTouchObject.
     *  
     *  @author Eric
     */
	public DefaultTouchObject() {
		super(new Coord(0.0, 0.0), new Animation("null"), new GridPosition(0, 0), GridColor.WHITE);
	}

	//@Override
	public boolean update(long elapsedTime) {
		// Do Nothing
		System.out.println("DefaultTouchObject was updated");
		throw new RuntimeException();
	}

}
