package tests.drawing;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import controller.Controller;
import model.Model;
import model.drawing.Animation;
import model.drawing.LockedAnimation;
import model.grid.PixelGrid;
import model.grid.gridcell.GridPosition;
import model.grid.griditem.GridColor;
import view.View;

public class LockedAnimationTest {

	LockedAnimation anim;
	
	@Before
	public void setup(){
		Model m = new Model();
		m.initialize(100, 100);
		Animation.initialize();
		anim = new LockedAnimation("pollutant_tower", 7);
	}
	
	@Test
	public void testUpdate() {
		// Test case 1
		int newIndex = anim.getLength() - 1; 
		anim.setIndex(newIndex);
		anim.setElapsedTime(500000);
		anim.update(1000);
		assertEquals(500000, anim.getElapsedTime());
		assertEquals(newIndex, anim.getIndex());
		
		// Test case 2 with if
		anim.setIndex(7);
		anim.setAnimationTime(500);
		anim.setElapsedTime(0);
		anim.update(1000);
		assertEquals(0, anim.getElapsedTime());
		assertEquals(0, anim.getIndex());
		
		// Test case 2 without if
		anim.setIndex(7);
		anim.setAnimationTime(10000);
		anim.setElapsedTime(0);
		anim.setFrameTime(10);
		anim.update(1000);
		assertEquals(1000, anim.getElapsedTime());
		assertEquals(100, anim.getIndex());
	}
	
	@Test
	public void testReverseUpdate(){
		// Test case 1
		int newIndex = 0; 
		anim.setIndex(newIndex);
		anim.setElapsedTime(500000);
		anim.reverseUpdate(1000);;
		assertEquals(500000, anim.getElapsedTime());
		assertEquals(newIndex, anim.getIndex());
		
		// Test case 2 with if
		anim.setIndex(7);
		anim.setAnimationTime(500);
		anim.setElapsedTime(1000);
		anim.reverseUpdate(10000);
		assertEquals(0, anim.getElapsedTime());
		assertEquals(0, anim.getIndex());
		
		// Test case 2 without if
		anim.setIndex(7);
		anim.setAnimationTime(1000);
		anim.setElapsedTime(1000);
		anim.setFrameTime(10);
		anim.reverseUpdate(100);
		assertEquals(900, anim.getElapsedTime());
		assertEquals(90, anim.getIndex());
	}

}
