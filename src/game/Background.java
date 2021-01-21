package game;

import game.engine.object.GameObject;
import game.engine.object.component.Renderer;
import game.engine.sprite.SpriteSheet;

public class Background extends GameObject {
	
	public Background() {
		SpriteSheet background = new SpriteSheet(546, 748, 1, 0, "resources//HDSprites//Background.png", 0, 0);
		setAppearance(background.getSprite());
		
		Renderer render = new Renderer();
		addComponent(render);
	}
	
	@Override
	public void start() {
		super.start();
		
		transform.setPosition(546 / 2, 748 / 2);
	}
}