package logic.views;

import java.io.File;
import java.awt.List;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Scanner;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import logic.configuration.Constants;
import logic.configuration.Paths;
import logic.controllers.ViewFactory;
import logic.controllers.ViewFactory.VIEW_TYPE;
import logic.models.ImageButton;

public class GameOverView extends StreakerView {
	
	private int coins;
	private String hms;
	private int finalScore;
	private ScoreEntry finalScoreEntry;
	private ArrayList<ScoreEntry> entries;
	private File highScoresFile;
	
	public GameOverView(ViewFactory vc) {
		super(vc);
	}
	
	public GameOverView(ViewFactory vc, String hms, int coins) {
		/*
		 * TODO: make the game over screen relatively pritteh with 
		 * text and stuff about how long player lasted and how many
		 * coins they got. maybe have a picture of the background
		 * up in there, who knows?
		 */
		super(vc);
		this.coins = coins;
		this.hms = hms;
		finalScore = calculateScore();
		finalScoreEntry = prepareCurrentScore();
		try {
			highScoresFile = new File(".highscores.dat");
		}
		catch (Exception e) {
			System.out.println("line 59: " + e.getMessage());
		}
		if (!highScoresFile.exists()) {
			try {
				System.out.println("high scores file did not exist, creating it now");
				highScoresFile.createNewFile();
			}
			catch (Exception e) {
				System.out.println("line 66: " + e.getMessage());
			}		
		}
		entries = getTopScores();
		saveHighScores();
	}
	
	public Scene setupScene() {
		AnchorPane root = new AnchorPane();
		BackgroundImage bgi = new BackgroundImage(new Image(Paths.GAME_OVER_PATH), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		root.setBackground(new Background(bgi));
		
		
		//text nodes for time lasted, coins collected, and total score
		Text time = new Text(225, 290, hms);
		time.setFont(new Font(20));
		
		Text collected = new Text(480, 290, "$" + Integer.toString(coins));
		collected.setFont(new Font(20));
		
		Text totScore = new Text(730, 290, Integer.toString(finalScore));
		totScore.setFont(new Font(20));
		
		root.getChildren().add(time);
		root.getChildren().add(collected);
		root.getChildren().add(totScore);
	
		//vbox for buttons
		VBox buttons = setupButtons();
		setButtonAnchors(buttons);
	
		root.getChildren().add(buttons);
		
		//vbox for scores
		VBox scores = setupHighScoresVBox(entries);
		setHighScoreAnchors(scores);
		
		root.getChildren().add(scores);
		
		return(new Scene(root, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT));
	}
	
	private VBox setupButtons() {
		VBox buttons = new VBox();
		buttons.setSpacing(30);
		
		ImageButton newGame = new ImageButton();
		newGame.updateImages(new Image(Paths.GAME_OVER_BUTTONS[0]), new Image(Paths.GAME_OVER_BUTTONS_PRESSED[0]));
		newGame.setOnAction(this::goToNewGame);
		buttons.getChildren().add(newGame);
		
		ImageButton mainMenu = new ImageButton();
		mainMenu.updateImages(new Image(Paths.GAME_OVER_BUTTONS[1]), new Image(Paths.GAME_OVER_BUTTONS_PRESSED[1]));
		mainMenu.setOnAction(this::goToMainMenu);
		buttons.getChildren().add(mainMenu);
		
		ImageButton exitGame = new ImageButton();
		exitGame.updateImages(new Image(Paths.GAME_OVER_BUTTONS[2]), new Image(Paths.GAME_OVER_BUTTONS_PRESSED[2]));
		exitGame.setOnAction(this::exitGame);
		buttons.getChildren().add(exitGame);
		
		return buttons;
	}
	
	private VBox setupHighScoresVBox(ArrayList<ScoreEntry> entries) {
		//upper-bounded loop index for on-the-fly creation of text nodes
		int bound = entries.size() < 5 ? entries.size() : 5;
		
		VBox scores = new VBox();
		scores.setSpacing(15);
		
		for (int i = 0; i < bound; i++) {
			Text temp = new Text(entries.get(i).getContent());
			temp.setFont(new Font(20));
			scores.getChildren().add(temp);
		}
		
		return scores;
	}
	
	private void setButtonAnchors(VBox buttons) {
		AnchorPane.setBottomAnchor(buttons, 25.0);
		AnchorPane.setTopAnchor(buttons, 345.0);
		AnchorPane.setLeftAnchor(buttons, 140.0);
		AnchorPane.setRightAnchor(buttons, 550.0);
	}
	
	//to be used once I actually add high scores lmao
	private void setHighScoreAnchors(VBox scores) {
		AnchorPane.setBottomAnchor(scores, 25.0);
		AnchorPane.setTopAnchor(scores, 425.0);
		AnchorPane.setLeftAnchor(scores, 575.0);
		AnchorPane.setRightAnchor(scores, 150.0);
	}
	
	private void goToMainMenu(ActionEvent click) {
		viewController.updateView(VIEW_TYPE.MAIN_MENU);
	}
	
	private void goToNewGame(ActionEvent click) {
		viewController.updateView(VIEW_TYPE.GAMEPLAY);
	}
	
	private void exitGame(ActionEvent click) {
		Platform.exit();
	}
	
	private int calculateScore() {
		int finalScore = 0;
		String[] partials = hms.split(":");
		
		finalScore += Integer.parseInt(partials[0]) * 1000;
		finalScore += Integer.parseInt(partials[1]) * 100;
		finalScore += Integer.parseInt(partials[2]);
		finalScore += coins * 3;
		
		return finalScore;
	}
	
	private ArrayList<ScoreEntry> getTopScores() {
		ArrayList<ScoreEntry> scores = new ArrayList<>();
		Scanner fr;
		String content;
		int val;
		
		try {
			fr = new Scanner(highScoresFile);
		}
		catch (Exception e) {
			//nope
			scores.add(new ScoreEntry(0, "Error Fetching High Scores..."));
			return scores;
		}
		
		while (fr.hasNextLine()) {
			content = fr.nextLine();
			val = Integer.parseInt(content.split("\\|")[0].trim());
			scores.add(new ScoreEntry(val, content));
		}
		scores.add(finalScoreEntry);

		Collections.sort(scores);
		fr.close();
		
		return scores;
	}
	
	private ScoreEntry prepareCurrentScore() {
		String[] date = new Date().toString().split(" ");
		String content = Integer.toString(finalScore) + " | " + date[1] + " " + date[2] + ", " + date[5];
		
		return new ScoreEntry(finalScore, content);
	}
	
	private void saveHighScores() {
		BufferedWriter writer;
		int bound = entries.size() < 5 ? entries.size() : 5;
		
		try {
			writer = new BufferedWriter(new FileWriter(highScoresFile));
			
			for(int i = 0; i < bound; i++) {
				writer.write(entries.get(i).getContent() + "\n");
			}
			
			writer.close();
		}
		catch (Exception e) {
			System.out.println("Exception Caught:" + e.getMessage());
		}
	}
	
	private class ScoreEntry implements Comparable<ScoreEntry> {
		private int value;
		private String content;
		
		public ScoreEntry(int v, String c) {
			value = v;
			content = c;
		}
		
		//inverts integer ordering so that Collections.sort returns list in descending order
		@Override
		public int compareTo(ScoreEntry sc) {
			return - ((Integer)this.value).compareTo((Integer)sc.value);
		}
	
		public String getContent() {
			return content;
		}
	}
}
