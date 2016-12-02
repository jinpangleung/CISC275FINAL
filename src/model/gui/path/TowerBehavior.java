package model.gui.path;

import model.inventory.Inventory;

public class TowerBehavior implements PathBehavior {

	//@Override
	public void terminate(Path p) {
		Inventory.getInstance().replaceTower(p.getGridItem());
	}
	
}
