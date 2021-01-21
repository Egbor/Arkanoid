package game.capsules;

import game.Ball;
import game.capsules.Capsule;
import game.engine.object.component.Audio;
import game.engine.object.component.Collider;

public class CapsuleCatch extends Capsule {

	private final static Audio aVoiceCatch = new Audio("resources//Sound//voice_catch.wav");
	
	public CapsuleCatch(float x, float y) {
		this.initCapsule(CapsuleId.CATCH);
		transform.setPosition(x, y);
	}
	
	@Override
	public void onTriggerEnter(Collider other) {
		super.onTriggerEnter(other);
	
		if (vaus.isContaneComponent(other)) {
			aVoiceCatch.getSound().play();
			Ball.magnetized = true;
		}
	}
}
