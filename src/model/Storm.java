package model;

import java.awt.Graphics;
import java.util.Collection;
import java.util.Iterator;

import model.grid.Grid;
import model.grid.gridcell.GridPosition;
import model.grid.griditem.GridItem;
import model.grid.griditem.gabion.Gabion;
import model.grid.griditem.towers.Tower;

/**
 * Storm
 * A storm comes in to damage the estuary and it interacts with gabions, towers, and esturayHealth
 * 
 * @author Roy
 *
 */

public class Storm {
	protected boolean isStorming;
	protected int damage;
	
	int sizeX = Grid.getInstance().getSquareWidth();
	int sizeY = Grid.getInstance().getSquareHeight();
	boolean done = false;
	
	public Storm(){
		isStorming = true;
		damage = 100;
	}
	
	public void dealDamage(){
		//TODO
		Collection<GridItem> allItems = Grid.getInstance().getItems();
		Collection<Gabion> gabion = Grid.getInstance().getGabions();
		Collection<Tower> tower = Grid.getInstance().getTowers();
		
		for (int i=sizeX; i>=0; i--){
			for(Iterator<Gabion> gLoop = gabion.iterator(); gLoop.hasNext();){
				Gabion gRandom = gLoop.next();
				if (gRandom.getGridPosition().getX()==i){
					gabion.remove(this);
					allItems.remove(this);
					done = true;
					break;
				}
			}
			if (done == false){
				for(Iterator<Tower> tLoop = tower.iterator(); tLoop.hasNext();){
					Tower gTower = tLoop.next();
					if (gTower.getGridPosition().getX()==i){
						gTower.setCooldownRemaining(10);
						done = true;
						break;
					}
				}
			}
			
			if (done == false){
				Player.getInstance().setEstuaryHealth(Player.getInstance().getEstuaryHealth()-100);
			}
			
		}
	}
	
	public boolean isStorming() {
		return isStorming;
	}
	
	public void setStorming(boolean isStorming) {
		this.isStorming = isStorming;
	}
	
	static public void draw(Graphics g){
		// TODO
	}
}
