package game.engine.physics;

import game.engine.math.Point;
import game.engine.math.Vector;

public class PhysicalShape {
	
	public int verticesAmount;
	private Point[] vertices;
	private Vector[] normals;
	
	public float mass = 1.0f;
	private Point previousPosition;
	private Point position;
	private Point[] shape;
	
	private static class Projection {
		public float minProjectionPoint;
		public float maxProjectionPoint;
	}
	
	public PhysicalShape(Point[] vertices) {
		verticesAmount = vertices.length;
		this.vertices = vertices;												// Initialize array of vertices
		this.position = new Point();
		this.previousPosition = new Point();
		
		this.initilizeNormals();
		this.initilizeShape();
	}
	
	public PhysicalShape() {
		
	}
	
	public void setShpae(Point[] vertices) {
		verticesAmount = vertices.length;
		this.vertices = vertices;												// Initialize array of vertices
		this.position = new Point();
		this.previousPosition = new Point();
		
		this.initilizeNormals();
		this.initilizeShape();
	}
	
	public void initilizeNormals() {
		normals = new Vector[verticesAmount];								// Initialize array of normals
		for (int i = 0; i < verticesAmount; i++) {
			int j = i + 1;
			j %= verticesAmount;
			Vector normal = new Vector(
					-(vertices[j].y - vertices[i].y),
					 (vertices[j].x - vertices[i].x)
			);		
			normal.normalize();
			normals[i] = normal; 
		}
	}
	
	public void initilizeShape() {
		shape = new Point[verticesAmount];
		for (int i = 0; i < shape.length; i++) {
			shape[i] = new Point();
		}
	}
	
	public Point getVertex(int index) {
		return shape[index];
	}
	
	public Point getPosition() {
		return position;
	}
	
	public Vector getNormal(int index) {
		return normals[index];
	}
	
	public Vector getMovement() {
		return new Vector(position.x - previousPosition.x,
						  position.y - previousPosition.y);
	}
	
	public void setShapePosition(Point position) {
		this.previousPosition.x = this.position.x;
		this.previousPosition.y = this.position.y;
		this.position.x = position.x;
		this.position.y = position.y;
		this.placeShapeAboutPosition(position);
	}
	
	private void placeShapeAboutPosition(Point position) {
		for (int i = 0; i < verticesAmount; i++) {
			shape[i].x = vertices[i].x + position.x;
			shape[i].y = vertices[i].y + position.y;
		}
	}
	
	private static Projection projectShapeOntoAxis(PhysicalShape shape, final Vector normal) {
		float minProjectionPoint = Vector.dotProduct(new Vector(shape.getVertex(0)), normal);
		float maxProjectionPoint = minProjectionPoint;
		
		for (int i = 1; i < shape.verticesAmount; i++) {
			float tempProjectionPoint = Vector.dotProduct(new Vector(shape.getVertex(i)), normal);
			if (tempProjectionPoint < minProjectionPoint) {
				minProjectionPoint = tempProjectionPoint;
			} 
			if (tempProjectionPoint > maxProjectionPoint) {
				maxProjectionPoint = tempProjectionPoint;
			}
		}
		
		Projection projection = new Projection();
		projection.minProjectionPoint = minProjectionPoint;
		projection.maxProjectionPoint = maxProjectionPoint;
		return projection;
	}
	
	private static boolean checkCollisionOneShape(PhysicalShape shapeA, PhysicalShape shapeB) {
		boolean isCollide = true;
		int normalIndex = 0;
		while (isCollide && (normalIndex < shapeA.verticesAmount)) {
			Projection projectionA = projectShapeOntoAxis(shapeA, shapeA.getNormal(normalIndex));
			Projection projectionB = projectShapeOntoAxis(shapeB, shapeA.getNormal(normalIndex));
			isCollide = !((projectionA.minProjectionPoint > projectionB.maxProjectionPoint) ||
					(projectionA.maxProjectionPoint < projectionB.minProjectionPoint));
//			if ((projectionA.minProjectionPoint > projectionB.maxProjectionPoint) ||
//					(projectionA.maxProjectionPoint < projectionB.minProjectionPoint)) {
//				isCollide = false;
//			}
			normalIndex++;
		}
		return isCollide;
	}
	
	private static float findEntryDepth(PhysicalShape shapeA, PhysicalShape shapeB, Vector normal) {
		Projection projectionA = projectShapeOntoAxis(shapeA, normal);
		Projection projectionB = projectShapeOntoAxis(shapeB, normal);
		float depthA = Math.abs(projectionA.minProjectionPoint - projectionB.maxProjectionPoint);
		float depthB = Math.abs(projectionA.maxProjectionPoint - projectionB.minProjectionPoint);
		return (depthA < depthB) ? depthA : depthB;
	}
	
	private static Vector findDepthVectorRelativeShapeA(PhysicalShape shapeA, PhysicalShape shapeB) { 
		int normalIndex = 0;
		float minAbsRepulsiveForce = findEntryDepth(shapeA, shapeB, shapeA.getNormal(normalIndex));
		for (int i = 1; i< shapeA.verticesAmount; i++) {
			float tempAbsRepulsiveForce = findEntryDepth(shapeA, shapeB, shapeA.getNormal(i));
			if (tempAbsRepulsiveForce < minAbsRepulsiveForce) {
				minAbsRepulsiveForce = tempAbsRepulsiveForce;
				normalIndex = i;
			}
		}
		return Vector.mull(minAbsRepulsiveForce + 1, shapeA.getNormal(normalIndex));
	}
	
	private static Vector findDepthVector(PhysicalShape shapeA, PhysicalShape shapeB) {
		Vector forceA = findDepthVectorRelativeShapeA(shapeA, shapeB);
		Vector forceB = findDepthVectorRelativeShapeA(shapeB, shapeA);
		return (forceA.abs() < forceB.abs()) ? forceA : forceB;
	}
	
	private static void repulseShape(PhysicalShape shapeA, PhysicalShape shapeB, Vector depth) {
		Vector dir = new Vector(shapeB.position.x - shapeA.position.x,
							    shapeB.position.y - shapeA.position.y);
		float forceProjection = Vector.dotProduct(depth, dir);
		if (forceProjection < 0.0f) {
			depth = Vector.mull(-1.0f, depth);
		}
		Point newPosition = new Point(shapeA.position.x - depth.getDirection().x,
			    					  shapeA.position.y - depth.getDirection().y);
		shapeA.setShapePosition(newPosition);
	}
	
	public static Vector repulseShapesFromEachOther(PhysicalShape shapeA, PhysicalShape shapeB) {
		Vector depthVector = findDepthVector(shapeA, shapeB);
		if (shapeA.mass > shapeB.mass) {
			repulseShape(shapeB, shapeA, depthVector);
		} else {
			repulseShape(shapeA, shapeB, depthVector);
		}
		return depthVector;
	}
	
	public static boolean isCollide(PhysicalShape shapeA, PhysicalShape shapeB) {
		boolean isCollideRelativeShapeA = checkCollisionOneShape(shapeA, shapeB);
		boolean isCollideRelativeShapeB = checkCollisionOneShape(shapeB, shapeA);
		return isCollideRelativeShapeA && isCollideRelativeShapeB;
	}
}
