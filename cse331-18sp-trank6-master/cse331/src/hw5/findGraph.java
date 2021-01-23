package hw5;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
/**
 * <b> findGraph</b> The graph represents <b>mutable</b> the node and edge of graph.
 * @specfield the node of graph
 * <p>
 * @specfield the edge output include destination and label of the graph
 * 
 * @author Khai Tran
 *
 * @param <A>
 * @param <B>
 */
		
public class findGraph<A, B extends Comparable<B>> {
/**
 * Rep invariant:
 * 		findGraph is not null
 * 		All node and edge in findGraph are not null
 * 		findGraph must contain n node and n edge.
 * Abstraction function:
 * 		findGraph which is f graph:
 *      f{} is empty graph
 *      if there is a node x in f graph
 *      {x=[],...} with x has no edge
 *      {x=[a(b), c(k),...], b=[...], c[...]}
 *      in that a and c are edges of graph b and k are label of the edge
 */
	
	// data structure for graph
	private final Map<A, HashSet<Lab_Edge<A,B>>> fGraph;
	/**
	 * Initial empty findGraph
	 * @effects construct empty findGraph
	 */
	public findGraph() {
		// TODO Auto-generated constructor stub
		fGraph = new HashMap<A, HashSet<Lab_Edge<A, B>>>();
		checkRep();
	}
	/**
	 * AddNode n to the graph if not exist
	 * @param node is to be add
	 * @modifies this.node
	 * @effects Add node n to the graph if not exist
	 * @return return true if add successful
	 */
	public boolean addNode(A node) {
		checkRep();
		if (node == null) {
			throw new IllegalArgumentException("Node is not null.");
		}
		// check node available
		if (fGraph.containsKey(node)) {
			return false;
		}
		// add node
		fGraph.put(node, new HashSet<Lab_Edge<A,B>>());
		return true;
		
	}
	/**
	 * add edge at to de to the graph if nodes exist in graph
	 * but the edge and label do not exist.
	 * 
	 * @param at from original edge
	 * @param de destination of edge
	 * @param lab label of edge
	 * 
	 * @effects add edge at to de to the graph if nodes exist in graph
	 * but the edge and label do not exist
	 * 
	 * @throws IllegalArgumentException if at, de and lab are null
	 * @return true if findGraph does not contains edge and label
	 *  from at and de
	 */
	
	public boolean  addEdge(A at, A de, B lab) {
		checkRep();
		if (at == null || de== null || lab == null) {
			throw new IllegalArgumentException("Node and label are not null!");
		}
		if (!fGraph.containsKey(at) || !fGraph.containsKey(de)) {
			throw new IllegalArgumentException("Node " + at +" or " +de+" does not exist!");
		}
		Set<Lab_Edge<A, B>> at_edge = fGraph.get(at);
		Lab_Edge<A, B> ed = new Lab_Edge<A, B>(de, lab);
		// check if edge available
		if (at_edge.contains(ed)) {
			return false;
		}
		at_edge.add(ed);
		checkRep();
		return true;
	}
	
	/**
	 * Return true if findGraph contains node
	 * 
	 * @param node is the node
	 * @requires node != null
	 * @return return true if findGraph contains node
	 */
	public boolean hasNode(A node) {
		checkRep();
		if(node == null) {
			throw new IllegalArgumentException("Node can not be null!");
		}
		checkRep();
		return fGraph.containsKey(node);
	}
	/**
	 * Return set of nodes
	 * 
	 * @return set of nodes
	 */
	public Set<A> getNode(){
		checkRep();
		return new HashSet<A>(fGraph.keySet());
	}
	/**
	 * Return number of nodes in findGraph
	 * 
	 * @return number of nodes in findGraph
	 */
	public int size() {
		checkRep();
		return fGraph.size();
	}
	/**
	 * Return if the findGraph is empty
	 * 
	 * @return if the findGraph is empty
	 */
	public boolean isEmpty() {
		checkRep();
		return fGraph.isEmpty();
	}
	/**
	 * Return set of edge and label of node
	 * 
	 * @param node is the node in graph
	 * @throw IllegalArgumentException if node is null
	 * @return set of edge and label of node
	 */
	public Set<Lab_Edge<A, B>> childNode(A node){
		checkRep();
		if (node == null) {
			throw new IllegalArgumentException("Node can not be null!");
		}
		if (!hasNode(node)) {
			throw new IllegalArgumentException("Node "+ node +"and label are not in graph!");
		}
		Set<Lab_Edge<A, B>> ed = new  HashSet<>(fGraph.get(node));
		checkRep();
		return ed;
	}
	/**
	 * Return the number of edges that child of node1 equal node2
	 * 
	 * @param node1 is the fist node 
	 * @param node2 is the second node
	 * @throw IllegalArgumentException if node1 and node2 == null
	 * @return the number of edges that child destination of node1 equal node2
	 */
	public int countEdge (A node1, A node2){
		checkRep();
		if (node1 == null || node2 == null) {
			throw new IllegalArgumentException("Node can not be null!");
		}
		if (!fGraph.containsKey(node1) || !fGraph.containsKey(node2)) {
			throw new IllegalArgumentException("Node "+ node1 +" or "+node2 +"Node does not exist!");
		}
		Set<Lab_Edge<A, B>> ed = fGraph.get(node1);
		int count = 0;
		for (Lab_Edge<A, B> lab : ed) {
			// number of destination edge = node
			if (lab.getDest().equals(node2)) {
				count++;
			}
		}
		checkRep();
		return count;
	}
	/**
	 * Return remove the edge from graph successful
	 * null if not found the destination with the label
	 * 
	 * @param at is the original edge
	 * @param de is destination
	 * @param lab is label of edge
	 * @modifies the target edge
	 * 
	 * @effects remove the target edge
	 * 
	 * @throw IllegalArgumentException if at, de , lab == null
	 * @return remove the edge from graph successful
	 * null if not found the destination with the label 
	 */
	
	public Lab_Edge<A, B> removeEdge(A at, A de, B lab){
		checkRep();
		if (at == null || de == null || lab == null) {
			throw new IllegalArgumentException("Node and label are not null!");
		}
		if (!fGraph.containsKey(at) || !fGraph.containsKey(de)) {
			throw new IllegalArgumentException("Node " + at +" or " +de+" does not exist!");
		}
		Set<Lab_Edge<A, B>> at_edge = fGraph.get(at);
		Lab_Edge<A, B> ed = new Lab_Edge<A, B>(de, lab);
		// check if edge available
		if (!at_edge.contains(ed)) {
			return null;
		}
		at_edge.remove(ed);
		checkRep();
		return ed;
	}
	/**
	 * Return string out put from the graph
	 * @return string out put from the graph
	 */
	public String toString () {
		checkRep();
		return fGraph.toString();
	}
	
	/*
	 * Return check if validate the invariant
	 */
	boolean flage = false;
	private void checkRep() throws RuntimeException {
            // check the graph if null
			if (fGraph == null) {
				throw new RuntimeException("The graph is not null!");
			}
			Set<A> node = fGraph.keySet();
			// check if node is null
			if(flage) {
			for(A nde : node) {
				if (nde == null) {
					throw new RuntimeException("The node is not null!");
				}
				HashSet<Lab_Edge<A, B>> n_edge = fGraph.get(nde);
				for (Lab_Edge<A,B> nE : n_edge) {
					if (nE == null) {
						throw new RuntimeException("The edge is not null!");
					}
					// check destination if node of edge not exist
					if (!fGraph.containsKey(nE.getDest())) {
						throw new RuntimeException("Destination node of edge should exist before the edge!");
					}
					
				}
			}
			}
		}
}
