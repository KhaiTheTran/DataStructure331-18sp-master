package hw7.test;


import org.junit.Before;
import org.junit.Test;

import hw5.findGraph;
import hw7.MarvelPaths2;

public class MarvelPaths2Test {
	private findGraph<String, Double> fg;
	private final int timeouT = 99;
	
	@Before
	public void setUp() throws Exception {
		fg = MarvelPaths2.loadGraph("testmarvelone.tsv");
	}
    // test loadGraph
	@Test (timeout = timeouT, expected = IllegalArgumentException.class)
	public void testNullInput() throws Exception{
		MarvelPaths2.loadGraph(null);
	}
	@Test (timeout = timeouT, expected = IllegalArgumentException.class)
	public void testNullInput_BFS() throws Exception{
		MarvelPaths2.AlgorDijks(null, "YY", "AA");
	}
	@Test (timeout = timeouT, expected = IllegalArgumentException.class)
	public void testNullInput_StartisNull() throws Exception{
		MarvelPaths2.AlgorDijks(fg, null, "XX");
	}
	@Test (timeout = timeouT, expected = IllegalArgumentException.class)
	public void testNullInput_DESTisNull() throws Exception{
		MarvelPaths2.AlgorDijks(fg, "XX", null);
	}
	@Test (timeout = timeouT, expected = IllegalArgumentException.class)
	public void testInput_DESTnotExist() throws Exception{
		MarvelPaths2.AlgorDijks(fg, "YY", "KKKK");
	}
	@Test (timeout = timeouT, expected = IllegalArgumentException.class)
	public void testInput_StartnotExist() throws Exception{
		MarvelPaths2.AlgorDijks(fg, "KKKK", "XX");
	}
	@Test (timeout = timeouT)
	public void testInput() throws Exception{
		MarvelPaths2.AlgorDijks(fg, "BB", "GG");
	}
}
