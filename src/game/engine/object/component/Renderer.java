package game.engine.object.component;

import game.engine.GameApplication;
import game.engine.sprite.Sprite;

import javafx.scene.image.ImageView;

public class Renderer extends Component {

	@Override
	public void update () {	
		Sprite sprite = rootGameObject.getAppearance();
		ImageView spriteView = sprite.toImageView();   // Convert sprite to javafx component
		spriteView.setX(rootGameObject.transform.getPosition().x - sprite.getWidth() / 2); // Set sprite x position
		spriteView.setY(rootGameObject.transform.getPosition().y - sprite.getHeight() / 2); // Set sprite y position
		GameApplication.gLoop.getRootScene().getChildren().add(spriteView);
	}
}
