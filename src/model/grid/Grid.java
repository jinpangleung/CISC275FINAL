package model.grid;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.*;

import controller.Controller;
import model.Button;
import model.Model;
import model.Time;
import model.TutorialStep;
import model.difficulty.Difficulty;
import model.drawing.Animation;
import model.drawing.Coord;
import model.grid.gridcell.GridCell;
import model.grid.gridcell.GridPosition;
import model.grid.griditem.GridItem;
import model.grid.griditem.gabion.Gabion;
import model.grid.griditem.tower.Tower;
import model.grid.griditem.trailitem.TrailItem;
import model.gui.component.Component;
import model.gui.component.ComponentPosition;
import model.gui.path.Path;
import model.gui.touch.Touch;
import model.inventory.Inventory;
import model.inventory.factory.TowerFactory;
import model.player.Player;

public class Grid extends Component {
	
	private List<TrailItem> trailItems;
	private List<Gabion> gabions;
	private List<Tower> towers;
	private List<Path> paths;
	private static Grid instance;
	private Board board;
	private PixelGrid pixelGrid;
	private Difficulty difficulty;
	private Collection<GridItem> toBeAdded;
	private Collection<GridItem> toBeRemoved;
	private Collection<Path> pathsToBeRemoved;
	private Collection<Path> pathsToBeAdded;
	private TutorialStep step;
	private boolean readyToGo;
	private Button readyButton;
	
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
		step = TutorialStep.CLICK_TOWER;
		readyToGo = false;
	}
	
	public void initReadyButton(){
		int w = (int) (Model.getInstance().getScreenWidth() * 0.2);
		int h = (int) (Model.getInstance().getScreenHeight() * 0.2);
		int x = Model.getInstance().getScreenWidth() - w;
		int y = 0;
		readyButton = new ReadyButton(x, y, w, h);
	}
	
	public void ready(){
		readyToGo = true;
	}
	
	public void unReady(){
		readyToGo = false;
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
	
	public Button getReadyButton(){
		return readyButton;
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
				// If tutorial is running and player has placed item then call correct action
				if(Controller.isRunningTutorial()){
					if(step == TutorialStep.PLACE_ITEM){
						donePlaceItem();
					}
					else{
						donePlaceWrongItem();
					}
				}
					
				t.release(x, y);
				return;
			}
		}
		Path.snap();
	}
	
	public void placeTower(int x, int y){
		
		// Prevent placing more towers during tutorial
		if(Controller.isRunningTutorial() && towers.size() > 0){
			Path.snap();
			return;
		}
		
		GridCell gc = PixelGrid.getInstance().getGridCell(x, y);
		if(!gc.isCanPlaceTower()){
			
			// If tutorial is running and player is holding tower then call correct action
			if(Controller.isRunningTutorial() && step == TutorialStep.PLACE_TOWER){
				doneClickTowerUndo();
			}
			if(Controller.isRunningTutorial() && step == TutorialStep.PLACE_GABBION){
				doneClickGabbionUndo();
			}
			
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
		
		// If tutorial is running and player has placed tower then call correct action
		if(Controller.isRunningTutorial() && step == TutorialStep.PLACE_TOWER){
			donePlaceTower();
		}
	}
	
	public void placeGabion(int x, int y){
		//Prevent placing gabbion prematurely during tutorial
		// Prevent placing more towers during tutorial
		if(Controller.isRunningTutorial() && step != TutorialStep.PLACE_GABBION){
			Path.snap();
			return;
		}
		
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
		
		// If tutorial is running and player has placed tower then call correct action
		if(Controller.isRunningTutorial() && step == TutorialStep.PLACE_GABBION){
			donePlaceGabbion();
		}
	}
	
	public void update(long timeElapsed){
		addItems();
		removeItems();
		addPaths();
		removePaths();
		Iterator<TrailItem> tit = trailItems.iterator();
		if(readyToGo){
			while(tit.hasNext()){
				TrailItem ti = tit.next();
				if(ti.update(timeElapsed)){
					removeItem(ti);
				}
			}
		}
		Iterator<Tower> towit = towers.iterator();
		while(towit.hasNext()){
			Tower t = towit.next();
			t.update(timeElapsed);
		}
		Iterator<Gabion> gib = gabions.iterator();
		while(gib.hasNext()){
			Gabion g = gib.next();
			g.update(timeElapsed);
		}
		Iterator<Path> pit = paths.iterator();
		while(pit.hasNext()){
			Path p = pit.next();
			if(p.update(timeElapsed)){
				pit.remove();
			}
		}
		if(readyToGo){
			difficulty.update(timeElapsed);
		}
	}
	
	public boolean getReadyToGo(){
		return readyToGo;
	}
	
	// This method also contains checks for certain tutorial steps
	public void updateTutorial(long timeElapsed) {
		long originalTimeElapsed = timeElapsed;
		
		// Check if game is paused if so then only keep paths moving
		if(Controller.isPaused()){
			timeElapsed = 0;
		}
		// Same as update() but without updating difficulty, which stops spawning
		addItems();
		removeItems();
		addPaths();
		removePaths();
		Iterator<TrailItem> tit = trailItems.iterator();
		while(tit.hasNext()){
			TrailItem ti = tit.next();
			if(ti.update(timeElapsed)){
				Difficulty.collect(ti);
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
			if(p.update(originalTimeElapsed)){
				pit.remove();
			}
		}
		
		// Special update instructions
		switch(step){
		case CLICK_ITEM:
			if(!towers.isEmpty() && !trailItems.isEmpty()){
				// At this step only one item so index 0
				Tower tower = towers.get(0);
				Coord itemCoord = trailItems.get(0).getCoord();
				Coord modifedCoord = new Coord(itemCoord.getX() - 10, itemCoord.getY());
				if(tower.isInRange(modifedCoord)){
					Controller.setPaused(true);
					step = TutorialStep.PLACE_ITEM;
				}
			}
			break;
		case CLICK_WRONG_ITEM:
			if(!towers.isEmpty() && !trailItems.isEmpty()){
				// At this step only one item so index 0
				Tower tower = towers.get(0);
				Coord itemCoord = trailItems.get(0).getCoord();
				Coord modifedCoord = new Coord(itemCoord.getX() - 10, itemCoord.getY());
				if(tower.isInRange(modifedCoord)){
					Controller.setPaused(true);
					step = TutorialStep.PLACE_WRONG_ITEM;
				}
			}
			break;
		}
	}
	
	public void doneClickTower(){
		System.out.println("Tower was clicked");
		step = TutorialStep.PLACE_TOWER;
	}
	
	public void doneClickTowerUndo() {
		System.out.println("Tower was unclicked.");
		step = TutorialStep.CLICK_TOWER;
	}
	
	public void donePlaceTower(){
		System.out.println("Tower was placed.");
		step = TutorialStep.SPAWN_ITEM;
		Controller.setPaused(false);
		difficulty.spawnTutorial(step);
		doneSpawnItem();
	}
	
	public void doneSpawnItem(){
		System.out.println("Item was spawned");
		step = TutorialStep.CLICK_ITEM;
	}
	
	public void donePlaceItem(){
		System.out.println("Item was placed in tower");
		step = TutorialStep.SPAWN_WRONG_ITEM;
		Controller.setPaused(false);
		difficulty.spawnTutorial(step);
		doneSpawnWrongItem();
	}
	
	public void doneSpawnWrongItem(){
		System.out.println("Wrong Item was spawned");
		step = TutorialStep.CLICK_WRONG_ITEM;
	}
	
	public void donePlaceWrongItem(){
		System.out.println("Wrong Item was placed in tower");
		// Drastic decrease in health to show effect
		Player.getInstance().decreaseHappiness(25);
		step = TutorialStep.CLICK_GABBION;
	}
	
	public void doneClickGabbion(){
		System.out.println("Gabbion was clicked");
		step = TutorialStep.PLACE_GABBION;
	}
	
	public void doneClickGabbionUndo() {
		System.out.println("Gabbion was unclicked.");
		step = TutorialStep.CLICK_GABBION;
	}
	
	public void donePlaceGabbion(){
		System.out.println("Gabbion was placed.");
		step = TutorialStep.DONE;
		Controller.setPaused(false);
		doneDone();
	}
	
	public void doneDone(){
		System.out.println("Tutorial is done continue game.");
		// Restore happiness
		Player.getInstance().increaseHappiness(25);
		Controller.setRunningTutorial(false);
		unReady();
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
		
		
		int leftX = this.getTopLeft().getX();
		int leftY = this.getBottomRight().getY();
		leftX = (int) (leftX + this.getWidth() * .4);
		leftY = (int) (leftY - this.getHeight() * .9);
		int boxLength = (int) (this.getWidth() * .5);
		int boxHeight = (int) (this.getHeight() * .1);
		int arrowX = -500;
		int arrowY = -500;
		int degrees = 0;
		double scaleX = 1;
		double scaleY = 1;
		
		String message = "";
		boolean draw = true;
		boolean isHorizontal = false;
		
		if(Controller.isRunningTutorial()){
			// Draw stuff for specific steps
			switch(step){
			case CLICK_GABBION:
				message = "Gabions are used to stop the storm from hurting your towers.";
				TowerFactory gabFactory = Inventory.getInstance().getCgf();
				arrowX = (int) (gabFactory.getTopLeft().getX() - (gabFactory.getWidth() * 1.5));
				arrowY = (int) (gabFactory.getBottomRight().getY() - (gabFactory.getHeight()));
				scaleX = .6;
				scaleY = .3;
				degrees = 90;
				isHorizontal = true;
				break;
			case CLICK_ITEM:
				message = "Items can only be dragged within the tower's range.";
				break;
			case CLICK_TOWER:
				message = "Welcome to our tutorial!";
				TowerFactory redFactory = Inventory.getInstance().getRtf();
				arrowX = (int) (redFactory.getTopLeft().getX() - (redFactory.getWidth() * 1.5));
				arrowY = (int) (redFactory.getBottomRight().getY() - (redFactory.getHeight()));
				scaleX = .6;
				scaleY = .3;
				degrees = 90;
				isHorizontal = true;
				break;
			case CLICK_WRONG_ITEM:
				draw = false;
				break;
			case DONE:
				draw = false;
				break;
			case PLACE_GABBION:
				message = "Place the gabion and the game will start!";
				
				int width = Board.getInstance().getWidth();
				int height = Board.getInstance().getHeight();
				double squareWidth = PixelGrid.getInstance().getSquareWidth();
				double squareHeight = PixelGrid.getInstance().getSquareHeight();
				int smallWidth = (int)( squareWidth * 0.7);
				int smallHeight = (int) (squareHeight * 0.7);
				GridCell gc = Board.getInstance().getGridCell(Math.floorDiv(width, 3), height - 1);
				Coord center = PixelGrid.getInstance().getCenter(gc.getGridPosition());
				int centerX = center.getX().intValue();
				int centerY = center.getY().intValue();
				
				arrowX = centerX - (smallWidth / 2);
				arrowY = centerY - 4 * smallHeight;
				scaleX = .3;
				scaleY = .6;
				degrees = 180;
				break;
			case PLACE_ITEM:
				message = "Items must be dragged to the correct tower to earn points.";
				if(!trailItems.isEmpty()){
					Coord itemCoord = trailItems.get(0).getCoord();
					arrowX = itemCoord.getX().intValue();
					arrowY = itemCoord.getY().intValue();
					scaleX = .3;
					scaleY = .6;
				}
				break;
			case PLACE_TOWER:
				message = "Strategic tower placing will help you win.";
				
				double squareWidth2 = PixelGrid.getInstance().getSquareWidth();
				double squareHeight2 = PixelGrid.getInstance().getSquareHeight();
				int smallWidth2 = (int)( squareWidth2 * 0.7);
				int smallHeight2 = (int) (squareHeight2 * 0.7);
				Coord center2 = PixelGrid.getInstance().getCenter(new GridPosition(5,5));
				int center2X = center2.getX().intValue();
				int center2Y = center2.getY().intValue();
				
				arrowX = center2X - (smallWidth2 / 2);
				arrowY = center2Y + smallHeight2;
				scaleX = .3;
				scaleY = .6;
				break;
			case PLACE_WRONG_ITEM:
				message = "Dragging an item to the wrong tower loses you points.";
				if(!trailItems.isEmpty()){
					Coord itemCoord = trailItems.get(0).getCoord();
					arrowX = (int) Math.round(itemCoord.getX());
					arrowY = (int) Math.round(itemCoord.getY());
					scaleX = .3;
					scaleY = .6;
				}
				break;
			case SPAWN_ITEM:
				draw = false;
				break;
			case SPAWN_WRONG_ITEM:
				draw = false;
				break;
			}
			
			if(draw){
				g.setColor(Color.WHITE);
				g.fillRect(leftX, leftY, boxLength, boxHeight);
				g.setColor(Color.BLACK);
				g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
				g.drawString(message,
					(int) (leftX + (boxLength * .1)),
					(int) (leftY + (boxHeight * .5) + 8));
				BufferedImage arrow = Animation.getImage("Arrow");
				
				
				// Scale arrow
				BufferedImage scaledArrow;
				AffineTransform at = new AffineTransform();
				if(isHorizontal){
					scaledArrow = new BufferedImage(arrow.getHeight(), arrow.getWidth(), BufferedImage.TYPE_INT_ARGB);	
				}
				else{
					scaledArrow = new BufferedImage(arrow.getWidth(), arrow.getHeight(), BufferedImage.TYPE_INT_ARGB);
				}
				
				at.scale(scaleX, scaleY);
				at.rotate(Math.toRadians(degrees), arrow.getWidth()/2, arrow.getHeight()/2);
				AffineTransformOp scaleOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
				scaledArrow = scaleOp.filter(arrow, scaledArrow);
				
				g.drawImage(scaledArrow, arrowX, arrowY, null);
			}
		}
		if(!readyToGo){
			readyButton.draw(g);
		}
	}
	
	public List<Tower> getTowers(){
		return towers;
	}

	public List<TrailItem> getTrailItems() {
		return trailItems;
	}

	public void setTrailItems(List<TrailItem> trailItems) {
		this.trailItems = trailItems;
	}

	public List<Gabion> getGabions() {
		return gabions;
	}

	public void setGabions(List<Gabion> gabions) {
		this.gabions = gabions;
	}

	public List<Path> getPaths() {
		return paths;
	}

	public void setPaths(List<Path> paths) {
		this.paths = paths;
	}

	public void setTowers(List<Tower> towers) {
		this.towers = towers;
	}
	
	public Difficulty getDifficulty(){
		return difficulty;
	}

	public TutorialStep getStep() {
		return step;
	}

}
