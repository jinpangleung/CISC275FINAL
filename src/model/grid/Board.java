package model.grid;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import model.grid.gridcell.Direction;
import model.grid.gridcell.GridCell;
import model.grid.gridcell.GridPosition;
import model.grid.griditem.GridItem;
import model.grid.griditem.towers.Tower;
import model.grid.griditem.trailitem.TrailItem;
import model.gui.path.BackToGridBehavior;
import model.gui.touch.Touch;

/**
 * Board
 * the board is basically the whole screen, where it contains all Grid and inventory
 * 
 * @author Roy, Eric
 *
 */

public class Board {
	
	private GridCell[][] gridCell;
	private int gridSizeX;
	private int gridSizeY;
	private List<GridPosition> spawnPositions;
	
	// Constructor
	public Board(){
		try{
			// Read from grid.txt
			Path path = Paths.get("grid.txt");
		    List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
		    gridSizeX = lines.get(0).length();
		    gridSizeY = lines.size();
		    System.out.println("Grid Size " + Integer.toString(gridSizeX));
		    gridCell = new GridCell[gridSizeX][gridSizeY];
		    spawnPositions = new ArrayList<GridPosition>();
		    spawnPositions.add(new GridPosition(0, 0));
		    for(int i = 0; i < gridSizeY; i++){ // i is the y component
		    	String str = lines.get(i);
		    	for(int j = 0; j < gridSizeX; j++){ // j is the x component
		    		/*
		    		char c = str.charAt(j);
		    		Direction dir;
		    		boolean isTrail;
		    		switch(c){
		    		case 'N': dir = Direction.NORTH; isTrail = true; break;
		    		case 'E': dir = Direction.EAST; isTrail = true; break;
		    		case 'W': dir = Direction.WEST; isTrail = true; break;
		    		case 'S': dir = Direction.SOUTH; isTrail = true; break;
		    		case 'A': dir = Direction.NORTHEAST; isTrail = true; break;
		    		case 'B': dir = Direction.NORTHWEST; isTrail = true; break;
		    		case 'C': dir = Direction.SOUTHEAST; isTrail = true; break;
		    		case 'D': dir = Direction.SOUTHWEST; isTrail = true; break;
		    		case 'X': dir = Direction.SOUTH; isTrail = false; break;
		    		default: throw new InvalidCellException();
		    		}
		    		*/
		    		gridCell[j][i] = new GridCell(new GridPosition(j, i), true, Direction.SOUTH);
		    	}
		    }
		} catch (IOException e){
			System.out.println("Could not open grid.txt");
			throw new RuntimeException();
		}
	}
	
	public int getSquareWidth(){
		return gridSizeX;
	}
	public int getSquareHeight(){
		return gridSizeY;
		
	}
	public GridCell getGridCell(int x, int y){
		return gridCell[x][y];
	}
	public GridCell getGridPosition(GridPosition gp){
		return getGridCell(gp.getX(),gp.getY());
	}
	public int getGridSizeX(){
		return gridSizeX;
	}
	public int getGridSizeY(){
		return gridSizeY;
	}
	public List<GridPosition> getSpawnPositions(){
		return spawnPositions;
	}
	public GridCell getGridCell(GridPosition gp){
		System.out.println(gp);
		return getGridCell(gp.getX(), gp.getY());
	}
	public int getWidthByHeight(){
		return gridSizeX * gridSizeY;
	}
	public class DirectionGrid {
		
	}
	public void print(){
		for(int i = 0; i < gridCell[0].length; i++){
			for(int j = 0; j < gridCell.length; j++){
				System.out.print(gridCell[j][i].getDirection());
			}
			System.out.println("");
		}
	}
}
