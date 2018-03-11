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

public class HelpView extends StreakerView {


    public HelpView(ViewController vc) {
        super(vc);
    }

    public Scene setupScene() {

        AnchorPane root = new AnchorPane();
        BackgroundImage bgi = new BackgroundImage(new Image(Paths.HELP_BACKGROUND_PATH), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        root.setBackground(new Background(bgi));

        VBox buttons = setupButtons();
        setButtonAnchors(buttons);

        Text q0 = new Text(410, 290, getQuestion(0));
        q0.setFont(new Font(20));

        Text a0 = new Text(210, 320, getAnswer(0));
        a0.setFont(new Font(20));

        root.getChildren().add(q0);
        root.getChildren().add(a0);

        root.getChildren().add(buttons);
        return new Scene(root, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

    }

    private VBox setupButtons() {
        VBox buttons = new VBox();
        buttons.setSpacing(30);

        ImageButton backButton = new ImageButton();
        backButton.updateImages(new Image(Paths.HELP_BUTTONS[0]), new Image(Paths.HELP_BUTTONS[0]));
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
        viewController.updateView(VIEW_TYPE.MAIN_MENU);
    }

    public String getQuestion(int i) {
        switch (i) {

            case 0:
                return "What is Streaker?";

            default:
                return null;

        }

    }

    public String getAnswer(int i) {
        switch (i) {

            case 0:
                return "The best game you've never played and probably never will.";

            default:
                return null;

        }

    }


}
