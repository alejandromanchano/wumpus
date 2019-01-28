package Wumpus.Wumpus;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class BoardTest {
	
	Board board;
	
	@Before
	public void setUp() {
		board = new Board(3,3,3);
	}
		
	@Test
	public void constructorTest() {
		Assert.assertEquals(board.getN(), (Integer) 3);
		Assert.assertEquals(board.getM(), (Integer) 3);
	}
	
	
}
