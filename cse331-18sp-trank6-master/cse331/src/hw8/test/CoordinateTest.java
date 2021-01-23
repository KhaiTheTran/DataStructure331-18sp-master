package hw8.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
/**
 * This class contain implement CoordinateTest
 * @author Khai Tran
 *
 */

import hw8.Coordinate;
public class CoordinateTest {
	private static final int TimeOut = 59;
	private static final double Epsilon = 0.00000001;
	Coordinate coor;
	@Before
	public void setUp() throws Exception {
		coor = new Coordinate(9.5, 27.1);
	}
	@Test(timeout = TimeOut)
	public void testHashCode() {
		assertEquals(coor.hashCode(), (new Coordinate(9.5, 27.1)).hashCode());
	}
	@Test(timeout = TimeOut)
	public void testGetX() {
		assertTrue(Math.abs(coor.getX()-9.5)<Epsilon);;
	}
	@Test(timeout = TimeOut)
	public void testGetY() {
		assertTrue(Math.abs(coor.getY()-27.1)<Epsilon);;
	}
	
	@Test(timeout = TimeOut)
	public void testEqualNull() {
		assertFalse(coor.equals(null));
	}
	
	@Test(timeout = TimeOut)
	public void testEqualwithCoordinate() {
		assertTrue(coor.equals(new Coordinate(9.5, 27.1)));
	}
}
