package hw6.test;


import org.junit.Before;
import org.junit.Test;

import hw5.findGraph;
import hw6.MarvelPaths;

public class MarvelPathsTest {
	private findGraph<String, String> fg;
	private final int timeouT = 99;
	
	@Before
	public void setUp() throws Exception {
		fg = MarvelPaths.loadGraph("testmarvelone.tsv");
	}
    // test loadGraph
	@Test (timeout = timeouT, expected = IllegalArgumentException.class)
	public void testNullInput() throws Exception{
		MarvelPaths.loadGraph(null);
	}
	@Test (timeout = timeouT, expected = IllegalArgumentException.class)
	public void testNullInput_BFS() throws Exception{
		MarvelPaths.AlgorBFS(null, "YY", "AA");
	}
	@Test (timeout = timeouT, expected = IllegalArgumentException.class)
	public void testNullInput_StartisNull() throws Exception{
		MarvelPaths.AlgorBFS(fg, null, "XX");
	}
	@Test (timeout = timeouT, expected = IllegalArgumentException.class)
	public void testNullInput_DESTisNull() throws Exception{
		MarvelPaths.AlgorBFS(fg, "XX", null);
	}
	@Test (timeout = timeouT, expected = IllegalArgumentException.class)
	public void testInput_DESTnotExist() throws Exception{
		MarvelPaths.AlgorBFS(fg, "YY", "KKKK");
	}
	@Test (timeout = timeouT, expected = IllegalArgumentException.class)
	public void testInput_StartnotExist() throws Exception{
		MarvelPaths.AlgorBFS(fg, "KKKK", "XX");
	}
	@Test (timeout = timeouT)
	public void testInput() throws Exception{
		MarvelPaths.AlgorBFS(fg, "BB", "GG");
	}
}
