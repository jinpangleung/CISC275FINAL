package model.difficulty;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import model.*;
import model.drawing.Coord;
import model.grid.Board;
import model.grid.Grid;
import model.grid.PixelGrid;
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
	private long spawn_time = 3500000000L;
	private double velocityScale = 1;
	private int oysterCount = 1;
	private int invasiveCount = 1;
	private int pollutantCount = 1;
	private int larvaeCount = 1;
	private static int oysterCollected = 1;
	private static int invasiveCollected = 1;
	private static int pollutantCollected = 1;
	private static int larvaeCollected = 1;
	private int totalSpawned = 4;
	private final static double MAX_VEL = PixelGrid.getInstance().getSquareHeight()/Time.nanosecond;//MovableObject.getInstance().getMaxVelocity();//this is causing error
	private final static double MIN_VEL = (PixelGrid.getInstance().getSquareHeight()/Time.nanosecond)-0000000005;
	private final static long MAX_SPAWN = 6000000000L;
	private final static long MIN_SPAWN = 2000000000L;
	
	
	public Difficulty(){
		timeToSpawn = spawn_time;
	}
	
	/**
	 * Returns void
	 * <p>
	 * Look at the current Grid and decide how to scale difficulty and when to spawn new objects
	 */
	public void update(long timeElapsed){
		if(timeToSpawn > 0){
			timeToSpawn -= timeElapsed;
		} else {
			spawn();
			timeToSpawn = spawn_time;
		}
	}
	
	/**
	 * Place the trail item into the Grid
	 * 
	 */
	
	public void spawn(){
		int randomIndex = ThreadLocalRandom.current().nextInt(0, Board.getInstance().getSpawnPositions().size());
		GridPosition spawnPoint = Board.getInstance().getSpawnPositions().get(randomIndex);
		Coord inGrid = PixelGrid.getInstance().getCoord(spawnPoint);
		Coord outsideGrid = new Coord(inGrid.getX(), -PixelGrid.getInstance().getSquareHeight());
		TrailItem toBeSpawned = decideSpawn(outsideGrid);
		toBeSpawned.setGridPosition(spawnPoint);
		Grid.getInstance().addPath(new Path(toBeSpawned, inGrid, new BackToGridBehavior()));
		totalSpawned++;
		decideVelocityAndSpawnRate();
	}
	
	/**
	 * Place the trail item into the Grid for the tutorial
	 * 
	 */
	
	public void spawnTutorial(TutorialStep step){
		// 0 is the first spawn point
		int index = 0;
		GridPosition spawnPoint = Board.getInstance().getSpawnPositions().get(index);
		Coord inGrid = PixelGrid.getInstance().getCoord(spawnPoint);
		Coord outsideGrid = new Coord(inGrid.getX(), -PixelGrid.getInstance().getSquareHeight());
		TrailItem toBeSpawned;
		if(step == TutorialStep.SPAWN_ITEM){
			toBeSpawned = new Pollutant(outsideGrid);
			pollutantCount++;
		}
		else{
			toBeSpawned = new Oyster(outsideGrid);
			oysterCount++;
		}
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
	
	//this decides when to spawn
	public TrailItem decideSpawn(Coord startCoord){
		Random rand = new Random();
		int r = rand.nextInt(100);
		
		float oyster = getPercentCollected(oysterCollected, getTotalCollected());
		float pollutant = oyster + getPercentCollected(pollutantCollected, getTotalCollected());
		float invasiveItem = pollutant + getPercentCollected(invasiveCollected, getTotalCollected());
		if (r <= oyster){
			oysterCount++;
			return new Oyster(startCoord);
		}else if (r <= pollutant){
			pollutantCount++;
			return new Pollutant(startCoord);
		}else if (r <= invasiveItem){
			invasiveCount++;
			return new InvasiveItem(startCoord);
		}else if(r > invasiveItem){
			larvaeCount++;
			return new Larvae(startCoord);
		}
		else{
			larvaeCount++;
			return new Larvae(startCoord);
		}
	}
	//this will give time to spawn its new spawn rate
	public void setSpawnRate(long spawnRate){
		this.spawn_time = spawnRate;
	}
	
	public void decideVelocityAndSpawnRate(){
		
		decideHelper();
		if(spawn_time < MIN_SPAWN){
			spawn_time = MIN_SPAWN;
		}
		else if(spawn_time > MAX_SPAWN){
			spawn_time = MAX_SPAWN;
		}
	}
	public void decideHelper(){
		
		if (totalSpawned > 10){
			if (getPercentTotal(getTotalCollected(), totalSpawned) < 16){
				MovableObject.setMaxSpeed(15);
				setSpawnRate(this.spawn_time + 500000000);
			}else if (getPercentTotal(getTotalCollected(), totalSpawned) < 31){
				MovableObject.setMaxSpeed(30);
				setSpawnRate(this.spawn_time + 300000000);
			}else if(getPercentTotal(getTotalCollected(), totalSpawned) < 46){
				MovableObject.setMaxSpeed(45);
				setSpawnRate(this.spawn_time + 100000000);
			}else if(getPercentTotal(getTotalCollected(), totalSpawned) < 66){
				MovableObject.setMaxSpeed(65);
				setSpawnRate(this.spawn_time - 200000000);
			}else if(getPercentTotal(getTotalCollected(), totalSpawned) < 81){
				MovableObject.setMaxSpeed(80);
				setSpawnRate(this.spawn_time - 400000000);
			}else{
				MovableObject.setMaxSpeed(95);
				setSpawnRate(this.spawn_time - 600000000);
			}
		}
	}


	public static void collect(TrailItem item){
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

//	public double getVelocityScale() {
//		return velocityScale;
//	}
//
//	public void setVelocityScale(double velocityScale) {
//		this.velocityScale = velocityScale;
//	}
}