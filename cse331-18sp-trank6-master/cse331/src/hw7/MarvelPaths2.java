package hw7;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import hw5.Lab_Edge;
import hw5.findGraph;
import hw7.MarvelParser.MalformedDataException;
/**
 * The class has loadGraph method which loads data from a file .tsv
 * and put to the findGraph() method. Using Dijkstra's algorithm with the graph to find the shortest
 * way from node a to node b.
 * 
 * @author Khai Tran
 *
 */

public class MarvelPaths2 {
	
   
	/**
	 * This method using a file name call parseData() and put to findGraph<String, Double>
	 * @param filename
	 *  @requires filename != null
	 * @return findGraph<String, Double>
	 * @throws MalformedDataException when reading data errors
	 * 
	 */
	public static findGraph<String, Double> loadGraph(String filename) throws MalformedDataException{
		if (filename == null) throw new IllegalArgumentException("File name must be correct!");
		findGraph<String, Double> fg = new findGraph<String, Double>();
		Map<String, HashMap<String, Integer>> fdata = new HashMap<String, HashMap<String, Integer>>();
		MarvelParser.parseData(filename, fdata);
		
		// add nodes (character) from the list to findGraph
		for(String character : fdata.keySet()) {
				fg.addNode(character);
		}
		//load book to findGraph
		for (String charac : fdata.keySet()) {
			
			HashMap<String, Integer> list_b = fdata.get(charac);
				
				for(String de : list_b.keySet()) {
					int num_node = list_b.get(de);					   
					fg.addEdge(charac, de, 1.0/num_node);					
				}
			}
		return fg;
	}
	/**
	 * Find the shortest path from node a to node b
	 * @param start is a beginning character
	 * @param destination is end character
	 * @return ArrayList<Lab_Edge<String,String>> with the shortest path from 
	 * character a to character b.
	 * @throws IllegalArgumentException
	 */
	public static <A> ArrayList<Lab_Edge<A, Double>> AlgorDijks(findGraph<A, Double> fGraph, A start, A destination){
		if (fGraph == null) throw new IllegalArgumentException("Graph can not be null!");
		if (!fGraph.hasNode(start) || start == null) throw new IllegalArgumentException("Start can not be found!");
		if (!fGraph.hasNode(destination) || destination == null) throw new IllegalArgumentException("Destination can not be found!");
		
		// queue of node active
		PriorityQueue<ArrayList<Lab_Edge<A, Double>>> active = compareCost();
		
		// set up a graph with start key
		ArrayList<Lab_Edge<A, Double>> In_list = new ArrayList<Lab_Edge<A, Double>>();
		
		//Initially the path from start to itself with a cost of zero
		In_list.add(new Lab_Edge<A, Double>(start, 0.0));
		active.add(In_list);
		// list visited path
		ArrayList<A> finished = new ArrayList<A>();
		while (!active.isEmpty()) {
			 // minimum-cost path to some node
			ArrayList<Lab_Edge<A, Double>> minPath = active.remove();
			//@SuppressWarnings("null")
			Lab_Edge<A, Double> minDest_Edge = minPath.get(minPath.size()-1);
			A minDest = minDest_Edge.getDest();
			if(minDest.equals(destination)) {
				return minPath;
			}
			if (finished.contains(minDest)) {
				continue;
     		}
			// sort the edges
			Set<Lab_Edge<A, Double>> children = fGraph.childNode(minDest);
			// get min cost
			double mincost = minDest_Edge.getLabel();
			for (Lab_Edge<A, Double> e : children) {
				// If we don't know the minimum-cost path from start to child,
	            // examine the path we've just found
				if (!finished.contains(e.getDest())) {
					ArrayList<Lab_Edge<A, Double>> newPath = new ArrayList<Lab_Edge<A, Double>>(minPath);
					// update the path with not visited
					newPath.add(new Lab_Edge<A, Double>(e.getDest(), (e.getLabel()+mincost)));
					active.add(newPath);					
				}
			}
			// add visited to queue
			finished.add(minDest);
		}
		// can not find path
		return null;
	}
	/**
	 * This is user interface which allows user to find the the minimum cost
	 * form character a to character b.
	 * @param args
	 * @throws MalformedDataException, IllegalArgumentException
	 *
	 */
    public static void main(String[] args) throws MalformedDataException {
    	// loading graph
    	 String filename;// = "testMarvel.tsv";
    	 filename = "marvel.tsv";
         findGraph<String, Double> fGraph = loadGraph(filename);
         if (fGraph == null) throw new IllegalArgumentException("Can not load data from: "+filename);
         System.out.println("This is the finding minimum cost of two characters in Marvel!\n");
         Scanner console = new Scanner(System.in);
         String start = "";
         int exit = 0;
         while(true) {
        	 // if input more than three times wrong
        	 // the program will ask continue or not
        	 if (exit > 2) {
        		 System.out.println("Would you like to find more graph? Y/N");
            	 start = console.nextLine();
            	 if (start.trim().equalsIgnoreCase("Y")) {
            		 start = "";
            		 exit = 0;
            		 continue;
            	 } else {
            		 System.out.println("See you again!");
            		 break;
            	 }
        		 
        	 }
        	 
        	 if (start.equalsIgnoreCase("")) {
        	 System.out.println("Please, input correct the start of the path!");
        	 start = console.nextLine();
        	 if (start.equalsIgnoreCase("")) {
        		 continue;
        	 }
        	 // check start node
        	 if (!fGraph.hasNode(start)) {        		
        		 System.out.println("unknown character "+start);
        		 start = "";
        		 exit++;
        		 continue;
        	 }
        	 }
        	 System.out.println("Please, input correct the destination character of the path!");
        	 String destination = console.nextLine();
        	 if (destination.equalsIgnoreCase("")) {
        		 continue;
        	 }
        	 // check destination node
        	 if (!fGraph.hasNode(destination)) {
        		 System.out.println("unknown character "+destination);
        		 exit++;
        		 continue;
        	 }
        	 List<Lab_Edge<String, Double>> path = AlgorDijks(fGraph, start, destination);
        	 String outprint ="path from "+start+" to "+destination+":";
        	 String currentStar = start;
        	 double minCost = 0.0;
        	 if (path == null) {
        		 outprint += "\nno path found";
        	 } else {
        		 path = path.subList(1, path.size());
        		 for (Lab_Edge<String, Double> l_edge : path) {
        			outprint += "\n"+currentStar+ " to " +l_edge.getDest() +" with weight "
        						+String.format("%.3f", l_edge.getLabel() - minCost);
        			currentStar = l_edge.getDest();
        			minCost = l_edge.getLabel();
        		 }
        	 }
        	 outprint += "\ntotal cost: "+String.format("%.3f", minCost);
        	 System.out.println(outprint);
        	 System.out.println("Would you like to find more graph? Y/N");
        	 start = console.nextLine();
        	 if (start.trim().equalsIgnoreCase("Y")) {
        		 start = "";
        		 exit = 0;
        		 continue;
        	 } else {
        		 System.out.println("See you again!");
        		 break;
        	 }
         }
         console.close();
    }
    /**
     * Sort PriorityQueue with comparator
     * @return PriorityQueue<ArrayList<Lab_Edge<A, Double>>>
     */
    public static <A> PriorityQueue<ArrayList<Lab_Edge<A, Double>>> compareCost() {
    	PriorityQueue<ArrayList<Lab_Edge<A, Double>>> active = new PriorityQueue<>(10, new
    			Comparator<ArrayList<Lab_Edge<A, Double>>>() {

			@Override
			public int compare(ArrayList<Lab_Edge<A, Double>> o1, ArrayList<Lab_Edge<A, Double>> o2) {
				// TODO Auto-generated method stub
				Lab_Edge<A, Double> cost1 = o1.get(o1.size()-1);
				Lab_Edge<A, Double> cost2 = o2.get(o2.size()-1);
				if (!cost1.getLabel().equals(cost2.getLabel())) {
					return cost1.getLabel().compareTo(cost2.getLabel());
				}
					
					return o1.size() - o2.size();
				
			}
		});
		return active;
    	
    }
    
}
