package game.engine.animation;

import game.engine.animation.Frame;

public class Animation {
	
	public static final int PLAY_ONE_TIME 	  = 0;
	public static final int PLAY_CONTINUOUSLY = 1;
	
	public static final int PLAYING_FORWARD  = 0;
	public static final int PLAYING_BACK 	 = 1;
	
	private final Frame currentFrame = new Frame();
	
	private Frame[] film;
	private int frameCounter = 0;
	private int frameExposure = 0;
	
	private boolean isAnimationEnd  = false;
	private int playConfiguration   = 0;
	private int repeatConfiguration = 0;
	
	public Animation(Frame[] film, int playConfiguration, int repeatConfiguration) {
		this.film = film;
		this.playConfiguration = playConfiguration;
		this.repeatConfiguration = repeatConfiguration;
		if (playConfiguration == PLAYING_BACK) {
			frameCounter = film.length - 1;
			currentFrame.copyOf(film[frameCounter]);
		} else {
			frameCounter = 0;
			currentFrame.copyOf(film[frameCounter]);
		}
	}
	
	public boolean isEnd() {
		return isAnimationEnd;
	}
	
	public void setPlayingType(int playConfiguration) {
		this.playConfiguration = playConfiguration;
	}
	
	public Frame getLinkToCurrentFrame() {
		return currentFrame;
	}
	
	public void playNext() {
		if (currentFrame.getDelay() == frameExposure) {
			frameExposure = 0;
			changeFrame(playConfiguration, repeatConfiguration);
		} else {
			frameExposure++;
		}
	}
	
	public void refresh() {
		isAnimationEnd  = false;
		if (playConfiguration == PLAYING_BACK) {
			frameCounter = film.length - 1;
			currentFrame.copyOf(film[frameCounter]);
		} else {
			frameCounter = 0;
			currentFrame.copyOf(film[frameCounter]);
		}
	}
	
	private void changeFrame(int playConfiguration, int repeatConfiguration) {
		switch (playConfiguration) {
			case PLAYING_FORWARD:
				currentFrame.copyOf(getNextFrame(repeatConfiguration));
				break;
			case PLAYING_BACK:
				currentFrame.copyOf(getPreviousFrame(repeatConfiguration));
				break;
		}
	}
	
	private void nullifyFrame(int repeatConfiguration) {
		isAnimationEnd = repeatConfiguration == PLAY_ONE_TIME;
		if (!isAnimationEnd) {
			frameCounter = 0;
		}
	}
	
	private Frame getNextFrame(int repeatConfiguration) {
		if (frameCounter == (film.length - 1)) {
			nullifyFrame(repeatConfiguration);
		} else {
			frameCounter++;
		}
		return film[frameCounter];
	}
	
	private Frame getPreviousFrame(int repeatConfiguration) {
		if (frameCounter == 0) {
			nullifyFrame(repeatConfiguration);
		} else {
			frameCounter--;
		}
		return film[frameCounter];
	}
}
