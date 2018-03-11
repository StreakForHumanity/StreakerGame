package logic.models;

import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;

public class AnimatedItem extends WorldItem {

    private Image[] frames;
    private double duration;

    public Image getFrame(double time) {
        int index = (int) ((time % (frames.length * duration)) / duration);
        return frames[index];
    }

    public void setFrame(Image[] i) {
        frames = i;
        width = i[0].getWidth();
        height = i[0].getHeight();
    }

    public void render(GraphicsContext gc, double time) {
        gc.drawImage(getFrame(time), getX(), getY());
    }

    public void setFrames(Image[] frames) {
        this.frames = frames;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }
}
