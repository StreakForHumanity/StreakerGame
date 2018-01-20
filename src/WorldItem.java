import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;
import javafx.geometry.Rectangle2D;

public class WorldItem {
    private Image image;
    private double positionX;
    private double positionY;
    private double width;
    private double height;

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
}
