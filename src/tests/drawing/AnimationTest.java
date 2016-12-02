package tests.drawing;

import static org.junit.Assert.*;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Test;

public class AnimationTest {

	@Test
	public void test() {
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("images/EH1.png"));
		} catch (IOException e) {
			System.out.println(" failed");
		}
		int originalWidth = img.getWidth();
		int originalHeight = img.getHeight();
		System.out.println(originalWidth);
		System.out.println(originalHeight);
		BufferedImage newImg = resize(img, 1, 1);
		System.out.println(newImg.getWidth());
		System.out.println(newImg.getHeight());
		assertEqual(1, newImg.getWidth(), 1);
		assertEqual(1, newImg.getHeight(), 1);
	}
	
	private void assertEqual(int i, int width, int j) {
		// TODO Auto-generated method stub
		
	}

	public static BufferedImage resize(BufferedImage img, int newW, int newH) { 
	    Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
	    BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

	    Graphics2D g2d = dimg.createGraphics();
	    g2d.drawImage(tmp, 0, 0, null);
	    g2d.dispose();

	    return dimg;
	} 

}
