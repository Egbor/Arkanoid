package game.capsules;

import game.engine.object.component.Audio;
import game.engine.object.component.Collider;

public class CapsuleReduce extends Capsule {
	
	private static final Audio aVoiceReduce = new Audio("resources//Sound//voice_reduce.wav");
	
	public CapsuleReduce(float x, float y) {
		this.initCapsule(CapsuleId.REDUCE);
		transform.setPosition(x, y);
	}
	
	@Override
	public void onTriggerEnter(Collider other) {
		super.onTriggerEnter(other);
		
		if (vaus.isContaneComponent(other)) {
			aVoiceReduce.getSound().play();
			vaus.reduce();
		}
	}
}
