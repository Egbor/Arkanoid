package game;

import game.engine.math.Point;
import game.engine.math.Vector;
import game.engine.object.GameObject;
import game.engine.sprite.SpriteSheet;
import game.vaus.Vaus;
import game.engine.object.component.Renderer;
import game.engine.object.component.Collider;
import game.engine.object.component.Audio;

public class Ball extends GameObject {
	
	public Vaus vaus;
	
	private Audio audio;
	
	public static float speed = 10.0f;
	public static boolean magnetized = false;
	
	private Vector movement;
	private static final Point[] vertices = {new Point(-8.5f, -11.5f), new Point(-4.5f, -11.5f), 
											 new Point(-1.5f, -8.5f), new Point(-1.5f, -4.5f),
											 new Point(-4.5f, -1.5f), new Point(-8.5f, -1.5f),
											 new Point(-11.5f, -4.5f), new Point(-11.5f, -8.5f)};
	
	public Ball() {
		SpriteSheet sprite = new SpriteSheet(23, 23, 1, 0, "resources//HDSprites//General.png", 812, 60);
		setAppearance(sprite.getSprite());
		
		Renderer renderer = new Renderer();
		Collider collider = new Collider(vertices);
		audio = new Audio("resources//Sound//ball_collide.wav");
		
		addComponent(renderer);
		addComponent(collider);
	}
	
	@Override
	public void start() {
		super.start();
		speed = 10.0f;
		magnetized = false;
		transform.setPosition(-100, -200);
		movement = new Vector(1.0f, -1.0f);
		movement.normalize();
		movement = Vector.mull(speed, movement);
	}
	
	@Override
	public void update() {
		super.update();
		
		//Collider collider = (Collider)getComponent(Collider.class);
		//collider.showDebugShape();
		translate();
	} 
	
	@Override
	public void onTriggerEnter(Collider collider) {
		if (collider.isSolid) {
			audio.getSound().play();
		}
		
		if (vaus.isContaneComponent(collider)) {
			movement = new Vector(transform.getPosition().x - vaus.transform.getPosition().x, 
								  transform.getPosition().y - vaus.transform.getPosition().y);
		} else if (collider.isSolid) {
			collider.collisionSide.normalize();
			float projection = Vector.dotProduct(movement, collider.collisionSide);
			if (projection > 0.0f) {
				collider.collisionSide = Vector.mull(-1.0f, collider.collisionSide);
			}
			movement = Vector.reflect(movement, collider.collisionSide);
		}
	}
	
	public static void resetToDefault() {
		speed = 10.0f;
		magnetized = false;
	}
	
	private void translate() {
		if (movement.abs() < 0.0000000001f) {
			movement.setDirection(1.0f, -1.0f);
		}
		movement.setLength(speed);
		transform.translate(movement);
	}
}
