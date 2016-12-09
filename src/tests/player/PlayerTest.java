package tests.player;

import static org.junit.Assert.*;

import org.junit.Test;

import model.player.Player;

public class PlayerTest {

	@Test
	public void testConstructor() {
		new Player();
		assertEquals(50, Player.getInstance().getHappiness());
		
		Player.getInstance().update(100);
		assertEquals(50, Player.getInstance().getHappiness());
		
		Player.getInstance().increaseHappiness(10);
		assertEquals(60, Player.getInstance().getHappiness());
		
		Player.getInstance().decreaseHappiness(10);
		assertEquals(50, Player.getInstance().getHappiness());
		
		assertFalse(Player.getInstance().increaseHappiness(49));
		assertEquals(99, Player.getInstance().getHappiness());
		assertTrue(Player.getInstance().increaseHappiness(1));
		assertEquals(100, Player.getInstance().getHappiness());
		assertTrue(Player.getInstance().increaseHappiness(1));
		assertEquals(100, Player.getInstance().getHappiness());
	}

}
