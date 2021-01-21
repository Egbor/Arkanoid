package game;

import game.engine.math.Point;
import game.engine.object.GameObject;
import game.engine.object.component.Collider;

public class Abyss extends GameObject {
	
	private Collider collider;
	
	private static final Point[] vertices = { new Point(-247, 30), new Point(247, 30),
									 		  new Point(247, -30), new Point(-247, -30) };
	
	public Abyss() {
		collider = new Collider(vertices);
		addComponent(collider);
		
		collider.isSolid = false;
	}
	
	@Override
	public void start() {
		super.start();
		
		transform.setPosition(273.0f, 750.0f);
	}
	
	@Override
	public void onTriggerEnter(Collider other) {
		other.getOwner().destroy();
	}
}
