package model.gui.touch;

import model.drawing.Animation;
import model.drawing.Coord;
import model.drawing.DrawableObject;
import model.grid.GridColor;
import model.grid.gridcell.GridPosition;
import model.grid.griditem.GridItem;

public class DefaultTouchObject extends GridItem {

	public DefaultTouchObject() {
		super(new Coord(0.0, 0.0), new Animation(), new GridPosition(0, 0), GridColor.WHITE);
	}

}
