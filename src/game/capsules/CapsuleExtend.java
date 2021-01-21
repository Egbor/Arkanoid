package game.capsules;

import game.capsules.Capsule;
import game.engine.object.component.Audio;
import game.engine.object.component.Collider;

public class CapsuleExtend extends Capsule {
	
	private static final Audio aVoiceExtend = new Audio("resources//Sound//voice_extend.wav");
	
	public CapsuleExtend(float x, float y) {
		this.initCapsule(CapsuleId.EXTEND);
		transform.setPosition(x, y);
	}
	
	@Override
	public void onTriggerEnter(Collider other) {
		super.onTriggerEnter(other);
		
		if (vaus.isContaneComponent(other)) {
			aVoiceExtend.getSound().play();
			vaus.extend();
		}
	}
}
