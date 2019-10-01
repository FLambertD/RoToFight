package fr.umlv.rotofight.World.Environment;

import fr.umlv.rotofight.World.Element.Player;
import fr.umlv.rotofight.World.Element.Walls;
import fr.umlv.rotofight.World.Event.RenderTimer;
import fr.umlv.zen5.ApplicationContext;

public class World {
	/**
	 * Private constructor of World, should not be used.
	 */
	private World() {
		throw new RuntimeException("Cannot instantiate World class.");
	}
	/**
	 * Makes all the world move.
	 * @param g
	 * 		Current Gravity.
	 * @param cst
	 * 		Constants of the game.
	 * @param board
	 * 		Walls board.
	 * @param player1
	 * 		Player1.
	 * @param player2
	 * 		Player2.
	 */
	public static void step(Gravity g, Constants cst, Walls board, Player player1, Player player2) {
		player1.move(board, g, cst);
		player2.move(board, g, cst);
		g.updateGravity();
	}
	/**
	 * Checks the life of each player.
	 * @param player1
	 * 		Player1.
	 * @param player2
	 * 		Player2.
	 * @return
	 * 		true if both have life, else false.
	 */
	public static boolean checkLives(Player player1, Player player2) {
		if(player1.getLife() == 0) {
			return false;
		}
		else if(player2.getLife() == 0) {
			return false;
		}
		return true;
	}
	/**
	 * Ends the game.
	 * @param player1
	 * 		Player1.
	 * @param player2
	 * 		Player2.
	 * @param context
	 * 		ApplicationContext of the game.
	 * @param cst
	 * 		Constants of the game.
	 */
	public static void end(Player player1, Player player2, ApplicationContext context, Constants cst) {
		context.renderFrame(graphics -> {
			if(player1.getLife() == 0) {
				graphics.setColor(player2.getColor());
				graphics.drawString("Player 1 wins !", cst.getWidth()/2, cst.getHeight()/2);
			}
			else {
				graphics.setColor(player1.getColor());
				graphics.drawString("Player 2 wins !", cst.getWidth()/2, cst.getHeight()/2);
			}
		});
		RenderTimer.stop(3000);
		context.exit(0);
	}
}
