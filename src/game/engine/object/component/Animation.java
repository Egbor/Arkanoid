package game.engine.object.component;

import java.util.LinkedList;
import java.util.Queue;

import game.engine.animation.Frame;
import game.engine.sprite.Sprite;

public class Animation extends Component {
	
	private final Frame currentFrame;
	
	private Queue<Frame> film;
	private int exposure;
	
	public Animation() {
		currentFrame = new Frame();
		film = new LinkedList<Frame>();
		exposure = 0;
	}
	
	public Frame getCurrentFrame() {
		return currentFrame;
	}
	
	public void addFrame(Sprite sprite, int delay) {
		Frame frame = new Frame(sprite, delay);
		film.add(frame);
	}
	
	public void createFilm(Frame[] frames) {
		film.clear();
		for (Frame frame : frames) {
			film.add(frame);
		}
	}
	
	private Frame nextFrame() {
		Frame temp = film.poll(); // Get and remove frame in the head of this queue
		film.add(temp);			  // Add the frame in the end of this queue
		return temp;
	}
	
	@Override
	public void start() {
		currentFrame.copyOf(nextFrame());	// Get first frame
	}
	
	@Override
	public void update() {
		if (exposure == currentFrame.getDelay()) {
			currentFrame.copyOf(nextFrame());	// Get next frame
			exposure = 0;
		} else {
			exposure++;
		}
	}
} 
