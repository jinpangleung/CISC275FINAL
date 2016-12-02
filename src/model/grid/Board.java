package model.grid;

import model.grid.gridcell.GridCell;
import model.grid.gridcell.GridPosition;

/**
 * Board
 * Contains a 2D array of GridCells
 * Reads from grid.txt to make the board
 * 
 * @author Eric
 *
 */

public class Board {
	
	private int width;
	private int height;
	private GridCell[][] gridCells;
	private String fileName;
	
	public Board(String fileName){
		this.fileName = fileName;
		initialize();
	}
	
	public Board(){
		this("grid.txt");
	}
	
	public GridCell getGridCell(int x, int y){
		if(x >= width || y >= height){
			throw new OutOfGridBoundsException();
		}
		return gridCells[x][y];
	}
	
	public GridCell getGridCell(GridPosition gp){
		return getGridCell(gp.getX(), gp.getY());
	}
	
	public void initialize(){
		// TODO
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	

}
