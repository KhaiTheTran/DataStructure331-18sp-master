package hw8;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeSet;

public class CampusRouteMachine {
	
	/**
	 * List all abbreviated and full name of building
	 * @require model != null
	 * @param model of CampusRouteModel
	 */
	public static void getBuildingcollection(CampusRouteModel model) {
		// TODO Auto-generated constructor stub
		if (model == null) {
			throw new IllegalArgumentException("Model is not null!");
		}
		String nameofBuilding = "Building names:\n";
		Map<String, String> buildingName = model.getbuildingName();
		
		// TreeSet sort the key
		TreeSet<String> building_key = new TreeSet<>(buildingName.keySet());
		
		for (String abb_name : building_key) {
			String full_name = buildingName.get(abb_name);
			nameofBuilding += "\t" + abb_name+ ": " + full_name + "\n";
		}
		System.out.println(nameofBuilding);
	}
    /**
     * Get the shortest route through buildings on campus
     * @requires model, start, destination != null, and start and destination are abbreviated name
     * @param model is the model of CampusRouteModel
     * @param start is the first abbreviated building name
     * @param destination is the end of abbreviated building name
     *
     */
	public static void getShortestRoute(CampusRouteModel model, String start, String destination) {
		if(model == null) {
			throw new IllegalArgumentException("model is not null!");
		}
		
		if(start == null) {
			throw new IllegalArgumentException("start is not null!");
		}
		
		if(destination == null) {
			throw new IllegalArgumentException("destination is not null!");
		}
		// get the location of start and destination buildings
		Coordinate start_locate = model.getLoactionB(start);
		Coordinate destination_locate = model.getLoactionB(destination);
		
		if (start_locate == null && destination_locate == null) {
			System.out.println("Unknown building: "+start);
			System.out.println("Unknown building: "+destination);
		} else if (start_locate == null) {
			System.out.println("Unknown building: "+start);
		} else if (destination_locate == null) {
			System.out.println("Unknown building: "+destination);
		} else {
			String routeWalk = "";
			// get full name
			String start_FN = model.getFullBuidingName(start);
			String destination_FN = model.getFullBuidingName(destination);
			
			routeWalk += "Path from " + start_FN+ " to "+destination_FN+":\n";
			Map<Coordinate, Double> shortest_route = model.findShortestRoute(start_locate, destination_locate);
			
			double currentX = start_locate.getX();
			double currentY = start_locate.getY();
			double current_distance = 0.0;
			
			List<Coordinate> coord = new ArrayList<>(shortest_route.keySet());
			coord = coord.subList(1, coord.size());
			for(Coordinate node_e : coord) {
				double d_x = node_e.getX();
				double d_y = node_e.getY();
				double distant = shortest_route.get(node_e).doubleValue();
				double theta = Math.atan2(d_y -currentY, d_x-currentX);
				String direct = getDirection(theta);
				routeWalk += String.format("\tWalk %.0f feet %s to (%.0f, %.0f)\n", distant-current_distance, direct,d_x,d_y);
				
				// update the current coordinate for next edge theta
				currentX = d_x;
				currentY = d_y;
				current_distance = distant;
			}
			routeWalk += String.format("Total distance: %.0f feet\n", current_distance);
			System.out.println(routeWalk);
		}
		
	}
	/**
	 * Determine the direction according to the angle of theta
	 * The direction is E, SE, NE, S, N, SW, NW, or W
	 * @param theta is the angle of the theta coordinate
	 * @return
	 */
	public static String getDirection(double theta) {
		final double PIoverEight = Math.PI/8;
		final double PI3Eight = 3*PIoverEight;
		final double PI5Eight = 5*PIoverEight;
		final double PI7Eight = 7*PIoverEight;
		final double nePIoneEight = -1*PIoverEight;
		final double nePI3Eight = -1*PI3Eight;
		final double nePI5Eight = -1*PI5Eight;
		double nePI7Eight = -1*PI7Eight;
		double Epsilon = 0.00000001;
		if (Math.abs(theta-PIoverEight) < Epsilon || Math.abs(theta)< Epsilon || Math.abs(theta - nePIoneEight) < Epsilon 
				|| (theta > nePIoneEight && theta < 0)|| (theta>0 && theta < PIoverEight)) {
			return "E";
		} else if (theta > PIoverEight && theta < PI3Eight) {
			return "SE";
		} else if (theta > nePI3Eight && theta < nePIoneEight) {
			return "NE";
		} else if (Math.abs(theta - PI5Eight) < Epsilon || Math.abs(theta- PI3Eight) < Epsilon ||
				(theta > PI3Eight && theta < PI5Eight)) {
			return "S";
		} else if (Math.abs(theta- nePI5Eight) < Epsilon || Math.abs(theta- nePI3Eight) <Epsilon || 
				(theta > nePI5Eight && theta < nePI3Eight)) {
			return "N";
		} else if (theta > PI5Eight && theta < PI7Eight) {
			return "SW";
		} else if (theta > nePI7Eight && theta < nePI5Eight) {
			return "NW";
		} else {
			return "W";
		}
	}
	/**
	 * Main method is the user interface to find the shortest route
	 * between buildings
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			CampusRouteModel model = new CampusRouteModel("campus_buildings.dat", "campus_paths.dat");
			String opiton = "Menu:\n"+ "\tr to find a route\n \tb to see a list of all buildings\n"+"\tq to quit\n";
			System.out.println(opiton);
			Scanner console = new Scanner(System.in);
			System.out.print("Enter an option ('m' to see the menu): ");
			while (true) {
				String input = console.nextLine();
				// check empty and begin with #
				if (input.length() == 0 || input.startsWith("#")) {
					System.out.println(input);
					continue;
				}
				if (input.equalsIgnoreCase("m")) {
					System.out.println(opiton);
				} else if (input.equalsIgnoreCase("b")) {
					getBuildingcollection(model);
				} else if (input.equalsIgnoreCase("r")) {
					System.out.print("Abbreviated name of starting building: ");
					String start = console.nextLine();
					System.out.print("Abbreviated name of ending building: ");
					String destination = console.nextLine();
					getShortestRoute(model, start, destination);
				} else if (input.equalsIgnoreCase("q")) {
					console.close();
					break;
				} else {
					System.out.println("Unknown option\n");
				}
				System.out.print("Enter an option ('m' to see the menu): ");
			}
		}catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.toString());
			e.printStackTrace();
		}
	}
}
