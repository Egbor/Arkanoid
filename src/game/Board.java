package game;

import game.engine.math.Point;
import game.engine.object.component.Collider;
import game.engine.object.GameObject;

public class Board extends GameObject {
	
	public Point position;
	public Point[] vertices;
	
	private Collider collider = new Collider();
	
	@Override
	public void start() {
		super.start();
		
		collider.setShape(vertices);;
		collider.setMass(99999.0f);
		transform.setPosition(position);
		
		this.addComponent(collider);
	}
	
	@Override
	public void update() {
		super.update();
		
		//collider.showDebugShape();
	}
	
}
