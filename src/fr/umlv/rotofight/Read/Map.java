package fr.umlv.rotofight.Read;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

import fr.umlv.rotofight.Read.ReadMap;


public class Map {
	private ArrayList<ArrayList<Integer>> map;
	/**
	 * Map constructor.
	 * @param filename
	 * 		Path to the map to load.
	 * @throws IOException
	 */
	public Map(String filename) throws IOException {
		this.map = ReadMap.read(Paths.get(filename));
	}
	/**
	 * Get the number of columns.
	 * @return
	 * 		number of columns of the map.
	 */
	public int getColumn() {
		return map.get(0).size();
	}
	/**
	 * Get the number of lines.
	 * @return
	 * 		number of lines of the map.
	 */
	public int getLine() {
		return map.size();
	}
	/**
	 * Get an element of the map according to its indexes.
	 * @return
	 * 		integer representing an element of the map.
	 */
	public int getIndex(int i, int j) {
		return map.get(i).get(j);
	}
	
	/**
	 * Check if all the booleans given are true.
	 * @param test
	 * 		booleans to be checked.
	 * @return
	 * 		true if all booleans values are true, else false.
	 */
	boolean min(boolean ...test ) {
		for (int i = 0; i < test.length; i++) {
			if (test[i] = false)
				return false;
		}
		return true;
	}
	
	/**
	 * Checks if the first and last lines of the map are walls.
	 * @return
	 * 		true if there are only walls, else false.
	 */
	boolean checkFirstLastLine() {
		for (int i = 0; i < map.get(0).size(); i++) {
			if (map.get(0).get(i) != 1 || map.get(map.size()-1).get(i)!=1)
				return false;
		}
		return true;
	}
	/**
	 * Checks if the first and last columns of the maps are walls.
	 * @return
	 * 		true if there are only walls, else false.
	 */
	boolean checkFirstLastColumn() {
		for (int i = 0; i < map.size(); i++) {
			if (map.get(i).get(0) != 1 || map.get(i).get(map.get(i).size()-1)!=1)
				return false;
		}
		return true;
	}
	/**
	 * Checks if the map is rectangular.
	 * @return
	 * 		true if the map is rectangular, else false.
	 */
	boolean checkRectangular() {
		if (map.size()>map.get(0).size())
			return false;
		return true;
	}
	/**
	 * Get the indexes of the initial position of a player in the map.
	 * @param nPlayer
	 * 		true for player1, false for player2.
	 * @return
	 * 		the position of the player chosen in the map.
	 */
	public int[] getPlayer(boolean nPlayer) {
		int[] player = new int[2];
		int res;
		if (nPlayer) {
			res = 2;
		}
		else {
			res = 3;
		}	
		for (int i = 0; i < map.size(); i++) {
			for (int j = 0; j < map.get(i).size(); j++) {
				if(map.get(i).get(j) == res) {
					player[1] = i;
					player[0] = j; 
				}
			}
		}
		System.out.println(player[0] +" "+ player[1]);
		return player;
	}
	/**
	 * Check if players are on the map and if there's only one instance of each player.
	 * @return
	 * 		true if the predicate is true, else false.
	 */
	boolean checkPlayers() {
		boolean foundPlayer1 = false;
		boolean foundPlayer2 = false;
		for (int i = 0; i < map.size(); i++) {
			for (int j = 0; j < map.get(i).size(); j++) {
				switch(map.get(i).get(j)) {
				case 2 : if (foundPlayer1) return false;foundPlayer1 = true;break;
				case 3 : if (foundPlayer2)return false;foundPlayer2 = true;break;
				case -1: return false;
				}
			}
		}
		return (foundPlayer1 && foundPlayer2);
	}
	/**
	 * Checks if the map is valid according to all the other check methods.
	 * @return
	 * 		true if all check methods return true, else false.
	 */
	public boolean validMap() {
		return min(checkRectangular(),checkFirstLastLine(),checkFirstLastColumn(),checkPlayers());
	}
	
	
}
