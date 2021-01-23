package hw8.test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import hw5.findGraph;
import hw8.CampusParser;
import hw8.CampusParser.MalformedDataException;
import hw8.Coordinate;
/**
 * Implement test for CampusParser
 * 
 * @author Khai Tran
 *
 */
public class CampusParserTest {
	private static final int TimEOut = 59;
	private Map<String, String> buiding_N;
	private Map<String, String> rebuiding_N;
	private Map<String, Coordinate> b_location;
	private findGraph<Coordinate, Double> campusPath;

	@Before
	public void setUp() throws Exception {
		buiding_N = new HashMap<>();
		rebuiding_N = new HashMap<>();
		b_location = new HashMap<>();
		campusPath = new findGraph<>();
	}

	@Test(timeout = TimEOut)
	public void testEmptyBuilding() throws Exception {
		CampusParser.findCamPath("empty.dat", campusPath);
		assertEquals(new HashMap<String, String>(), buiding_N);
		assertEquals(new HashMap<String, Coordinate>(), b_location);
	}
	
	@Test(timeout = TimEOut)
	public void testEmptyCampusPaths() throws Exception {
		CampusParser.findCamPath("empty.dat", campusPath);
		assertEquals((new findGraph<Coordinate, Double>()).getNode(), campusPath.getNode());
	}	
	@Test(timeout = TimEOut)
	public void testBuildingTwoBuildings() throws Exception {
		CampusParser.parseData("Two_Buildings.dat", buiding_N, rebuiding_N, b_location);
		Map<String, String> building = new HashMap<String, String>();
		Map<String, Coordinate> blocate = new HashMap<String, Coordinate>();
		building.put("XX", "CSE X");
		building.put("XY", "CSE Y");
		blocate.put("XX", new Coordinate(9, 9));
		blocate.put("XY", new Coordinate(5, 6));
		assertEquals(building, buiding_N);
		assertEquals(blocate, b_location);
	}
	
	@Test(timeout = TimEOut)
	public void testBuildTwoPaths() throws Exception {
		CampusParser.findCamPath("Two_Paths_one_Edge.dat", campusPath);
		findGraph<Coordinate, Double> fg = new findGraph<Coordinate, Double>();
		Coordinate xx = new Coordinate(9, 9);
		Coordinate xy = new Coordinate(5, 6);
		fg.addNode(xx);
		fg.addNode(xy);
		fg.addEdge(xx, xy, 7.0);
		fg.addEdge(xy, xx, 7.0);
		assertEquals(fg.getNode(), campusPath.getNode());
	}
	
	@Test(timeout = TimEOut)
	public void testTwoBuildingsWithComments() throws Exception {
		CampusParser.parseData("Two_Building_comment.dat", buiding_N, rebuiding_N, b_location);
		Map<String, String> buiding = new HashMap<String, String>();
		Map<String, Coordinate> blocate = new HashMap<String, Coordinate>();
		buiding.put("XX", "CSE X");
		buiding.put("XY", "Paul Y");
		blocate.put("XX", new Coordinate(9, 9));
		blocate.put("XY", new Coordinate(5, 6));
		assertEquals(buiding, buiding_N);
		assertEquals(blocate, b_location);
	}
	
	@Test(timeout = TimEOut)
	public void testTwoPathsWithComments() throws Exception {
		CampusParser.findCamPath("Two_Paths_comment.dat", campusPath);
		findGraph<Coordinate, Double> fg = new findGraph<Coordinate, Double>();
		Coordinate xx = new Coordinate(9, 9);
		Coordinate xy = new Coordinate(5, 6);
		fg.addNode(xx);
		fg.addNode(xy);
		fg.addEdge(xx, xy, 7.0);
		fg.addEdge(xy, xx, 7.0);
		assertEquals(fg.getNode(), campusPath.getNode());
	}
	// not separated by tab character
	@Test(timeout = TimEOut, expected = MalformedDataException.class)
	public void testbadFormatedBuilding() throws Exception {
		CampusParser.parseData("Bad_Buiding_address.dat", buiding_N, rebuiding_N, b_location);
	}
	// bad indented line
	@Test(timeout = TimEOut, expected = MalformedDataException.class)
	public void testBadFormatedPaths() throws Exception {
		CampusParser.findCamPath("Incorrect_paths.dat", campusPath);
	}
	// bad non-indented line
	@Test(timeout = TimEOut, expected = MalformedDataException.class)
	public void testBadFormatedPathsTwo() throws Exception {
		CampusParser.findCamPath("IncorrectOneLine_Paths.dat", campusPath);
	}
	
	@Test(timeout = TimEOut)
	public void testBuildingManyData() throws Exception {
		CampusParser.parseData("Many_Building.dat", buiding_N, rebuiding_N, b_location);
		Map<String, String> building = new HashMap<String, String>();
		Map<String, Coordinate> blocate = new HashMap<String, Coordinate>();
		building.put("ABC", "PORT");
		building.put("ERT", "ALLOP");
		building.put("UIT", "LOP");
		building.put("OPG", "QWER");
		building.put("BYU", "GERT");
		building.put("YTU", "QSIT");
		blocate.put("ABC", new Coordinate(5, 9));
		blocate.put("ERT", new Coordinate(9, 6));
		blocate.put("UIT", new Coordinate(6, 9));
		blocate.put("OPG", new Coordinate(9, 5));
		blocate.put("BYU", new Coordinate(12, 12));
		blocate.put("YTU", new Coordinate(17, 17));
		assertEquals(building, buiding_N);
		assertEquals(blocate, b_location);
	}
	
}
