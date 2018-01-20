import javafx.scene.canvas.GraphicsContext;

public class Coin extends Character {
    private static final String _PATH = "../assets/images/coin.png";

    private BackgroundItem background;
    private int MOVING_SPEED;

    public Coin(BackgroundItem background, int MOVING_SPEED) {
        this.background = background;
        this.MOVING_SPEED = MOVING_SPEED;
        this.setImage(_PATH);
        this.resetPosition();
    }
    public void handleSpeed(GraphicsContext gc) {
        this.setSpeed(0, MOVING_SPEED);
        this.updateSpeed();
        this.render(gc);
    }
    public void resetPosition() {
        double x = background.getWidth() * Math.random();
        double y = - background.getHeight() * Math.random();
        this.setPosition(x, y);
    }
}
