package hw8;
/**
 * CampusRouteModel represents the route-finding model
 * @specfield building_N : HashMap<String, String>
 * 
 * @specfield campusPath : findGraph<Coordinate, Double>
 * 
 * @specfield building_locat : HashMap<String, Coordinate>
 * 
 * @author Khai Tran
 *
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import hw5.Lab_Edge;
import hw5.findGraph;
import hw7.MarvelPaths2;
import hw8.CampusParser.MalformedDataException;

public class CampusRouteModel {

	// Rep invariant:
	// building_N, campusPath, and building_locat not null
	// key value of building_N is not null
	// key value of building_locat is not null
	
	// Abstract function:
	// AF(this) = a CampusRouteModel rm such that
	//            rm.building_N = this.building_N
	//            rm.campusPath = this.campusPath
	//            rm.building_locat = this.building_locat
	
	// checkRep() variable 
	private static final boolean checkR = true;
	
	// the abbreviated and full name building
	private Map<String, String> building_N;
	
	// the abbreviated and full name building
	private Map<String, String> rebuilding_N;
	
	// the abbreviated name location
	private Map<String, Coordinate> building_locat;
	
	// the graph
	
	private findGraph<Coordinate, Double> campusPath;
	
	/**
	 * Construct the campus graph
	 * 
	 * @requires Data files are well-formed. Each data line must contain four tokens
	 * separated by a tab character.
	 * 
	 * @param filename contain data of campus building
	 * @param path contains data of the campus paths
	 * @throws MalformedDataException 
	 *  
	 */
	 public CampusRouteModel (String filename, String paths) throws MalformedDataException{
		 if (filename == null && paths == null) {
			 throw new IllegalArgumentException("filename and paths are not null!"); 
		 } else if (filename == null) {
			 throw new IllegalArgumentException("filename is not null!"); 
		 } else if (paths == null) {
			 throw new IllegalArgumentException("paths is not null!"); 
		 }
		 // the abbreviated and full name building
		 building_N = new HashMap<String, String>();
		 
		 // the abbreviated and full name building
		 rebuilding_N = new HashMap<String, String>();
		 
		 // the abbreviated name location
		 building_locat = new HashMap<String, Coordinate>();
		 
		 // the graph
		 campusPath = new findGraph<Coordinate, Double>();
		 
		 CampusParser.parseData(filename, building_N, rebuilding_N, building_locat);
		 CampusParser.findCamPath(paths, campusPath);
		 
		 checkRep();
		 
	 }
	 /**
	  * Return  a HashMap of abbreviated and full building name 
	  * @return a HashMap of abbreviated and fullbuilding name
	  */
	 public Map<String, String> getbuildingName(){
		 checkRep();
		 return new HashMap<String, String>(building_N);
	 }
	 
	 /**
	  * Return full name of the building
	  * @required name != null and name must be in campus
	  * 
	  * @param name is the abbreviated name of building
	  */
	 public String getFullBuidingName(String name) {
		 checkRep();
		 if (name == null) {
			 throw new IllegalArgumentException("Building name can not be null!");
		 }
		 if (!building_N.containsKey(name)) {
			 throw new IllegalArgumentException("The name: "+name + "doesn't exist!");
		 }
		 return building_N.get(name);
	 }
	 
	 /**
	  * Return abbreviated name of the building
	  * @required name != null and name must be in campus
	  * @param name is the full name of building
	  */
	 public String getAbbreviatedBuidingName(String name) {
		 checkRep();
		 if (name == null) {
			 throw new IllegalArgumentException("Building name can not be null!");
		 }
		 if (!rebuilding_N.containsKey(name)) {
			 throw new IllegalArgumentException("The name: "+name + "doesn't exist!");
		 }
		 return rebuilding_N.get(name);
	 }
	 /**
	  * Find the shortest route from a point A to a point B on campus
	  * @param start is the start of the route
	  * @param destination is the destination
	  * @return the shortest route
	  */
	 public Map<Coordinate, Double> findShortestRoute(Coordinate start, Coordinate destination){
		 ArrayList<Lab_Edge<Coordinate, Double>> route = MarvelPaths2.AlgorDijks(campusPath, start, destination);
		 // if path not found, return null
		 if (route == null) {
			 return null;
		 }
		 Map<Coordinate, Double> routepath = new LinkedHashMap<Coordinate, Double>();
		 for(Lab_Edge<Coordinate, Double> path : route) {
			 routepath.put(path.getDest(), path.getLabel());
		 }
		return routepath;
	 }
	 
	 /**
	  * Return the location of the Building
	  * @require name != null
	  * @param name is the building name on campus
	  * @return the location of the building
	  */
	 public Coordinate getLoactionB(String name) {
		 checkRep();
		 if (name == null) {
			 throw new IllegalArgumentException("Building name can not be null!");
		 }
		 return building_locat.get(name);
	 }
	 
	 /**
	  * Check the representation invariant if it holds
	  */
	 public void checkRep() {
		 if (checkR) {
			 if (building_N == null) {
				 throw new RuntimeException("The building name is null.");
			 }
			 
			 if (campusPath == null) {
				 throw new RuntimeException("The graph of campus path is null.");
			 }
			 
			 if (building_locat == null) {
				 throw new RuntimeException("The location of building is null.");
			 }
			 // check keyset for building
			 Set<String> B_names = building_N.keySet();
			 
			 for(String name : B_names) {
				 if (name == null) {
					 throw new RuntimeException("The abbreviated of building name can not null!");
				 }
				 
				 if (building_N.get(name) == null) {
					 throw new RuntimeException("The full name of building can not null!");
				 }
			 }
			 
			 Set<String> BR_names = rebuilding_N.keySet();
			 
			 for(String name : BR_names) {
				 if (name == null) {
					 throw new RuntimeException("The full name of building can not null!");
				 }
				 
				 if (rebuilding_N.get(name) == null) {
					 throw new RuntimeException("The abbreviated of building name can not null!");
				 }
			 }
			 
			 Set<String> location = building_locat.keySet();
			 
			 for(String name : location) {
				 if (name == null) {
					 throw new RuntimeException("The abbreviated of building location name can not null!");
				 }
				 
				 if (building_N.get(name) == null) {
					 throw new RuntimeException("The location of building can not null!");
				 }
			 }
		 }
	 }
}
