import javafx.scene.image.Image;

public class Guard extends AnimatedImage {

    public Guard() {
        Image[] imageArray = new Image[4];
        imageArray[0] = new Image(Paths.GUARD_PATHS[0]);
        imageArray[1] = new Image(Paths.GUARD_PATHS[1]);
        imageArray[2] = new Image(Paths.GUARD_PATHS[2]);
        imageArray[3] = new Image(Paths.GUARD_PATHS[3]);
        this.setFrame(imageArray);
        this.duration = Constants.FRAME_DURATION;
        width = imageArray[0].getWidth();
        height = imageArray[0].getHeight();
    }
}
