package fr.umlv.rotofight.World.Environment;

import java.time.Clock;

public class Gravity {
	private double stepX;
	private double stepY;
	private int gravityAngle;
	private int gravityType;
	private final int mod;
	private int force;
	private long time;
	private Clock clock;
	private long phase;
	/**
	 * Gravity constructor.
	 * @param mod
	 * 		Gravity type.
	 * @param phase
	 * 		Gravity phase.
	 */
	public Gravity(int mod, int phase){
		System.out.println("phase: " + phase);
		this.mod = mod;
		stepX = 0;
		stepY = 0;
		gravityAngle=0;
		gravityType=0;
		force = 1;
		this.phase = phase;
		clock = Clock.systemDefaultZone();
		time = clock.millis();
	}

	/**
	 * Gets the current angle of the Gravity.
	 * @return
	 * 		Gravity's current angle.
	 */
	public int getGravityAngle() {
		return gravityAngle;
	}
	/**
	 * Gets Gravity's type.
	 * @return
	 * 		Gravity's type.
	 */
	public int getGravityType() {
		return gravityType;
	}
	/**
	 * Gets the x step of the current gravity.
	 * @return
	 * 		x step of the gravity.
	 */
	public double getStepX() {
		return stepX;
	}
	/**
	 * Gets the y step of the current gravity.
	 * @return
	 * 		y step of the gravity.
	 */
	public double getStepY() {
		return stepY;
	}
	/**
	 * Gets the force of the Gravity.
	 * @return
	 * 		Gravity's force.
	 */
    public int getForce() { 
    	return force; 
    }
    /**
     * Gets the mode of the Gravity
     * @return
     * 		Gravity's mode.
     */
    public int getMode(){
		return mod;
	}
    /**
     * Updates the Gravity type according to its angle.
     */
	public void updateGravityType(){
		int test = getGravityAngle();
		if (test % 90 < 45)
			gravityType = test/90;
		else if(test > (270 + 45))
			gravityType = 0;
		else
			gravityType = test / 90 + 1;
	}
	/**
	 * Returns the remainder of Gravity's angle divided by 90. 
	 * @return
	 * 		The remainder.
	 */
	private double simpleAngle(){
		double deg_360 = getGravityAngle();
		return deg_360%90;
	}
	/**
	 * Updates the Gravity step according to its angle.
	 */
	public void updateGravityStep(){
		int deg_360 = getGravityAngle();
		double deg = simpleAngle();
		switch(deg_360/90){
			case 0: stepX = Math.sin(Math.toRadians(deg));
					stepY = Math.cos(Math.toRadians(deg));
					break;
			case 1: stepY = -Math.sin(Math.toRadians(deg));
					stepX = Math.cos(Math.toRadians(deg));
					break;
			case 2: stepX = -Math.sin(Math.toRadians(deg));
					stepY = -Math.cos(Math.toRadians(deg));
					break;
			case 3:	stepX = -Math.cos(Math.toRadians(deg));
					stepY = Math.sin(Math.toRadians(deg));
					break;
		}
	}
	/**
	 * Updates the Gravity angle according to its mode.
	 */
	public void updateAngle(){
		switch (mod){
			case 1: gravityAngle=0;break;
			case 2: if (gravityAngle == 0) gravityAngle =180;
					else gravityAngle = 0;
					break;
			case 4: gravityAngle = gravityAngle-90;
				if (gravityAngle <0) gravityAngle =270;
				break;
			case 360: gravityAngle+=20;
				if(gravityAngle>=360) gravityAngle =0;
				break;
		}
	}
	/**
	 * Sets the force of the Gravity to 0.
	 */
    public void resetForce() {
    	force  = 0;
    }
    /**
     * Updates the angle of Gravity after a certain amount of time.
     */
	public void updateGravity() {
		if(clock.millis() > time + phase) {
			updateAngle();
			time = clock.millis();
		}
		updateGravityType();
		updateGravityStep();
	}

}
