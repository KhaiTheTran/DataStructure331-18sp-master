package hw7;
import java.io.*;
import java.util.*;


/**
 * Parser utility to load the Marvel Comics dataset.
 * 
 * @author Khai Tran (inherited from HW6)
 */
public class MarvelParser {
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
   * Reads the Marvel Universe dataset.
   * Each line of the input file contains a character name and a comic
   * book the character appeared in, separated by a tab character
   * 
   * @requires filename is a valid file path
   * @param filename the file that will be read
   * @param characters list in which all character names will be stored;
   *          typically empty when the routine is called
   * @param books map from titles of comic books to characters that
   *          appear in them; typically empty when the routine is called
   * @modifies characters, books
   * @effects fills books with a list of all unique character names
   *          then fill the book edge with the cost of edge
   * @effects fills characters with a map from each comic book to all characters
   *          appearing in it
   * @throws MalformedDataException if the file is not well-formed:
   *          each line contains exactly two tokens separated by a tab,
   *          or else starting with a # symbol to indicate a comment line.
   */
  public static void parseData(String filename, Map<String,
		  HashMap<String, Integer>> books) throws MalformedDataException {
    // Why does this method accept the Collections to be filled as
    // parameters rather than making them a return value? To allows us to
    // "return" two different Collections. If only one or neither Collection
    // needs to be returned to the caller, feel free to rewrite this method
    // without the parameters. Generally this is better style.
	  filename = "src/hw7/data/"+filename;
    BufferedReader reader = null;
    try {
        reader = new BufferedReader(new FileReader(filename));

        // Construct the collections of characters and books, one
        // <character, book> pair at a time.
        String inputLine;
        HashMap<String, ArrayList<String>> characters = new HashMap<String, ArrayList<String>>();
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
            if (tokens.length != 2) {
                throw new MalformedDataException("Line should contain exactly one tab: "
                                                 + inputLine);
            }

            String character = tokens[0];
            String book = tokens[1];
            
            // if character not exist, add to map
            if (!books.containsKey(character)) {
            	books.put(character, new HashMap<String, Integer>());
            }
            // Add the parsed data to the character and book collections.
           
            if (!characters.containsKey(book)) {
            	ArrayList<String> addchar = new ArrayList<String>();
            	addchar.add(character);
            	characters.put(book, addchar);
            	
            } else {
            	HashMap<String, Integer> data = books.get(character);
            	ArrayList<String> boo = characters.get(book);
            	
            	// count the cost for character in the node
            	for (String charac : boo) {
            		if (!data.containsKey(charac)) {
            			// add new cost with new character
            			data.put(charac, 1);
            		} else {
            			// update cost exist character
            			data.put(charac, data.get(charac) + 1);
            		}
      
            		// count the cost for character in the edge
            		HashMap<String, Integer> characN = books.get(charac);
            		if (!characN.containsKey(character)) {
            			characN.put(character, 1);
            		} else {
            			characN.put(character, characN.get(character) +1 );
            		}
            		
            	}
            
            // add character to book
            characters.get(book).add(character);
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
