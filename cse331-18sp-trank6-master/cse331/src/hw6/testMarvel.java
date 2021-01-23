package hw6;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import hw5.Lab_Edge;
import hw5.findGraph;
import hw6.MarvelParser.MalformedDataException;

public class testMarvel {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
      // MarvelParser mr = new MarvelParser();
      // findGraph<String,Lab_Edge<String, String>> books = new findGraph<>();
      // List<String> characters = new  
       
      // Map<String, List<String>> books = new HashMap<>();
     //  mr.parseData("marvel.tsv", characters, books);
       //MarvelParser ps = new MarvelParser();
		MarvelPaths.loadGraph("testMarvel.tsv");
		System.out.println("done");
		System.out.println("done");
		//loadGraph("testMarvel.tsv");
		System.out.println("done");
	}
	
	

}
