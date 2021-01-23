package hw8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import hw5.findGraph;

/**
 * Parser utility to load the campus_buidings.dat and campus_paths.dat dataset.
 * 
 * @author Khai Tran (inherited from HW7)
 */
public class CampusParser {

	 /**
     * A checked exception class for bad data files
     */
    @SuppressWarnings("serial")
    public static class MalformedDataException extends Exception {
        public MalformedDataException() { }

        public MalformedDataException(String message) {
            super(message);
        }

        public MalformedDataException(Throwable cause) {
            super(cause);
        }

        public MalformedDataException(String message, Throwable cause) {
            super(message, cause);
        }
    }

  /**
   * Reads the campus_buidings dataset.
   * Each line of the input file contains a reduced building name, a full building name, and
   * the coordinate (x, y) position of the building, separated by a tab character
   * 
   * @param filename the file that will be read
   * @param mapBuiding list in which all character names will be stored;
   *          typically empty when the routine is called
   * @param remapBuiding list in which all character names will be stored;
   *          typically empty when the routine is called
   * @param loCation store the position of the building with the coordinate (x, y)
   * 
   * @throws MalformedDataException if the file is not well-formed:
   *          each line contains exactly 4 tokens separated by a tab,
   *          or else starting with a # symbol to indicate a comment line.
   */
  public static void parseData(String filename, Map<String, String> mapBuiding,
		  Map<String, String> remapBuiding, Map<String, Coordinate> loCation) throws MalformedDataException {
    // Why does this method accept the Collections to be filled as
    // parameters rather than making them a return value? To allows us to
    // "return" two different Collections. If only one or neither Collection
    // needs to be returned to the caller, feel free to rewrite this method
    // without the parameters. Generally this is better style.
	  filename = "src/hw8/data/"+filename;
    BufferedReader reader = null;
    try {
        reader = new BufferedReader(new FileReader(filename));

        // Construct the collections of characters and books, one
        // <character, book> pair at a time.
        String inputLine;
        while ((inputLine = reader.readLine()) != null) {

            // Ignore comment lines.
            if (inputLine.startsWith("#")) {
                continue;
            }

            // Parse the data, stripping out quotation marks and throwing
            // an exception for malformed lines.
            inputLine = inputLine.replace("\"", "");
            String[] tokens = inputLine.split("\t");
            //System.err.println(Arrays.toString(tokens));
            if (tokens.length != 4) {
                throw new MalformedDataException("Line should contain exactly one tab: "
                                                 + inputLine);
            }

            String short_N = tokens[0];
            String full_N = tokens[1];
            double x = Double.parseDouble(tokens[2]); 
            double y = Double.parseDouble(tokens[3]);
            
            // push data to hash map
            mapBuiding.put(short_N, full_N);
            remapBuiding.put(full_N, short_N);
            
            // location of building
            loCation.put(short_N, new Coordinate(x, y));
           
        }
    } catch (IOException e) {
        System.err.println(e.toString());
        e.printStackTrace(System.err);
    } finally {
        if (reader != null) {
            try {
                reader.close();
            } catch (IOException e) {
                System.err.println(e.toString());
                e.printStackTrace(System.err);
            }
        }
    }
  }
  /**
   * Read campus_paths dataset and create the campus path.
   * 
   * @param filename is the file data name
   * @param fg is the graph
   * 
   * @throws MalformedDataException if the file is not well-formed.
   */
  public static void findCamPath(String filename, findGraph<Coordinate, Double> fg) throws MalformedDataException{
	
		  filename = "src/hw8/data/"+filename;
	    BufferedReader reader = null;
	    try {
	        reader = new BufferedReader(new FileReader(filename));

	        // Construct the collections of characters and books, one
	        // <character, book> pair at a time.
	        String inputLine;
	        Coordinate location = null;
	        while ((inputLine = reader.readLine()) != null) {

	            // Ignore comment lines.
	            if (inputLine.startsWith("#")) {
	                continue;
	            }

	            // Parse the data, stripping out quotation marks and throwing
	            // an exception for malformed lines.
	            inputLine = inputLine.replace("\"", "");
	            inputLine = inputLine.replace("\t", "");
	            String[] tokens = inputLine.split(": ");
	            String[] toArr = tokens[0].split(",");
	            double x = Double.parseDouble(toArr[0]);
	            double y = Double.parseDouble(toArr[1]);
	            
	            Coordinate coor = new Coordinate(x, y);
	            
	            // tokens.length == 1 is non-intended line
	            if (tokens.length == 1) {
	            	
	            	// if the coordinate is not in the graph, add coordinate
	            	if(!fg.hasNode(coor)) {
	            		fg.addNode(coor);
	            	}
	            	
	               // set the location is the coordinate
	            	location = coor;
	            // tokens.length == 2 is non-intended line
	            } else if (tokens.length == 2) {
	            	if (location == null) {
	            		throw new MalformedDataException("Intended line should after non-intended line!");
	            	}
	            	// if the coordinate is not in the graph, add coordinate
	            	if (!fg.hasNode(coor)) {
	            		fg.addNode(coor);
	            	}
	            	fg.addEdge(location, coor, Double.parseDouble(tokens[1]));
	            } else {
	            	throw new MalformedDataException("The data file is not wel form!" + inputLine);
	            }
	            
	        }
	        } catch (IOException e) {
		    	System.err.println(e.toString());
		    	e.printStackTrace(System.err);
		    } finally {
		    	if (reader != null) {
		    		try {
		                reader.close();
		            } catch (IOException e) {
		                System.err.println(e.toString());
		                e.printStackTrace(System.err);
		            }
		    	}
		    }
  }
	  
  }


