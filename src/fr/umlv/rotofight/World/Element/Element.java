package fr.umlv.rotofight.World.Element;

public class Element {
	private Position position;
	/**
	 * Element constructor.
	 * @param pos
	 * 		The position of the element.
	 */
	Element(Position pos){
		this.position = pos;
	}
	/**
	 * Get the position of the current Element.
	 * @return
	 * 		the position (x, y).
	 */
	public Position getPosition() {
		return new Position(position.getX(),position.getY());
	}
	/**
	 * Append a vector to the current position of the element (float).
	 * @param x
	 * 		x value of the vector.
	 * @param y
	 * 		y value of the vector.
	 */
	public void appendPosition(float x, float y) {
		position.appendX(x);
		position.appendY(y);
	}
	/**
	 * Append a vector to the current position of the element (double).
	 * @param x
	 * 		x value of the vector.
	 * @param y
	 * 		y value of the vector.
	 */
	public void appendPosition(double x, double y) {
		position.appendX(x);
		position.appendY(y);
	}
	/**
	 * Set the position of the element(float).
	 * @param x
	 * 		x value of the position.
	 * @param y
	 * 		y value of the position.
	 */
	public void setPos(float x, float y) {
		position.setX(x);
		position.setY(y);
	}
	/**
	 * Set the x value of the position of the element.
	 * @param x
	 * 		x value of the position.
	 */
	public void setX(float x) {
		position.setX(x);
	}
	/**
	 * Set the y value of the position of the element.
	 * @param y
	 * 		y value of the position.
	 */
	public void setY(float y) {
		position.setY(y);
	}
}
