package game.engine.object.component;

import game.engine.object.GameObject;

public class Component {
	
	protected GameObject rootGameObject;
	
	public void bindGameObject(GameObject obj) {
		rootGameObject = obj;
	}
	
	public GameObject getOwner() {
		return rootGameObject;
	}
	
	public void start() {}
	public void update() {}
	public void stop() {}
}
