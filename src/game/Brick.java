package game;

import java.lang.Math;

import game.capsules.Capsule;
import game.capsules.CapsuleCatch;
import game.capsules.CapsuleExtend;
import game.capsules.CapsuleReduce;
import game.capsules.CapsuleSlow;

import game.engine.math.Point;
import game.engine.object.GameObject;
import game.engine.object.component.Collider;
import game.engine.object.component.Renderer;
import game.engine.sprite.SpriteSheet;

public class Brick extends GameObject {

	public SpriteSheet sprites;
	
	private Renderer renderer;
	private Collider collider;
	private Point[] vertices = { new Point(-28.5f, -17.5f), new Point(15.5f, -17.5f),
								 new Point(14.5f, 4.5f), new Point(-28.5f, 4.5f) };
	
	private static boolean hasBrokenInThatFrame = false;
	
	@Override
	public void start() {
		super.start();
		
		setAppearance(sprites.getSprite());
		
		renderer = new Renderer();
		collider = new Collider(vertices);
		collider.setMass(10.0f);
		
		this.addComponent(collider);
		this.addComponent(renderer);
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
			this.generatePowerCapsule();
			this.destroy();
		}
	}
	
	private void generatePowerCapsule() {
		int dice = (int)(Math.random()*((3 - 1) + 1)) + 1;
		if (dice == 1) {
			dice = (int)(Math.random()*((4 - 1) + 1)) + 1;
			dice %= 4;
			initPowerCapsule(dice);
		}
	}
	
	private void initPowerCapsule(int id) {
		Point position = new Point(transform.getPosition().x - 6.5f, transform.getPosition().y - 6.5f);
		Capsule capsule = null;
		switch (id) {
			case 0:
				capsule = new CapsuleSlow(position.x, position.y);
				break;
			case 1:
				capsule = new CapsuleCatch(position.x, position.y);
				break;
			case 2:
				capsule = new CapsuleExtend(position.x, position.y);
				break;
			case 3:
				capsule = new CapsuleReduce(position.x, position.y);
				break;
		}
		this.getGameScene().addObject(capsule);
	}
	
}
