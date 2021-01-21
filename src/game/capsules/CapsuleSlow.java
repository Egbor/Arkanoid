package game.capsules;

import game.Ball;
import game.capsules.Capsule;
import game.engine.object.component.Audio;
import game.engine.object.component.Collider;

public class CapsuleSlow extends Capsule {
	
	private final static Audio aVoiceSlow = new Audio("resources//Sound//voice_slow.wav");
	
	public CapsuleSlow(float x, float y) {
		this.initCapsule(CapsuleId.SLOW);
		transform.setPosition(x, y);
	}
	
	@Override
	public void onTriggerEnter(Collider other) {
		super.onTriggerEnter(other);
		
		if (vaus.isContaneComponent(other)) {
			aVoiceSlow.getSound().play();
			Ball.speed = 6.0f;
		}
	}
}
