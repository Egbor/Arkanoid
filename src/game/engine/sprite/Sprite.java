package game.engine.sprite;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Sprite {
	
	private Image sprite;
	private int width;
	private int height;
	
	public Sprite(Image sprite, int width, int height) {
		this.sprite = sprite;
		this.width = width;
		this.height = height;
	}
	
	public void setImage(Image sprite, int width, int height) {
		this.sprite = sprite;
		this.width = width;
		this.height = height;
	}
	
	public void copyOf(Sprite sprite) {
		this.sprite = sprite.sprite;
		this.width = sprite.width;
		this.height = sprite.height;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public Image getImage() {
		return sprite;
	}
	
	public ImageView toImageView() {
		return new ImageView(sprite);	// create ImageView and return it
	}
}

