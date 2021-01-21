package game.vaus;

import game.Ball;
import game.engine.input.Input;
import game.engine.math.Vector;
import game.engine.math.Point;

import game.engine.object.GameObject;
import game.engine.object.component.Animator;
import game.engine.object.component.Audio;
import game.engine.object.component.Renderer;
import game.engine.object.component.Collider;

public class Vaus extends GameObject {
	
	private Collider collider;
	private Animator animator;
	private Renderer renderer;
	
	private static final Point[] vausShortHitBox = { new Point(-22, 11), new Point(22, 11),
			   										  new Point(22, -11), new Point(-22, -11) };
	private static final Point[] vausDefaultHitBox = { new Point(-44, 11), new Point(44, 11),
			 										   new Point(44, -11), new Point(-44, -11) };
	private static final Point[] vausLongHitBox = { new Point(-66, 11), new Point(66, 11),
			   										new Point(66, -11), new Point(-66, -11) };
	
	private static final int SIZE_MODE_SHORT   = 0;
	private static final int SIZE_MODE_DEFAULT = 1;
	private static final int SIZE_MODE_LONG    = 2;
	
	public Ball ball = null;
	public int currentSizeMode = 1;
	
	public Vaus() {
		animator = new Animator();
		renderer = new Renderer();
		collider = new Collider(vausDefaultHitBox);
		
		addComponent(collider);
		addComponent(animator);
		addComponent(renderer);
	}
	
	public void handleMovementControl() {
		Vector movement = new Vector(0.0f, 0.0f);
//		if (Input.getKeyPress("W")) {
//			movement = Vector.add(movement, Vector.TOP);
//		}
		if (Input.getKeyPress("A")) {
			movement = Vector.add(movement, Vector.LEFT);
		}
//		if (Input.getKeyPress("S")) {
//			movement = Vector.add(movement, Vector.BOTTOM);
//		}
		if (Input.getKeyPress("D")) {
			movement = Vector.add(movement, Vector.RIGHT);
		}
		if (Input.getKeyPress("Space") && (ball != null)) {
			ball = null;
		}
		movement.setLength(10.0f);
		transform.translate(movement);
	}
	
	public void holdBall() {
		ball.transform.setPosition(transform.getPosition().x + 5.75f, 660.0f);
	}
	
	public void extend() {
		switch (currentSizeMode) {
			case 0:
				VausAnimations.startExtendToDefaultAnimation(animator);
				collider.setShape(vausDefaultHitBox);
				currentSizeMode = SIZE_MODE_DEFAULT;
			    break;
			case 1:
				VausAnimations.startExtendToLongAnimation(animator);
				collider.setShape(vausLongHitBox);
				currentSizeMode = SIZE_MODE_LONG;
			    break;
		}
	}
	
	public void reduce() {
		switch (currentSizeMode) {
			case 1:
				VausAnimations.startReduceToShortAnimation(animator);
				collider.setShape(vausShortHitBox);
				currentSizeMode = SIZE_MODE_SHORT;
			    break;
			case 2:
				VausAnimations.startReduceToDefaultAnimation(animator);
				collider.setShape(vausDefaultHitBox);
				currentSizeMode = SIZE_MODE_DEFAULT;
			    break;
		}
	}
	
	public void reset() {
		currentSizeMode = 1;
		collider.setShape(vausDefaultHitBox);;
		animator.createAnimation(VausAnimations.vausDefaultAnimation);
	}
	
	@Override
	public void start() {
		super.start();
		currentSizeMode = 1;
		collider.setMass(10.0f);
		collider.setShape(vausDefaultHitBox);
		animator.createAnimation(VausAnimations.vausDefaultAnimation);
		transform.setPosition(273.0f, 670.0f);
	}
	
	@Override
	public void update() {
		super.update();
		
		if (ball != null) {
			holdBall();
		}
		this.handleMovementControl();
		//collider.showDebugShape();
	}
	
	@Override
	public void onTriggerEnter(Collider other) {
		if (other.getOwner() instanceof Ball && Ball.magnetized) {
			ball = (Ball)other.getOwner();
		}
	}
}
