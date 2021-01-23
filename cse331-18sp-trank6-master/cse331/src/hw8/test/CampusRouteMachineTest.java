package hw8.test;

import static org.junit.Assert.*;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import hw8.CampusRouteMachine;
import hw8.CampusRouteModel;
import hw8.Coordinate;

public class CampusRouteMachineTest {
	private static final int TimeOut = 2000;

	@Test(timeout = TimeOut)
	public void testCampusRouteMachingetdirectionE() {
		assertEquals("E", CampusRouteMachine.getDirection(0));
	}
	@Test(timeout = TimeOut)
	public void testCampusRouteMachingetdirectionSE() {
		assertEquals("SE", CampusRouteMachine.getDirection(1));
	}
	
	@Test(timeout = TimeOut)
	public void testCampusRouteMachingetdirectionSW() {
		assertEquals("SW", CampusRouteMachine.getDirection(2));
	}
	
	@Test(timeout = TimeOut)
	public void testCampusRouteMachingetdirectionW() {
		assertEquals("W", CampusRouteMachine.getDirection(3));
	}
	
	@Test(timeout = TimeOut)
	public void testCampusRouteMachingetdirectionNE() {
		assertEquals("NE", CampusRouteMachine.getDirection(-1));
	}
	
	@Test(timeout = TimeOut)
	public void testCampusRouteMachingetdirectionNW() {
		assertEquals("NW", CampusRouteMachine.getDirection(-2));
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
