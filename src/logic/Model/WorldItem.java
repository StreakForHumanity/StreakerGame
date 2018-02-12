package logic.Model;

import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;
import javafx.geometry.Rectangle2D;

public class WorldItem {
    private Image image;
    protected double positionX;
    protected double positionY;
    protected double width;
    protected double height;

    private double velocityX;
    private double velocityY;
    private double speedX;
    private double speedY;

    public WorldItem() {}

    public void setImage(Image i) {
        image = i;
        width = i.getWidth();
        height = i.getHeight();
    }

    public void setImage(String filename) {
        Image i = new Image(filename);
        setImage(i);
    }

    public void setPosition(double x, double y) {
        positionX = x;
        positionY = y;
    }

    public void updatePosition(double x, double y) {
        positionX += x;
        positionY += y;
    }

    public double getX() {
        return positionX;
    }

    public double getY() {
        return positionY;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(image, positionX, positionY);
    }

    public Rectangle2D getBoundary() {
        return new Rectangle2D(positionX, positionY, width, height);
    }

    public boolean intersects(Rectangle2D s) {
        return s.intersects(this.getBoundary());
    }

    public void setVelocity(double x, double y) {
        velocityX = x;
        velocityY = y;
    }

    public void addVelocity(double x, double y) {
        velocityX += x;
        velocityY += y;
    }

    public void updateVelocity(double time) {
        positionX += velocityX * time;
        positionY += velocityY * time;
    }

    public void setSpeed(double x, double y) {
        speedX = x;
        speedY = y;
    }

    /* Bennett - I'm not super sure what thet utility of the 'velocity' group of functions is,
        but if someone is planning on using them, I've made this function so as not to step
        on any of the pre-established nomenclature. It's a bit awkward, and should definitely
        be changed pending future communication.
    */
    public void modifySpeed(double x, double y) {
        speedX += x;
        speedY += y;
    }

    public void updateSpeed() {
        updatePosition(speedX, speedY);
    }
}
