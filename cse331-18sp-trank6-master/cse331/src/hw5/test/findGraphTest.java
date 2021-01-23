package hw5.test;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import hw5.*;
public class findGraphTest {


	private final String node_XX = "XX";
	private final String node_YY = "YY";
	private final String edge_XXXX = "XXXX";
	private final String edge_XXYY = "XXYY";
	private final String edge_XXYY9 = "XXYY999";
	private final String edge_YYXX = "YYXX";
	private final String edge_YYYY = "YYYY";
	
	private  findGraph<String, String> findgraph;
	private  Set<String> nodes;
	private  Set<Lab_Edge<String, String>> edges;
	@Before
	public void setUpBeforeClass() throws Exception {
		findgraph = new findGraph<String, String>();
		nodes = new HashSet<String>();
		edges = new HashSet<Lab_Edge<String, String>>();
	}

	@Test
	public void testconstructedIsempty() {
		
		assertTrue(findgraph.isEmpty());
	}
    
	@Test
	public void testconstructedSize() {
		
		assertEquals(0, findgraph.size());
	}
	

	@Test
	public void testconstructedtoString() {
		
		assertEquals("{}", findgraph.toString());
	}
	
	@Test
	public void testconstructedGetnode() {
		
		assertEquals(nodes, findgraph.getNode());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testAddNullNode() {
		
		findgraph.addNode(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testhasNullNodesentnull() {
		
		findgraph.hasNode(null);
	}
	@Test(expected = IllegalArgumentException.class)
	public void testNullnodeofchild() {
		
		findgraph.childNode(null);
	}
	@Test
	public void testnode_XXbutnotAdded() {
		
		assertFalse(findgraph.hasNode(node_XX));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testchildnoNode_xx() {
		
		findgraph.childNode(node_XX);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testaddEdgewith_all_null() {
		
		findgraph.addEdge(null, null, null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testaddEdgewith_de_label_null() {
		
		findgraph.addEdge(node_XX, null, null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testaddEdgewith_label_null() {
		
		findgraph.addEdge(node_XX, node_YY, null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_removeEdgewith_at_de_label_null() {
		
		findgraph.removeEdge(null, null, null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_removeEdgewith_de_label_null() {
		
		findgraph.removeEdge(node_XX, null, null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_removeEdgewith_label_null() {
		
		findgraph.removeEdge(node_XX, node_YY, null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_countEdgewith_NODE_1_null() {
		
		findgraph.countEdge(null, null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_countEdgewith_NODE_2_null() {
		
		findgraph.countEdge(node_XX, null);
	}
	
	@Test
	public void test_add_node1() {
		
		assertTrue(findgraph.addNode(node_XX));
	}
	
	@Test
	public void test_add_node1_checkempty() {
		test_add_node1();
		assertFalse(findgraph.isEmpty());
	}
	
	@Test
	public void test_size_add_node1() {
		test_add_node1();
		assertEquals(1,findgraph.size());
	}
	
	@Test
	public void test_toString_add_node1() {
		test_add_node1();
		assertEquals("{XX=[]}",findgraph.toString());
	}
	
	@Test
	public void test_hasNode_add_node1() {
		test_add_node1();
		assertTrue(findgraph.hasNode("XX"));
	}
	
	@Test
	public void test_hasNodeYY_add_nodeXX() {
		test_add_node1();
		
		assertFalse(findgraph.hasNode(node_YY));
	}
	@Test
	public void test_childof_add_nodeXX() {
		test_add_node1();
		assertTrue(findgraph.childNode(node_XX).isEmpty());
	}
	
	@Test
	public void test_getnode_add_nodeXX() {
		test_add_node1();
		nodes.add("XX");
		assertEquals(nodes, findgraph.getNode());;
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_addEdgeXXYYwithout_NODE_XX() {
		//test_add_node1();
		findgraph.addEdge(node_XX, node_YY, edge_XXYY);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_addEdgeYYXXwithout_NODE_YY() {
		//test_add_node1();
		findgraph.addEdge(node_YY, node_XX, edge_YYXX);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_countEdgefrom_nodeXXtonodeYYwithout_NODE_YY() {
		//test_add_node1();
		findgraph.countEdge(node_YY, node_XX);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_countEdgefrom_nodeYYtonodeXXwithout_NODE_YY() {
		//test_add_node1();
		findgraph.countEdge(node_XX, node_YY);
	}
	
	@Test
	public void test_add_duplicate() {
		test_add_node1();
		assertFalse(findgraph.addNode(node_XX));
	}
	
	@Test
	public void test_size_add_duplicate() {
		test_add_duplicate();
		assertEquals(1,findgraph.size());
	}
	
	@Test
	public void test_toString_add_duplicate() {
		test_add_duplicate();
		assertEquals("{XX=[]}",findgraph.toString());
	}
	
	
	@Test
	public void test_add_differenceNodes() {
		test_add_node1();
		assertTrue(findgraph.addNode(node_YY));
	}
	
	@Test
	public void test_getnodes_withmorethanonenode() {
		test_add_differenceNodes();
		nodes.add(node_XX);
		nodes.add(node_YY);
		assertEquals(nodes,findgraph.getNode());
	}
	@Test
	public void test_numberofEdge_betweenNodeYYandNodeXXnotadd_edge() {
		test_add_differenceNodes();
		assertEquals(0,findgraph.countEdge(node_YY, node_XX));
	}
	@Test
	public void test_numberofEdge_betweenNodexxandNodeyynotadd_edge() {
		test_add_differenceNodes();
		assertEquals(0,findgraph.countEdge(node_XX, node_YY));
	}
	@Test
	public void test_add_Edge_of_NodeXX() {
		test_add_node1();
		assertTrue(findgraph.addEdge(node_XX, node_XX, edge_XXXX));
	}
	@Test
	public void test_child_of_Edge_of_NodeXX() {
		test_add_Edge_of_NodeXX();
		edges.add(new Lab_Edge<String, String>("XX","XXXX"));
		assertEquals(edges, findgraph.childNode(node_XX));
	}
	@Test
	public void test_toString_of_Edge_of_NodeXX() {
		test_add_Edge_of_NodeXX();
		
		assertEquals("{XX=[XX(XXXX)]}", findgraph.toString());
	}
	@Test
	public void test_number_of_Edge_nodeXX_of_NodeXX() {
		test_add_Edge_of_NodeXX();
		
		assertEquals(1, findgraph.countEdge(node_XX, node_XX));
	}
	@Test(expected = IllegalArgumentException.class)
	public void test_remove_of_Edge_of_NodeXX() {
		test_add_Edge_of_NodeXX();
		findgraph.removeEdge(node_XX, node_YY, edge_XXXX);
	}
	@Test
	public void testRemove_Edge_NodeXX() {
		test_add_Edge_of_NodeXX();
		assertEquals(new Lab_Edge<String, String>("XX", "XXXX"), 
				     findgraph.removeEdge(node_XX, node_XX, edge_XXXX));
	}
	@Test
	public void test_remove_of_Edge_of_NodeXX_differ_lable() {
		test_add_Edge_of_NodeXX();
		
	assertTrue(findgraph.removeEdge(node_XX, node_XX, edge_XXYY) == null);
	}
	@Test
	public void test_child_remove_of_Edge_of_NodeXX() {
		testRemove_Edge_NodeXX();
	assertEquals("{XX=[]}", findgraph.toString());
	}
	
	@Test
	public void test_child_remove_affter_Edge_of_NodeXX() {
		testRemove_Edge_NodeXX();
	assertEquals(edges, findgraph.childNode(node_XX));
	}
	
	@Test
	public void test_add_of_Edge_between_2NodeXX() {
		test_add_differenceNodes();
	assertTrue(findgraph.addEdge(node_XX, node_YY, edge_XXYY));
	}
	
	@Test
	public void test_addchildnode_of_Edge_between_2NodeXX() {
		test_add_of_Edge_between_2NodeXX();
	    edges.add(new Lab_Edge<String, String>("YY","XXYY"));
	    assertEquals(edges, findgraph.childNode(node_XX));
	}
	
	@Test
	public void test_addchildnode_of_Edge_between_2NodeXXnodeXX() {
		test_add_of_Edge_between_2NodeXX();
	    edges.add(new Lab_Edge<String, String>("YY","XXYY"));
	    assertEquals(edges, findgraph.childNode(node_XX));
	}
	@Test
	public void test_addchildnode_of_Edge_between_2NodeXXandnodeYY() {
		test_add_of_Edge_between_2NodeXX();
	    assertEquals(edges, findgraph.childNode(node_YY));
	}
	
	@Test
	public void test_numberofNode_of_Edge_between_2NodeXXandnodeXX() {
		test_add_of_Edge_between_2NodeXX();
	    assertEquals(1, findgraph.countEdge(node_XX, node_YY));
	}
	@Test
	public void test_numberofNode_of_Edge_between_2NodeXXandnodeYY() {
		test_add_of_Edge_between_2NodeXX();
	    assertEquals(0, findgraph.countEdge(node_YY, node_XX));
	}
	
	@Test
	public void test_addsame_Edge_between_2NodeXXandnodeYY() {
		test_add_of_Edge_between_2NodeXX();
	    assertFalse(findgraph.addEdge(node_XX, node_YY, edge_XXYY));
	}
	@Test
	public void test_addsame_Edge_childrenofnode() {
		test_addsame_Edge_between_2NodeXXandnodeYY();
		edges.add(new Lab_Edge<String, String>("YY", "XXYY"));
	    assertEquals(edges, findgraph.childNode(node_XX));
	}
	@Test
	public void test_afteraddsame_Edge_childrenofnode() {
		test_addsame_Edge_childrenofnode();
		
	    assertEquals(new HashSet<Lab_Edge<String, String>>(), findgraph.childNode(node_YY));
	}
	
	@Test
	public void test_add2invertnode_of_Edge_between_2NodeXXXXandnodeYY() {
		test_add_of_Edge_between_2NodeXX();
	    assertTrue(findgraph.addEdge(node_YY, node_XX, edge_YYXX));
	}
	
	@Test
	public void test_addchildren_2invertnode_of_Edge_between_2edge() {
		test_add2invertnode_of_Edge_between_2NodeXXXXandnodeYY();
		edges.add(new Lab_Edge<String, String>("YY", "XXYY"));
	    assertEquals(edges, findgraph.childNode(node_XX));
	}
	
	@Test
	public void test_addchildrennodeYYXX_2invertnode_of_Edge_between_2edge() {
		test_add2invertnode_of_Edge_between_2NodeXXXXandnodeYY();
		edges.add(new Lab_Edge<String, String>("XX", "YYXX"));
	    assertEquals(edges, findgraph.childNode(node_YY));
	}
	
	
	@Test
	public void test_create_graphwith2nodes() {
		test_add_Edge_of_NodeXX();
		assertTrue(findgraph.addNode(node_YY));
		assertTrue(findgraph.addEdge(node_XX, node_YY, edge_XXYY));
		assertTrue(findgraph.addEdge(node_YY, node_XX, edge_YYXX));
		assertTrue(findgraph.addEdge(node_YY, node_YY, edge_YYYY));
	}
	@Test
	public void test_childrenedge_graphwith2nodes() {
		test_create_graphwith2nodes();
		edges.add(new Lab_Edge<String, String>("XX", "XXXX"));
		edges.add(new Lab_Edge<String, String>("YY", "XXYY"));
		assertEquals(edges, findgraph.childNode(node_XX));
	}
	@Test
	public void test_childrenedge_graphwith2nodesYYXX() {
		test_create_graphwith2nodes();
		edges.add(new Lab_Edge<String, String>("XX", "YYXX"));
		edges.add(new Lab_Edge<String, String>("YY", "YYYY"));

		assertEquals(edges, findgraph.childNode(node_YY));
	}
	
	@Test
	public void test_numberofnode_graphwith2nodesXXXX() {
		test_create_graphwith2nodes();		
		assertEquals(1, findgraph.countEdge(node_XX, node_XX));
	}
	@Test
	public void test_numberofnodeXXYY_graphwith2nodes() {
		test_create_graphwith2nodes();
		assertEquals(1, findgraph.countEdge(node_XX, node_YY));
	}
	@Test
	public void test_greatnode_graphwith2nodes() {
		test_create_graphwith2nodes();
		assertTrue(findgraph.addEdge(node_XX, node_YY, edge_XXYY9));
	}
	@Test
	public void test_childrengreatnode_graphwith2nodes() {
		test_greatnode_graphwith2nodes();
		edges.add(new Lab_Edge<String, String>("XX", "XXXX"));
		edges.add(new Lab_Edge<String, String>("YY", "XXYY"));
		edges.add(new Lab_Edge<String, String>("YY", "XXYY999"));
		assertEquals(edges, findgraph.childNode(node_XX));
	}
	@Test
	public void test_childrengreatnodeYYXX_graphwith2nodes() {
		test_greatnode_graphwith2nodes();
		edges.add(new Lab_Edge<String, String>("XX", "YYXX"));
		edges.add(new Lab_Edge<String, String>("YY", "YYYY"));
		
		assertEquals(edges, findgraph.childNode(node_YY));
	}
	@Test
	public void test_childrengreatnodeXXXX_graphwith2nodes() {
		test_greatnode_graphwith2nodes();		
		
		assertEquals(1, findgraph.countEdge(node_XX, node_XX));
	}
	
	@Test
	public void test_childrenXXgreatnodeYYXX_graphwith2nodes() {
		test_greatnode_graphwith2nodes();		
		
		assertEquals(2, findgraph.countEdge(node_XX, node_YY));
	}
	
	@Test
	public void test_childrenYYgreatnodeYYXX_graphwith2nodes() {
		test_greatnode_graphwith2nodes();		
		
		assertEquals(1, findgraph.countEdge(node_YY, node_XX));
	}
	

	@Test
	public void test_childrenYYgreatnodeYYYY_graphwith2nodes() {
		test_greatnode_graphwith2nodes();		
		
		assertEquals(1, findgraph.countEdge(node_YY, node_YY));
	}
	@Test
	public void test_() {
		test_greatnode_graphwith2nodes();		
		
		assertEquals(1, findgraph.countEdge(node_YY, node_YY));
	}
	
}
