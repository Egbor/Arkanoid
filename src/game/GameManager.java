package game;

import game.capsules.Capsule;
import game.engine.GameApplication;
import game.engine.object.GameObject;
import game.vaus.Vaus;

public class GameManager extends GameObject {
	
	public Vaus vaus;
	public Ball ball;
	public static int target = 88;
	
	public GameManager() {
		vaus = new Vaus();
		ball = new Ball();
	}
	
	@Override
	public void start() {
		target = 88;
		
		vaus.ball = ball;
		ball.vaus = vaus;
		
		Capsule.ball = ball;
		Capsule.vaus = vaus;
	}
	
	@Override
	public void update() {
		super.update();
		
		if (!this.getGameScene().hasObject(Ball.class)) {
			GameApplication.gLoop.overGame();
		}
		if (target == 0) {
			GameApplication.gLoop.winGame();
		}
	}
	
}
