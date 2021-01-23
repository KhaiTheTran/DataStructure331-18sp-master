package hw8;
/**
 * This class stored the coordinate
 * 
 * @author Khai Tran
 *
 */
public class Coordinate {

	private Double x;
	private Double y;
	
	/**
	 * Initial construct coordinate
	 * @param x, y is the point of coordinate
	 * 
	 */
	public Coordinate(double x, double y) {
		this.x = x;
		this.y = y;
		checkRep();
	}
	/**
	 * Get the value of x coordinate
	 * @return value of x coordinate
	 */
	public double getX() {
		checkRep();
		return this.x;
	}
	/**
	 * Get the value of y coordinate
	 * @return value of y coordinate
	 */
	public double getY() {
		checkRep();
		return this.y;
	}
	/**
	 * Compare between obj and coordinate
	 * @param obj is the object to be compare
	 * @return true if obj the same with coordinate else false
	 */
	public boolean equals(Object obj) {
		checkRep();
		if (!(obj instanceof Coordinate)) {
			return false;
		}
		Coordinate coor = (Coordinate) obj;

		return coor.x.equals(this.x) && coor.y.equals(this.y);
	}
	/**
	 * return a hashCode of this.Coordinate
	 * @return hashCode of this.Coordinate
	 */
	@Override
	public int hashCode() {
		checkRep();
		return x.hashCode() + y.hashCode();
	}
	/**
	 * check invariant 
	 */
	private void checkRep() {
		if (this.x == null && this.y == null) {
			throw new RuntimeException("Coordinate x and y can not be null!");
		}else if(this.x == null){
			throw new RuntimeException("Coordinate x and y can not be null!");
		}else if(this.y == null) {
			throw new RuntimeException("Coordinate x and y can not be null!");
		}
	}
}
