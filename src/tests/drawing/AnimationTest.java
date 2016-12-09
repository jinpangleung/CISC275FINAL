package tests.drawing;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;

import org.junit.Test;
import model.drawing.Animation;
import model.grid.Grid;
import model.gui.touch.DefaultTouchObject;

public class AnimationTest {
	
	@Test
	public void testUpdate(){
		Animation.initialize();
		Animation a1 = new Animation("null");
		assertEquals(a1.getImageWidth(), 100);
		assertEquals(a1.getImageHeight(), 100);
		assertEquals(a1.getxOffset(), 50);
		assertEquals(a1.getyOffset(), 50);
		assertEquals(a1.getAnimationTime(), 33333333L);
		assertEquals(a1.getFrameTime(), 33333333L);
		assertEquals(a1.getElapsedTime(), 0);
		assertEquals(a1.getIndex(), 0);
		
		a1.update(33333332L);
		assertEquals(a1.getAnimationTime(), 33333333L);
		assertEquals(a1.getFrameTime(), 33333333L);
		assertEquals(a1.getElapsedTime(), 33333332L);
		assertEquals(a1.getIndex(), 0);
		
		a1.update(1L);
		assertEquals(a1.getAnimationTime(), 33333333L);
		assertEquals(a1.getFrameTime(), 33333333L);
		assertEquals(a1.getElapsedTime(), 0L);
		assertEquals(a1.getIndex(), 0);
	}
	
	@Test
	public void testDefaultTouchObject(){
		Animation.initialize();
		DefaultTouchObject to = new DefaultTouchObject();
		assertEquals(to.getAnimation().getImageWidth(), 100);
		assertEquals(to.getAnimation().getImageHeight(), 100);
		assertEquals(to.getAnimation().getxOffset(), 50);
		assertEquals(to.getAnimation().getyOffset(), 50);
		assertEquals(to.getAnimation().getAnimationTime(), 33333333L);
		assertEquals(to.getAnimation().getFrameTime(), 33333333L);
		assertEquals(to.getAnimation().getElapsedTime(), 0);
		assertEquals(to.getAnimation().getIndex(), 0);
		Animation.getImage("null");
	}
	
	@Test
	public void testImageLoadingAndResizing(){
		new Grid(0, 0, 1000, 1000);
		Animation.initialize();
		
		BufferedImage image1 = Animation.readImageFromFile("images/test1.png");
		assertEquals(100, image1.getWidth());
		assertEquals(100, image1.getHeight());
		
		Animation.insertBufferedImage(image1, "test1");
		BufferedImage test1 = Animation.getImage("test1");
		assertEquals(100, test1.getWidth());
		assertEquals(100, test1.getHeight());
		
		BufferedImage resize1 = Animation.resize(test1, 10, 10);
		assertEquals(10, resize1.getWidth());
		assertEquals(10, resize1.getHeight());
	}

}
