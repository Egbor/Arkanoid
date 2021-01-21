package game.engine.math;

public class Vector {
	
	public static final Vector RIGHT = new Vector(1.0f, 0.0f);
	public static final Vector LEFT = new Vector(-1.0f, 0.0f);
	public static final Vector BOTTOM = new Vector(0.0f, 1.0f);
	public static final Vector TOP = new Vector(0.0f, -1.0f);
	
	private Point direction;
	
	public Vector(Point direction) {
		this.direction = direction;
	}
	
	public Vector(float x, float y) {
		direction = new Point(x, y);
	}
	
	public void setDirection(Point direction) {
		this.direction = direction;
	}
	
	public void setDirection(float x, float y) {
		direction.x = x;
		direction.y = y;
	}
	
	public Point getDirection() {
		return direction;
	}
	
	public float abs() {
		return (float)Math.sqrt(direction.x * direction.x + direction.y * direction.y);
	}
	
	public void normalize() {
		float abs = abs();
		if (abs > 0) {
			direction.x /= abs;
			direction.y /= abs;
		}
	}
	
	public void setLength(float length) {
		this.normalize();
		this.direction.x *= length;
		this.direction.y *= length;
	}
	
	public static Vector reflect(Vector vectorA, Vector normal) {
		float dotProductValueA = Vector.dotProduct(normal, vectorA);
		Vector sumVectorAandB = Vector.mull(2.0f * dotProductValueA, normal);
		return Vector.sub(vectorA, sumVectorAandB);
	}
	
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
}
