package fr.umlv.rotofight.World.Element;
import fr.umlv.rotofight.Read.Map;
import fr.umlv.rotofight.World.Environment.Constants;
import fr.umlv.rotofight.World.Environment.Gravity;
import fr.umlv.rotofight.World.View.Draw;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class Walls implements Draw {
	List<Wall> walls;
	private final Color color;
	/**
	 * Walls constructor.
	 */
	public Walls(){
		this.walls = new ArrayList<>();
		this.color = Color.DARK_GRAY;
	}
	/**
	 * Gets the color of the Walls.
	 * @return
	 * 		color of the Walls.
	 */
	public Color getColor() {
		return color;
	}
	/**
	 * Gets the list of Wall.
	 * @return
	 * 		List of Wall.
	 */
	public List<Wall> getWalls() {
		List<Wall> w = new ArrayList<Wall>();
		for(Wall wall : walls) {
			w.add(wall);
		}
		return w;
	}
	/**
	 * Gets the size of the Wall array.
	 * @return
	 * 		Size of Walls.
	 */
	public int getSize() {
		return walls.size();
	}
	/**
	 * Gets the Wall indexed by i.
	 * @param i
	 * 		index of the chosen Wall.
	 * @return
	 * 		Wall indexed by i.
	 */
	public Wall getWall(int i) {
		Wall tmp = walls.get(i);
		return new Wall(tmp.getPosition(), tmp.getWidth(), tmp.getHeight());
	}
	/**
	 * Adds a Wall to the array of Wall.
	 * @param wall
	 * 		Wall to be added to the array.
	 */
	public void add(Wall wall) {
		walls.add(wall);
	}
	/**
	 * Initializes the Wall array.
	 * @param map
	 * 		Map of the game
	 * @param cst
	 * 		Constants of the game.
	 */
	public void init(Map map, Constants cst) {
		float width = cst.getWallWidth();
		float height = cst.getWallHeight();
		System.out.println(width+"*"+height);
		for (int i = 0; i < map.getColumn(); i++) {
			for (int j = 0; j < map.getLine()	; j++) {
				if (map.getIndex(j, i)==1)
					this.add(new Wall(new Position(i*width, j*height),width , height));
			}
			
		}
	}

	@Override
	public void drawElement(Graphics2D graphics, Gravity g) {
		graphics.setColor(getColor());
		for (Wall wall : getWalls()) {
			graphics.fill(wall.bodyWall());
		}
	}
}
