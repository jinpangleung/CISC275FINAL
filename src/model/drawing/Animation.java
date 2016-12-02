package model.drawing;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import model.Time;

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
	
	/**
	 * insertImage
	 * Open image at given file name, load into ImageLibrary with given image name
	 * 
	 * @param String fileName
	 * @param String imageName
	 */
	private static void insertImage(String fileName, String imageName){
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File(fileName));
		    imageLibrary.put(imageName, img);
		} catch (IOException e) {
			System.out.println(fileName + " failed to load\n" + imageName + " set to null.");
			imageLibrary.put(imageName, null);
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
	private String[] sprites;
	private int length;
	private int index;
	private int xOffset;
	private int yOffset;
	private int imageWidth;
	private int imageHeight;
	private long animationTime;
	private long frameTime;
	private long elapsedTime;
	
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
	}
	
	// Draw Image at curent index
	public void draw(Graphics g, double x, double y){
		g.drawImage(Animation.getImage(sprites[index]), (int) x + xOffset, (int) y + yOffset, null);
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
