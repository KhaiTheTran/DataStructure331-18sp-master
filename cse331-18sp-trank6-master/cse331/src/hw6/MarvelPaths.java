package hw6;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

import org.checkerframework.stubparser.ast.stmt.ThrowStmt;

import hw5.Lab_Edge;
import hw5.findGraph;
import hw5.test.HW5TestDriver;
import hw6.MarvelParser.MalformedDataException;
/**
 * The class has loadGraph method which loads data from a file .tsv
 * and put to the findGraph() method. Using BFS algorithm with the graph to find the shortest
 * way from node a to node b.
 * 
 * @author Khai Tran
 *
 */

public class MarvelPaths {
	
   private static findGraph<String, String> fg;
   private static Set<String> characters;
   private static Map<String, List<String>> books;
	/**
	 * This method using a file name call parseData() and put to findGraph<String, String>
	 * @param filename
	 *  @requires filename != null
	 * @return findGraph<String, String>
	 * @throws MalformedDataException when reading data errors
	 */
	public static findGraph<String, String> loadGraph(String filename) throws MalformedDataException{
		if (filename == null) throw new IllegalArgumentException("File name must be correct!");

		fg= new findGraph<>();
		characters = new HashSet<>();
		books = new HashMap<String, List<String>>();
		MarvelParser.parseData(filename, characters, books);
		// add nodes (character) from the list to findGraph
		for(String character : characters) {
			fg.addNode(character);
		}
		//load book to findGraph
		for (String book : books.keySet()) {
			// if the graph have a, b, c edge of a label, connecting a to b, and c
			// interact connection between b and a, .
			// After adding all the in and outgoing edges to the graph 
			// and connecting b to c and interact connection between c and b.
			
			List<String> list_b = books.get(book);
			for (String at : list_b) {
				
				for(String de : list_b) {
					// Eliminated reflexive edge
					if (!at.equals(de)) {
						fg.addEdge(at, de, book);						
					}
				}
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
	public static ArrayList<Lab_Edge<String,String>> AlgorBFS(findGraph<String, String> fGraph,String start, String destination){
		if (fGraph == null) throw new IllegalArgumentException("Graph can not be null!");
		if (!fGraph.hasNode(start) || start.trim().equals("")) throw new IllegalArgumentException("Start can not be found!");
		if (!fGraph.hasNode(destination) || destination.trim().equals("")) throw new IllegalArgumentException("Destination can not be found!");
		// queue of node visited
		Queue<String> visited = new LinkedList<String>();
		visited.add(start);
		// set up a graph with start key
		HashMap<String, ArrayList<Lab_Edge<String, String>>> startGraph = new HashMap<>();
		startGraph.put(start, new ArrayList<Lab_Edge<String,String>>());
		// BFS algorithm
		
		while (!visited.isEmpty()) {
			String character = visited.poll();
			if(character.equals(destination)) {
				return new ArrayList<Lab_Edge<String, String>>(startGraph.get(character));
			}
			// sort the edges
			Set<Lab_Edge<String, String>> labE_sort = HW5TestDriver.compare();
			labE_sort.addAll(fGraph.childNode(character));
			
			for (Lab_Edge<String, String> l_edge : labE_sort) {
				String dest = l_edge.getDest();
				if (!startGraph.containsKey(dest)) {
					// update the path with not visited
					ArrayList<Lab_Edge<String, String>> p_edge =new ArrayList<Lab_Edge<String, String>>(startGraph.get(character));
					p_edge.add(l_edge);
					startGraph.put(dest, p_edge);
					// mark visited to queue
					visited.add(dest);
				}
			}
		}
		// can not find path
		return null;
	}
	/**
	 * This is user interface which allows user to find the shortest path
	 * form character a to character b.
	 * @param args
	 * @throws MalformedDataException, IllegalArgumentException
	 *
	 */
    public static void main(String[] args) throws MalformedDataException {
    	// loading graph
    	 String filename = "testMarvel.tsv";
    	 filename = "marvel.tsv";
         findGraph<String, String> fGraph = loadGraph(filename);
         if (fGraph == null) throw new IllegalArgumentException("Can not load data from: "+filename);
         
         System.out.println("This is finding graph program!\n");
         Scanner console = new Scanner(System.in);
         String start = "";
         int exit = 0;
         while(true) {
        	 // if input more than three times wrong
        	 // the program will ask continue or not
        	 if (exit > 3) {
        		 System.out.println("Would you like to find more graph? Y/N");
            	 start = console.nextLine();
            	 if (start.trim().equalsIgnoreCase("Y")) {
            		 start = "";
            		 exit = 0;
            		 continue;
            	 } else {
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
        		 System.out.println("There is not start character: "+start);
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
        		 System.out.println("There is not destination character: "+destination);
        		 exit++;
        		 continue;
        	 }
        	 ArrayList<Lab_Edge<String, String>> path = AlgorBFS(fGraph, start, destination);
        	 String outprint ="path from "+start+" to "+destination+":";
        	 String currentStar = start;
        	 if (path == null) {
        		 outprint += "\nno path found";
        	 }else {
        		 for (Lab_Edge<String, String> l_edge : path) {
        			outprint += "\n"+currentStar+ " to " +l_edge.getDest() +" via " +l_edge.getLabel();
        			currentStar = l_edge.getDest();
        		 }
        	 }
        	 System.out.println(outprint);
        	 System.out.println("Would you like to find more graph? Y/N");
        	 start = console.nextLine();
        	 if (start.trim().equalsIgnoreCase("Y")) {
        		 start = "";
        		 exit = 0;
        		 continue;
        	 } else {
        		 break;
        	 }
         }
         console.close();
    }
}
