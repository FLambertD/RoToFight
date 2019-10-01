package fr.umlv.rotofight.World.Element;

public class Position {
	private float x;
	private float y;
	/**
	 * Position constructor.
	 * @param x
	 * 		x value of position.
	 * @param y
	 * 		y value of position.
	 */
	public Position(float x, float y) {
		this.x = x;
		this.y= y;
	}
	/**
	 * Gets the x value of the position.
	 * @return
	 * 		the x value of the position.
	 */
	public float getX() {
		return x;
	}
	/**
	 * Gets the y value of the position.
	 * @return
	 * 		the y value of the position.
	 */
	public float getY() {
		return y;
	}
	/**
	 * Appends step given to the x value (float).
	 * @param step
	 * 		step to add to x.
	 */
	public void appendX(float step) {
		x+=step;
	}
	/**
	 * Appends step given to the y value (float).
	 * @param step
	 * 		step to add to y.
	 */
	public void appendY(float step) {
		y+=step;
	}
	/**
	 * Appends step given to the x value (double).
	 * @param step
	 * 		step to add to x.
	 */
	public void appendX(double step) {
		x+=step;
	}
	/**
	 * Appends step given to the y value (double).
	 * @param step
	 * 		step to add to y.
	 */
	public void appendY(double step) {
		y+=step;
	}
	/**
	 * Sets x to given value.
	 * @param value
	 * 		New value of x.
	 */
	public void setX(float value) {
		x = value;
	}
	/**
	 * Sets y to given value.
	 * @param value
	 * 		New value of y.
	 */
	public void setY(float value) {
		y = value;
	}
}
