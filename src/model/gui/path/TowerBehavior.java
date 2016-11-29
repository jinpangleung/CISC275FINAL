package model.gui.path;

import model.grid.Grid;
import model.inventory.Inventory;

public class TowerBehavior implements PathBehavior {

	@Override
	public void terminate(Path p) {
		Grid.getInstance().removePath(p);
		Inventory.getInstance().getTowerFactory(p.getGridItem().getGridColor()).increaseTowerCount();
	}
	
	

}
