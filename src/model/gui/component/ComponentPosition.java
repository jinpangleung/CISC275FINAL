package model.gui.component;

import model.OrderedPair;


/**
 * ComponentPosition
 * x,y position for the topleft or bottomright corner of a component
 * @attributes Integer x
 * @attributes Integer y
 * @author Eric
 *
 */
public class ComponentPosition extends OrderedPair<Integer> {
	private static final long serialVersionUID = 1L;

	public ComponentPosition(int x, int y) {
		super(x, y);
	}

}
