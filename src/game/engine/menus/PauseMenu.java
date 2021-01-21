package game.engine.menus;

import game.engine.GameApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class PauseMenu {
	public static GameApplication mainApp;
	
	@FXML private Button btnRetry;
	@FXML private Button btnEndGame;
	
	@FXML
	private void onReturnButtonClick() {
		mainApp.switchToResumeGame();
	}
	
	@FXML
	private void onEndGameButtonClick() {
		GameApplication.gLoop.endGame();
		mainApp.switchToMainMenu();
	}
}
