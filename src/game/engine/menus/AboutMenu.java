package game.engine.menus;

import game.engine.GameApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class AboutMenu {
	
	public static GameApplication mainApp;
	
	@FXML private Button btnStart;
	
	@FXML
	private void onBackButtonClick() {
		mainApp.switchToMainMenu();
	}
}
