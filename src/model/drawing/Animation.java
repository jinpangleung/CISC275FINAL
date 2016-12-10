package model.drawing;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
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
 * @param imageLibrary - the image library that makes sure images don't get load in more than  once
 * @param RESIZE_X_PERCENT - resize ratio for images
 * @param RESIZE_Y_PERCENT - resize ratio for images
 * @param sprites - animations
 * @param length - length of the animation
 * @param index - the index for sprites
 * @param xOffset - off set value on x component
 * @param yOffset - off set value on y component
 * @param imageWidth - width of the image
 * @param imageHeight - height of the image
 * @param animationTime - how long the animation lasts
 * @param frameTime - how long each frame is
 * @param elapsedTime - the game time
 * @param completedCycle - boolean of whether the cycle for the animation is done
 *
 */

public class Animation implements Serializable{
	
	// Image Library
	private static HashMap<String, BufferedImage> imageLibrary;
	
	// Resizing
	private static final double RESIZE_X_PERCENT = 0.7;
	private static final double RESIZE_Y_PERCENT = 0.8;
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
	 * insertCloudItemImage
	 * Read cloud images from file, and resize it then insert it
	 * @param String fileName
	 * @param String imageName
	 * @return none
	 */
	public static void insertCloudImage(String fileName, String imageName){
		double x = Model.getInstance().getScreenWidth();
		double y = Model.getInstance().getScreenHeight() * 0.2;
	    BufferedImage img = readImageFromFile(fileName);
	    if(img == null){
	    	insertBufferedImage(null, imageName);
	    } else {
	    	insertBufferedImage(resize(img, (int) x, (int) y), imageName);
	    }
	   }
	
	/**
	 * insertRainItemImage
	 * Read rain images from file, and resize it then insert it
	 * @param String fileName
	 * @param String imageName
	 * @return none
	 */
	public static void insertRainImage(String fileName, String imageName){
		double x = Model.getInstance().getScreenWidth();
		double y = Model.getInstance().getScreenHeight() * 0.8;
	    BufferedImage img = readImageFromFile(fileName);
	    if(img == null){
	    	insertBufferedImage(null, imageName);
	    } else {
	    	insertBufferedImage(resize(img, (int) x, (int) y), imageName);
	    }
	}
	
	/**
	 * insertWaveItemImage
	 * Read wave images from file, and resize it then insert it
	 * @param String fileName
	 * @param String imageName
	 * @return none
	 */
	public static void insertWaveImage(String fileName, String imageName){
		double x = Model.getInstance().getScreenWidth();
		double y = Model.getInstance().getScreenHeight();
	    BufferedImage img = readImageFromFile(fileName);
	    if(img == null){
	    	insertBufferedImage(null, imageName);
	    } else {
	    	insertBufferedImage(resize(img, (int) x, (int) y), imageName);
	    }
	}
	
	/**
	 * insertTitleButtonImage
	 * Read title button images from file, and resize it then insert it
	 * @param String fileName
	 * @param String imageName
	 * @return none
	 */
	public static void insertTitleButtonImage(String fileName, String imageName){
		int x =(int) (Model.getInstance().getScreenWidth() * 0.3);
		int y =(int) (Model.getInstance().getScreenHeight() * 0.3);
		BufferedImage img = readImageFromFile(fileName);
	    if(img == null){
	    	insertBufferedImage(null, imageName);
	    } else {
	    	insertBufferedImage(resize(img, x, y), imageName);
	    }
	}
	
	/**
	 * insertReadyToGoButton
	 * Read ready button images from file, and resize it then insert it
	 * @param String fileName
	 * @param String imageName
	 * @return none
	 */
	public static void insertReadyToGoButton(String fileName, String imageName){
		int x = (int) (Model.getInstance().getScreenHeight() * 0.1);
		int y = (int) (Model.getInstance().getScreenHeight() * 0.1);
		BufferedImage img = readImageFromFile(fileName);
	    if(img == null){
	    	insertBufferedImage(null, imageName);
	    } else {
	    	insertBufferedImage(resize(img, x, y), imageName);
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
		insertTowerImage("images/ConcreteGabion.png", "ConcreteGabion");
		insertImage("images/arrow.png", "Arrow");
		insertScreenImage("images/Losing Screen.png", "LosingScreen");
		insertTowerImage("images/OysterGabion.png", "OysterGabion");
		insertTrailItemImage("images/invasive_item.png", "invasive_item");
		insertTrailItemImage("images/invasive_item_tower.png", "invasive_item_tower");
		insertTrailItemImage("images/larvae.png", "larvae");
		insertTrailItemImage("images/oyster.png", "oyster");
		insertTowerImage("images/oyster_tower.png", "oyster_tower");
		insertTrailItemImage("images/pollutant1.png", "pollutant1");
		insertTrailItemImage("images/pollutant2.png", "pollutant2");
		insertTrailItemImage("images/pollutant3.png", "pollutant3");
		insertTrailItemImage("images/pollutant4.png", "pollutant4");
		insertTowerImage("images/pollutant_tower1.png", "pollutant_tower1");
		insertTowerImage("images/pollutant_tower2.png", "pollutant_tower2");
		insertTowerImage("images/pollutant_tower3.png", "pollutant_tower3");
		insertTowerImage("images/pollutant_tower4.png", "pollutant_tower4");
		insertTowerImage("images/pollutant_tower5.png", "pollutant_tower5");
		insertTowerImage("images/pollutant_tower6.png", "pollutant_tower6");
		insertTowerImage("images/pollutant_tower7.png", "pollutant_tower7");
		insertScreenImage("images/bcg.png", "bcg");
		insertScreenImage("images/end.png", "end");
		insertTowerImage("images/BlueTowerIcon.png", "BlueTowerIcon");
		insertTowerImage("images/RedTowerIcon.png", "RedTowerIcon");
		insertTowerImage("images/GreenTowerIcon.png", "GreenTowerIcon");
		insertTitleButtonImage("images/NewPlayerButton.png", "NewPlayerButton");
		insertTitleButtonImage("images/ExperiencedPlayerButton.png", "ExperiencedPlayerButton");
		insertReadyToGoButton("images/play.png", "play");
		insertScreenImage("images/title.png", "title");
		for (int i=1; i<=34; i++){
			insertCloudImage("images/cloud" + Integer.toString(i) + ".png", "cloud" + Integer.toString(2*i - 1));
			insertCloudImage("images/cloud" + Integer.toString(i) + ".png", "cloud" + Integer.toString(2*i));
		}
		for (int i=16; i<=34; i++){
			int a = i + 19;
			int b = a + 19;
			int c = b + 19;
			int d = c + 19;
			//System.out.println(d);
			insertCloudImage("images/cloud" + Integer.toString(i) + ".png", "cloud" + Integer.toString(2*a - 1));
			insertCloudImage("images/cloud" + Integer.toString(i) + ".png", "cloud" + Integer.toString(2*a));
			insertCloudImage("images/cloud" + Integer.toString(i) + ".png", "cloud" + Integer.toString(2*b - 1));
			insertCloudImage("images/cloud" + Integer.toString(i) + ".png", "cloud" + Integer.toString(2*b));
			insertCloudImage("images/cloud" + Integer.toString(i) + ".png", "cloud" + Integer.toString(2*c - 1));
			insertCloudImage("images/cloud" + Integer.toString(i) + ".png", "cloud" + Integer.toString(2*c));
			insertCloudImage("images/cloud" + Integer.toString(i) + ".png", "cloud" + Integer.toString(2*d - 1));
			insertCloudImage("images/cloud" + Integer.toString(i) + ".png", "cloud" + Integer.toString(2*d));
		}
		for (int i=35; i<=71; i++){
			int a = i + 76;
			insertCloudImage("images/cloud" + Integer.toString(i) + ".png", "cloud" + Integer.toString(2*a - 1));
			insertCloudImage("images/cloud" + Integer.toString(i) + ".png", "cloud" + Integer.toString(2*a));
		}
		
//		insertCloudImage("images/cloud east0021.png", "cloud1");
//		insertCloudImage("images/cloud east0022.png", "cloud2");
//		insertCloudImage("images/cloud east0023.png", "cloud3");
//		insertCloudImage("images/cloud east0024.png", "cloud4");
//		insertCloudImage("images/cloud east0025.png", "cloud5");
//		insertCloudImage("images/cloud east0026.png", "cloud6");
//		insertCloudImage("images/cloud east0027.png", "cloud7");
//		insertCloudImage("images/cloud east0028.png", "cloud8");
//		insertCloudImage("images/cloud east0029.png", "cloud9");
//		insertCloudImage("images/cloud east0030.png", "cloud10");
//		insertCloudImage("images/cloud east0031.png", "cloud11");
//		insertCloudImage("images/cloud east0032.png", "cloud12");
//		insertCloudImage("images/cloud east0033.png", "cloud13");
//		insertCloudImage("images/cloud east34to18.png", "cloud14");
//		insertCloudImage("images/cloud east0018.png", "cloud15");
//		insertCloudImage("images/cloud east0019.png", "cloud16");
//		insertCloudImage("images/cloud east0020.png", "cloud17");
//		insertCloudImage("images/cloud east0002.png", "cloud18");
//		insertCloudImage("images/cloud east0003.png", "cloud19");
//		insertCloudImage("images/cloud east0004.png", "cloud20");
//		insertCloudImage("images/cloud east0005.png", "cloud21");
//		insertCloudImage("images/cloud east0006.png", "cloud22");
//		insertCloudImage("images/cloud east0007.png", "cloud23");
//		insertCloudImage("images/cloud east0008.png", "cloud24");
//		insertCloudImage("images/cloud east0009.png", "cloud25");
//		insertCloudImage("images/cloud east0010.png", "cloud26");
//		insertCloudImage("images/cloud east0011.png", "cloud27");
//		insertCloudImage("images/cloud east0012.png", "cloud28");
//		insertCloudImage("images/cloud east0013.png", "cloud29");
//		insertCloudImage("images/cloud east0014.png", "cloud30");
//		insertCloudImage("images/cloud east0015.png", "cloud31");
//		insertCloudImage("images/cloud east0016.png", "cloud32");
//		insertCloudImage("images/cloud east0017.png", "cloud33");
//		insertCloudImage("images/cloud east0018.png", "cloud34");
//		insertCloudImage("images/cloud east0019.png", "cloud35");
//		insertCloudImage("images/cloud east0020.png", "cloud36");
//		insertCloudImage("images/cloud east0002.png", "cloud37");
//		insertCloudImage("images/cloud east0003.png", "cloud38");
//		insertCloudImage("images/cloud east0004.png", "cloud39");
//		insertCloudImage("images/cloud east4to35.png", "cloud40");
//		insertCloudImage("images/cloud east0036.png", "cloud41");
//		insertCloudImage("images/cloud east0037.png", "cloud42");
//		insertCloudImage("images/cloud east0038.png", "cloud43");
//		insertCloudImage("images/cloud east0039.png", "cloud44");
//		insertCloudImage("images/cloud east0040.png", "cloud45");
//		insertCloudImage("images/cloud east0041.png", "cloud46");
//		insertCloudImage("images/cloud east0042.png", "cloud47");
//		insertCloudImage("images/cloud east0043.png", "cloud48");
//		insertCloudImage("images/cloud east0044.png", "cloud49");
//		insertCloudImage("images/cloud east0045.png", "cloud50");
//		insertCloudImage("images/cloud east0046.png", "cloud51");
//		insertCloudImage("images/cloud east0047.png", "cloud52");
		insertRainImage("images/rain01.png", "rain1");
		insertRainImage("images/rain02.png", "rain2");
		insertRainImage("images/rain03.png", "rain3");
		insertRainImage("images/rain04.png", "rain4");
		insertRainImage("images/rain05.png", "rain5");
		insertRainImage("images/rain06.png", "rain6");
		insertRainImage("images/rain07.png", "rain7");
		insertRainImage("images/rain08.png", "rain8");
		insertRainImage("images/rain09.png", "rain9");
		insertRainImage("images/rain10.png", "rain10");
		for (int i=1; i<=13; i++){
			insertWaveImage("images/wave" + Integer.toString(i) + ".png", "wave" + Integer.toString(i));
		}
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
	
	
	/**
	 * Constructor for animation
	 * @author Roy
	 * @param sprite
	 * @return none
	 *
	 */
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
	
	
	/**
	 * Constructor for animation
	 * @author Roy
	 * @param base
	 * @param num
	 *
	 */
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
	
	/**
	 * reverseUpdate()
	 * doesn't do anything here, would be overwritten in the sub classes
	 * @author Roy
	 * @param long elapsedTime
	 * @return none
	 *
	 */
	public void reverseUpdate(long elapsedTime){
		// Do nothing
	}
	
	/**
	 * draw
	 * draw the animation at the specified spot
	 * @author Roy
	 * @param Graphics g
	 * @param double x
	 * @param double y
	 * @return none
	 *
	 */
	public void draw(Graphics g, double x, double y){
		g.drawImage(Animation.getImage(sprites[index]), (int) x - xOffset, (int) y - yOffset, null);
	}
	
	/**
	 * draw
	 * draw the animation at the specified spot
	 * @author Roy
	 * @param Graphics g
	 * @param Coord c
	 * @return none
	 *
	 */
	public void draw(Graphics g, Coord c){
		this.draw(g, c.getX(), c.getY());
	}
	
	/**
	 * update
	 * keep updating the animation
	 * @author Roy
	 * @param Graphics g
	 * @param long elapsedTime
	 * @return none
	 *
	 */
	public void update(long elapsedTime){
		this.elapsedTime += elapsedTime;
		this.elapsedTime = this.elapsedTime % this.animationTime;
		long newIndex = this.elapsedTime / this.frameTime;
		this.index = (int) newIndex;
	}

	/**
	 * getSprites()
	 * gets the attributes sprites
	 * @author Roy
	 * @return sprites
	 *
	 */
	public String[] getSprites() {
		return sprites;
	}

	/**
	 * setSprites()
	 * sets the attributes sprites
	 * @author Roy
	 * @param sprites
	 * @return none
	 *
	 */
	public void setSprites(String[] sprites) {
		this.sprites = sprites;
	}

	/**
	 * getLength()
	 * gets the attributes length
	 * @author Roy
	 * @return length
	 *
	 */
	public int getLength() {
		return length;
	}
	
	/**
	 * setLength()
	 * sets the attributes length
	 * @author Roy
	 * @param length
	 * @return none
	 *
	 */
	public void setLength(int length) {
		this.length = length;
	}

	/**
	 * getIndex()
	 * gets the attributes index
	 * @author Roy
	 * @return index
	 *
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * setIndex()
	 * sets the attributes index
	 * @author Roy
	 * @param index
	 * @return none
	 *
	 */
	public void setIndex(int index) {
		this.index = index;
	}

	/**
	 * getxOffset()
	 * gets the attributes xOffset
	 * @author Roy
	 * @return xOffset
	 *
	 */
	public int getxOffset() {
		return xOffset;
	}
	
	/**
	 * setxOffset()
	 * sets the attributes xOffset
	 * @author Roy
	 * @param xOffset
	 * @return none
	 *
	 */
	public void setxOffset(int xOffset) {
		this.xOffset = xOffset;
	}
	
	/**
	 * getyOffset()
	 * gets the attributes yOffset
	 * @author Roy
	 * @return yOffset
	 *
	 */
	public int getyOffset() {
		return yOffset;
	}

	/**
	 * setyOffset()
	 * sets the attributes yOffset
	 * @author Roy
	 * @param yOffset
	 * @return none
	 *
	 */
	public void setyOffset(int yOffset) {
		this.yOffset = yOffset;
	}

	/**
	 * getImageWidth()
	 * gets the attributes imageWidth
	 * @author Roy
	 * @return imageWidth
	 *
	 */
	public int getImageWidth() {
		return imageWidth;
	}

	/**
	 * setImageWidth()
	 * sets the attributes imageWidth
	 * @author Roy
	 * @param imageWidth
	 * @return none
	 *
	 */
	public void setImageWidth(int imageWidth) {
		this.imageWidth = imageWidth;
	}

	/**
	 * getImageHeight()
	 * gets the attributes imageWidth
	 * @author Roy
	 * @return imageHeight
	 *
	 */
	public int getImageHeight() {
		return imageHeight;
	}
	
	/**
	 * setImageHeight()
	 * sets the attributes imageHeight
	 * @author Roy
	 * @param imageHeight
	 * @return none
	 *
	 */
	public void setImageHeight(int imageHeight) {
		this.imageHeight = imageHeight;
	}

	/**
	 * getAnimationTime()
	 * gets the attributes animationTime
	 * @author Roy
	 * @return animationTime
	 *
	 */
	public long getAnimationTime() {
		return animationTime;
	}

	/**
	 * setAnimationTime()
	 * sets the attributes animationTime
	 * @author Roy
	 * @param animationTime
	 * @return none
	 *
	 */
	public void setAnimationTime(long animationTime) {
		this.animationTime = animationTime;
	}

	/**
	 * getFrameTime()
	 * gets the attributes frameTime
	 * @author Roy
	 * @return frameTime
	 *
	 */
	public long getFrameTime() {
		return frameTime;
	}

	/**
	 * setFrameTime()
	 * sets the attributes frameTime
	 * @author Roy
	 * @param frameTime
	 * @return none
	 *
	 */
	public void setFrameTime(long frameTime) {
		this.frameTime = frameTime;
	}

	/**
	 * getElapsedTime()
	 * gets the attributes elapsedTime
	 * @author Roy
	 * @return elapsedTime
	 *
	 */
	public long getElapsedTime() {
		return elapsedTime;
	}

	/**
	 * setElapsedTime()
	 * sets the attributes elapsedTime
	 * @author Roy
	 * @param elapsedTime
	 * @return none
	 *
	 */
	public void setElapsedTime(long elapsedTime) {
		this.elapsedTime = elapsedTime;
	}
}