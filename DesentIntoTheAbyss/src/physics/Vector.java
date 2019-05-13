package physics;

/**
 * <b>Vector</b>
 * <p>Representation of a vector</p>
 * @author Neo Zhao
 * 5/7/2019
 */
public class Vector {
	//variable declarations
	private double xComp, yComp;
	
	//*Constructors*//
	public Vector(double x, double y) {
		this.xComp = x;
		this.yComp = y;
	}
	
	//*Getters and Setters*//
	public double getxComp() {return xComp;}
	public void setxComp(double xComp) {this.xComp = xComp;}
	public double getyComp() {return yComp;}
	public void setyComp(double yComp) {this.yComp = yComp;}
	
	//*Other Methods*//
	/**
	 * <b>addX</b>
	 * <p>adds the given x value to the current x component</p>
	 * @param x the value to be added to the current x component
	 */
	public void addX(double x) {
		this.xComp += x;
	}
	
	/**
	 * <b>addY</b>
	 * <p>adds the given y value to the current y component</p>
	 * @param y the value to be added to the current y component
	 */
	public void addY(double y) {
		this.yComp += y;
	}
}
