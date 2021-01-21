package game;

import game.engine.math.Point;
import game.engine.object.GameObject;
import game.engine.object.component.Animation;
import game.engine.object.component.Collider;
import game.engine.object.component.Renderer;
import game.engine.sprite.SpriteSheet;

public class Powerup extends GameObject {
	
	Collider collider;
	
	public Powerup() {
		Animation powerupAnimation = new Animation();
		SpriteSheet powerupSprite = new SpriteSheet(352, 22, 8, 0);
		powerupSprite.load("resources//HDSprites//General.png", 0, 326);
		powerupAnimation.addFrame(powerupSprite.getSprite(0), 3);
		powerupAnimation.addFrame(powerupSprite.getSprite(1), 3);
		powerupAnimation.addFrame(powerupSprite.getSprite(2), 3);
		powerupAnimation.addFrame(powerupSprite.getSprite(3), 3);
		powerupAnimation.addFrame(powerupSprite.getSprite(4), 9);
		powerupAnimation.addFrame(powerupSprite.getSprite(5), 3);
		powerupAnimation.addFrame(powerupSprite.getSprite(6), 3);
		powerupAnimation.addFrame(powerupSprite.getSprite(7), 3);
		
		Renderer render = new Renderer();
		
		Point[] vertices = { new Point(-22, 11),
				 			 new Point(22, -11), new Point(-22, -11) };
		collider = new Collider(vertices);
		
		addComponent(collider);
		addComponent(powerupAnimation);
		addComponent(render);
	}
	
	@Override
	public void start() {
		super.start();
		
		transform.setPosition(100.0f, 100.0f);
	}
	
	@Override
	public void update() {
		super.update();
		
		collider.showDebugShape();
		
		//transform.translate(0.0f, 2.0f);
		//System.out.println(transform.getPosition().x + " " + transform.getPosition().y);
	}
	
	@Override
	public void onTriggerEnter(Collider other) {
		//destroy();
		//System.out.println("destroyed!");
	}
}
