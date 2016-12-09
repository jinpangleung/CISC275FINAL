package tests.gui.component;

import static org.junit.Assert.*;

import org.junit.Test;

import model.gui.component.*;

public class DefaultComponentTest {

	@Test
	public void test() {
		Component c1 = new DefaultComponent(10, 10, 100, 100);
		assertEquals(c1.getTopLeft().getX(), Integer.valueOf(10));
		assertEquals(c1.getTopLeft().getY(), Integer.valueOf(10));
		assertEquals(c1.getBottomRight().getX(), Integer.valueOf(109));
		assertEquals(c1.getBottomRight().getX(), Integer.valueOf(109));
		
		c1.mouseClicked(0, 0); // Shouldn't do anything
		c1.mouseClicked(1000, 1000); // Shouldn't do anything
		
		c1.mouseReleased(0, 0); // Touch should be snapped
		c1.mouseReleased(1000, 1000); // Touch should be snapped
		assertEquals(c1.getTopLeft().getX(), Integer.valueOf(10));
		assertEquals(c1.getTopLeft().getY(), Integer.valueOf(10));
		assertEquals(c1.getBottomRight().getX(), Integer.valueOf(109));
		assertEquals(c1.getBottomRight().getX(), Integer.valueOf(109));
		
		assertEquals(100, c1.getWidth());
		assertEquals(100, c1.getHeight());
	}

}
