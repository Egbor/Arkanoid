package game.engine.math;

public class Point {
	public float x;
	public float y;
	
	public Point () {
		x = 0.0f;
		y = 0.0f;
	}
	
	public Point(Point point) {
		x = point.x;
		y = point.y;
	}
	
	public Point(float x, float y) {
		this.x = x;
		this.y = y;
	}
}
