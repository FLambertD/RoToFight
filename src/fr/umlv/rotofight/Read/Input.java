package fr.umlv.rotofight.Read;

import fr.umlv.rotofight.World.Element.Player;
import fr.umlv.rotofight.World.Environment.Constants;
import fr.umlv.zen5.ApplicationContext;
import fr.umlv.zen5.Event;
import fr.umlv.zen5.Event.Action;
import fr.umlv.zen5.KeyboardKey;

public class Input {
	/**
	 * Private constructor of Input, should not be used.
	 */
	private Input() {
		throw new RuntimeException("Cannot instantiate the Input class.");
	}
	/**
	 * Checks all the keyboard inputs and quits the game if the correct key is pressed
	 * @param context
	 * 		The ApplicationContext of the game.
	 * @param cst
	 * 		Constants of the game.
	 * @param player1
	 * 		player1
	 * @param player2
	 * 		player2
	 */
	public static void inputHandle(ApplicationContext context, Constants cst, Player player1, Player player2) {
		if(Input.detectInput(context,cst, player1, player2) == 1) {
	          context.exit(0);
		}
	}
	/**
	 * Applies actions according to the given key pressed/released.
	 * @param k
	 * 		the key pressed/released.
	 * @param p
	 * 		player to be affected by key press.
	 * @param mode
	 * 		true for key press, false for key release.
	 * @param cst
	 * 		Constants of the game.
	 * @return
	 * 		1 if exit key was pressed, else 0
	 */
	private static int keypressesToAction(KeyboardKey k, Player p, boolean mode, Constants cst) {
		KeyboardKey[] keys = p.getKeys();
		if(k == KeyboardKey.SPACE) {
			return 1;
		}
		if (k == KeyboardKey.L){
			cst.appendZoom((float) 0.01);
		}
		if (k == KeyboardKey.M){
			cst.appendZoom((float) -0.01);
		}
		for(int i = 0; i < keys.length; i++) {
			if(keys[i].equals(k)) {
				p.setKeypress(i, mode);
			}
		}
		return 0;
	}
	/**
	 * Detects key presses and releases.
	 * @param context
	 * 		ApplicationContext of the game.
	 * @param cst
	 * 		Constants of the game.
	 * @param players
	 * 		Players of the game.
	 * @return
	 * 		1 if exit key was detected, else 0.
	 */
	public static int detectInput(ApplicationContext context,  Constants cst, Player... players) {
		Event event = context.pollEvent();
		int keyboardActionType = 0;
		if(event != null) {
			Action action = event.getAction();
			KeyboardKey k = event.getKey();
			for(Player j : players) {
				if(action == Action.KEY_PRESSED) {
					keyboardActionType = keypressesToAction(k, j, true, cst);
				}
				else if(action == Action.KEY_RELEASED) {
					keyboardActionType = keypressesToAction(k, j, false, cst);
				}
			}
		}
	
		return keyboardActionType;
	}
}