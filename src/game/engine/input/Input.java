package game.engine.input;

import javafx.scene.Group;

public class Input {
	
	public static void startOnKeyEvent(Group root) {
		root.requestFocus();
		KeyInput.startOnKeyUpEvent(root);
		KeyInput.startOnKeyPressedEvent(root);
	}
	
	public static void startOnMouseEvent(Group root) {
		MouseInput.startOnMouseMovedEvent(root);
	}
	
	public static boolean getKeyUp(String keyCode) {
		return KeyInput.isKeyUp(
				keyCode.substring(0, 1).toUpperCase() + 
				keyCode.substring(1).toLowerCase()
		);
	}
	
	public static boolean getKeyPress(String keyCode) {
		return KeyInput.isKeyPress(
				keyCode.substring(0, 1).toUpperCase() + 
				keyCode.substring(1).toLowerCase()
		);
	}
	
	public static float getMouseXPosition() {
		return (float)MouseInput.getX();
	}
	
	public static float getMouseYPosition() {
		return (float)MouseInput.getY();
	}
	
	public static void clear() {
		KeyInput.clear();
	}
}
