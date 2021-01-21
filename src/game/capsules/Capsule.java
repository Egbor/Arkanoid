package game.capsules;

import game.Ball;

import game.engine.math.Point;
import game.engine.object.GameObject;
import game.engine.object.component.Animation;
import game.engine.object.component.Collider;
import game.engine.object.component.Renderer;
import game.engine.sprite.SpriteSheet;
import game.vaus.Vaus;

public class Capsule extends GameObject {
	
	public static enum CapsuleId { SLOW, CATCH, EXTEND, REDUCE };
	
	public static Vaus vaus;
	public static Ball ball;
	
	private Collider collider;
	private Renderer renderer;
	private Animation animator;
	
	private static final String spriteSheetPath = "resources//HDSprites//General.png";
	private static final SpriteSheet spriteSheetSlow = new SpriteSheet(352, 22, 8, 0, spriteSheetPath, 0, 398);
	private static final SpriteSheet spriteSheetCatch = new SpriteSheet(352, 22, 8, 0, spriteSheetPath, 0, 374);
	private static final SpriteSheet spriteSheetExtend = new SpriteSheet(352, 22, 8, 0, spriteSheetPath, 0, 350);
	private static final SpriteSheet spriteSheetReduce = new SpriteSheet(352, 22, 8, 0, spriteSheetPath, 0, 422);
	
	protected SpriteSheet loadCapsuleSprites(CapsuleId id) {
		switch (id) {
			case SLOW:
				return spriteSheetSlow;
			case CATCH:
				return spriteSheetCatch;
			case EXTEND:
				return spriteSheetExtend;
			case REDUCE:
				return spriteSheetReduce;
		}
		return null;
	}
	
	protected void initCapsuleAnimation(CapsuleId id) {
		animator = new Animation();
		SpriteSheet sprites = loadCapsuleSprites(id);
		for (int i = 0; i < 8; i++) {
			int delay = 3;
			if (i == 4) {
				delay = 9;
			}
			animator.addFrame(sprites.getSprite(i), delay);
		}
	}
	
	protected void initCapsuleCollider() {
		Point[] vertices = { new Point(-22, 11), new Point(22, 11),
				 			 new Point(22, -11), new Point(-22, -11) };
		collider = new Collider(vertices);
		collider.isSolid = false;
	}
	
	protected void initCapsuleRenderer() {
		this.setAppearance(animator.getCurrentFrame().getSprite());
		renderer = new Renderer();
	}
	
	protected void initCapsule(CapsuleId id) {
		initCapsuleCollider();
		initCapsuleAnimation(id);
		initCapsuleRenderer();
		
		this.addComponent(collider);
		this.addComponent(animator);
		this.addComponent(renderer);
	}
	
	@Override
	public void update() {
		super.update();
		
		transform.translate(0.0f, 2.0f);
		//collider.showDebugShape();
	}
	
	@Override
	public void onTriggerEnter(Collider other) {
		super.onTriggerEnter(other);
		
		if (vaus.isContaneComponent(other)) {
			Ball.resetToDefault();
			this.destroy();
		}
	}
}
