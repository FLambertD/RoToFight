package fr.umlv.rotofight.World.Event;
import fr.umlv.rotofight.Read.Map;
import fr.umlv.rotofight.World.Element.*;
import fr.umlv.rotofight.World.Environment.Constants;
import fr.umlv.rotofight.World.Environment.Gravity;
import fr.umlv.rotofight.World.View.Draw;
import fr.umlv.zen5.ApplicationContext;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

public class Collision {
	/**
	 * Private constructor of Collision, should not be used.
	 */
	private Collision() {
		throw new RuntimeException("Cannot instantiate Collision class.");
	}
	/**
	 * Handles all the collisions occurring on one frame.
	 * @param context
	 * 		ApplicationContext of the game.
	 * @param board
	 * 		Walls of the game.
	 * @param cst
	 * 		Constants of the game.
	 * @param g
	 * 		Current Gravity.
	 * @param map
	 * 		Map of the game.
	 * @param player1
	 * 		Player1.
	 * @param player2
	 * 		Player2.
	 * @return
	 * 		true if a player died, else false.
	 */
	public static boolean collisionHandle(ApplicationContext context, Walls board, Constants cst, Gravity g, Map map, Player player1, Player player2) {
		if(Collision.collisionSwords(player1, player2) || Collision.collisionSwords(player2, player1)) {
			Draw.drawAll(context, player1, player2, board, cst.getZoom(), g);
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return true;
		}
		Collision.collisionSwordsWall(board, player1, player2);
		return false;
	}
	/**
	 * Detects collisions types between a Wall and a Player on y axis.
	 * @param px
	 * 		x position of Player.
	 * @param py
	 * 		y position of Player.
	 * @param wx
	 * 		x position of Wall.
	 * @param wy
	 * 		y position of Wall.
	 * @param player
	 * 		Player on collision.
	 * @param wall
	 * 		Wall on collision.
	 * @return
	 * 		Type of collision.
	 */
	public static int collisionTypeY(float px, float py, float wx, float wy, Player player, Wall wall) {
		float pSize = player.getRadius() * 2, wHeight = wall.getHeight(), wWidth = wall.getWidth();
		float pStepY = player.getStepY();
		if((px < wx + wWidth && px > wx) || (px + pSize < wx + wWidth && px + pSize > wx)) {
			if(wy < py + pStepY + pSize  && wy + wHeight > py + pStepY + pSize) {
				player.setY(wy - pSize/2 - 1);
				player.setStepY(0);
				return 1;
			}
			else if(wy + wHeight > py + pStepY && wy < py + pStepY) {
				player.setY(wy + wHeight + pSize/2 + 1);
				player.setStepY(0);
				return 2;
			}
		}
		return 0;
	}
	/**
	 * Detects collisions types between a Wall and a Player on x axis.
	 * @param px
	 * 		x position of Player.
	 * @param py
	 * 		y position of Player.
	 * @param wx
	 * 		x position of Wall.
	 * @param wy
	 * 		y position of Wall.
	 * @param player
	 * 		Player on collision.
	 * @param wall
	 * 		Wall on collision.
	 * @return
	 * 		Type of collision
	 */
	public static int collisionTypeX(float px, float py, float wx, float wy, Player player, Wall wall) {
		float pSize = player.getRadius() * 2, wHeight = wall.getHeight(), wWidth = wall.getWidth();
		float pStepX = player.getStepX() ;
		if((py > wy && py < wy + wHeight) || (py + pSize > wy && py + pSize < wy + wHeight)) {
			if(wx < px + pSize + pStepX && wx + wWidth > px + pSize + pStepX){
				player.setX(wx - pSize/2 - 1);
				player.setStepX(0);
				return 1;
			}
			else if(wx + wWidth > px + pStepX && wx < px + pStepX) {
				player.setX(wx + wWidth + pSize/2 + 1);
				player.setStepX(0);
				return 2;
			}
		}
		return 0;
	}
	/**
	 * Detects collisions between Wall and Player (x and y).
	 * @param wall
	 * 		Wall colliding.
	 * @param player
	 * 		Player colliding.
	 * @return
	 * 		Types of colliding on x and y axis.
	 */
	public static int[] collisionType(Wall wall, Player player) {
		float px = player.getPosition().getX() - player.getRadius();
		float py = player.getPosition().getY() - player.getRadius();
		float wx = wall.getPosition().getX();
		float wy = wall.getPosition().getY();
		int[] collisions = {0, 0};
		collisions[0] = collisionTypeX(px, py, wx, wy, player, wall);
		collisions[1] = collisionTypeY(px, py, wx, wy, player, wall);
		return collisions;
	}
	
	/**
	 * Checks if any Wall collided with Player.
	 * @param board
	 * 		Walls of the game.
	 * @param player
	 * 		Player to check.
	 * @param g
	 * 		Current Gravity.
	 */
	public static void collisionPlayerWall(Walls board, Player player, Gravity g) {
		int[] collisions;
		for (Wall wall : board.getWalls()) {
			collisions = collisionType(wall, player);
			if(collisions[0] != 0 || collisions[1] != 0) {
				player.setJump(collisions, g);
			}
		}
	}
	/**
	 * Checks if Sword collides with Player.
	 * @param player
	 * 		Player to check.
	 * @param s
	 * 		Sword to check.
	 * @return
	 * 		true if collided, else false.
	 */
	public static boolean collisionPlayerSword(Player player, Sword s) {
		Ellipse2D.Double body_p = player.bodyPlayer();
		Line2D.Double body_s = s.bodySword();
		return body_s.intersects(body_p.getX(),body_p.getY(),body_p.getWidth(),body_p.getHeight());
	}
	/**
	 * Checks if any Sword collides with any Wall.
	 * @param walls
	 * 		Walls to be checked.
	 * @param throwers
	 * 		Players to be checked (each one has its own swords).
	 */
	public static void collisionSwordsWall(Walls walls, Player...throwers) {
		for(Player player: throwers) {
			Swords swords = player.getSwords();
			for(int i = 0; i < walls.getSize(); i++) {
				for(int j = 0; j < swords.getSize() ; j++) {
					if(swords.getElement(j).bodySword().intersects(walls.getWall(i).bodyWall())) {
						player.removeSword(j);
						swords = player.getSwords();
					}
				}
			}
		}
	}
	/**
	 * Check if the Swords of thrower are colliding with the target player.
	 * @param target
	 * 		The player checked for collisions.
	 * @param thrower
	 * 		The player who threw swords.
	 * @return
	 * 		true if target is colliding with a sword from thrower, else false.
	 */
	public static boolean collisionSwords(Player target, Player thrower) {
		Swords swords = thrower.getSwords();
		for(int i = 0; i < swords.getSize(); i++) {
			if(collisionPlayerSword(target, swords.getElement(i))) {
				target.kill();
				target.looseLife();
				swords.remove(i);
				return true;
			}
		}
		return false;
	}
}
