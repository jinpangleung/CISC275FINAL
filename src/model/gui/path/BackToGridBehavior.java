package model.gui.path;

import model.grid.Grid;

/**
 * BackToGridBehavior
 * When a path is done add it back to the grid wherever it initially started from
 * 
 * @author Eric
 *
 */

public class BackToGridBehavior implements PathBehavior {
	
	public void terminate(Path p){
		Grid.getInstance().addItem(p.getGridItem());
	}

}
