package game.engine.object.component;

import game.engine.GameApplication;
import game.engine.physics.PhysicalShape;
import game.engine.math.Point;
import game.engine.math.Vector;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Collider extends Component {
	
	public PhysicalShape shape;
	public Vector collisionSide;
	
	public boolean isSolid = true;
	
	public Collider(Point[] vertices) {
		shape = new PhysicalShape(vertices);
	}
	
	public Collider() {
		shape = new PhysicalShape();
	}
	
	public void updateShapePosition() {
		shape.setShapePosition(rootGameObject.transform.getPosition());
	}
	
	public void setMass(float mass) {
		shape.mass = mass;
	}
	
	public void setShape(Point[] shape) {
		this.shape.setShpae(shape);
	}
	
	public void showDebugShape() {
		for (int i = 0; i < shape.verticesAmount; i++) {
			int j = i + 1;
			j %= shape.verticesAmount;
			Point vertexStart = shape.getVertex(i);
			Point vertexEnd = shape.getVertex(j);
			Line line = new Line(vertexStart.x, vertexStart.y, vertexEnd.x, vertexEnd.y);
			line.setStroke(Color.LIME);
			GameApplication.gLoop.getRootScene().getChildren().add(line);
		}
	}
	
	public static void checkCollisionTwoObjects(Collider objectColliderA, Collider objectColliderB) {
		if (PhysicalShape.isCollide(objectColliderA.shape, objectColliderB.shape)) { 
			if (objectColliderA.isSolid && objectColliderB.isSolid) {
				Vector depth = PhysicalShape.repulseShapesFromEachOther(objectColliderA.shape, objectColliderB.shape);
				objectColliderA.collisionSide = depth;
				objectColliderB.collisionSide = depth;
				objectColliderA.rootGameObject.transform.setPosition(objectColliderA.shape.getPosition());
				objectColliderB.rootGameObject.transform.setPosition(objectColliderB.shape.getPosition());
			}
			objectColliderA.rootGameObject.onTriggerEnter(objectColliderB);
			objectColliderB.rootGameObject.onTriggerEnter(objectColliderA);
		}
	}

	@Override
	public void start() {
		shape.setShapePosition(rootGameObject.transform.getPosition());
	}
	
	@Override
	public void update() {
	}
	
}
