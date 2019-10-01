package fr.umlv.rotofight.World.Event;

public class RenderTimer {
	/**
	 * Private constructor of RenderTimer, should not be used.
	 */
	private RenderTimer() {
		throw new RuntimeException("Cannot instantiate RenderTimer class.");
	}
	/**
	 * Stops the game for x milliseconds.
	 */
	public static void stop(int x) {
		try {
			Thread.sleep(x);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
