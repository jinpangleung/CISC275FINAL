package model.drawing;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import model.Model;
import model.Time;
import model.grid.PixelGrid;

/**
 * Animation
 * Animation is how a DrawableObject should draw itself frame-by-frame
 * <p>
 * All DrawableObjects needs an animation
 * @author Eric
 *
 */

public class Animation {
	
	// Image Library
	private static HashMap<String, BufferedImage> imageLibrary;
	
	// Resizing
	private static final double RESIZE_X_PERCENT = 0.7;
	private static final double RESIZE_Y_PERCENT = 0.8;
	
	/**
	 * readImageFromFile
	 * Read a BufferedImage from a file
	 * @param String fileName
	 * @return BufferedImage from given file
	 */
	public static BufferedImage readImageFromFile(String fileName){
		try {
		    BufferedImage img = ImageIO.read(new File(fileName));
		    return img;
		} catch (IOException e) {
			System.out.println(fileName + " failed to load\n" + "Returning null.");
			return null;
		}
	}
	
	/**
	 * insertImage
	 * Open image at given file name, load into ImageLibrary with given image name
	 * 
	 * @param String fileName
	 * @param String imageName
	 */
	public static void insertImage(String fileName, String imageName){
		insertBufferedImage(readImageFromFile(fileName), imageName);
	}
	
	/**
	 * insertBufferedImage
	 * 
	 * Insert BufferedImage With Given Name
	 * @param BufferedImage bi
	 * @param String imageName
	 */
	public static void insertBufferedImage(BufferedImage bi, String imageName){
		imageLibrary.put(imageName, bi);
	}
	
	/**
	 * resize
	 * 
	 * Resize given BufferedImage to desired pixel width and height
	 * 
	 * @param BufferedImage img
	 * @param int newW
	 * @param int newH
	 * @return resize buffered image
	 */
	public static BufferedImage resize(BufferedImage img, int newW, int newH) { 
	    Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
	    BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

	    Graphics2D g2d = dimg.createGraphics();
	    g2d.drawImage(tmp, 0, 0, null);
	    g2d.dispose();

	    return dimg;
	} 
	
	/**
	 * insertScreenImage
	 * Image should be loaded from file, then resized to the size of the entire screen
	 * 
	 * @param String fileName
	 * @param String imageName
	 */
	public static void insertScreenImage(String fileName, String imageName){
		int x = Model.getInstance().getScreenWidth();
		int y = Model.getInstance().getScreenHeight();
	    BufferedImage img = readImageFromFile(fileName);
	    if(img == null){
	    	insertBufferedImage(null, imageName);
	    } else {
	    	insertBufferedImage(resize(img, x, y), imageName);
	    }
	}
	
	/**
	 * insertTowerImage
	 * Read image from file, then resize it to be slightly larger than a trail item
	 * @param String fileName
	 * @param String imageName
	 */
	public static void insertTowerImage(String fileName, String imageName){
		double x = PixelGrid.getInstance().getSquareWidth();
		double y = PixelGrid.getInstance().getSquareHeight();
	    BufferedImage img = readImageFromFile(fileName);
	    if(img == null){
	    	insertBufferedImage(null, imageName);
	    } else {
	    	insertBufferedImage(resize(img, (int) x, (int) y), imageName);
	    }
	}
	
	/**
	 * insertTrailItemImage
	 * Read image from file, the resize to be slightly smaller than a square
	 * @param String fileName
	 * @param String imageName
	 */
	public static void insertTrailItemImage(String fileName, String imageName){
		double x = PixelGrid.getInstance().getSquareWidth();
		double y = PixelGrid.getInstance().getSquareHeight();
		x = x * RESIZE_X_PERCENT;
		y = y * RESIZE_Y_PERCENT;
		BufferedImage img = readImageFromFile(fileName);
		if(img == null){
			insertBufferedImage(null, imageName);
		} else {
			insertBufferedImage(resize(img, (int) x, (int) y), imageName);
		}
	}
	
	/**
	 * initialize
	 * Load all images into image library 
	 */
	public static void initialize(){
		imageLibrary = new HashMap<String, BufferedImage>();
		// Load all required images
		insertImage("images/null.png", "null");
		insertScreenImage("images/test1.png", "bcg");
		insertTowerImage("images/ConcreteGabion.png", "ConcreteGabion");
		insertImage("images/EH1.png", "EH1");
		insertImage("images/EH2.png", "EH2");
		insertImage("images/EH3.png", "EH3");
		insertImage("images/EH4.png", "EH4");
		insertTowerImage("images/Fisherman.png", "Fisherman");
		insertImage("images/Losing Screen.png", "LosingScreen");
		insertTowerImage("images/OysterGabion.png", "OysterGabion");
		insertImage("images/Rain.png", "rain");
		insertImage("images/Storm.png", "storm");
		insertScreenImage("images/background.png", "background");
		insertTrailItemImage("images/invasive_item.png", "invasive_item");
		insertTrailItemImage("images/invasive_item_tower.png", "invasive_item_tower");
		insertTrailItemImage("images/larvae.png", "larvae");
		insertTrailItemImage("images/oyster.png", "oyster");
		insertTowerImage("images/oyster_tower.png", "oyster_tower");
		insertTrailItemImage("images/pollutant1.png", "pollutant1");
		insertTrailItemImage("images/pollutant2.png", "pollutant2");
		insertTrailItemImage("images/pollutant3.png", "pollutant3");
		insertTrailItemImage("images/pollutant4.png", "pollutant4");
		insertTowerImage("images/pollutant_tower.png", "pollutant_tower");
		insertScreenImage("images/bcg.png", "bcg");
	}
	
	/**
	 * getImage
	 * Return the image that is stored under given name
	 * 
	 * @param String imageName
	 * @return BufferedImage
	 */
	public static BufferedImage getImage(String imageName){
		BufferedImage img = imageLibrary.get(imageName);
		if(img == null){
			insertImage("images/null.png", imageName);
			return getImage(imageName);
		} else {
			return img;
		}
	}
	
	
	// Animation
	
	// Unless otherwise specified, how often should the frame of the animation be switched
	private static final int DESIRED_FPS = 30;
	
	// Attributes
	protected String[] sprites;
	protected int length;
	protected int index;
	protected int xOffset;
	protected int yOffset;
	protected int imageWidth;
	protected int imageHeight;
	protected long animationTime;
	protected long frameTime;
	protected long elapsedTime;
	protected boolean completedCycle;
	
	// Constructor
	public Animation(String sprite){
		this.sprites = new String[1];
		this.sprites[0] = sprite;
		this.index = 0;
		this.length = 1;
		BufferedImage img = getImage(sprite);
		this.imageWidth = img.getWidth();
		this.imageHeight = img.getHeight();
		this.xOffset = this.imageWidth / 2;
		this.yOffset = this.imageHeight / 2;
		double secondsOneCycle = 1.0 / ((double) DESIRED_FPS / (double) this.length);
		double nanoOneCycle = secondsOneCycle * Time.nanosecond;
		this.animationTime = (long) nanoOneCycle;
		this.frameTime = (long) (nanoOneCycle / (double) this.length);
		this.elapsedTime = 0;
		this.completedCycle = false;
	}
	
	public Animation(String base, int num){
		this.sprites = new String[num];
		for(int i = 0; i < num; i++){
			this.sprites[i] = base + Integer.toString(i+1);
		}
		this.index = 0;
		this.length = num;
		BufferedImage img = getImage(this.sprites[1]);
		this.imageWidth = img.getWidth();
		this.imageHeight = img.getHeight();
		this.xOffset = this.imageWidth / 2;
		this.yOffset = this.imageHeight / 2;
		double secondsOneCycle = 1.0 / ((double) DESIRED_FPS / (double) this.length);
		double nanoOneCycle = secondsOneCycle * Time.nanosecond;
		this.animationTime = (long) nanoOneCycle;
		this.frameTime = (long) (nanoOneCycle / (double) this.length);
		this.elapsedTime = 0;
		this.completedCycle = false;
	}
	
	public void reverseUpdate(long elapsedTime){
		// Do nothing
	}
	
	// Draw Image at current index
	public void draw(Graphics g, double x, double y){
		g.drawImage(Animation.getImage(sprites[index]), (int) x - xOffset, (int) y - yOffset, null);
	}
	
	// Draw Image given coord
	public void draw(Graphics g, Coord c){
		this.draw(g, c.getX(), c.getY());
	}
	
	// Update the index based on how much time has passed
	public void update(long elapsedTime){
		this.elapsedTime += elapsedTime;
		this.elapsedTime = this.elapsedTime % this.animationTime;
		long newIndex = this.elapsedTime / this.frameTime;
		this.index = (int) newIndex;
	}

	public String[] getSprites() {
		return sprites;
	}

	public void setSprites(String[] sprites) {
		this.sprites = sprites;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getxOffset() {
		return xOffset;
	}

	public void setxOffset(int xOffset) {
		this.xOffset = xOffset;
	}

	public int getyOffset() {
		return yOffset;
	}

	public void setyOffset(int yOffset) {
		this.yOffset = yOffset;
	}

	public int getImageWidth() {
		return imageWidth;
	}

	public void setImageWidth(int imageWidth) {
		this.imageWidth = imageWidth;
	}

	public int getImageHeight() {
		return imageHeight;
	}

	public void setImageHeight(int imageHeight) {
		this.imageHeight = imageHeight;
	}

	public long getAnimationTime() {
		return animationTime;
	}

	public void setAnimationTime(long animationTime) {
		this.animationTime = animationTime;
	}

	public long getFrameTime() {
		return frameTime;
	}

	public void setFrameTime(long frameTime) {
		this.frameTime = frameTime;
	}

	public long getElapsedTime() {
		return elapsedTime;
	}

	public void setElapsedTime(long elapsedTime) {
		this.elapsedTime = elapsedTime;
	}
}