package game.engine;

import game.engine.menus.AboutMenu;
import game.engine.menus.GameOverMenu;
import game.engine.menus.MainMenu;
import game.engine.menus.PauseMenu;
import game.engine.menus.YouWinMenu;
import game.engine.scene.GameScene;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GameApplication extends Application {
	
	private static Stage primaryStage;
	
	public static Scene scMainMenu;
	public static Scene scAboutMenu;
	public static Scene scPauseMenu;
	public static Scene scGameOverMenu;
	public static GameLoop gLoop;

	protected static String title = "Arkanoid";
	protected static String pthMainMenu;
	protected static String pthPauseMenu;
	protected static String pthAboutMenu;
	protected static String pthYouWinMenu;
	protected static String pthGameOverMenu;
	
	public GameApplication() {
		MainMenu.mainApp = this;
		AboutMenu.mainApp= this;
		PauseMenu.mainApp = this;
		GameOverMenu.mainApp = this;
		YouWinMenu.mainApp = this;
		GameScene.mainApp = this;
	}
	
	private void initPrimaryStage(Stage primaryStage) {
		GameApplication.primaryStage = primaryStage;
		GameApplication.primaryStage.setTitle(title);
		GameApplication.primaryStage.setResizable(false);
		GameApplication.primaryStage.sizeToScene();
		GameApplication.primaryStage.setScene(new Scene(new Pane()));
	}
	
	public void switchToMainMenu() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(pthMainMenu));
			Parent root = loader.load();
			primaryStage.getScene().setRoot(root);
			primaryStage.show();
		} catch (Exception e) {
			System.out.println("An error occurred while loading the application");
			e.printStackTrace();
		}
	}
	
	public void switchToAboutMenu() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(pthAboutMenu));
			Parent root = loader.load();
			primaryStage.getScene().setRoot(root);
			primaryStage.show();
		} catch (Exception e) {
			System.out.println("An error occurred while loading the application");
			e.printStackTrace();
		}
	}
	
	public void switchToPauseMenu() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(pthPauseMenu));
			Parent root = loader.load();
			primaryStage.getScene().setRoot(root);
			primaryStage.show();
		} catch (Exception e) {
			System.out.println("An error occurred while loading the application");
			e.printStackTrace();
		}
	}
	
	public void switchToGameOverMenu() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(pthGameOverMenu));
			Parent root = loader.load();
			primaryStage.getScene().setRoot(root);
			primaryStage.show();
		} catch (Exception e) {
			System.out.println("An error occurred while loading the application");
			e.printStackTrace();
		}
	}
	
	public void switchToYouWinMenu() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(pthYouWinMenu));
			Parent root = loader.load();
			primaryStage.getScene().setRoot(root);
			primaryStage.show();
		} catch (Exception e) {
			System.out.println("An error occurred while loading the application");
			e.printStackTrace();
		}
	}
	
	public void switchToGame() {
		primaryStage.getScene().setRoot(gLoop.getRootScene());
		primaryStage.show();
		gLoop.startGame();
	}
	
	public void switchToResumeGame() {
		primaryStage.getScene().setRoot(gLoop.getRootScene());
		primaryStage.show();
		gLoop.start();
	}
	
	public void close() {
		primaryStage.close();
	}
	
	@Override
	public void start(Stage primaryStage) {
		initPrimaryStage(primaryStage);
		switchToMainMenu();
	}
}
