package fr.umlv.rotofight.World.Element;
import java.awt.Color;
import java.awt.geom.Rectangle2D;

public class Wall extends Element {
	private final Color color;
	private final float height;
	private final float width;
	/**
	 * Wall constructor.
	 * @param position
	 * 		the position of the wall.
	 * @param height
	 * 		the height of the wall.
	 * @param width
	 * 		the width of the wall.
	 */
	public Wall(Position position, float height, float width) {
		super(position);
		this.color = Color.DARK_GRAY;
		this.height = width;
		this.width = height;
	}
	/**
	 * Gets the color of the Wall.
	 * @return
	 * 		Color of the Wall.
	 */
	public Color getColor() {
		return color;
	}
	/**
	 * Gets the width of the Wall.
	 * @return
	 * 		the width of the Wall.
	 */
	public float getWidth() {
		return width;
	}
	/**
	 * Gets the height of the Wall.
	 * @return
	 * 		the height of the Wall.
	 */
	public float getHeight() {
		return height;
	}
	/**
	 * Creates the body of the Wall.
	 * @return
	 * 		The shape of the Wall.
	 */
	public Rectangle2D.Double bodyWall() {
		Rectangle2D.Double body = new Rectangle2D.Double(
				getPosition().getX(),
				getPosition().getY(),
				getWidth(),
				getHeight());
		return body;
	}
}
