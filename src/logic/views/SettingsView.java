package logic.views;

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
import logic.controllers.ViewController;
import logic.controllers.ViewController.VIEW_TYPE;
import logic.models.ImageButton;

public class SettingsView extends StreakerView {


    public SettingsView(ViewController vc) {
        super(vc);
    }

    public Scene setupScene() {

        AnchorPane root = new AnchorPane();
        BackgroundImage bgi = new BackgroundImage(new Image(Paths.SETTINGS_BACKGROUND_PATH), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        root.setBackground(new Background(bgi));

        VBox buttons = setupButtons();
        setButtonAnchors(buttons);

        root.getChildren().add(buttons);
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
