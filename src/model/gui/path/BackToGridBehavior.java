package model.gui.path;

import java.io.Serializable;

import model.grid.Grid;

/**
 * BackToGridBehavior is a PathBehavior that adds to the Grid the GridItem
 * when its done traveling along a Path.
 */
public class BackToGridBehavior implements PathBehavior, Serializable {
	
    //@Override
	public void terminate(Path p){
		Grid.getInstance().addItem(p.getGridItem());
	}

}
