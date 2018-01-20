import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;

public class BackgroundItem {
    private static final String _PATH = "../assets/images/background.png";

    private ArrayList<Character> backgrounds;
    private int SCREEN_HEIGHT;
    private int MOVING_SPEED;

    public BackgroundItem(int SCREEN_HEIGHT, int MOVING_SPEED) {
        this.SCREEN_HEIGHT = SCREEN_HEIGHT;
        this.MOVING_SPEED = MOVING_SPEED;
        backgrounds = new ArrayList<Character>();
        Character backgroundOne = new Character();
        Character backgroundTwo = new Character();
        this.setBackground(backgroundOne, true);
        this.setBackground(backgroundTwo, false);
    }
    public void setBackground(Character background, boolean first) {
        background.setImage(_PATH);
        if (first) {
            background.setPosition(0, SCREEN_HEIGHT - background.getHeight());
        } else {
            background.setPosition(0, SCREEN_HEIGHT -(2 * background.getHeight()));
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
        if (backgrounds.get(0).getY() > SCREEN_HEIGHT) {
            backgrounds.get(0).setPosition(0, backgrounds.get(1).getY() - backgrounds.get(0).getHeight());
        }
        if (backgrounds.get(1).getY() > SCREEN_HEIGHT) {
            backgrounds.get(1).setPosition(0, backgrounds.get(0).getY() - backgrounds.get(1).getHeight());
        }
    }
    public void setBackgroundSpeed(GraphicsContext gc) {
        backgrounds.get(0).setSpeed(0, MOVING_SPEED);
        backgrounds.get(0).updateSpeed();
        backgrounds.get(0).render(gc);
        backgrounds.get(1).setSpeed(0, MOVING_SPEED);
        backgrounds.get(1).updateSpeed();
        backgrounds.get(1).render(gc);
    }
}
