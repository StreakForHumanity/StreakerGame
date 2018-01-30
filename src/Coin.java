import javafx.scene.canvas.GraphicsContext;

public class Coin extends WorldItem {
    private static final String _PATH = "./assets/coin.png";
    private BackgroundItem background;
    private int MOVING_SPEED;
    private Constants constants;

    public Coin(BackgroundItem background, int MOVING_SPEED) {
        this.background = background;
        this.MOVING_SPEED = MOVING_SPEED;
        this.setImage(_PATH);
        this.resetPosition();
        this.constants = new Constants(background);
    }

    public void handleSpeed(GraphicsContext gc) {
        this.setSpeed(0, MOVING_SPEED);
        this.updateSpeed();
        this.render(gc);
    }

    public void resetPosition() {
        double x = ((background.getWidth() - 100) * Math.random()) + 50;
        double y = - background.getHeight() * Math.random();
        this.setPosition(x, y);
    }
}
