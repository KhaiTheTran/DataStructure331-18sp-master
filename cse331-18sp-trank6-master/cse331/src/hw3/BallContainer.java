/**
 * This is part of HW0: Environment Setup and Java Introduction for CSE 331.
 */
package hw3;
/**
 * Khai Tran
 * CSE 331
 */
import java.lang.Iterable;
import java.util.Set;
import java.util.LinkedHashSet;
import java.util.Iterator;
import java.util.Collections;

/**
 * This is a container can be used to contain Balls.
 * A given Ball may only appear in a BallContainer once.
 */
public class BallContainer implements Iterable<Ball> {

    // Contents of the BallContainer.
    private Set<Ball> contents;
    private double ball_V; // to save volume of balls
    private int ball_num;  // number of balls
    /**
     * Constructor that creates a new ballcontainer.
     */
    public BallContainer() {
        contents = new LinkedHashSet<Ball>();
        ball_V = 0;
        ball_num = 0;
        
    }

    /**
     * Implements the Iterable interface for this container.
     * @return an Iterator over the Ball objects contained
     * in this container.
     */
    public Iterator<Ball> iterator() {
        // If we just returned the iterator of "contents", a client
        // could call the remove() method on the iterator and modify
        // it behind our backs.  Instead, we wrap contents in an
        // "unmodifiable set"; calling remove() on this iterator
        // throws an exception.  This is an example of avoiding
        // "representation exposure."  You will learn more about this
        // concept later in the course.
        return Collections.unmodifiableSet(contents).iterator();
    }

    /**
     * Adds a ball to the container. This method returns <tt>true</tt>
     * if ball was successfully added to the container, i.e. ball is
     * not already in the container. Of course, you are allowed to put
     * a Ball into a container only once. Hence, this method returns
     * <tt>false</tt>, if ball is already in the container.
     * @param b Ball to be added.
     * @requires b != null.
     * @return true if ball was successfully added to the container,
     * i.e. ball is not already in the container. Returns false, if ball is
     * already in the container.
     */
    public boolean add(Ball b) {
    	
    	if(!contents.contains(b)) {
    	    ball_V += b.getVolume();
    	    ball_num ++;
    		return contents.add(b);
    	} 
    	
    	return false;
    }

    /**
     * Removes a ball from the container. This method returns
     * <tt>true</tt> if ball was successfully removed from the
     * container, i.e. ball is actually in the container. You cannot
     * remove a Ball if it is not already in the container and so ths
     * method will return <tt>false</tt>, otherwise.
     * @param b Ball to be removed.
     * @requires b != null.
     * @return true if ball was successfully removed from the container,
     * i.e. ball is actually in the container. Returns false, if ball is not
     * in the container.
     */
    public boolean remove(Ball b) {
    	
    	if (contents.contains(b)) {
    		ball_V -= b.getVolume();
    		ball_num --;
    		return contents.remove(b);
    	}
    	
    	return false;
        
    }

    /**
     * Each Ball has a volume. This method returns the total volume of
     * all the Balls in the container.
     * @return the volume of the contents of the container.
     */
    public double getVolume() { 
       return this.ball_V;
    }

    /**
     * Returns the number of Balls in this container.
     * @return the number of Balls in this container.
     */
    public int size() {
        return ball_num;
    }

    /**
     * Empties the container, i.e. removes all its contents.
     */
    public void clear() {
        ball_num = 0;
        ball_V = 0;
        contents.clear();
    }

    /**
     * This method returns <tt>true</tt> if this container contains
     * the specified Ball. It will return <tt>false</tt> otherwise.
     * @param b Ball to be checked if its in container
     * @requires b != null.
     * @return true if this container contains the specified Ball. Returns
     * false, otherwise.
     */
    public boolean contains(Ball b) {
    	Iterator<Ball> iter = contents.iterator();
        if(b != null) {
        	while(iter.hasNext()) {
        		if(iter.next().equals(b)) {
        			return true;
        		}
        	}
        }
        return false;
    }

}
