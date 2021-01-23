package hw5.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import hw5.Lab_Edge;
/**
 * 
 * @author Khai Tran
 *
 */
public class Lab_EdgeTest {
	
	private Lab_Edge<String, String> lab_edge;
	
	@Before
	public void setUPBeforeclass() throws Exception{
		lab_edge = new Lab_Edge<String, String>("start", "go");
	}
	@Test (expected = IllegalArgumentException.class)
	public void test_construct_null_dest() {
		new Lab_Edge<>("XX", null);
	}
	@Test (expected = IllegalArgumentException.class)
	public void test_add_null_dest() {
		new Lab_Edge<>(null, "XXYY");
	}
	@Test
	public void test_construct_get_dest() {
		assertEquals("start", lab_edge.getDest());
	}
	@Test
	public void test_construct_get_label() {
		assertEquals("go", lab_edge.getLabel());
	}
	@Test
	public void test_construct_toString() {
		assertEquals("start(go)", lab_edge.toString());
	}
	@Test
	public void test_equal_constructwithout_tostring_construct() {
		assertFalse(lab_edge.equals("start(go)"));
	}
	@Test
	public void test_equal_constructwith_same_labeledge() {
		assertTrue(lab_edge.equals(new Lab_Edge<String, String>("start", "go")));
	}
	@Test
	public void test_equal_constructwith_differ_labeledge() {
		assertFalse(lab_edge.equals(new Lab_Edge<String, String>("we", "go")));
	}
	
	@Test
	public void test_hashcode_with_same_labeledge() {
		assertEquals(lab_edge.hashCode(),("start".hashCode()+"go".hashCode()));
	}
	
	@Test
	public void test_compareTo_AlphaOrderwith_labeledge_GreaterDest() {
		assertTrue(lab_edge.compareTo(new Lab_Edge<String, String>("zstart", "go"))<0);
	}
	@Test
	public void test_compareTo_AlphaOrder_same_labeledge_equal() {
		assertTrue(lab_edge.compareTo(new Lab_Edge<String, String>("start", "go"))==0);
	}
	@Test
	public void test_compareTo_AlphaOrder__LabelEdge_lessDEst() {
		assertTrue(lab_edge.compareTo(new Lab_Edge<String, String>("dstart", "go"))>0);
	}
	@Test
	public void test_compareTo_AlphaOrder_LabelEdge_GreaterLabel() {
		assertTrue(lab_edge.compareTo(new Lab_Edge<String, String>("start", "zgo"))<0);
	}
	public void test_compareTo_AlphaOrder_LabelEdge_lessLabel() {
		assertTrue(lab_edge.compareTo(new Lab_Edge<String, String>("start", "ago"))>0);
	}
}
