package game.engine.input;

import java.util.HashMap;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class KeyInput {
	
	private static HashMap<String, KeyCode> keyPress = new HashMap<String, KeyCode>();
	private static HashMap<String, KeyCode> keyUp = new HashMap<String, KeyCode>();
	
	public static void startOnKeyUpEvent(Group root) {
		root.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {
				KeyCode key = e.getCode();
				keyUp.put(key.getName(), key);
				keyPress.remove(key.getName());
				//System.out.println(keyPress.size());
			}
		});
	}
	
	public static void startOnKeyPressedEvent(Group root) {
		root.setOnKeyPressed(new EventHandler<KeyEvent> () {
			@Override
			public void handle(KeyEvent e) {
				KeyCode key = e.getCode();
				keyPress.put(key.getName(), key);
			}
		});
	}
	
	public static boolean isKeyUp(String keyCode) {
		return keyUp.containsKey(keyCode);
	}
	
	public static boolean isKeyPress(String keyCode) {
		return keyPress.containsKey(keyCode);
	}
	
	public static void clear() {
		keyUp.clear();
		keyPress.clear();
	}
}
