import javafx.scene.canvas.GraphicsContext;

public class Coin extends WorldItem {
    private static final String _PATH = "./assets/coin.png";

    public Coin() {
        this.setImage(_PATH);
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
