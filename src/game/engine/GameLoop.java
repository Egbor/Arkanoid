package game.engine;

import java.util.ArrayList;

import game.engine.input.Input;
import game.engine.object.GameObject;
import game.engine.scene.GameScene;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;

public class GameLoop extends AnimationTimer {

	private GameScene scene;
	private ArrayList<GameObject> gObjectList;
	
	public void setScene(ArrayList<GameObject> map) {
		this.scene = new GameScene();
		this.gObjectList = map;
	}
	
	@Override
	public void start() {
		super.start();
		Input.startOnKeyEvent(scene.getRoot());
		Input.startOnMouseEvent(scene.getRoot());
	}
	
	@Override
	public void handle(long now) {
		Time.begin();
		
		if (Input.getKeyPress("Esc")) {
			this.puaseGame();
		}
		
		//Input.clear();				// Clear all inputed keys on this frame
		scene.clear();					// Clear frame to redraw
		scene.beginUpdateGameObjectEvent();
		scene.beginOnTriggerGameObjectEvent();
		
		Time.end();
	}
	
	@Override
	public void stop() {
		super.stop();
		Input.clear();
		this.scene.beginStopGameObjectEvent();
	}
	
	public void startGame() {
		this.scene.createScene(gObjectList);
		this.scene.beginStartGameObjectEvent();
		this.start();
	}
	
	public void resetGame() {
		this.stop();
		this.scene.deleteAllObject();
		this.scene.createScene(gObjectList);
		this.start();
	}
	
	public void overGame() {
		this.stop();
		GameScene.mainApp.switchToGameOverMenu();
	}
	
	public void winGame() {
		this.stop();
		GameScene.mainApp.switchToYouWinMenu();
	}
	
	public void puaseGame() {
		this.stop();
		GameScene.mainApp.switchToPauseMenu();
	}
	
	public void endGame() {
		this.stop();
		this.scene.deleteAllObject();
	}
	
	public Group getRootScene() {
		return scene.getRoot();
	}
}
