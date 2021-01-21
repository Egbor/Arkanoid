package game.vaus;

import game.engine.animation.Animation;
import game.engine.animation.Frame;
import game.engine.object.component.Animator;
import game.engine.sprite.SpriteSheet;

public class VausAnimations {
	
	/*
	 * All of this is a holy bull sheet.
	 * It is all should be in separating files.
	 * But I'm a lazy ass hole. 
	 * Good luck!)
	 * */
	
	private static final String spriteSheetPath = "resources//HDSprites//_Vaus_.png";
	private static final SpriteSheet vausDefault = new SpriteSheet(352, 22, 4, 0, spriteSheetPath, 0, 22);
	private static final SpriteSheet vausLong = new SpriteSheet(528, 22, 4, 0, spriteSheetPath, 0, 0);
	private static final SpriteSheet vausShort = new SpriteSheet(176, 22, 4, 0, spriteSheetPath, 0, 44);
	private static final SpriteSheet vausDefaultTranformation = new SpriteSheet(672, 22, 8, 0, spriteSheetPath, 0, 66);
	private static final SpriteSheet vausLongTranformation = new SpriteSheet(992, 22, 8, 0, spriteSheetPath, 0, 88);
	private static final SpriteSheet vausShortTranformation = new SpriteSheet(352, 22, 8, 0, spriteSheetPath, 0, 110);
	
	private static final Frame[] vausDefaultFilm = { 
			new Frame(vausDefault.getSprite(0), 3), new Frame(vausDefault.getSprite(1), 3),
			new Frame(vausDefault.getSprite(2), 3), new Frame(vausDefault.getSprite(3), 3), 
			new Frame(vausDefault.getSprite(2), 3), new Frame(vausDefault.getSprite(1), 3) 
	};
	private static final Frame[] vausLongFilm = { 
			new Frame(vausLong.getSprite(0), 3), new Frame(vausLong.getSprite(1), 3),
			new Frame(vausLong.getSprite(2), 3), new Frame(vausLong.getSprite(3), 3), 
			new Frame(vausLong.getSprite(2), 3), new Frame(vausLong.getSprite(1), 3) 
	};
	private static final Frame[] vausShortFilm = { 
			new Frame(vausShort.getSprite(0), 3), new Frame(vausShort.getSprite(1), 3),
			new Frame(vausShort.getSprite(2), 3), new Frame(vausShort.getSprite(3), 3), 
			new Frame(vausShort.getSprite(2), 3), new Frame(vausShort.getSprite(1), 3) 
	};
	private static final Frame[] vausDefaultTranformationFilm = { 
			new Frame(vausDefaultTranformation.getSprite(0), 1), new Frame(vausDefaultTranformation.getSprite(1), 1),
			new Frame(vausDefaultTranformation.getSprite(2), 1), new Frame(vausDefaultTranformation.getSprite(3), 1), 
			new Frame(vausDefaultTranformation.getSprite(4), 1), new Frame(vausDefaultTranformation.getSprite(5), 1),
			new Frame(vausDefaultTranformation.getSprite(6), 1), new Frame(vausDefaultTranformation.getSprite(7), 1)
	};
	private static final Frame[] vausLongTranformationFilm = { 
			new Frame(vausLongTranformation.getSprite(0), 1), new Frame(vausLongTranformation.getSprite(1), 1),
			new Frame(vausLongTranformation.getSprite(2), 1), new Frame(vausLongTranformation.getSprite(3), 1), 
			new Frame(vausLongTranformation.getSprite(4), 1), new Frame(vausLongTranformation.getSprite(5), 1),
			new Frame(vausLongTranformation.getSprite(6), 1), new Frame(vausLongTranformation.getSprite(7), 1)
	};
	private static final Frame[] vausShortTranformationFilm = { 
			new Frame(vausShortTranformation.getSprite(0), 1), new Frame(vausShortTranformation.getSprite(1), 1),
			new Frame(vausShortTranformation.getSprite(2), 1), new Frame(vausShortTranformation.getSprite(3), 1), 
			new Frame(vausShortTranformation.getSprite(4), 1), new Frame(vausShortTranformation.getSprite(5), 1),
			new Frame(vausShortTranformation.getSprite(6), 1), new Frame(vausShortTranformation.getSprite(7), 1)
	};
	
	public static final Animation vausDefaultAnimation = new Animation(vausDefaultFilm, Animation.PLAYING_FORWARD, Animation.PLAY_CONTINUOUSLY);
	public static final Animation vausLongAnimation = new Animation(vausLongFilm, Animation.PLAYING_FORWARD, Animation.PLAY_CONTINUOUSLY);
	public static final Animation vausShortAnimation = new Animation(vausShortFilm, Animation.PLAYING_FORWARD, Animation.PLAY_CONTINUOUSLY);
	public static final Animation vausDefaultTranformationAnimation = new Animation(vausDefaultTranformationFilm, Animation.PLAYING_FORWARD, Animation.PLAY_ONE_TIME);
	public static final Animation vausLongTranformationAnimation = new Animation(vausLongTranformationFilm, Animation.PLAYING_FORWARD, Animation.PLAY_ONE_TIME);
	public static final Animation vausShortTranformationAnimation = new Animation(vausShortTranformationFilm, Animation.PLAYING_FORWARD, Animation.PLAY_ONE_TIME);
	
	public static void startExtendToDefaultAnimation(Animator animator) {
		vausShortTranformationAnimation.setPlayingType(Animation.PLAYING_FORWARD);
		vausDefaultTranformationAnimation.setPlayingType(Animation.PLAYING_BACK);
		vausDefaultAnimation.setPlayingType(Animation.PLAYING_FORWARD);
		Animation[] animation = { vausShortTranformationAnimation, vausDefaultTranformationAnimation, vausDefaultAnimation };
		animator.createAnimationStack(animation);
	}
	
	public static void startExtendToLongAnimation(Animator animator) {
		vausDefaultTranformationAnimation.setPlayingType(Animation.PLAYING_FORWARD);
		vausLongTranformationAnimation.setPlayingType(Animation.PLAYING_BACK);
		vausLongAnimation.setPlayingType(Animation.PLAYING_FORWARD);
		Animation[] animation = { vausDefaultTranformationAnimation, vausLongTranformationAnimation, vausLongAnimation };
		animator.createAnimationStack(animation);
	}
	
	public static void startReduceToShortAnimation(Animator animator) {
		vausDefaultTranformationAnimation.setPlayingType(Animation.PLAYING_FORWARD);
		vausShortTranformationAnimation.setPlayingType(Animation.PLAYING_BACK);
		vausShortAnimation.setPlayingType(Animation.PLAYING_FORWARD);
		Animation[] animation = { vausDefaultTranformationAnimation, vausShortTranformationAnimation, vausShortAnimation };
		animator.createAnimationStack(animation);
	}
	
	public static void startReduceToDefaultAnimation(Animator animator) {
		vausLongTranformationAnimation.setPlayingType(Animation.PLAYING_FORWARD);
		vausDefaultTranformationAnimation.setPlayingType(Animation.PLAYING_BACK);
		vausDefaultAnimation.setPlayingType(Animation.PLAYING_FORWARD);
		Animation[] animation = { vausLongTranformationAnimation, vausDefaultTranformationAnimation, vausDefaultAnimation };
		animator.createAnimationStack(animation);
	}
}
