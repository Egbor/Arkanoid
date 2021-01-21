package game.engine.menus;

import game.engine.GameApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainMenu {
	
	public static GameApplication mainApp;
	
	@FXML private Button btnStart;
	@FXML private Button btnAbout;
	@FXML private Button btnQuit;
	
	@FXML
	private void onStartButtonClick() {
		mainApp.switchToGame();
	}
	
	@FXML
	private void onAboutButtonClick() {
		mainApp.switchToAboutMenu();
	}
	
	@FXML
	private void onQuitButtonClick() {
		mainApp.close();
	}
}
