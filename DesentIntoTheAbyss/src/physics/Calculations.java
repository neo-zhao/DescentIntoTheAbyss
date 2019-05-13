package physics;

/**
 * <b>Calculations</b>
 * <p>just a class with a bunch of static methods that are useful in claculations</p>
 * @author abori
 * 5/11/2019
 */
public class Calculations {
	/**
	 * <b>isBetween</b>
	 * <p>returns true if c is between a and b</p>
	 * @param a the lower bound
	 * @param b the upper bound
	 * @param c the value that will be tested to see if it is between the bounds
	 * @return true if c is between a and b
	 */
	public static boolean isBetween(double a, double b, double c) {
		return (a < c) && (c < b);
	}
	
	/**
	 * <b>hasOverlap</b>
	 * <p>returns true if bounds of a overlaps with b given their upper and lower bounds</p>
	 * @param aLower lower bound of a
	 * @param aUpper upper bound of a
	 * @param bLower lower bound of b
	 * @param bUpper lower bound of b
	 * @return true if bounds of a overlaps with b given their upper and lower bounds
	 */
	public static boolean hasOverlap(double aLower, double aUpper, double bLower, double bUpper) {
		//check if bounds of b are between a
		if (isBetween(aLower, aUpper, bLower) || isBetween(aLower,aUpper, bUpper)) {
			return true;
		}
		//check if bounds of a are between b
		if (isBetween(bLower, bUpper, aLower) || isBetween(bLower,bUpper, aUpper)) {
			return true;
		}
		//if no overlaps
		return false;
	}
	
	
}
