package model.grid;

import java.awt.Graphics;
import java.util.*;

import model.drawing.Animation;
import model.grid.gridcell.GridCell;
import model.grid.griditem.GridItem;
import model.grid.griditem.gabion.Gabion;
import model.grid.griditem.tower.Tower;
import model.grid.griditem.trailitem.TrailItem;
import model.gui.component.Component;
import model.gui.component.ComponentPosition;
import model.gui.path.Path;
import model.gui.touch.Touch;

/**
 * Grid
 * the grid is the area where the player is going to be playing on the game on
 * you can only click in the grid area plus drag and drop it on grid
 * 
 * @author Roy, Eric
 *
 */

public class Grid extends Component {

	public Grid(ComponentPosition topLeft, int width, int height) {
		super(topLeft, width, height);
		items = new ArrayList<GridItem>();
		paths = new ArrayList<Path>();
		gabions = new ArrayList<Gabion>();
		towers = new ArrayList<Tower>();
		trailItems = new ArrayList<TrailItem>();
		instance = this;
		board = new Board("board_text_files/grid.txt");
		pixelGrid = new PixelGrid(board);
	}
	
	public Grid(int x, int y, int w, int l){
		this(new ComponentPosition(x, y), w, l);
	}
	
	public Grid(ComponentPosition topLeft, int width, int height, String testBoard) {
		super(topLeft, width, height);
		items = new ArrayList<GridItem>();
		paths = new ArrayList<Path>();
		gabions = new ArrayList<Gabion>();
		towers = new ArrayList<Tower>();
		trailItems = new ArrayList<TrailItem>();
		instance = this;
		board = new Board(testBoard);
		pixelGrid = new PixelGrid(board);
	}
	
	public Grid(int x, int y, int w, int l, String testBoard){
		this(new ComponentPosition(x, y), w, l, testBoard);
	}
	
	private Collection<GridItem> items;
	private Collection<TrailItem> trailItems;
	private Collection<Gabion> gabions;
	private Collection<Tower> towers;
	private Collection<Path> paths;
	private static Grid instance;
	private Board board;
	private PixelGrid pixelGrid;
	
	public static Grid getInstance(){
		return instance;
	}
	
	public void addItem(GridItem gi){
		items.add(gi);
		
		if(gi instanceof TrailItem){
			trailItems.add((TrailItem) gi);
		} else if (gi instanceof Tower){
			towers.add((Tower) gi);
		} else if (gi instanceof Gabion){
			gabions.add((Gabion) gi);
		}
	}
	
	private void removeTrailItem(TrailItem toBeRemoved){
		Iterator<TrailItem> it = trailItems.iterator();
		while(it.hasNext()){
			TrailItem ti = it.next();
			if(ti.equals(toBeRemoved)){
				it.remove();
				return;
			}
		}
	}
	
	private void removeTower(Tower toBeRemoved){
		Iterator<Tower> it = towers.iterator();
		while(it.hasNext()){
			Tower t = it.next();
			if(t.equals(toBeRemoved)){
				it.remove();
				return;
			}
		}
	}
	
	private void removeGabion(Gabion toBeRemoved){
		Iterator<Gabion> it = gabions.iterator();
		while(it.hasNext()){
			Gabion g = it.next();
			if(g.equals(toBeRemoved)){
				it.remove();
				return;
			}
		}
	}
	
	public void removeItem(GridItem toBeRemoved){
		Iterator<GridItem> it = items.iterator();
		while(it.hasNext()){
			GridItem gi = it.next();
			if(gi.equals(toBeRemoved)){
				it.remove();
				if(toBeRemoved instanceof TrailItem){
					removeTrailItem((TrailItem) toBeRemoved);
				} else if (toBeRemoved instanceof Tower){
					removeTower((Tower) toBeRemoved);
				} else if (toBeRemoved instanceof Gabion){
					removeGabion((Gabion) toBeRemoved);
				}
				return;
			}
		}
		throw new GridItemNotFoundException();
	}
	
	public void addPath(Path p){
		paths.add(p);
	}
	
	public void removePath(Path toBeRemoved){
		Iterator<Path> it = paths.iterator();
		while(it.hasNext()){
			Path p = it.next();
			if(p.equals(toBeRemoved)){
				it.remove();
				return;
			}
		}
		throw new PathNotFoundException();
	}

	@Override
	public void mouseClicked(int mouseX, int mouseY) {
		Iterator<TrailItem> it = trailItems.iterator();
		while(it.hasNext()){
			TrailItem ti = it.next();
			if(ti.isWithin(mouseX, mouseY)){
				if(ti.click()){
					removeItem(ti);
				}
				return;
			}
		}
	}

	@Override
	public void mouseReleased(int mouseX, int mouseY) {
		if(!Touch.getInstance().isHolding()){
			return;
		}
		GridItem gi = Touch.getInstance().getHolding();
		if(gi instanceof TrailItem){
			dropGridItem(mouseX, mouseY);
		} else if (gi instanceof Tower){
			placeTower(mouseX, mouseY);
		} else if (gi instanceof Gabion){
			placeGabion(mouseX, mouseY);
		}
	}
	
	public void dropGridItem(int x, int y){
		for(Tower t : towers){
			if(t.isWithin(x, y)){
				t.release(x, y);
				return;
			}
		}
		Path.snap();
	}
	
	public void placeTower(int x, int y){
		
		GridCell gc = PixelGrid.getInstance().getGridCell(x, y);
		if(!gc.isCanPlaceTower()){
			Path.snap();
			return;
		}
		
		Tower tower = (Tower) Touch.getInstance().getHolding();
		
		for(Tower t : towers){
			if(tower.isInRange(t.getCoord())){
				Path.snap();
				return;
			}
		}
		
		Touch.getInstance().unClamp();
		this.addItem(tower);
		
	}
	
	public void placeGabion(int x, int y){
		GridCell gc = PixelGrid.getInstance().getGridCell(x, y);
		if(!gc.isCanPlaceGabion()){
			Path.snap();
			return;
		}
		for(Gabion t : gabions){
			if(t.getGridPosition().equals(gc.getGridPosition())){
				Path.snap();
				return;
			}
		}
		
		GridItem gabion = Touch.getInstance().unClamp();
		this.addItem(gabion);
	}
	
	public void update(long timeElapsed){
		Iterator<GridItem> git = items.iterator();
		while(git.hasNext()){
			GridItem gi = git.next();
			if(gi.update(timeElapsed)){
				git.remove();
				trailItems.remove(gi);
			}
		}
		Iterator<Path> pit = paths.iterator();
		while(pit.hasNext()){
			Path p = pit.next();
			if(p.update(timeElapsed)){
				pit.remove();
			}
		}
		for(Path p : paths){
			p.update(timeElapsed);
		}
	}
	
	@Override
	public void draw(Graphics g){
		super.draw(g);
		for(GridItem gi : items){
			gi.draw(g);
		}
		for(Path p : paths){
			p.getGridItem().draw(g);
		}
	}
	
	public Collection<Tower> getTowers(){
		return towers;
	}

	public Collection<GridItem> getItems() {
		return items;
	}

	public void setItems(Collection<GridItem> items) {
		this.items = items;
	}

	public Collection<TrailItem> getTrailItems() {
		return trailItems;
	}

	public void setTrailItems(Collection<TrailItem> trailItems) {
		this.trailItems = trailItems;
	}

	public Collection<Gabion> getGabions() {
		return gabions;
	}

	public void setGabions(Collection<Gabion> gabions) {
		this.gabions = gabions;
	}

	public Collection<Path> getPaths() {
		return paths;
	}

	public void setPaths(Collection<Path> paths) {
		this.paths = paths;
	}

	public void setTowers(Collection<Tower> towers) {
		this.towers = towers;
	}
	
	

}
