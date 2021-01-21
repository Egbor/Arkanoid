package game.engine.animation;

import game.engine.sprite.Sprite;

public class Frame {
	private Sprite sprite;
	private int delay;
	
	public Frame() {
		sprite = new Sprite(null, 0, 0);
		delay = 0;
	}
	
	public Frame(Sprite sprite, int delay) {
		this.sprite = sprite;
		this.delay = delay;
	}
	
	public void copyOf(Frame frame) {
		sprite.setImage(frame.sprite.getImage(),
						frame.sprite.getWidth(), 
						frame.sprite.getHeight()
		);
		delay = frame.delay;
	}
	
	public int getDelay() {
		return delay;
	}
	
	public Sprite getSprite() {
		return sprite;
	}
}
