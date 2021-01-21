package game.engine.object.component;

import game.engine.sprite.Sprite;
import game.engine.animation.Animation;
import java.util.Stack;

public class Animator extends Component {
	
	private Animation currentPlayableAnimation = null;
	private Stack<Animation> animationStack = new Stack<Animation>();
	
	public Sprite getLinkToCurrentAnimationSprite() {
		return currentPlayableAnimation.getLinkToCurrentFrame().getSprite();
	}
	
	public void createAnimation(Animation animation) {
		animationStack.clear();
		currentPlayableAnimation = animation;
		currentPlayableAnimation.refresh();
		linkGameObjectAppearanceWithCurrentAnimation();
	}
	
	public void createAnimationStack(Animation[] animations) {
		createAnimation(animations[0]);
		for (int i = animations.length - 1; i > 0; i--) {
			animations[i].refresh();
			animationStack.push(animations[i]);
		}
	}
	
	private void linkGameObjectAppearanceWithCurrentAnimation() {
		Sprite linkedSprite = currentPlayableAnimation.getLinkToCurrentFrame().getSprite();
		rootGameObject.setAppearance(linkedSprite);
	}
	
	@Override
	public void update() {
		currentPlayableAnimation.playNext();
		if (!animationStack.isEmpty() && currentPlayableAnimation.isEnd()) {
			currentPlayableAnimation = animationStack.pop();
			linkGameObjectAppearanceWithCurrentAnimation();
		}
	}
}
