package logic.views;

import javafx.scene.paint.Color;
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
import javafx.scene.image.ImageView;

public class HelpView extends StreakerView {


    public HelpView(ViewFactory vc) {
        super(vc);
    }

    public Scene setupScene() {

        AnchorPane root = new AnchorPane();
        BackgroundImage bgi = new BackgroundImage(new Image(Paths.HELP_BACKGROUND_PATH), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        root.setBackground(new Background(bgi));

        VBox buttons = setupButtons();
        setButtonAnchors(buttons);

        Text q0 = new Text(410, 250, getQuestion(0));
        q0.setFont(new Font(20));
        q0.setFill(Color.RED);

        Text a0 = new Text(210, 280, getAnswer(0));
        a0.setFont(new Font(20));

        Text q1 = new Text(370, 350, getQuestion(1));
        q1.setFont(new Font(20));
        q1.setFill(Color.RED);

        Text a1 = new Text(210, 380, getAnswer(1));
        a1.setFont(new Font(20));

        Image arrowKeys = new Image(Paths.HELP_KEYS_PATH);
        ImageView av = new ImageView();
        av.setImage(arrowKeys);
        av.setX(240);
        av.setY(400);
        av.setFitWidth(500);
        av.setFitHeight(300);
        av.setSmooth(true);
        av.setCache(true);

        root.getChildren().add(av);
        root.getChildren().add(q0);
        root.getChildren().add(a0);
        root.getChildren().add(q1);
        root.getChildren().add(a1);

        root.getChildren().add(buttons);
        return new Scene(root, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

    }

    private VBox setupButtons() {
        VBox buttons = new VBox();
        buttons.setSpacing(30);

        ImageButton backButton = new ImageButton();
        backButton.updateImages(new Image(Paths.getHelpButtons()[0]), new Image(Paths.getHelpButtons()[0]));
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

    public String getQuestion(int i) {
        switch (i) {

            case 0:
                return "What is Streaker?";

            case 1:
                return "How do you play Streaker?";

            default:
                return null;

        }

    }

    public String getAnswer(int i) {
        switch (i) {

            case 0:
                return "The best game you've never played and probably never will.";

            case 1:
                return "Use the arrow keys as displayed below to evade the guards.\n " +
                        "Collect ButtCoin and try and stay alive as long as you can,\n" +
                        "         Your score will based on the above two factors.\n" +
                        "                                       Have fun :)";

            default:
                return null;

        }

    }


}
