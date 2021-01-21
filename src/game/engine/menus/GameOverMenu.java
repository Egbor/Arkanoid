package game.engine.menus;

import game.engine.GameApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class GameOverMenu {
	public static GameApplication mainApp;
	
	@FXML private Button btnRetry;
	@FXML private Button btnEndGame;
	
	@FXML
	private void onRetryButtonClick() {
		GameApplication.gLoop.endGame();
		mainApp.switchToGame();
	}
	
	@FXML
	private void onEndGameButtonClick() {
		GameApplication.gLoop.endGame();
		mainApp.switchToMainMenu();
	}
}
