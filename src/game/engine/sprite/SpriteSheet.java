package game.engine.sprite;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ClassLoader;
import java.net.MalformedURLException;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

public class SpriteSheet {
	
	private Sprite[] spriteSheet;
	private int width;
	private int height;
	private int offset;
	private int amount;
	
	public SpriteSheet(int width, int height, int amount, int offset) {
		this.width = width;					// Set sprite-sheet width
		this.height = height;				// Set sprite-height height
		this.offset = offset;				// Set size between sprites
		this.amount = amount;				// Set amount of sprites;
		spriteSheet = new Sprite[amount];	// Initialize sprite array
	}
	
	public SpriteSheet(int width, int height, int amount, int offset, String filepath, int x, int y) {
		this.width = width;					// Set sprite-sheet width
		this.height = height;				// Set sprite-height height
		this.offset = offset;				// Set size between sprites
		this.amount = amount;				// Set amount of sprites;
		spriteSheet = new Sprite[amount];	// Initialize sprite array
		this.load(filepath, x, y);
	}
	
	public Sprite getSprite() {
		return spriteSheet[0];
	}
	
	public Sprite getSprite(int index) {
		return spriteSheet[index];
	}
	
	public Sprite[] getAllSprites() {
		return spriteSheet;
	}
	
	private Image setImage(String path) {
		try {
			File fImage = new File(path);
			System.out.println(fImage.toURI().toString());
			return new Image(fImage.toURI().toString());
		} catch (Exception e) {
			return new Image(path);
		}
	}
	
	public void load(String filepath, int x, int y) {
		//System.out.println(this.getClass().getResource(filepath).getPath());
		Image image = setImage(filepath);						// Open file with sprites
		int singleSpriteWidth = (width - offset * (amount - 1)) / amount;					// Calculate sprite width
		int singleSpriteHeight = height;													// Calculate sprite height
		for (int i = 0; i < amount; i++) {
			spriteSheet[i] = new Sprite(new WritableImage(image.getPixelReader(), x, y,
					singleSpriteWidth, singleSpriteHeight), singleSpriteWidth, singleSpriteHeight);  // Load sprite from file
			x += singleSpriteWidth + offset;														 // offset to the next sprite
		}
	}
}
