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

/**
 * Board is a class that provides the facilities to read a game board from
 * a text file. It also sets up the 2D array of GridCell objects.
 * @author Sam, Eric
 */
public class Board {
	
	private int width;
	private int height;
	private GridCell[][] gridCells;
	private String fileName;
	private List<GridPosition> spawnPositions;
	private static Board instance;
	
	public Board(String fileName){
		this.fileName = fileName;
		instance = this;
		initialize();
	}
	
	public Board(){
		this("board_text_files/grid.txt");
	}
	
	public static Board getInstance(){
		return instance;
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
		try{
			// Read from grid.txt
			Path path = Paths.get(this.fileName);
		    List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
		    width = lines.get(0).length();
		    height = lines.size();
		    System.out.println("Grid Size " + Integer.toString(width));
		    GridCell.initialize();
		    gridCells = new GridCell[width][height];
		    spawnPositions = new ArrayList<GridPosition>();
		    for(int i = 1; i < height; i++){ // i is the y component
		    	String str = lines.get(i);
		    	for(int j = 0; j < width; j++){ // j is the x component
		    		char c = str.charAt(j);
		    		GridCell gg = makeGridCell(c, j, i);
		    		gridCells[j][i] = gg;
		    	}
		    }
		    for (int k = 0; k < width; k++){
		    	String st = lines.get(0);
		    	char h = st.charAt(k);
		    	GridCell gc = makeGridCell(h, k, 0);
		    	gridCells[k][0] = gc;
		    	if(gc.getDirection() == Direction.SOUTH){
		    		spawnPositions.add(gc.getGridPosition());
		    	}
		    }
		} catch (IOException e){
			System.out.println("Could not open grid.txt");
			throw new RuntimeException();
		}
	}
	public GridCell makeGridCell(char z, int x, int y){
		Direction dir;
		boolean isTrail;
		boolean isTower;
		boolean isGabion;
		switch(z){
		case 'N': dir = Direction.NORTH; isTrail = true; isTower = false; isGabion = false; break;
		case 'E': dir = Direction.EAST; isTrail = true; isTower = false; isGabion = false; break;
		case 'W': dir = Direction.WEST; isTrail = true; isTower = false; isGabion = false; break;
		case 'S': dir = Direction.SOUTH; isTrail = true; isTower = false; isGabion = false; break;
		case 'A': dir = Direction.NORTHEAST; isTrail = true; isTower = false; isGabion = false; break;
		case 'B': dir = Direction.NORTHWEST; isTrail = true; isTower = false; isGabion = false; break;
		case 'C': dir = Direction.SOUTHEAST; isTrail = true; isTower = false; isGabion = false; break;
		case 'D': dir = Direction.SOUTHWEST; isTrail = true; isTower = false; isGabion = false; break;
		case 'X': dir = Direction.NONE; isTrail = false; isTower = true; isGabion = false; break;
		case 'G': dir = Direction.NONE; isTrail = false; isTower = false; isGabion = true; break;
		default: throw new InvalidCellException();
		}
		return new GridCell(new GridPosition(x, y), dir, isTrail, isTower, isGabion);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public List<GridPosition> getSpawnPositions() {
		return spawnPositions;
	}
	
	

}
