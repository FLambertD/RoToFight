package fr.umlv.rotofight.World.Element;

import java.awt.geom.Line2D;

public class Sword extends Element{
	private final float size;
	private final double[] absoluteDirection;
	/**
	 * Sword constructor.
	 * @param position
	 * 		Position of the sword.
	 * @param size
	 * 		Size of the sword.
	 * @param player
	 * 		Player who has thrown the sword.
	 */
	public Sword(Position position, float size, Player player) {
		super(position);
		this.size = size;
		this.absoluteDirection = player.getAbsoluteDirection();
	}
	/**
	 * Sword constructor.
	 * @param position
	 * 		Position of the sword.
	 * @param size
	 * 		Size of the sword.
	 * @param absoluteDirection
	 * 		Direction of the sword.
	 */
	public Sword(Position position, float size, double[] absoluteDirection) {
		super(position);
		this.size = size;
		this.absoluteDirection = absoluteDirection;
	}
	/**
	 * Gets the size of sword.
	 * @return
	 * 		Size of the sword.
	 */
	public float getSize() {
		return size;
	}
	/**
	 * Gets a copy of the direction of the sword.
	 * @return
	 * 		Direction of the Sword.
	 */
	public double[] getAbsoluteDirection() {
		return absoluteDirection.clone();
	}
	/**
	 * Moves the sword.
	 */
	public void move() {
		appendPosition(absoluteDirection[0] * 15, absoluteDirection[1] * 15);
	}
	/**
	 * Creates the body of the sword.
	 * @return
	 * 		The shape of the sword.
	 */
	public Line2D.Double bodySword() {
		Line2D.Double body = new Line2D.Double(
				getPosition().getX(),
				getPosition().getY(),
				getPosition().getX() + absoluteDirection[0] * size,
				getPosition().getY() + absoluteDirection[1] * size);
		return body;
	}
}
