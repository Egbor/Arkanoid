package game.engine.math;

public class VectorOperation {
	
	public static Vector mull(float value, Vector a) {
		float x = a.getDirection().x;
		float y = a.getDirection().y;
		return new Vector(value * x, value * y);
	}
	
	public static Vector add(Vector a, Vector b) {
		float aX = a.getDirection().x;
		float aY = a.getDirection().y;
		float bX = b.getDirection().x;
		float bY = b.getDirection().y;
		return new Vector(aX + bX, aY + bY);
	}
	
	public static Vector sub(Vector a, Vector b) {
		float aX = a.getDirection().x;
		float aY = a.getDirection().y;
		float bX = b.getDirection().x;
		float bY = b.getDirection().y;
		return new Vector(aX - bX, aY - bY);
	}
	
	public static float abs(Vector vec) {
		float x = vec.getDirection().x;
		float y = vec.getDirection().y;
		return (float)Math.sqrt(x * x + y * y);
	}
	
	public static float dotProduct(Vector a, Vector b) {
		return a.getDirection().x * b.getDirection().x +
				a.getDirection().y * b.getDirection().y;
	}
	
	public static void normalize(Vector vec) {
		if (vec.abs() > 0) {
			vec.getDirection().x /= vec.abs();
			vec.getDirection().y /= vec.abs();
		}
	}
}
