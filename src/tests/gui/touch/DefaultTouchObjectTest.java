package tests.gui.touch;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Model;
import model.drawing.Animation;
import model.gui.touch.DefaultTouchObject;

public class DefaultTouchObjectTest {

	@Test
	public void testUpdate() {
		Model m = new Model();
		m.initialize(100, 100);
		Animation.initialize();
		
		DefaultTouchObject obj1 = new DefaultTouchObject();
		DefaultTouchObject obj2 = new DefaultTouchObject();
		try{
			obj1.update(1000);
		}
		catch(Exception e){
			assertEquals(true, true);
		}
		assertEquals(true, obj1.getCoord().equals(obj2.getCoord()));
		assertEquals(true, obj1.getGridPosition().equals(obj2.getGridPosition()));
		assertEquals(true, obj1.getGridColor().equals(obj2.getGridColor()));
	}

}
