package model.gui.touch;

import model.drawing.Animation;
import model.drawing.Coord;
import model.grid.griditem.GridColor;
import model.grid.gridcell.GridPosition;
import model.grid.griditem.GridItem;

public class DefaultTouchObject extends GridItem {

	public DefaultTouchObject() {
		super(new Coord(0.0, 0.0), new Animation("null"), new GridPosition(0, 0), GridColor.WHITE);
	}

	@Override
	public boolean update(long elapsedTime) {
		// Do Nothing
		System.out.println("DefaultTouchObject was updated");
		throw new RuntimeException();
	}

}
