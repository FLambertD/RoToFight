package fr.umlv.rotofight.Main;
import java.awt.Color;
import java.io.IOException;

import fr.umlv.rotofight.Read.Command;
import fr.umlv.rotofight.Read.Input;
import fr.umlv.rotofight.Read.Map;
import fr.umlv.rotofight.World.Element.Player;
import fr.umlv.rotofight.World.Element.Walls;
import fr.umlv.rotofight.World.Environment.Constants;
import fr.umlv.rotofight.World.Environment.Gravity;
import fr.umlv.rotofight.World.Environment.World;
import fr.umlv.rotofight.World.Event.Collision;
import fr.umlv.rotofight.World.Event.RenderTimer;
import fr.umlv.rotofight.World.View.Draw;
import fr.umlv.zen5.Application;

public class Main {
/**
 * Main method of the game. Creates players, walls and runs the game.
 */
	public static void main(String[] args) throws IOException {
		Walls board = new Walls();
		Map map = new Map(Command.lvlName(args));
		Gravity g = new Gravity(Command.gravityValue(args), Command.gravityPhaseValue(args));
		Application.run(Color.WHITE, context -> {
			Constants cst = new Constants(context, map, Command.swordValue(args));
			board.init(map,cst);
			Player player1 = Player.initPlayer(map, cst, false, 3), player2 = Player.initPlayer(map, cst, true, 3);
			for(;;) {
				Input.inputHandle(context, cst, player1, player2);
				World.step(g, cst, board, player1, player2);
				if(Collision.collisionHandle(context, board, cst, g, map, player1, player2)) {
					player1 = Player.initPlayer(map, cst, false, player1.getLife());
					player2 = Player.initPlayer(map, cst, true, player2.getLife());
					if(!World.checkLives(player1, player2)) {
						break;
					}
				}
				Draw.drawAll(context, player1, player2, board, cst.getZoom(), g);
				RenderTimer.stop(30);
			}
			World.end(player1, player2, context, cst);
		});
	}
}

