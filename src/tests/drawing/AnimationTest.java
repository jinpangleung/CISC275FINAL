package tests.drawing;

import static org.junit.Assert.*;
import org.junit.Test;
import model.drawing.Animation;

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

}
