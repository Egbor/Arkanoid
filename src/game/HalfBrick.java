package game;

import game.engine.math.Point;
import game.engine.object.GameObject;
import game.engine.object.component.Collider;
import game.engine.object.component.Renderer;
import game.engine.sprite.SpriteSheet;

public class HalfBrick extends GameObject {
	public SpriteSheet sprites;
	
	private Renderer renderer;
	private Collider collider;
	private Point[] vertices1 = { new Point(-22.0f, -11.0f), new Point(22.0f, -11.0f),
								  new Point(-22.0f, 11.0f) };
	private Point[] vertices2 = { new Point(-22.0f, -11.0f), new Point(22.0f, -11.0f),
			 					  new Point(22.0f, 11.0f) };
	
	private static boolean hasBrokenInThatFrame = false;
	
	public HalfBrick(int mode) {
		renderer = new Renderer();
		if (mode == 0) {
			collider = new Collider(vertices1);
		} else {
			collider = new Collider(vertices2);
		}
		this.addComponent(collider);
		this.addComponent(renderer);
	}
	
	@Override
	public void start() {
		super.start();
		setAppearance(sprites.getSprite());
		collider.setMass(10.0f);
	}
	
	@Override
	public void update() {
		super.update();
		
		hasBrokenInThatFrame = false;
		//collider.showDebugShape();
	}
	
	@Override
	public void onTriggerEnter(Collider other) {
		if (other.isSolid && !hasBrokenInThatFrame) {
			GameManager.target--;
			hasBrokenInThatFrame = true;
			this.destroy();
		}
	}
}
