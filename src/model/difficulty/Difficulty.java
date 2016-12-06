package model.difficulty;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import model.*;
import model.drawing.Coord;
import model.grid.Board;
import model.grid.Grid;
import model.grid.PixelGrid;
import model.grid.gridcell.Direction;
import model.grid.gridcell.GridPosition;
import model.grid.griditem.trailitem.InvasiveItem;
import model.grid.griditem.trailitem.Larvae;
import model.grid.griditem.trailitem.Oyster;
import model.grid.griditem.trailitem.Pollutant;
import model.grid.griditem.trailitem.TrailItem;
import model.gui.path.*;
import model.moving.MovableObject;

/**
 * Difficulty
 * the difficulty of the game, how fast stuff spawn
 * it also handles where the trailItem spawns
 * 
 *
 */

public class Difficulty {
	
	private long timeToSpawn;
	private final long SPAWN_TIME = 2400000000L;
	private long timeToStorm = 240000000000L;
	private int oysterCount = 1;
	private int invasiveCount = 1;
	private int pollutantCount = 1;
	private int larvaeCount = 1;
	private int oysterCollected = 1;
	private int invasiveCollected = 1;
	private int pollutantCollected = 1;
	private int larvaeCollected = 1;
	private int totalSpawned = 1;
	//private final static double MAX_VEL = MovableObject.getMaxVelocity();
	private final static double MIN_VEL = 3;
	private final static long MAX_SPAWN = 6000000000L;
	private final static long MIN_SPAWN = 1000000000L;
	
	
	public Difficulty(){
		timeToSpawn = SPAWN_TIME;
	}
	
	/**
	 * Returns void
	 * <p>
	 * Look at the current Grid and decide how to scale difficulty and when to spawn new objects
	 */
	//Difficulty.getSpawnRate()
	public void update(long timeElapsed){
		if(timeToSpawn > 0){
			timeToSpawn -= timeElapsed;
			timeToStorm -= timeElapsed;
		} else {
			spawn();
			timeToSpawn = SPAWN_TIME;
			timeToStorm -= timeElapsed;
		}
	}
	
	/**
	 * Place the trail item into the Grid
	 * 
	 */
	
	public void spawn(){
		int randomIndex = ThreadLocalRandom.current().nextInt(0, Board.getInstance().getSpawnPositions().size());
		GridPosition randomPosition = Board.getInstance().getSpawnPositions().get(randomIndex);
		GridPosition spawnPoint = new GridPosition(randomPosition.getX(), randomPosition.getY());
		Coord inGrid = PixelGrid.getInstance().getCoord(spawnPoint);
		Coord outsideGrid = new Coord(inGrid.getX(), -PixelGrid.getInstance().getSquareHeight());
		TrailItem toBeSpawned = decideSpawn(outsideGrid);
		toBeSpawned.setGridPosition(spawnPoint);
		Grid.getInstance().addPath(new Path(toBeSpawned, inGrid, new BackToGridBehavior()));
		totalSpawned++;
	}
	
	/**
	 * Returns void
	 * <p>
	 * Use the difficulty and what has been spawned recently to decide
	 * what needs to be spawned, avoid unwinnable scenarios
	 */
	//This will get percentage of items collected specifically versus total items collected
	public float getPercentCollected(int specificCollect, int totalCollect){
		return specificCollect * 100f / totalCollect;
	}
	//This will get percentage of items collected versus total items spawned
	public float getPercentTotal(int totalCollect, int totalSpawn){
		return totalCollect * 100f / totalSpawn;
	}
	
	public TrailItem decideSpawn(Coord startCoord){
		
		Random rand = new Random();
		int r = rand.nextInt(100);
		
		float oyster = getPercentCollected(oysterCollected, getTotalCollected());
		float pollutant = oyster + getPercentCollected(pollutantCollected, getTotalCollected());
		float invasiveItem = oyster + pollutant + getPercentCollected(invasiveCollected, getTotalCollected());
		
		if (r <= oyster){
			return new Oyster(startCoord);
		}else if (r <= pollutant){
			return new Pollutant(startCoord);
		}else if (r <= invasiveItem){
			return new InvasiveItem(startCoord);
		}else{
			return new Larvae(startCoord);
		}
	}
	
	public void setSpawnRate(long spawnRate){
		this.timeToSpawn = spawnRate;
	}
	
	/*public void decideVelocityAndSpawnRate(){
		double currentVel = MovableObject.getVelocity();
		
		//I will make this its own function, no time now
		if(timeToSpawn >= MIN_SPAWN && timeToSpawn <= MAX_SPAWN){
			decideHelper();
		}
		if(currentVel >= MIN_VEL && currentVel <= MAX_VEL){
			decideHelper();
		}
	}
	public void decideHelper(){
		if (getPercentTotal(getTotalCollected(), totalSpawned) < 16){
			MovableObject.setVelocity(MovableObject.getVelocity()/.1);
			setSpawnRate((long) (timeToSpawn/.1));
		}else if (getPercentTotal(getTotalCollected(), totalSpawned) < 31){
			MovableObject.setVelocity(MovableObject.getVelocity()/.05);
			setSpawnRate((long) (timeToSpawn/.05));
		}else if(getPercentTotal(getTotalCollected(), totalSpawned) < 46){
			MovableObject.setVelocity(MovableObject.getVelocity()/.02);
			setSpawnRate((long) (timeToSpawn/.02));
		}else if(getPercentTotal(getTotalCollected(), totalSpawned) < 66){
			MovableObject.setVelocity(MovableObject.getVelocity()*.03);
			setSpawnRate((long) (timeToSpawn*.03));
		}else if(getPercentTotal(getTotalCollected(), totalSpawned) < 81){
			MovableObject.setVelocity(MovableObject.getVelocity()*.07);
			setSpawnRate((long) (timeToSpawn*.07));
		}else{
			MovableObject.setVelocity(MovableObject.getVelocity()*.1);
			setSpawnRate((long) (timeToSpawn*.1));
		}
	}
*/

	public void collect(TrailItem item){
		if (item instanceof Oyster){
			oysterCollected++;
		}else if(item instanceof Pollutant){
			pollutantCollected++;
		}else if(item instanceof Larvae){
			larvaeCollected++;
		}else if(item instanceof InvasiveItem){
			invasiveCollected++;
		}else{
			System.out.println("Could not collect trail item.");
		}
	}
	public int getTotalCollected(){
		return oysterCollected + pollutantCollected + larvaeCollected + invasiveCollected;
	}
}