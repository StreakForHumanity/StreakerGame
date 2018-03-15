package logic.views;

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
import logic.configuration.Constants;
import logic.configuration.Globals;
import logic.configuration.Paths;
import logic.controllers.ViewFactory;
import logic.controllers.ViewFactory.VIEW_TYPE;
import logic.models.ImageButton;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class SettingsView extends StreakerView {
    
    private final Slider difficulty = new Slider (0, 10, 5);
    private final Label difficultyCaption = new Label("Difficulty Level:");
    private static final Color textColor = Color.BLACK;
    private final Label difficultyValue = new Label("Medium");
    private AnchorPane root = new AnchorPane();
    private GridPane grid = new GridPane();
    
    public SettingsView(ViewFactory vc) {
        super(vc);
    }
    
    public Scene setupScene() {
        
        BackgroundImage bgi = new BackgroundImage(new Image(Paths.SETTINGS_BACKGROUND_PATH), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        root.setBackground(new Background(bgi));
        
        VBox buttons = setupButtons();
        setButtonAnchors(buttons);
        
        grid.setPadding(new Insets(200, 300, 200, 300));
        grid.setVgap(200);
        grid.setHgap(50);
        
        difficulty.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number oldVal, Number newVal) {
            	String diffText;
            	double n = newVal.doubleValue();
            	if (n < 2.0) {
            		diffText = "Beginner";
            	}
            	else if (n < 4.0) {
            		diffText = "Easy";
            	}
            	else if (n < 6.0) {
            		diffText = "Medium";
            	}
            	else if (n < 8.0) {
            		diffText = "Hard";
            	}
            	else {
            		diffText = "Expert";
            	}
            	
                difficultyValue.setText(diffText);
            }
        });
        
        difficultyCaption.setTextFill(textColor);
        difficultyCaption.setFont(new Font(20));
        GridPane.setConstraints(difficultyCaption, 0, 1);
        grid.getChildren().add(difficultyCaption);
        
        difficultyValue.setTextFill(textColor);
        difficultyValue.setFont(new Font(20));
        GridPane.setConstraints(difficultyValue, 2, 1);
        grid.getChildren().add(difficultyValue);
        
        GridPane.setConstraints(difficulty, 1, 1);
        grid.getChildren().add(difficulty);
        
        Button submit = new Button("Submit");
        GridPane.setConstraints(submit, 2, 2);
        submit.setOnAction(this::submit);
        
        grid.getChildren().add(submit);
        root.getChildren().add(grid);
        root.getChildren().add(buttons);
        
        return new Scene(root, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        
    }
    
    private VBox setupButtons() {
        VBox buttons = new VBox();
        buttons.setSpacing(30);
        
        ImageButton backButton = new ImageButton();
        backButton.updateImages(new Image(Paths.getSettingsButtons()[0]), new Image(Paths.getSettingsButtons()[0]));
        backButton.setOnAction(this::goBack);
        buttons.getChildren().add(backButton);
        
        return buttons;
    }
    
    private void setButtonAnchors(VBox buttons) {
        AnchorPane.setBottomAnchor(buttons, 25.0);
        AnchorPane.setTopAnchor(buttons, 20.0);
        AnchorPane.setLeftAnchor(buttons, 140.0);
        AnchorPane.setRightAnchor(buttons, 550.0);
    }
    
    public void goBack(ActionEvent click) {
    	if (click == null) {
			return;
		}
        viewController.updateView(VIEW_TYPE.MAIN_MENU);
    }
    public void submit(ActionEvent click) {
    	if (click == null) {
			return;
		}
        Text a1 = new Text(650, 600, "Difficulty set!");
        a1.setFont(new Font(20));
        a1.setFill(Color.RED);
        
        root.getChildren().add(a1);
        Globals.setTunnelsModifier(difficulty.getValue());
    }
}

