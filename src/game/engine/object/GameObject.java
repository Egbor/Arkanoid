package game.engine.object;

import java.util.ArrayList;

import game.engine.object.component.Collider;
import game.engine.object.component.Component;
import game.engine.object.component.Transform;
import game.engine.scene.GameScene;
import game.engine.sprite.Sprite;

public class GameObject {
	
	private ArrayList<Component> actionsComponentList;
	private GameScene roleplayingScene;
	private Sprite appearance;
	
	public Transform transform;
	
	public GameObject() {
		actionsComponentList = new ArrayList<Component>();
		transform = new Transform();
	}
	
	public void destroy() {
		roleplayingScene.deleteObject(this);
	}
	
	public void bindGameScene(GameScene scene) {
		roleplayingScene = scene;
	}
	
	public void addComponent(Component component) {
		component.bindGameObject(this);
		actionsComponentList.add(component);
	}
	
	public void removeComponent(Component component) {
		actionsComponentList.remove(component);
	}
	
	public void setAppearance(Sprite sprite) {
		appearance = sprite;
	}
	
	public Sprite getAppearance() {
		return appearance;
	}
	
	public GameScene getGameScene() {
		return roleplayingScene;
	}
	
	public Component getComponent(Class<?> componentClass) {
		for (Component com : actionsComponentList) {
			if (componentClass.isInstance(com)) {
				return com;
			}
		}
		return null;
	}
	
	public boolean isContaneComponent(Component component) {
		for (Component com : actionsComponentList) {
			if (com == component) {
				return true;
			}
		}
		return false;
	}
	
	public void start() {
		for (Component component : actionsComponentList) {
			component.start();
		}
	}
	
	public void update() {
		for (Component component : actionsComponentList) {
			component.update();
		}
	}
	
	public void stop() {
		for (Component component : actionsComponentList) {
			component.stop();
		}
	}
	
	public void onTriggerEnter(Collider other) {}
}
