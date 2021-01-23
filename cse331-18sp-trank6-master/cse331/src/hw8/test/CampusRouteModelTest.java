package hw8.test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;

import hw8.CampusParser.MalformedDataException;
import hw8.CampusRouteModel;
import hw8.Coordinate;
/**
 * The implementation test CampusRouteModelTest
 * @author Khai Tran
 */
public class CampusRouteModelTest {
	private static final int TimeOut = 2500;
	
	@Test(timeout = TimeOut, expected = IllegalArgumentException.class)
	public void testConstructionModelWithNull() throws MalformedDataException {
		new CampusRouteModel(null, null);
	}
	@Test(timeout = TimeOut)
	public void testConstructionModel() throws MalformedDataException {
		new CampusRouteModel("campus_buildings.dat", "campus_paths.dat");
	}
	@Test(timeout = TimeOut, expected = MalformedDataException.class)
	public void testConstructionModelExceptionwithBaddBuidingData() throws MalformedDataException {
		new CampusRouteModel("Bad_Buiding_address.dat", "Many_Paths.dat");
	}
	
	@Test(timeout = TimeOut, expected = MalformedDataException.class)
	public void testConstructionModelExceptionwithBaddPathData() throws MalformedDataException {
		new CampusRouteModel("Many_Building.dat", "Incorrect_paths.dat");
	}
	
	@Test(timeout = TimeOut, expected = IllegalArgumentException.class)
	public void testConstructionModelInputwithNUllBuildingData() throws MalformedDataException {
		new CampusRouteModel(null, "Many_Paths.dat");
	}
	
	@Test(timeout = TimeOut, expected = IllegalArgumentException.class)
	public void testConstructionModelInputWithNULLPathData() throws MalformedDataException {
		new CampusRouteModel("Many_Building.dat", null);
	}
	
	@Test(timeout = TimeOut)
	public void testModelwithEmpty() throws MalformedDataException {
		CampusRouteModel model = new CampusRouteModel("empty.dat", "empty.dat");
		assertEquals(model.getbuildingName(), new HashMap<String, String>());
	}
	
	@Test(timeout = TimeOut, expected = IllegalArgumentException.class)
	public void testModelGetFullBuidingwithNullArg() throws MalformedDataException {
		CampusRouteModel model = new CampusRouteModel("Many_Building.dat", "Many_Paths.dat");
		model.getFullBuidingName(null);
	}
	
	@Test(timeout = TimeOut, expected = IllegalArgumentException.class)
	public void testModelNONExistFullBuildingNameArgument() throws MalformedDataException {
		CampusRouteModel model = new CampusRouteModel("empty.dat", "empty.dat");
		model.getFullBuidingName("ARGument");
	}
	@Test(timeout = TimeOut, expected = IllegalArgumentException.class)
	public void testModelNONExistAbbreviatedBuildingNameArgument() throws MalformedDataException {
		CampusRouteModel model = new CampusRouteModel("empty.dat", "empty.dat");
		model.getAbbreviatedBuidingName("ARGument");
	}
	
	@Test(timeout = TimeOut, expected = IllegalArgumentException.class)
	public void testModelLocationBuildingNameNUllArgument() throws MalformedDataException {
		CampusRouteModel model = new CampusRouteModel("empty.dat", "empty.dat");
		model.getLoactionB(null);
	}
	
	@Test(timeout = TimeOut)
	public void testModelLocationBuildingWithNOExistBuilding() throws MalformedDataException {
		CampusRouteModel model = new CampusRouteModel("empty.dat", "empty.dat");
		assertTrue(model.getLoactionB("Arguement") == null);
	}
	@Test(timeout = TimeOut)
	public void testModelLocationTwoBuildingWithNOde() throws MalformedDataException {
		CampusRouteModel model = new CampusRouteModel("Two_Buildings.dat", "Two_Paths.dat");
		assertEquals(model.getLoactionB("XX"), new Coordinate(9, 9));
	}
	@Test(timeout = TimeOut, expected = IllegalArgumentException.class)
	public void testshortestRouteIncorercttLocation() throws MalformedDataException {
		CampusRouteModel model = new CampusRouteModel("Two_Buildings.dat", "Two_Paths.dat");
		model.findShortestRoute(new Coordinate(TimeOut, 0), new Coordinate(TimeOut, 9));
	}
	@Test(timeout = TimeOut, expected = IllegalArgumentException.class)
	public void testshortestRouteIncorerctDESTLocation() throws MalformedDataException {
		CampusRouteModel model = new CampusRouteModel("Two_Buildings.dat", "Two_Paths.dat");
		model.findShortestRoute(new Coordinate(9, 9), new Coordinate(9, 6));
	}
	@Test(timeout = TimeOut)
	public void testFindShortestRouteExistingLocations() throws Exception {
		CampusRouteModel model = new CampusRouteModel("Two_Buildings.dat", "Two_Paths.dat");
		
		Coordinate xx = new Coordinate(9, 9);
		Coordinate xy = new Coordinate(5, 6);
		Map<Coordinate, Double> path = new LinkedHashMap<Coordinate, Double>();
		path.put(xx, 0.0);
		path.put(xy, 3.0);
		assertEquals(path, model.findShortestRoute(xx, xy));
	}
	
	@Test(timeout = TimeOut)
	public void testFindShortestRouteExistingLocationsRevert() throws Exception {
		CampusRouteModel model = new CampusRouteModel("Two_Buildings.dat", "Two_Paths.dat");
		
		Coordinate xx = new Coordinate(9, 9);
		Coordinate xy = new Coordinate(5, 6);
		Map<Coordinate, Double> path = new LinkedHashMap<Coordinate, Double>();
		path.put(xx, 3.0);
		path.put(xy, 0.0);
		assertEquals(path, model.findShortestRoute(xy, xx));
	}
	@Test(timeout = TimeOut)
	public void testGetFullBuildings() throws Exception {
		CampusRouteModel model =
				new CampusRouteModel("Many_Building.dat", "Many_Paths.dat");
		
		assertEquals("PORT", model.getFullBuidingName("ABC"));
	}
	@Test(timeout = TimeOut)
	public void testGetManyBuildings() throws Exception {
		CampusRouteModel model =
				new CampusRouteModel("Many_Building.dat", "Many_Paths.dat");
		
		Map<String, String> buiding = new HashMap<String, String>();
		buiding.put("ABC", "PORT");
		buiding.put("ERT", "ALLOP");
		buiding.put("UIT", "LOP");
		buiding.put("OPG", "QWER");
		buiding.put("BYU", "GERT");
		buiding.put("YTU", "QSIT");
		assertEquals(buiding, model.getbuildingName());
	}
	@Test(timeout = TimeOut)
	public void testGetAbbreviatedBuildings() throws Exception {
		CampusRouteModel model =
				new CampusRouteModel("Many_Building.dat", "Many_Paths.dat");
		
		assertEquals("ABC", model.getAbbreviatedBuidingName("PORT"));
	}
	@Test(timeout = TimeOut)
	public void testGetAbbreviatedBuildingsLocationABC() throws Exception {
		CampusRouteModel model =
				new CampusRouteModel("Many_Building.dat", "Many_Paths.dat");
		
		assertEquals(new Coordinate(5, 9), model.getLoactionB("ABC"));
	}
	
	@Test(timeout = TimeOut)
	public void testGetAbbreviatedBuildingsLocationERT() throws Exception {
		CampusRouteModel model =
				new CampusRouteModel("Many_Building.dat", "Many_Paths.dat");
		
		assertEquals(new Coordinate(9, 6), model.getLoactionB("ERT"));
	}
	
	@Test(timeout = TimeOut)
	public void testGetAbbreviatedBuildingsLocationOPG() throws Exception {
		CampusRouteModel model =
				new CampusRouteModel("Many_Building.dat", "Many_Paths.dat");
		
		assertEquals(new Coordinate(9, 5), model.getLoactionB("OPG"));
	}
	
	@Test(timeout = TimeOut)
	public void testGetAbbreviatedBuildingsLocationYTU() throws Exception {
		CampusRouteModel model =
				new CampusRouteModel("Many_Building.dat", "Many_Paths.dat");
		
		assertEquals(new Coordinate(17, 17), model.getLoactionB("YTU"));
	}
	
	@Test(timeout = TimeOut)
	public void testShortestPathBuildingsfromABCtoYTU() throws Exception {
		CampusRouteModel model =
				new CampusRouteModel("Many_Building.dat", "Many_Paths.dat");
		
		Coordinate abc = model.getLoactionB("ABC");
		Coordinate ytu = model.getLoactionB("YTU");
		
		assertTrue(model.findShortestRoute(abc, ytu) == null);
	}
	@Test(timeout = TimeOut)
	public void testShortestPathBuildingsfromYTUtoABC() throws Exception {
		CampusRouteModel model =
				new CampusRouteModel("Many_Building.dat", "Many_Paths.dat");
		
		Coordinate abc = model.getLoactionB("YTU");
		Coordinate ytu = model.getLoactionB("ABC");
		
		assertTrue(model.findShortestRoute(abc, ytu) == null);
	}
	
	@Test(timeout = TimeOut)
	public void testShortestPathfromYTUtoABC() throws Exception {
		CampusRouteModel model =
				new CampusRouteModel("Many_Building.dat", "Many_Paths.dat");
		
		Coordinate abc = model.getLoactionB("ABC");
		Coordinate opg = model.getLoactionB("OPG");
		
		Map<Coordinate, Double> path = new LinkedHashMap<Coordinate, Double>();
		path.put(abc, 0.0);
		path.put(opg, 5.0);
		
		assertEquals(path, model.findShortestRoute(abc, opg));
	}
}
