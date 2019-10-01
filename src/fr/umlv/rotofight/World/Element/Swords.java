package fr.umlv.rotofight.World.Element;

import fr.umlv.rotofight.World.Environment.Gravity;
import fr.umlv.rotofight.World.View.Draw;

import java.awt.*;
import java.util.ArrayList;

public class Swords implements Draw {
	private final ArrayList<Sword> swords;
	private final int maxSwords;
	/**
	 * Swords constructor.
	 * @param nb
	 * 		Maximum number of Sword.
	 */
	public Swords(int nb) {
		swords = new ArrayList<Sword>();
		maxSwords = nb;
	}
	/**
	 * Gets the size of the array of Sword.
	 * @return
	 * 		Size of the array of swords.
	 */
	public int getSize() {
		return swords.size();
	}
	/**
	 * Gets the Sword of index i.
	 * @param i
	 * 		index of the sword.
	 * @return
	 * 		Sword indexed by i in the Swords array.
	 */
	public Sword getElement(int i) {
		Sword s = swords.get(i);
		return new Sword(s.getPosition(), s.getSize(), s.getAbsoluteDirection());
	}
	/**
	 * Gets a copy of the swords array.
	 * @return
	 * 		ArrayList of Sword.
	 */
	public ArrayList<Sword> getSwords() {
		ArrayList<Sword> s = new ArrayList<Sword>();
		for(int i = 0; i < s.size(); i++) {
			s.add(swords.get(i));
		}
		return s;
	}
	/**
	 * Adds a Sword to the Sword array.
	 * @param s
	 * 		Sword to be added.
	 */
	public void add(Sword s) {
		if (maxSwords != swords.size()) {
			swords.add(s);
		}
	}
	/**
	 * Moves all the Sword of the Sword array.
	 */
	public void move() {
		for (Sword s : swords) {
			s.move();
		}
	}
	/**
	 * Removes a Sword from the Sword array.
	 * @param s
	 * 		Sword to be removed.
	 */
	public void remove(int i) {
		swords.remove(i);
	}

	@Override
	public void drawElement(Graphics2D graphics, Gravity g) {
		for(int i = 0; i < getSize(); i++) {
			graphics.draw(getElement(i).bodySword());
		}
	}
}


