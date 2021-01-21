package game.engine.input;

import java.awt.AWTException;
import java.awt.Robot;

import game.engine.GameApplication;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;

public class MouseInput {
	
	private static double xRelativeScene;
	private static double yRelativeScene;
	
	public static void startOnMouseMovedEvent(Group root) {
		root.setOnMouseMoved(new EventHandler<MouseEvent> () {
			@Override
			public void handle(MouseEvent e) {
				xRelativeScene = e.getSceneX();
				yRelativeScene = e.getSceneY();
			}
		});
	}
	
//	public static void setMousePosition(int x, int y) {
//		try {
//			Robot robot = new Robot();
//			robot.mouseMove(x + GameApplication.getScreenX() + 3, y + GameApplication.getScreenY() + 3);
//		} catch (AWTException e) {
//			e.printStackTrace();
//		}
//	}
	
	public static double getX() {
		return xRelativeScene;
	}
	
	public static double getY() {
		return yRelativeScene;
	}
}
