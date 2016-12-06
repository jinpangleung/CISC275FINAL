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
	
	public Difficulty(){
		timeToSpawn = SPAWN_TIME;
	}
	
	/**
	 * Returns void
	 * <p>
	 * Look at the current Grid and decide how to scale difficulty and when to spawn new objects
	 */
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
		TrailItem toBeSpawned = randomItem(outsideGrid);
		//TrailItem toBeSpawned = decideSpawn(outsideGrid);
		toBeSpawned.setGridPosition(spawnPoint);
		Grid.getInstance().addPath(new Path(toBeSpawned, inGrid, new BackToGridBehavior()));
		totalSpawned++;
	}
	
	public TrailItem randomItem(Coord c){
		Random rand = new Random();
		int r = rand.nextInt(3);
		switch(r){
		case 0: return new Pollutant(c);
		case 1: return new Oyster(c);
		case 2: return new InvasiveItem(c);
		case 3: return new Larvae(c);
		default: throw new RuntimeException();
		}
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
		
		if (r <= getPercentCollected(oysterCollected, getTotalCollected())){
			return new Oyster(startCoord);
		}else if (r <= getPercentCollected(oysterCollected, getTotalCollected()) + getPercentCollected(pollutantCollected, getTotalCollected())){
			return new Pollutant(startCoord);
		}else if (r <= getPercentCollected(oysterCollected, getTotalCollected()) + getPercentCollected(pollutantCollected, getTotalCollected())
		+ getPercentCollected(invasiveCollected, getTotalCollected())){
			return new InvasiveItem(startCoord);
		}else{
			return new Larvae(startCoord);
		}
	}
	public void decideVelocityAndSpawnRate(){
		double maxVel = 15;
		double minVel = 5;
		double currentVel = MovableObject.getVelocity();
		int maxSpawn = 8;//sec
		int minSpawn = 2;//sec
		int currentSpawn = MovableObject.getSpawnRate();
		
		//I will make this its own function, no time now
		if(currentSpawn >= minSpawn && currentSpawn <= maxSpawn){
			decideHelper();
		}
		if(currentVel >= minVel && currentVel <= maxVel){
			decideHelper();
		}
	}
	public void decideHelper(){
		if (getPercentTotal(getTotalCollected(), totalSpawned) < 16){
			MovableObject.setVelocity(MovableObject.getVelocity()/.1);
			MovableObject.setSpawnRate(MoveableObject.getSpawnRate()/.1);
		}else if (getPercentTotal(getTotalCollected(), totalSpawned) < 31){
			MovableObject.setVelocity(MovableObject.getVelocity()/.05);
			MovableObject.setSpawnRate(MoveableObject.getSpawnRate()/.05);
		}else if(getPercentTotal(getTotalCollected(), totalSpawned) < 46){
			MovableObject.setVelocity(MovableObject.getVelocity()/.02);
			MovableObject.setSpawnRate(MoveableObject.getSpawnRate()/.02);
		}else if(getPercentTotal(getTotalCollected(), totalSpawned) < 66){
			MovableObject.setVelocity(MovableObject.getVelocity()*.03);
			MovableObject.setSpawnRate(MoveableObject.getSpawnRate()*.03);
		}else if(getPercentTotal(getTotalCollected(), totalSpawned) < 81){
			MovableObject.setVelocity(MovableObject.getVelocity()*.07);
			MovableObject.setSpawnRate(MoveableObject.getSpawnRate()*.07);
		}else{
			MovableObject.setVelocity(MovableObject.getVelocity()*.1);
			MovableObject.setSpawnRate(MoveableObject.getSpawnRate()*.1);
		}
	}
	
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