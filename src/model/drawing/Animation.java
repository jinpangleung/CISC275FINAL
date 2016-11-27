package model.drawing;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import javax.imageio.ImageIO;

/**
 * Animation
 * Animation keeps an array of strings and uses an underlying image library to draw
 * Animation also keeps an index to know which to draw
 * 
 * @author Eric
 *
 */

public class Animation {
	
	private static HashMap<String, BufferedImage> imageLibrary = new HashMap<String, BufferedImage>();
	
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
	
	public static void initialize(){
		insertImage("images/ConcreteGabion.png", "ConcreteGabion");
		insertImage("images/EH1.png", "EH1");
		insertImage("images/EH2.png", "EH2");
		insertImage("images/EH3.png", "EH3");
		insertImage("images/EH4.png", "EH4");
		insertImage("images/Fisherman.png", "Fisherman");
		insertImage("images/Losing Screen.png", "LosingScreen");
		insertImage("images/OysterGabion.png", "OysterGabion");
		insertImage("images/Rain.png", "rain");
		insertImage("images/Storm.png", "storm");
		insertImage("images/background.png", "background");
		insertImage("images/invasive_item.png", "invasive_item");
		insertImage("images/invasive_item_tower.png", "invasive_item_tower");
		insertImage("images/larvae.png", "larvae");
		insertImage("images/oyster.png", "oyster");
		insertImage("images/oyster_tower.png", "oyster_tower");
		insertImage("images/pollutant1.png", "pollutant1");
		insertImage("images/pollutant2.png", "pollutant2");
		insertImage("images/pollutant3.png", "pollutant3");
		insertImage("images/pollutant4.png", "pollutant4");
		insertImage("images/pollutant_tower.png", "pollutant_tower");
		insertImage("images/bcg.png", "bcg");
		
	}
	
	public static BufferedImage getImage(String imageName){
		return imageLibrary.get(imageName);
	}
	
	private String[] sprites;
	private int length;
	private int index;
	private int xOffset;
	private int yOffset;
	private int imageWidth;
	private int imageHeight;
	
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

	public Animation(){
		sprites = new String[1];
		sprites[0] = "null"; //This may need to get changed to work correctly
		this.length = 1;
		this.index = 0;
		this.xOffset = 0;
		this.yOffset = 0;
		this.imageWidth = 0;
		this.imageHeight = 0;
	}
	
	public Animation(String sprite, int xOffset, int yOffset){
		this.sprites = new String[1];
		this.sprites[0] = sprite;
		this.index = 0;
		this.length = 1;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		BufferedImage img = getImage(sprite);
		this.imageWidth = img.getWidth();
		this.imageHeight = img.getHeight();
	}
	
	// Create a new Animation using generic offsets
	public Animation(String sprite, Offset xOff, Offset yOff){
		this.sprites = new String[1];
		this.sprites[0] = sprite;
		this.index = 0;
		this.length = 1;
		BufferedImage img = getImage(sprite);
		this.imageWidth = img.getWidth();
		this.imageHeight = img.getHeight();
		switch(xOff){
		case CENTER: this.xOffset = imageWidth / 2; break;
		}
		switch(yOff){
		case CENTER: this.yOffset = imageHeight / 2; break;
		}
	}
	
	private boolean draw(Graphics g, int x, int y){
		int currentIndex = index;
		index = (index + 1) % length;
		BufferedImage image = getImage(sprites[currentIndex]);
		int drawX = x - xOffset;
		int drawY = y - yOffset;
		g.drawImage(image, drawX, drawY, null);
		return index == 0;
	}
	
	public boolean draw(Graphics g, Coord coord){
		return draw(g, (int) coord.getX(), (int) coord.getY());
	}
	
	// Draw without updating the animation index
	private void drawStatic(Graphics g, int x, int y){
		BufferedImage image = getImage(sprites[index]);
		int drawX = x - xOffset;
		int drawY = y - xOffset;
		g.drawImage(image, drawX, drawY, null);
	}
	
	public void drawStatic(Graphics g, Coord coord){
		drawStatic(g, (int) coord.getX(), (int) coord.getY());
	}
	
	/* Example String
	 * [oyster_1, oyster_2, oyster_3, oyster_4]
	 * Length : 4
	 * Current Index : 2 : oyster_3
	 * Size : 128x128
	 * Offset : 63, 63
	 */
	public String toString(){
		String str = "";
		str += Arrays.toString(sprites);
		str += "\nLength : " + Integer.toString(length);
		str += "\nCurrent Index : " + Integer.toString(index) + " : " + sprites[index];
		str += "\nSize : " + Integer.toString(imageWidth) + "x" + Integer.toString(imageHeight);
		str += "\nOffset : " + Integer.toString(xOffset) + ", " + Integer.toString(yOffset);
		return str;
	}

}
