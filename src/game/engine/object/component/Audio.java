package game.engine.object.component;

import javafx.scene.media.AudioClip;

import java.io.File;

public class Audio extends Component {
	
	private AudioClip sound;
	
	public Audio(String filename) {
		sound = new AudioClip(new File(filename).toURI().toString());
	}
	
	public AudioClip getSound() {
		return sound;
	}
}
