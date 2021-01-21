package game.engine;

public class Time {
	public static double deltaTime = 0.0;
	private static double endTime = 0.0;
	
	public static void begin() {
		deltaTime =  (System.currentTimeMillis() - endTime) / 1000.0;
	}
	
	public static void end() {
		endTime = System.currentTimeMillis();
		//deltaTime = (endTime - beginTime) / 1000.0;
	}
}
