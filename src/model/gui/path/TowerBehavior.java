package model.gui.path;

import java.io.Serializable;

import model.inventory.Inventory;

public class TowerBehavior implements PathBehavior , Serializable {

	//@Override
	public void terminate(Path p) {
		Inventory.getInstance().replaceTower(p.getGridItem());
	}
	
}
