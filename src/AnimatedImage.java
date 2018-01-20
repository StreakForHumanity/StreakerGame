import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;
import javafx.geometry.Rectangle2D;

public class AnimatedImage extends Character {
    public Image[] frames;
    public double duration;
    private double width;
    private double height;
    private double positionX;
    private double positionY;

    public Image getFrame(double time) {
        int index = (int)((time % (frames.length * duration)) / duration);
        return frames[index];
    }
    public void setFrame(Image[] i) {
        frames = i;
        width = i[0].getWidth();
        height = i[0].getHeight();
    }
    public void render(GraphicsContext gc, double time) {
        gc.drawImage(getFrame(time), positionX, positionY);
    }
}
