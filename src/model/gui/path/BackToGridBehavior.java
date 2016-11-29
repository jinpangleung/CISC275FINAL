package model.gui.path;

import model.grid.Grid;
import model.grid.griditem.trailitem.TrailItem;

/**
 * BackToGridBehavior
 * When a path is done add it back to the grid wherever it initially started from
 * 
 * @author Eric
 *
 */

public class BackToGridBehavior implements PathBehavior {
	
	public void terminate(Path p){
		Grid.getInstance().addTrailItem((TrailItem) p.getGridItem());
	}

}
