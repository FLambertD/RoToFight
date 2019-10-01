package fr.umlv.rotofight.World.View;

import java.awt.*;
import java.awt.geom.Rectangle2D;

import fr.umlv.rotofight.World.Element.Player;
import fr.umlv.rotofight.World.Element.Walls;
import fr.umlv.rotofight.World.Environment.Gravity;
import fr.umlv.zen5.ApplicationContext;
import fr.umlv.zen5.ScreenInfo;


public interface Draw {
	/**
	 * Draws the Element on screen.
	 * @param graphics
	 * 		Graphics2D of the game.
	 * @param g
	 * 		Current Gravity.
	 */
	void drawElement(Graphics2D graphics, Gravity g);
	/**
	 * Sets a zoom on the game window.
	 * @param graphics
	 * 		Graphics2D of the Game.
	 * @param zoom
	 * 		Scale of the zoom.
	 * @param width
	 * 		width of the screen.
	 * @param height
	 * 		height of the screen.
	 */
	public static void zoomAction(Graphics2D graphics, float zoom, float width, float height){
		graphics.translate((1-zoom)/2*width, (1-zoom)/2* height);
		graphics.scale(zoom,zoom);
	}
	/**
	 * Draws all elements instantiated in the game.
	 * @param context
	 * 		ApplicationContext of the game.
	 * @param player1
	 * 		Player1.
	 * @param player2
	 * 		Player2.
	 * @param board
	 * 		Walls of the game.
	 * @param zoom
	 * 		Current zoom value.
	 * @param g
	 * 		Current Gravity.
	 */
	public static void drawAll(ApplicationContext context, Player player1, Player player2, Walls board, float zoom, Gravity g) {
		ScreenInfo screenInfo = context.getScreenInfo();
	    float width = screenInfo.getWidth(); 
	    float height = screenInfo.getHeight();
		context.renderFrame(graphics -> {
			graphics.setColor(Color.WHITE);
		    graphics.fill(new  Rectangle2D.Float(0, 0, width, height));
		    zoomAction(graphics,zoom,width,height);
		    board.drawElement(graphics, g);
			player1.drawElement(graphics, g);
			player1.getSwords().drawElement(graphics, g);
			player2.drawElement(graphics, g);
			player2.getSwords().drawElement(graphics, g);
		});		
	}
}
