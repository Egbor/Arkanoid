package game.engine.scene;

import java.util.ArrayList;

import game.engine.GameApplication;
import game.engine.object.GameObject;
import game.engine.object.component.Collider;
import javafx.scene.Group;

public class GameScene {
	
	public static GameApplication mainApp;
	private Group scRoot;
	private ArrayList<GameObject> activeObjectList;
	
	public GameScene() {
		scRoot = new Group();
		activeObjectList = new ArrayList<GameObject>();
	}
	
	public void beginStartGameObjectEvent() {
		for (GameObject obj : activeObjectList) {
			obj.start();
		}
	}
	
	public void beginUpdateGameObjectEvent() {
		try {
			for (GameObject obj : activeObjectList) {
				obj.update();
			}
		} catch(Exception e) {
			System.out.print("Reseted");
			return;
		}
	}
	
	public void beginStopGameObjectEvent() {
		for (GameObject obj : activeObjectList) {
			obj.stop();
		}
	}
	
	public void updatePhysicalObject() {
		for (GameObject obj : activeObjectList) {
			Collider gameObjectCollider = (Collider)obj.getComponent(Collider.class);
			if (gameObjectCollider != null) {
				gameObjectCollider.updateShapePosition();
			}
		}
	}
	
	public void beginOnTriggerGameObjectEvent() {
		updatePhysicalObject();
		GameObject[] gameObjectArray = activeObjectList.toArray(new GameObject[0]);
		for (int i = 0; i < (gameObjectArray.length - 1); i++) {
			Collider gameObjectColliderA = (Collider)gameObjectArray[i].getComponent(Collider.class);
			if (gameObjectColliderA != null) {
				for (int j = (i + 1); j < gameObjectArray.length; j++) {
					Collider gameObjectColliderB =  (Collider)gameObjectArray[j].getComponent(Collider.class);
					if (gameObjectColliderB != null) {
						Collider.checkCollisionTwoObjects(gameObjectColliderA, gameObjectColliderB);
					}
				}
			}
		}
	}
	
	public void createScene(ArrayList<GameObject> gameObjectList) {
		for (GameObject obj : gameObjectList) {
			addObject(obj);
		}
	}
	
	public void addObject(GameObject object) {
		object.bindGameScene(this);
		activeObjectList.add(object);
	}
	
	public void deleteObject(GameObject object) {
		activeObjectList.remove(object);
	}
	
	public void deleteAllObject() {
		activeObjectList.clear();
	};
	
	public void clear() {
		scRoot.getChildren().clear();
	}
	
	public Group getRoot() {
		return scRoot;
	}
	
	public boolean hasObject(Class<?> objClass) {
		for (GameObject o : activeObjectList) {
			if (objClass.isInstance(o)) {
				return true;
			}
		}
		return false;
	}
	
	public int getObjectCount() {
		return activeObjectList.size();
	}
}
