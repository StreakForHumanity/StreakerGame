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
import logic.controllers.ViewController;
import logic.controllers.ViewController.VIEW_TYPE;
import logic.models.ImageButton;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;

public class SettingsView extends StreakerView {

    final Slider difficulty = new Slider (0.5, 2, 1);
    final Label difficultyCaption = new Label("Difficulty Level:");
    final static Color textColor = Color.BLACK;
    final Label difficultyValue = new Label(
            Double.toString(difficulty.getValue()));

    public SettingsView(ViewController vc) {
        super(vc);
    }

    public Scene setupScene() {

        AnchorPane root = new AnchorPane();
        BackgroundImage bgi = new BackgroundImage(new Image(Paths.SETTINGS_BACKGROUND_PATH), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        root.setBackground(new Background(bgi));

        VBox buttons = setupButtons();
        setButtonAnchors(buttons);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(200, 300, 200, 300));
        grid.setVgap(200);
        grid.setHgap(50);

        difficulty.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                difficultyValue.setText(String.format("%.2f", new_val));
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

        root.getChildren().add(grid);
        root.getChildren().add(buttons);

        // THIS IS NOT UPDATING SRY
        Globals.SETTINGS_MULTIPLIER = difficulty.getValue();

        return new Scene(root, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

    }

    private VBox setupButtons() {
        VBox buttons = new VBox();
        buttons.setSpacing(30);

        ImageButton BackButton = new ImageButton();
        BackButton.updateImages(new Image(Paths.SETTINGS_BUTTONS[0]), new Image(Paths.SETTINGS_BUTTONS[0]));
        BackButton.setOnAction(this::goBack);
        buttons.getChildren().add(BackButton);

        return buttons;
    }

    private void setButtonAnchors(VBox buttons) {
        AnchorPane.setBottomAnchor(buttons, 25.0);
        AnchorPane.setTopAnchor(buttons, 20.0);
        AnchorPane.setLeftAnchor(buttons, 140.0);
        AnchorPane.setRightAnchor(buttons, 550.0);
    }

    public void goBack(ActionEvent click) {
        viewController.updateView(VIEW_TYPE.MAIN_MENU);
    }


}
