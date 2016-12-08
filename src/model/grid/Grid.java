package model.grid;

import java.awt.Graphics;
import java.util.*;

import model.difficulty.Difficulty;
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

public class Grid extends Component {
	
	private Collection<TrailItem> trailItems;
	private Collection<Gabion> gabions;
	private Collection<Tower> towers;
	private Collection<Path> paths;
	private static Grid instance;
	private Board board;
	private PixelGrid pixelGrid;
	private Difficulty difficulty;
	private Collection<GridItem> toBeAdded;
	private Collection<GridItem> toBeRemoved;
	private Collection<Path> pathsToBeRemoved;
	private Collection<Path> pathsToBeAdded;
	
	public static Grid getInstance(){
		return instance;
	}
	
	public Grid(ComponentPosition topLeft, int width, int height) {
		this(topLeft, width, height, "board_text_files/grid.txt");
	}
	
	public Grid(int x, int y, int w, int l){
		this(new ComponentPosition(x, y), w, l);
	}
	
	public Grid(ComponentPosition topLeft, int width, int height, String testBoard) {
		super(topLeft, width, height);
		paths = new ArrayList<Path>();
		gabions = new ArrayList<Gabion>();
		towers = new ArrayList<Tower>();
		trailItems = new ArrayList<TrailItem>();
		instance = this;
		board = new Board(testBoard);
		pixelGrid = new PixelGrid(board);
		difficulty = new Difficulty();
		toBeAdded = new ArrayList<GridItem>();
		toBeRemoved = new HashSet<GridItem>();
		pathsToBeAdded = new ArrayList<Path>();
		pathsToBeRemoved = new HashSet<Path>();
	}
	
	public Grid(int x, int y, int w, int l, String testBoard){
		this(new ComponentPosition(x, y), w, l, testBoard);
	}
	
	public void addItem(GridItem gi){
		toBeAdded.add(gi);
	}
	
	public void removeItem(GridItem gi){
		toBeRemoved.add(gi);
	}
	
	private void addItems(){
		Iterator<GridItem> git = toBeAdded.iterator();
		while(git.hasNext()){
			GridItem gi = git.next();
			if(gi instanceof TrailItem){
				trailItems.add((TrailItem) gi);
				git.remove();
			} else if (gi instanceof Tower){
				towers.add((Tower) gi);
				git.remove();
			} else if (gi instanceof Gabion){
				gabions.add((Gabion) gi);
				git.remove();
			}
		}
	}
	
	private void removeItems(){
		Iterator<TrailItem> tit = trailItems.iterator();
		while(tit.hasNext()){
			TrailItem ti = tit.next();
			if(toBeRemoved.contains(ti)){
				tit.remove();
				toBeRemoved.remove(ti);
			}
		}
		Iterator<Tower> towit = towers.iterator();
		while(towit.hasNext()){
			Tower tow = towit.next();
			if(toBeRemoved.contains(tow)){
				towit.remove();
				toBeRemoved.remove(tow);
			}
		}
		Iterator<Gabion> git = gabions.iterator();
		while(git.hasNext()){
			Gabion g = git.next();
			if(toBeRemoved.contains(g)){
				git.remove();
				toBeRemoved.remove(g);
			}
		}
	}
	
	public void addPath(Path p){
		pathsToBeAdded.add(p);
	}
	
	public void removePath(Path toBeRemoved){
		pathsToBeRemoved.add(toBeRemoved);
	}
	
	public void addPaths(){
		paths.addAll(pathsToBeAdded);
		pathsToBeAdded.clear();
	}
	
	public void removePaths(){
		Iterator<Path> pit = paths.iterator();
		while(pit.hasNext()){
			Path p = pit.next();
			if(pathsToBeRemoved.contains(p)){
				pit.remove();
				pathsToBeRemoved.remove(p);
			}
		}
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
		gabion.setGridPosition(PixelGrid.getInstance().getGridPosition(gabion.getCoord()));
		gabion.setCoord(PixelGrid.getInstance().getCenter(gabion.getGridPosition()));
		this.addItem(gabion);
	}
	
	public void update(long timeElapsed){
		addItems();
		removeItems();
		addPaths();
		removePaths();
		Iterator<TrailItem> tit = trailItems.iterator();
		while(tit.hasNext()){
			TrailItem ti = tit.next();
			if(ti.update(timeElapsed)){
				removeItem(ti);
			}
		}
		Iterator<Tower> towit = towers.iterator();
		while(towit.hasNext()){
			Tower t = towit.next();
			t.update(timeElapsed);
		}
		Iterator<Path> pit = paths.iterator();
		while(pit.hasNext()){
			Path p = pit.next();
			if(p.update(timeElapsed)){
				pit.remove();
			}
		}
		difficulty.update(timeElapsed);
	}
	
	@Override
	public void draw(Graphics g){
		Collection<GridItem> items = new ArrayList<GridItem>();
		items.addAll(trailItems);
		items.addAll(gabions);
		items.addAll(towers);
		Collection<Path> pathSnapShot = new ArrayList<Path>();
		pathSnapShot.addAll(paths);
		//super.draw(g);
		for(GridItem gi : items){
			gi.draw(g);
		}
		for(Path p : pathSnapShot){
			p.getGridItem().draw(g);
		}
	}
	
	public Collection<Tower> getTowers(){
		return towers;
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
	
	public Difficulty getDifficulty(){
		return difficulty;
	}


}
