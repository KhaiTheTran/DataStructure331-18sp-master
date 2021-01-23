/**
 * This is part of HW0: Environment Setup and Java Introduction for CSE 331.
 */
package hw3;
/**
 * Khai Tran
 * CSE 331
 */
import java.lang.Iterable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * This is a container can be used to contain Balls. The key
 * difference between a BallContainer and a Box is that a Box has a
 * finite volume. Once a box is full, a client cannot put in more Balls.
 */
public class Box implements Iterable<Ball> {

    /**
     * ballContainer is used to internally store balls for this Box
     */
    private BallContainer ballContainer;
    private double maxVolume; // save max volume of box
    /**
     * Constructor that creates a new box.
     * @param maxVolume Total volume of balls that this box can contain.
     */
    public Box(double maxVolume) {
        this.maxVolume = maxVolume;
        ballContainer = new BallContainer();
    }

    /**
     * Implements the Iterable interface for this box.
     * @return an Iterator over the Ball objects contained
     * in this box.
     */
    public Iterator<Ball> iterator() {
        return ballContainer.iterator();
    }


    /**
     * This method is used to add Ball objects to this box of
     * finite volume.  The method returns true if a ball is
     * successfully added to the box, i.e., ball is not already in the
     * box and if the box is not already full; and it returns false,
     * if ball is already in the box or if the box is too full to
     * contain the new ball.
     * @param b Ball to be added.
     * @requires b != null.
     * @return true if ball was successfully added to the box,
     * i.e. ball is not already in the box and if the box is not
     * already full. Returns false, if ball is already in the box or
     * if the box is too full to contain the new ball.
     */
    public boolean add(Ball b) {
       if((getVolume()+ b.getVolume()) > maxVolume || contains(b)) {
    	   return false;
       } else {
    	   return ballContainer.add(b);
       }
    }

    /**
     * This method returns an iterator that returns all the balls in
     * this box in ascending size, i.e., return the smallest Ball
     * first, followed by Balls of increasing size.
     * @return an iterator that returns all the balls in this box in
     * ascending size.
     */
    
	public Iterator<Ball> getBallsFromSmallest() {
        Iterator<Ball> iter = iterator();
        Iterator<Ball> iter2 = iterator();
        iter2.next();
        // using array list to temporarily store Balls 
        ArrayList<Ball> temp = new ArrayList<Ball>();
        while (iter.hasNext()) {
        	temp.add(iter.next());
        }
       // sort them in increasing order
       Collections.sort(temp, new choseSmallest());
       return temp.iterator();
    }

    /**
     * Removes a ball from the box. This method returns
     * <tt>true</tt> if ball was successfully removed from the
     * container, i.e. ball is actually in the box. You cannot
     * remove a Ball if it is not already in the box and so ths
     * method will return <tt>false</tt>, otherwise.
     * @param b Ball to be removed.
     * @requires b != null.
     * @return true if ball was successfully removed from the box,
     * i.e. ball is actually in the box. Returns false, if ball is not
     * in the box.
     */
    public boolean remove(Ball b) {
        return ballContainer.remove(b);
    }

    /**
     * Each Ball has a volume. This method returns the total volume of
     * all the Balls in the box.
     * @return the volume of the contents of the box.
     */
    public double getVolume() {
       return ballContainer.getVolume();
    }

    /**
     * Returns the number of Balls in this box.
     * @return the number of Balls in this box.
     */
    public int size() {
        return ballContainer.size();
    }

    /**
     * Empties the box, i.e. removes all its contents.
     */
    public void clear() {
        ballContainer.clear();
    }

    /**
     * This method returns <tt>true</tt> if this box contains
     * the specified Ball. It will return <tt>false</tt> otherwise.
     * @param b Ball to be checked if its in box
     * @requires b != null.
     * @return true if this box contains the specified Ball. Returns
     * false, otherwise.
     */
    public boolean contains(Ball b) {
        return ballContainer.contains(b);
    }
    /**
     * choseSmallest class compare two balls and 
     * return the value 0 if B1 is numerically equal
     * to B2; a value less than 0 if B1 is numerically 
     * less than d2; and a value greater than 0 if B1 is 
     * numerically greater than B2
     *
     */
    
    public class choseSmallest implements Comparator<Ball>{
    	public int compare(Ball ball1, Ball ball2) {
    		return Double.compare(ball1.getVolume(), ball2.getVolume());
    	}
    }

}
