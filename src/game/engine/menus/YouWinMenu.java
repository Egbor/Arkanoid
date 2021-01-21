package game.engine.menus;

import game.engine.GameApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class YouWinMenu {
	public static GameApplication mainApp;
	
	@FXML private Button btnEndGame;
	
	@FXML
	private void onEndGameButtonClick() {
		GameApplication.gLoop.endGame();
		mainApp.switchToMainMenu();
	}
}
