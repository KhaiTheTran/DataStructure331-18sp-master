package hw7.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

import hw5.Lab_Edge;
import hw5.findGraph;
import hw7.MarvelParser.MalformedDataException;
import hw7.MarvelPaths2;

/**
 * This class implements a testing driver which reads test scripts
 * from files for testing Graph, the Marvel parser, and your BFS
 * algorithm.
 **/
public class HW7TestDriver {
	public static void main(String args[]) {
        try {
            if (args.length > 1) {
                printUsage();
                return;
            }

            HW7TestDriver td;

            if (args.length == 0) {
                td = new HW7TestDriver(new InputStreamReader(System.in),
                                       new OutputStreamWriter(System.out));
            } else {

                String fileName = args[0];
                File tests = new File (fileName);

                if (tests.exists() || tests.canRead()) {
                    td = new HW7TestDriver(new FileReader(tests),
                                           new OutputStreamWriter(System.out));
                } else {
                    System.err.println("Cannot read from " + tests.toString());
                    printUsage();
                    return;
                }
            }
            td.runTests();

        } catch (IOException e) {
            System.err.println(e.toString());
            e.printStackTrace(System.err);
        }
    }

    private static void printUsage() {
        System.err.println("Usage:");
        System.err.println("to read from a file: java hw6.test.HW6TestDriver <name of input script>");
        System.err.println("to read from standard in: java hw6.test.HW6TestDriver");
    }

    /** String -> Graph: maps the names of graphs to the actual graph **/
    //TODO for the student: Parameterize the next line correctly.
    private final Map<String, findGraph<String, Double>> graphs = new HashMap<String, findGraph<String, Double>>();
    private final PrintWriter output;
    private final BufferedReader input;

    /**
     * @requires r != null && w != null
     *
     * @effects Creates a new HW5TestDriver which reads command from
     * <tt>r</tt> and writes results to <tt>w</tt>.
     **/
    public HW7TestDriver(Reader r, Writer w) {
        input = new BufferedReader(r);
        output = new PrintWriter(w);
    }

    /**
     * @effects Executes the commands read from the input and writes results to the output
     * @throws IOException if the input or output sources encounter an IOException
     **/
    public void runTests()
        throws IOException
    {
        String inputLine;
        while ((inputLine = input.readLine()) != null) {
            if ((inputLine.trim().length() == 0) ||
                (inputLine.charAt(0) == '#')) {
                // echo blank and comment lines
                output.println(inputLine);
            }
            else
            {
                // separate the input line on white space
                StringTokenizer st = new StringTokenizer(inputLine);
                if (st.hasMoreTokens()) {
                    String command = st.nextToken();

                    List<String> arguments = new ArrayList<String>();
                    while (st.hasMoreTokens()) {
                        arguments.add(st.nextToken());
                    }

                    executeCommand(command, arguments);
                }
            }
            output.flush();
        }
    }

    private void executeCommand(String command, List<String> arguments) {
        try {
            if (command.equals("CreateGraph")) {
                createGraph(arguments);
            } else if (command.equals("AddNode")) {
                addNode(arguments);
            } else if (command.equals("AddEdge")) {
                addEdge(arguments);
            } else if (command.equals("ListNodes")) {
                listNodes(arguments);
            } else if (command.equals("ListChildren")) {
                listChildren(arguments);
            } else if(command.equals("LoadGraph")) {
            	LoadGraph(arguments);
            }else if(command.equals("FindPath")) {
            	FindPath(arguments);
            }else {
            
                output.println("Unrecognized command: " + command);
            }
        } catch (Exception e) {
            output.println("Exception: " + e.toString());
        }
    }
    
    private void LoadGraph (List<String> arguments) throws MalformedDataException {
    	if (arguments.size() != 2) {
    		 throw new CommandException("Bad arguments to CreateGraph: " + arguments);
    	}
    	// load graph
    	String graphname = arguments.get(0);
    	String graphat = arguments.get(1);
    	LoadGraph(graphname, graphat);
    	
    }
    
    private void LoadGraph (String graphname, String graphat ) throws MalformedDataException {
    	graphs.put(graphname, MarvelPaths2.loadGraph(graphat)); 
    	output.println("loaded graph "+graphname);
    }
    
    private void FindPath(List<String> arguments) {
	    if (arguments.size() != 3) {
	   		 throw new CommandException("Bad arguments to CreateGraph: " + arguments);
	   	}
	    
	    String graphname = arguments.get(0);
	    String start = arguments.get(1).replace("_", " ");
	    String destination = arguments.get(2).replace("_", " ");
	    FindPath(graphname, start, destination);
    }
    private void FindPath(String graphname, String start, String destination) {
    	findGraph<String, Double> fg = graphs.get(graphname);
    	if (!fg.hasNode(start) && !fg.hasNode(destination)) {
    		output.println("unknown character " +start +" and "+ destination);
    	} else
    		
    	if(!fg.hasNode(start)) {
    		output.println("unknown character " +start);
    	} else 
    		if(!fg.hasNode(destination)) {
    			output.println("unknown character " +destination);
    	} else {
    	// load list
	    List<Lab_Edge<String, Double>> path = MarvelPaths2.AlgorDijks(fg, start, destination);
	    String outprint ="path from "+start+" to "+destination+":";
	    String currentStar = start;
   	 	if (path == null) {
   	 		outprint += "\nno path found";
   	 	}else {
   	 		double mincost = 0.0;
   	 		path = path.subList(1, path.size());
   	 		for (Lab_Edge<String, Double> l_edge : path) {
   	 			outprint += "\n"+currentStar+ " to " +l_edge.getDest() +" with weight "
   	 					+ "" + String.format("%.3f", (l_edge.getLabel() - mincost));
   	 			currentStar = l_edge.getDest();
   	 			mincost = l_edge.getLabel();
   	 		}
   	 	outprint += "\ntotal cost: "+String.format("%.3f", mincost);
   	 	}
   	 	output.println(outprint);
    	}
    }
    private void createGraph(List<String> arguments) {
        if (arguments.size() != 1) {
            throw new CommandException("Bad arguments to CreateGraph: " + arguments);
        }

        String graphName = arguments.get(0);
        createGraph(graphName);
    }

    private void createGraph(String graphName) {
        

        graphs.put(graphName, new findGraph<String, Double>());
        output.println("created graph " +graphName);
    }

    private void addNode(List<String> arguments) {
        if (arguments.size() != 2) {
            throw new CommandException("Bad arguments to addNode: " + arguments);
        }

        String graphName = arguments.get(0);
        String nodeName = arguments.get(1).replace("_", " ");

        addNode(graphName, nodeName);
    }

    private void addNode(String graphName, String nodeName) {
       findGraph<String, Double> fg = graphs.get(graphName);
       fg.addNode(nodeName);
       output.println("added node "+ nodeName +" to " + graphName);
    }

    private void addEdge(List<String> arguments) {
        if (arguments.size() != 4) {
            throw new CommandException("Bad arguments to addEdge: " + arguments);
        }

        String graphName = arguments.get(0);
        String parentName = arguments.get(1).replace("_", " ");
        String childName = arguments.get(2).replace("_", " ");
        double edgeLabel = 0.0;
        try {
        	edgeLabel = Double.parseDouble(arguments.get(3));
		} catch (NumberFormatException e) {
			// TODO: handle exception
			throw new CommandException("The last arguments should be digits!");
		}
        

        addEdge(graphName, parentName, childName, edgeLabel);
    }

    private void addEdge(String graphName, String parentName, String childName,
            Double edgeLabel) {
        findGraph<String, Double> fg = graphs.get(graphName);
        fg.addEdge(parentName, childName, edgeLabel);
        output.println(String.format("added edge %.3f", edgeLabel)+" from "+parentName+" to "+childName+" in " +graphName);
    }

    private void listNodes(List<String> arguments) {
        if (arguments.size() != 1) {
            throw new CommandException("Bad arguments to listNodes: " + arguments);
        }

        String graphName = arguments.get(0);
        listNodes(graphName);
    }

    private void listNodes(String graphName) {

        findGraph<String, Double> fg = graphs.get(graphName);
        Set<String> node = new TreeSet<String>(fg.getNode());
        
        String oput = graphName +" contains:";
        for(String n : node) {
        	oput += " " + n;
        }
        output.println(oput);
    }

    private void listChildren(List<String> arguments) {
        if (arguments.size() != 2) {
            throw new CommandException("Bad arguments to listChildren: " + arguments);
        }

        String graphName = arguments.get(0);
        String parentName = arguments.get(1).replace("_", " ");
        listChildren(graphName, parentName);
    }

    private void listChildren(String graphName, String parentName) {

         findGraph<String, Double> fg = graphs.get(graphName);
         // sort in alphabetical order the output with help of comparator
        Set<Lab_Edge<String, Double>> lab_node = fg.childNode(parentName);
        
       // lab_node.addAll(fg.childNode(parentName));
        String oput = "the children of "+parentName+" in "+graphName+" are:";
        for(Lab_Edge<String, Double> child : lab_node) {
        	oput += " "+child.getDest() + String.format("%.3f", child.getLabel()) ; 
        }
        output.println(oput);
    }

    /**
     * This exception results when the input file cannot be parsed properly
     **/
    static class CommandException extends RuntimeException {

        public CommandException() {
            super();
        }
        public CommandException(String s) {
            super(s);
        }

        public static final long serialVersionUID = 3495;
    }

}
