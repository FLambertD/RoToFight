package fr.umlv.rotofight.World.Element;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

import fr.umlv.rotofight.Read.Map;
import fr.umlv.rotofight.World.Environment.Constants;
import fr.umlv.rotofight.World.Environment.Gravity;
import fr.umlv.rotofight.World.Event.Collision;
import fr.umlv.rotofight.World.View.Draw;
import fr.umlv.zen5.KeyboardKey;


public class Player extends Element implements Draw {
	private KeyboardKey[] keys;
	
	private int life = 3;
	private final boolean number;
	private final Color color;
	private float radius;
	private double[] absoluteDirection = {0, 0};
	private boolean relativeDirection;
	private int[] keypresses;
	
	private boolean alive;
	private float stepX = 0;
	private float stepY = 0;
	private boolean jump = false;
	private int[] jumpDirection = {0, 0};
	private long time = 0;
	
	private Swords swords;
	/**
	 * Player constructor.
	 * @param pos
	 * 		Position of the player.
	 * @param playerNumber
	 * 		true for player1, false for player2.
	 * @param diameter
	 * 		the diameter of the player's body
	 * @param swordsNb
	 * 		the number of swords that the player can throw simultaneously.
	 */
	public Player(Position pos, boolean playerNumber, float diameter, int swordsNb, int life) {
		super(pos);
		this.number = playerNumber;
		alive = true;
		this.radius = diameter/2;
		keys = new KeyboardKey[4];
		keypresses = new int[4];
		swords = new Swords(swordsNb);
		assignKeys();
		this.life = life;
		if (playerNumber) {
			this.color = Color.RED;
			this.relativeDirection = true;
		}else {
			this.color = Color.BLUE;
			this.relativeDirection = false;
		}
	}
	/**
	 * Initializes the player.
	 * @param t
	 * 		Map of the game.
	 * @param c
	 * 		Constants of the game.
	 * @param numberPlayer
	 * 		true for player1, false for player2.
	 * @return
	 * 		A new Player initialized.
	 */
	public static Player initPlayer(Map t, Constants c, boolean numberPlayer, int life) {
		float width = c.getWallWidth();
		float height = c.getWallHeight();
		int [] j1 = t.getPlayer(numberPlayer);
		return new Player(new Position(width/2+j1[0]*width,height/2+j1[1]*height),numberPlayer,c.getPlayerRadius(),c.getMaxSwords(), life);
	}
	/**
	 * Assign the keys for each player.
	 */
	public void assignKeys() {
		if(number) {
			keys[0] = KeyboardKey.LEFT;
			keys[1] = KeyboardKey.UP;
			keys[2] = KeyboardKey.RIGHT;
			keys[3] = KeyboardKey.DOWN;
		}
		else {
			keys[0] = KeyboardKey.Q;
			keys[1] = KeyboardKey.Z;
			keys[2] = KeyboardKey.D;
			keys[3] = KeyboardKey.S;
		}
	}
	
	/* GETTERS */
	/**
	 * Get the color of the player.
	 * @return
	 * 		the color of the player.
	 */
	public Color getColor() {
		return color;
	}
	/**
	 * Get the radius of the player's body.
	 * @return
	 * 		the radius in pixels of the player's body.
	 */
	public float getRadius() {
		return radius;
	}
	/**
	 * Get the x step of the player.
	 * @return
	 * 		x step of the player.
	 */
	public float getStepX() {
		return stepX;
	}
	/**
	 * Get the y step of the player.
	 * @return
	 * 		y step of the player.
	 */
	public float getStepY() {
		return stepY;
	}
	/**
	 * Get the jump state of the player.
	 * @return
	 * 		true if player can jump, else false.
	 */
	public boolean getJump() {
		return jump;
	}
	/**
	 * Get the keys of the player.
	 * @return
	 * 		keys of the player.
	 */
	public KeyboardKey[] getKeys() {
		return keys.clone();
	}
	/**
	 * Get a keypress from the player.
	 * @param index
	 * 		index of the keypress in all the keypresses.
	 * @return
	 * 		1 if key is pressed, else 0;
	 */
	public int getKeypress(int index) {
		return keypresses[index];
	}
	/**
	 * Get the swords thrown by the player.
	 * @return
	 * 		Swords thrown by the player.
	 */
	public Swords getSwords() {
		int size = swords.getSize();
		Swords s = new Swords(size);
		for(int i = 0; i < size; i++) {
			s.add(swords.getElement(i));
		}
		return s;
	}
	/**
	 * Get the number of a player.
	 * @return
	 * 		true for player 1, false for player 2.
	 */
	public boolean getNumberPlayer() {
		return number;
	}
	/**
	 * Get the absolute direction of the player (perpendicular to gravity).
	 * @return
	 * 		the absolute direction of the player.
	 */
	public double[] getAbsoluteDirection() {
		return absoluteDirection.clone();
	}
	/** 
	 * Get the life of a player.
	 * @return
	 * 		life of a player.
	 */
	public int getLife() {
		return life;
	}
	/**
	 * Get the time since the player didn't move.
	 * @return
	 * 		the time since the player didn't move.
	 */
	public long getTime() {
		return time;
	}
	/**
	 * Checks if any key is pressed by the player.
	 * @return
	 * 		true if at least one key is pressed, else false.
	 */
	public boolean anyAction(){
		for (int i = 0; i < keypresses.length ; i++) {
			if (keypresses[i] == 1)
				return true;
		}
		return false;
	}

	/* INPUT */
	/**
	 * Set a key press of the player.
	 * @param i
	 * 		the index of the key press.
	 * @param mode
	 * 		true for pressed, false for released.
	 */
	public void setKeypress(int i, boolean mode) {
		keypresses[i] = 0;
		if(mode) {
			keypresses[i] = 1;
		}
	}
	
	/* MOVEMENT */
	/**
	 * Set the x step of the position.
	 * @param x
	 * 		the x step to set.
	 */
	public void setStepX(float x) {
		stepX = x;
	}
	/**
	 * Set the y step of the position.
	 * @param y
	 * 		the y step to set.
	 */
	public void setStepY(float y) {
		stepY = y;
	}
	/**
	 * Add x to the current position step.
	 * @param x
	 * 		the x value to be added to the step.
	 */
	public void addStepX(float x) {
		stepX += x;
	}
	/**
	 * Add y to the current position step.
	 * @param y
	 * 		the y value to be added to the step.
	 */
	public void addStepY(float y) {
		stepY += y;
	}
	/**
	 * Add a sword to the current thrown swords.
	 * @param g
	 * 		the current gravity.
	 */
	public void addSword(Gravity g) {
		if(keypresses[3] == 1) {
			swords.add(new Sword(new Position(getPosition().getX(), getPosition().getY()), 40, this));
		}
		
	}
	/**
	 * Removes the given Sword from swords.
	 * @param s
	 * 		the Sword to remove.
	 */
	public void removeSword(int i) {
		swords.remove(i);
	}
	/**
	 * Jump according to the direction given by jumpDiretion.
	 */
	public void jump() {
		if(keypresses[1] == 1) {
			addStepX(jumpDirection[0]);
			addStepY(jumpDirection[1]);
			jumpDirection[0] = 0;
			jumpDirection[1] = 0;
		}
	}
	/**
	 * Sets the jumpDirection, equals {0, 0} by default.
	 * @param collisions
	 * 		the collisions between the player and the walls.
	 * @param g
	 * 		the current gravity.
	 */
	public void setJump(int[] collisions, Gravity g) {
		double angle = g.getGravityAngle();
		jumpDirection[0] = 0;
		jumpDirection[1] = 0;
		if(collisions[0] == 1 && angle >= 45 && angle <= 135) {
			jumpDirection[0] = -20;
		}
		else if(collisions[0] == 2 && angle <= 315 && angle >= 225) {
			jumpDirection[0] = 20;
		}
		else if(collisions[1] == 1 && (angle < 45 && angle >= 0 || angle > 315 && angle < 360)) {
			jumpDirection[1] = -20;
		}
		else if(collisions[1] == 2 && angle > 135 && angle < 225) {
			jumpDirection[1] = 20;
		}
	}
	/**
	 * Sets the direction of the player.
	 * @param g
	 * 		the current gravity.
	 * @param step
	 * 		the current x step of the player (relative to itself and not the world coordinates).
	 */
	public void setDirection(Gravity g, int step) {
		double angleG = g.getGravityAngle();
		double angle = Math.toRadians(angleG + 90);
		if(relativeDirection) {
			this.absoluteDirection[0] = Math.sin(angle);
			this.absoluteDirection[1] = Math.cos(angle);
		}
		else {
			this.absoluteDirection[0] = -Math.sin(angle);
			this.absoluteDirection[1] = -Math.cos(angle);
		}
		if(step != 0) {
			if(angleG > 135 && angleG < 225) {
				relativeDirection = (step == -1);
			}
			else {
				relativeDirection = (step == 1);
			}
		}
	}
	/**
	 * Checks the velocity of a player and reduces it if is exceeds the walls thickness.
	 * @param cst
	 * 		Constants of the game.
	 */
	private void checkVelocity(Constants cst) {
		float xMaxVelocity = cst.getWallWidth();
		float yMaxVelocity = cst.getWallHeight();
		if(stepX > xMaxVelocity) {
			stepX = xMaxVelocity - 1;
		}
		else if( stepX < -xMaxVelocity) {
			stepX = -xMaxVelocity + 1;
		}
		if(stepY > yMaxVelocity) {
			stepY = yMaxVelocity - 1;
		}
		else if(stepY < -yMaxVelocity) {
			stepY = -yMaxVelocity + 1;
		}
	}
	/**
	 * Move the player for a the next frame of the game.
	 * @param board
	 * 		The walls of the game.
	 * @param g
	 * 		The current gravity.
	 * @param cst
	 * 		Constants of the game.
	 */
	public void move(Walls board, Gravity g, Constants cst) {
		if (!anyAction())
			addTime(30);
		else
			resetTime();
		setStepMove(g);
		applyGravity(g);
		setDirection(g, keypresses[2] - keypresses[0]);
		checkVelocity(cst);
		Collision.collisionPlayerWall(board, this, g);
		jump();
		addSword(g);
		swords.move();
		appendPosition(stepX, stepY);
	}
	/**
	 * Applies given gravity to the player
	 * @param g
	 * 		Current gravity.
	 */
	public void applyGravity(Gravity g){
		addStepX((float) (g.getStepX()*g.getForce()));
		addStepY((float) (g.getStepY()*g.getForce()));
	}
	/**
	 * Assigns the correct movement to keys according to the gravity.
	 * @param g
	 * 		Current gravity
	 */
	public void setStepMove(Gravity g){
		float angle = g.getGravityAngle();
		if(angle > 315 && angle <= 359 || angle >= 0 && angle < 45 || angle > 135 && angle <= 225){
			setStepX(-15*(keypresses[0] - keypresses[2]));
		}
		else if (angle >= 45 && angle <= 135){
			setStepY(-15*(keypresses[2]-keypresses[0]));
		}
		else {
			setStepY(15*(keypresses[2]-keypresses[0]));
		}
	}
	/**
	 * Adds time to the no-action timer.
	 * @param timeStep
	 * 		Time to add.
	 */
	public void addTime(long timeStep){
		time+= timeStep; 
	}
	/**
	 * Sets to 0 the no-action timer.
	 */
	public void resetTime(){time = 0;}
	
	/* LIFE */
	/**
	 * Kills the player.
	 */
	public void kill() { 
		this.alive = false;
	}
	/**
	 * Makes the player loose a life.
	 */
	public void looseLife() {
		this.life -= 1;
	}
	/**
	 * Get the life state of the player.
	 * @return
	 * 		true if alive, else false.
	 */
	public boolean isAlive() {
		return this.alive;
	}
	/**
	 * Creates the body of the player.
	 * @return
	 * 		Shape of the body.
	 */
	public Ellipse2D.Double bodyPlayer() {
		Ellipse2D.Double body = new Ellipse2D.Double(
				getPosition().getX() - getRadius(), 
				getPosition().getY() - getRadius(), 
				getRadius() * 2,
				getRadius() * 2);
		return body;
	}
	/**
	 * Creates the eyebrow of the player.
	 * @param g
	 * 		Current gravity.
	 * @return
	 * 		The eyebrow's shape.
	 */
	public Line2D.Double eyebrowPlayer(Gravity g) {
		float x = getPosition().getX(), y = getPosition().getY();
		double angle_degrees = g.getGravityAngle(), angle = Math.toRadians(angle_degrees);
		Line2D.Double eyebrow;
		if(relativeDirection) {
			eyebrow = new Line2D.Double(
					x + (radius / 4) * Math.cos(angle) - (radius / 2) * Math.sin(angle),
					y - ((radius / 4) * Math.sin(angle) + (radius / 2) * Math.cos(angle)),
					x + (radius) * Math.cos(angle) - (radius / 4) * Math.sin(angle),
					y - ((radius) * Math.sin(angle) + (radius / 4) * Math.cos(angle)));
		}
		else {
			eyebrow = new Line2D.Double(
					x - ((radius / 4)* Math.cos(-angle) - (radius / 2) * Math.sin(-angle)),
					y - ((radius / 4)* Math.sin(-angle) + (radius / 2) * Math.cos(-angle)),
					x - ((radius) * Math.cos(-angle) - (radius / 4) * Math.sin(-angle)),
					y - ((radius) * Math.sin(-angle) + (radius / 4) * Math.cos(-angle)));
		}
		return eyebrow;
	}
	/**
	 * Creates the eyebrow of the inactive player.
	 * @param g
	 * 		Current gravity.
	 * @return
	 * 		The shape of the eyebrow.
	 */
	public Line2D.Double eyebrowUp(Gravity g){
		Line2D.Double test = eyebrowPlayer(g);
		double x = g.getStepX();
		double y = g.getStepY();
		double taille = radius/1.5f;
		return new Line2D.Double(
				test.getX1() - x * taille,
				test.getY1() - y * taille,
				test.getX2() - x * taille,
				test.getY2() - y * taille);
	}
	/**
	 * Creates the eyebrow of the dead player according to direction.
	 * @param x
	 * 		x position of the player.
	 * @param y
	 * 		y position of the player.
	 * @param angle
	 * 		rotation of the player.
	 * @param direction
	 * 		direction of the player (relative).
	 * @return
	 * 		the shape of the eyebrow.
	 */
	private Line2D.Double[] deadEyebrowsPlayerDirection(float x, float y, double angle, int direction) {
		Line2D.Double[] eyebrows = new Line2D.Double[2];
		eyebrows[0] = new Line2D.Double(
				x + direction * ((radius / 3) * Math.cos(angle) - (radius / 2) * Math.sin(angle)) ,
				y - ((radius / 3) * Math.sin(angle) + (radius / 2) * Math.cos(angle)),
				x + direction * ((radius / 2) * Math.cos(angle) - (radius / 4) * Math.sin(angle)),
				y - ((radius / 2) * Math.sin(angle) + (radius / 4) * Math.cos(angle)));
		eyebrows[1] = new Line2D.Double(
				x + direction * ((radius / 2) * Math.cos(angle) - (radius / 2) * Math.sin(angle)) ,
				y - ((radius / 2) * Math.sin(angle) + (radius / 2) * Math.cos(angle)),
				x + direction * ((radius / 3) * Math.cos(angle) - (radius / 4) * Math.sin(angle)) ,
				y - ((radius / 3) * Math.sin(angle) + (radius / 4) * Math.cos(angle)));
		return eyebrows;
	}
	/**
	 * Creates the eyebrow of the dead player.
	 * @param g
	 * 		Current gravity.
	 * @return
	 * 		the shape of the eyebrow.
	 */
	public Line2D.Double[] deadEyebrowsPlayer(Gravity g) {
		float x = getPosition().getX();
		float y = getPosition().getY();
		double degreesAngle = g.getGravityAngle();
		double angle = Math.toRadians(degreesAngle);
		Line2D.Double[] eyebrows = new Line2D.Double[2];
		if(relativeDirection) {
			eyebrows = deadEyebrowsPlayerDirection(x, y, angle, 1);
		}
		else {
			eyebrows = deadEyebrowsPlayerDirection(x, y, angle, -1);
		}
		return eyebrows;
	}
	
	@Override
	public String toString() {
		return getPosition().getX()+" "+ getPosition().getY();
	}
	
	@Override
	public void drawElement(Graphics2D graphics, Gravity g) {
		graphics.setColor(getColor());
		graphics.draw(bodyPlayer());
		if(alive) {
			if (getTime()>2000)
				graphics.draw(eyebrowUp(g));
			else
				graphics.draw(eyebrowPlayer(g));
		}
		else {
			for(Line2D l: deadEyebrowsPlayer(g)) {
				graphics.draw(l);
			}
			if (!getNumberPlayer()) { graphics.drawString("J1 took a sword in the face", 100, 100); }
			else{graphics.drawString("J2 took a sword in the face", 100, 100 );}
		}
	}
}
