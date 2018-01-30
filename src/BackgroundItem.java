import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;

public class BackgroundItem {

    private static final String _PATH = "./assets/stadium.png";
    private ArrayList<WorldItem> backgrounds;

    public BackgroundItem() {
        backgrounds = new ArrayList<WorldItem>();
        WorldItem backgroundOne = new WorldItem();
        WorldItem backgroundTwo = new WorldItem();
        this.setBackground(backgroundOne, true);
        this.setBackground(backgroundTwo, false);
    }

    public void setBackground(WorldItem background, boolean first) {
        background.setImage(_PATH);
        if (first) {
            background.setPosition(0, Constants.getScreenHeight() - background.getHeight());
        } else {
            background.setPosition(0, Constants.getScreenHeight() -(2 * background.getHeight()));
        }
        backgrounds.add(background);
    }

    public double getWidth() {
        return backgrounds.get(0).getWidth();
    }

    public double getHeight() {
        return backgrounds.get(0).getHeight();
    }

    public void loop() {
        if (backgrounds.get(0).getY() > Constants.getScreenHeight()) {
            backgrounds.get(0).setPosition(0, backgrounds.get(1).getY() - backgrounds.get(0).getHeight());
        }
        if (backgrounds.get(1).getY() > Constants.getScreenHeight()) {
            backgrounds.get(1).setPosition(0, backgrounds.get(0).getY() - backgrounds.get(1).getHeight());
        }
    }

    public void setBackgroundSpeed(GraphicsContext gc) {
        backgrounds.get(0).setSpeed(0, Constants.getStartingSpeed());
        backgrounds.get(0).updateSpeed();
        backgrounds.get(0).render(gc);
        backgrounds.get(1).setSpeed(0, Constants.getStartingSpeed());
        backgrounds.get(1).updateSpeed();
        backgrounds.get(1).render(gc);
    }
}
