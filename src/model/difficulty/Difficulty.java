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
import model.moving.Velocity;

/**
 * Difficulty
 * the difficulty of the game, how fast stuff spawn
 * it also handles where the trailItem spawns
 * 
 *
 */

public class Difficulty {
	
	private long timeToSpawn;
	private long spawn_time = 2400000000L;
	//private long timeToStorm = 240000000000L;
	private double velocityScale = 1;
	private int oysterCount = 1;
	private int invasiveCount = 1;
	private int pollutantCount = 1;
	private int larvaeCount = 1;
	private int oysterCollected = 1;
	private int invasiveCollected = 1;
	private int pollutantCollected = 1;
	private int larvaeCollected = 1;
	private int totalSpawned = 4;
	private final static double MAX_VEL = PixelGrid.getInstance().getSquareHeight()/Time.nanosecond;//MovableObject.getInstance().getMaxVelocity();//this is causing error
	private final static double MIN_VEL = (PixelGrid.getInstance().getSquareHeight()/Time.nanosecond)-0000000005;
	private final static long MAX_SPAWN = 6000000000L;
	private final static long MIN_SPAWN = 1000000000L;
	
	
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
			//timeToStorm -= timeElapsed;
		} else {
			spawn();
			timeToSpawn = spawn_time;
			//timeToStorm -= timeElapsed;
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
		}
		else{
			toBeSpawned = new Oyster(outsideGrid);
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
		}else{
			larvaeCount++;
			return new Larvae(startCoord);
		}
	}
	//this will give time to spawn its new spawn rate
	public void setSpawnRate(long spawnRate){
		this.spawn_time = spawnRate;
	}
	
	public void decideVelocityAndSpawnRate(){
		//Double currentVel = MovableObject.getInstance().getVelocity();
		//Velocity currentVel = MovableObject.getInstance().getVelocity();
		
//		Double velX = MovableObject.getInstance().getVelocityX();
//		Double velY = MovableObject.getInstance().getVelocityY();
		//System.out.println("spawn time " + spawn_time + ", min spawn, " + MIN_SPAWN + ", max spawn" + MAX_SPAWN);
		
		decideHelper();
		if(spawn_time < MIN_SPAWN){
			spawn_time = MIN_SPAWN;
		}
		else if(spawn_time > MAX_SPAWN){
			spawn_time = MAX_SPAWN;
		}
//		System.out.println("velx " + velX + "vely" + velY + "Min vel " + MIN_VEL + "max vel" + MAX_VEL);
//		if((velX >= MIN_VEL || velY >= MIN_VEL) && (velX <= MAX_VEL || velY <= MAX_VEL)){
//			System.out.println("goes to decide helper");
//			decideHelper();
//		}
	}
	public void decideHelper(){
		//System.out.println("spawn_time before helper: " + spawn_time);
		//System.out.println("total collected/total spawned: " + 
		//getTotalCollected() + "/" + totalSpawned + "=" + getPercentTotal(getTotalCollected(), totalSpawned));
		
		if (totalSpawned > 5){
			if (getPercentTotal(getTotalCollected(), totalSpawned) < 16){
				setVelocityScale(.995);
				//System.out.println("< 16 case");
				setSpawnRate(this.spawn_time + 500000000);
			}else if (getPercentTotal(getTotalCollected(), totalSpawned) < 31){
				setVelocityScale(.996);
				//System.out.println("< 31 case");
				setSpawnRate(this.spawn_time + 300000000);
			}else if(getPercentTotal(getTotalCollected(), totalSpawned) < 46){
				setVelocityScale(.997);
				//System.out.println("< 46 case");
				setSpawnRate(this.spawn_time + 100000000);
			}else if(getPercentTotal(getTotalCollected(), totalSpawned) < 66){
				setVelocityScale(1.001);
				//System.out.println("< 66 case");
				setSpawnRate(this.spawn_time - 200000000);
			}else if(getPercentTotal(getTotalCollected(), totalSpawned) < 81){
				setVelocityScale(1.002);
				//System.out.println("< 81 case");
				setSpawnRate(this.spawn_time - 400000000);
			}else{
				setVelocityScale(1.003);
				//System.out.println("else case");
				setSpawnRate(this.spawn_time - 600000000);
			}
		}
		//System.out.println("spawn_time after helper: " + this.spawn_time + "\n");
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

	public double getVelocityScale() {
		return velocityScale;
	}

	public void setVelocityScale(double velocityScale) {
		this.velocityScale = velocityScale;
	}
}