import javafx.scene.image.Image;

public class Streaker extends AnimatedImage {
    private static final String[] _PATHS = {
            "src/assets/guyForward.png",
            "src/assets/guyLeft.png",
            "src/assets/guyForward.png",
            "src/assets/guyRight.png"
    };

    private double FRAME_DURATION;
    private int SCREEN_HEIGHT;

    public Streaker(double FRAME_DURATION, int SCREEN_HEIGHT, BackgroundItem background) {
        this.FRAME_DURATION = FRAME_DURATION;
        this.SCREEN_HEIGHT = SCREEN_HEIGHT;
        Image[] imageArray = new Image[4];
        imageArray[0] = new Image(_PATHS[0]);
        imageArray[1] = new Image(_PATHS[1]);
        imageArray[2] = new Image(_PATHS[2]);
        imageArray[3] = new Image(_PATHS[3]);
        this.setFrame(imageArray);
        this.duration = FRAME_DURATION;
        this.setPosition((background.getWidth() / 2) - 40, SCREEN_HEIGHT / 2);
    }
}
