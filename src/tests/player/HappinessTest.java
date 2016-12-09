package tests.player;

import static org.junit.Assert.*;

import org.junit.Test;

import model.player.Happiness;

public class HappinessTest {

	@Test
	public void test() {
		Happiness h1 = new Happiness();
		assertEquals(50, h1.getHappiness());
		assertFalse(h1.increaseHappiness(49));
		assertEquals(99, h1.getHappiness());
		assertTrue(h1.increaseHappiness(1));
		assertEquals(100, h1.getHappiness());
		assertTrue(h1.increaseHappiness(1));
		assertEquals(100, h1.getHappiness());
		
		assertFalse(h1.decreaseHappiness(99));
		assertEquals(1, h1.getHappiness());
		assertTrue(h1.decreaseHappiness(1));
		assertEquals(0, h1.getHappiness());
		assertTrue(h1.decreaseHappiness(1));
		assertEquals(0, h1.getHappiness());
	}

}
