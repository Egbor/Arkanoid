package game;

import game.engine.sprite.SpriteSheet;

import java.util.ArrayList;

import game.engine.GameApplication;
import game.engine.GameLoop;
import game.engine.math.Point;
import game.engine.object.GameObject;

public class Arkanoid extends GameApplication {
	
	private static final ArrayList<GameObject> gObjectList = new ArrayList<GameObject>();
	
	public static void initBoards() {
		Point[] verticesForSide = {new Point(-11.5f, 363.5f), new Point(11.5f, 363.5f),
								   new Point(11.5f, -363.5f), new Point(-11.5f, -363.5f)};
		Point[] verticesForTop = {new Point(-253.0f, 11.5f), new Point(253.0f, 11.5f),
								  new Point (253.0f, -11.5f), new Point(-253.0f, -11.5f)};
		Board topBoard = new Board();
		Board leftBoard = new Board();
		Board rightBoard = new Board();
		topBoard.position = new Point(274.0f, 8.0f);
		leftBoard.position = new Point(11.5f, 384.5f);
		rightBoard.position = new Point(534.5f, 384.5f);
		topBoard.vertices = verticesForTop;
		leftBoard.vertices = verticesForSide;
		rightBoard.vertices = verticesForSide;
		
		gObjectList.add(topBoard);
		gObjectList.add(leftBoard);
		gObjectList.add(rightBoard);
	}
	
	public static void initBricks() {
		SpriteSheet spriteSheet = new SpriteSheet(57, 35, 1, 0, "resources//HDSprites//General.png", 0, 0);
		SpriteSheet ssHalfBrick1 = new SpriteSheet(44, 22, 1, 0, "resources//HDSprites//brick.png", 0, 0);
		SpriteSheet ssHalfBrick2 = new SpriteSheet(44, 22, 1, 0, "resources//HDSprites//brick.png", 44, 0);
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 11; j++) {
				Brick brick = new Brick();
				brick.sprites = spriteSheet;
				brick.transform.setPosition(55.5f + 45.0f * j, 120.0f + 23.0f * i);
		
				gObjectList.add(brick);
			}
		}
		HalfBrick brick1 = new HalfBrick(0);
		HalfBrick brick2 = new HalfBrick(1);
		brick1.sprites = ssHalfBrick1;
		brick2.sprites = ssHalfBrick2;
		brick1.transform.setPosition(49.5f + 45.0f * 0, 114.0f + 23.0f * 8);
		brick2.transform.setPosition(49.5f + 45.0f * 0, 114.0f + 23.0f * 8);
		brick2.transform.setPosition(49.5f + 45.0f * 10, 114.0f + 23.0f * 8);
		gObjectList.add(brick1);
		gObjectList.add(brick2);
	}
	
	public static void initPlayableObject() {
		GameManager gManager = new GameManager();
		
		gObjectList.add(gManager);
		gObjectList.add(gManager.ball);
		gObjectList.add(gManager.vaus);
	}
	
	public static void initScene() {
		gObjectList.add(new Background());
		gObjectList.add(new Abyss());
		initBoards();
		initBricks();
		initPlayableObject();
	}
	
	public static void main(String[] args) {
		pthMainMenu = "../resources/menus/main_menu.fxml";
		pthAboutMenu = "../resources/menus/about_menu.fxml";
		pthPauseMenu = "../resources/menus/pause_menu.fxml";
		pthYouWinMenu = "../resources/menus/you_win_menu.fxml";
		pthGameOverMenu = "../resources/menus/game_over_menu.fxml";
		initScene();
		gLoop = new GameLoop();
		gLoop.setScene(gObjectList);
		launch(args);
	}

}
