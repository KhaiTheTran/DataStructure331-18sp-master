package hw5;
/**
 * <b>Lab_Edge</b> represents edge, label and the destination of edge
 * Label is the label of the edge
 * Destination is the the destination of edge.
 * <p>
 * 
 * @author Khai Tran
 *
 * @param <A>
 * @param <B>
 */

public class Lab_Edge<A,B extends Comparable<B>> implements Comparable<Lab_Edge<A,B>> {
	// Representation invariant:
	//   lab != null and dest != null
	
	//  Abstraction Function:
	//  This abstraction function is not lab_E:
	//   this.dest = lab_E.dest
	//   this.lab = lab_E.lab 
	
    // Edge destination
	private final A dest;
	// Edge label
	private final B lab;
	/**
	 * Initial edge of destination and label
	 * 
	 * @param de is the destination
	 * @param lab is the label
	 * @requires de != null and lab != null
	 * @effects constructs edge of destination and label
	 */
	public Lab_Edge(A de, B lab ) {
		// TODO Auto-generated constructor stub
		if(de == null || lab == null) {
			throw new IllegalArgumentException("Destination and label are not null!");
		}
		this.dest = de;
		this.lab = lab;
		checkRep();
	}
	/*
	 * (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	
	@Override
	public int compareTo(Lab_Edge<A, B> lab_E) {
		// TODO Auto-generated method stub
		checkRep();
		// compare label
		if (!(this.lab.equals(lab_E.lab))) {
			return this.lab.compareTo(lab_E.lab);
			
		}
		// compare Destination by hashcode
		if (!(this.dest.equals(lab_E.dest))) {
			checkRep();
			return ((String) this.dest).compareTo((String) lab_E.dest);
		}
		
		return 0;
	}
	/*
	 * Return check of invariant is valid or not.
	 */
    public void checkRep()throws RuntimeException{
    	if (this.dest == null || this.lab == null) {
    		throw new RuntimeException("Destination or label should not be null!");
    	}
    }
    /*
     * Return destination of edge
     *  
     *@return destination of edge
     */
    public A getDest() {
    	checkRep();
    	return dest;
    }
    /*
     * return label of edge
     * @return destination of edge
     */
    public B getLabel() {
    	checkRep();
    	return this.lab;
    }
    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
    	checkRep();
    	return dest.toString() + "("+lab.toString()+")";
    }
    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
    	checkRep();
    	if (!(obj instanceof Lab_Edge<?, ?>)) {
    		return false;
    	}
    	Lab_Edge<?,?> lab_E = (Lab_Edge<?, ?>)obj;
    	checkRep();
    	return this.dest.equals(lab_E.dest) && this.lab.equals(lab_E.lab);
    }
    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
    	checkRep();
    	return this.dest.hashCode()+ this.lab.hashCode();
    }
}
