package fr.umlv.rotofight.World.Environment;
import fr.umlv.rotofight.Read.Map;
import fr.umlv.zen5.ApplicationContext;
import fr.umlv.zen5.ScreenInfo;

public class Constants {
	private final float width;
	private final float height;
	private final float wallWidth;
	private final float wallHeight;
	private final float playerRadius;
	private final float swordSize;
	private final int maxSwords;
	private float zoom;
	/**
	 * Constants constructor.
	 * @param context
	 * 		ApplicationContext of the game.
	 * @param map
	 * 		Map of the game.
	 * @param maxSwords
	 * 		Maximum amount of Sword per player.
	 */
	public Constants(ApplicationContext context, Map map, int maxSwords) {
		ScreenInfo screeninfo = context.getScreenInfo();
		this.width =  obtainWidth(screeninfo);
		this.height = obtainHeight(screeninfo);
		this.wallWidth = obtainEntityWidth(width, map);
		this.wallHeight = obtainWallHeight(height, map);
		this.playerRadius = obtainRadius(wallWidth, wallHeight);
		this.swordSize = obtainEntityWidth(width, map)/2;
		this.maxSwords = maxSwords;
		this.zoom = 1;
	}
	/**
	 * Gets the height of the screen.
	 * @param screeninfo
	 * 		ScreenInfo of the game.
	 * @return
	 * 		height of the screen.
	 */
	private static float obtainHeight(ScreenInfo screeninfo) {
		return screeninfo.getHeight();
	}
	/**
	 * Gets the width of the screen.
	 * @param screeninfo
	 * 		ScreenInfo of the game.
	 * @return
	 * 		width of the screen.
	 */
	private static float obtainWidth(ScreenInfo screeninfo) {
		return screeninfo.getWidth();
	}
	/**
	 * Gets the height of Wall.
	 * @param height
	 * 		height of screen.
	 * @param map
	 * 		Map of the game.
	 * @return
	 * 		height of Wall.
	 */
	private static float obtainWallHeight(float height, Map map) {
	    return height/map.getLine();
	}
	/**
	 * Gets the width of Wall.
	 * @param height
	 * 		width of screen.
	 * @param map
	 * 		Map of the game.
	 * @return
	 * 		width of Wall.
	 */
	private static float obtainEntityWidth(float width, Map map) {
	    return width/(map.getColumn() - 1);
	}
	/**
	 * Gets the radius of Player.
	 * @param width
	 * 		width of the screen.
	 * @param height
	 * 		height of the screen.
	 * @return
	 * 		radius of Player.
	 */
	private static float obtainRadius(float width, float height) {
		if (width<height)
			return width - 1;
		else
			return height - 1;
	}
	/**
	 * Appends zoom the given step.
	 * @param stepZoom
	 * 		step of the zoom.
	 */
	public void appendZoom(float stepZoom) {
		if (zoom + stepZoom > 1){
			zoom = 1;
			return;
		}
		zoom = zoom + stepZoom;
		if (zoom <= 0 || zoom * getWallHeight()<16 || zoom * getWallWidth()<16)
			zoom = zoom - stepZoom;
	}
	/**
	 * Gets Wall height.
	 * @return
	 * 		Wall height.
	 */
	public float getWallHeight() {
		return wallHeight;
	}
	/**
	 * Gets the height of the game.
	 * @return
	 * 		Game's height.
	 */
	public float getHeight() {
		return height;
	}
	/**
	 * Gets Wall's width.
	 * @return
	 * 		Wall's width.
	 */
	public float getWallWidth() {
		return wallWidth;
	}
	/**
	 * Gets the size of the Sword.
	 * @return
	 * 		Sword size.
	 */
	public float getSwordSize() {
		return swordSize;
	}
	/**
	 * Gets the radius of the player.
	 * @return
	 * 		Player's radius.
	 */
	public float getPlayerRadius() {
		return playerRadius;
	}
	/**
	 * Gets the width of the game.
	 * @return
	 * 		Game's width.
	 */
	public float getWidth() {
		return width;
	}
	/**
	 * Gets the max swords per player.
	 * @return
	 * 		Max swords per player.
	 */
	public int getMaxSwords() {
		return maxSwords;
	}
	/**
	 * Gets the value of the zoom.
	 * @return
	 * 		Zoom's value.
	 */
	public float getZoom() {
		return zoom;
	}
}
