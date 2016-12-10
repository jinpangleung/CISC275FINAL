package model.gui.path;

import java.io.Serializable;

import model.grid.Grid;

/**
 * BackToGridBehavior
 * When a path is done add it back to the grid wherever it initially started from
 * 
 * @author Eric
 *
 */

public class BackToGridBehavior implements PathBehavior, Serializable {
	
	public void terminate(Path p){
		Grid.getInstance().addItem(p.getGridItem());
	}

}
