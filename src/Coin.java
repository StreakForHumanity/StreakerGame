import javafx.scene.canvas.GraphicsContext;

public class Coin extends WorldItem {
    
    public Coin() {
        this.setImage(Paths.COIN_PATH);
        this.resetPosition();
    }

    public void handleSpeed(GraphicsContext gc) {
        this.setSpeed(0, Constants.STARTING_SPEED);
        this.updateSpeed();
        this.render(gc);
    }

    public void resetPosition() {
        double x = (Constants.SCREEN_WIDTH - 2 * Constants.STADIUM_BORDER) * Math.random() + Constants.STADIUM_BORDER;
        double y = - Constants.SCREEN_HEIGHT * Math.random();
        this.setPosition(x, y);
    }
}
