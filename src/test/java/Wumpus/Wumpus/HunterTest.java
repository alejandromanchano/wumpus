package Wumpus.Wumpus;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HunterTest {
	
	Hunter hunter;
	Integer[] position;
	
	@Before
	public void setUp() {
		position = new Integer[2];
		hunter = new Hunter("Alejandro", 3, true, position, false);
	}
	
	@Test
	public void contructorTest() {
		Assert.assertEquals(hunter.getName(), "Alejandro");
		Assert.assertEquals(hunter.getAlive(), true);
		Assert.assertEquals(hunter.isTreasure(), false);
		Assert.assertEquals(hunter.getArrows(), (Integer) 3);
	}

}
