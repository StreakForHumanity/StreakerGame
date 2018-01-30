import javafx.scene.canvas.GraphicsContext;

public class Coin extends WorldItem {
    private static final String _PATH = "./assets/coin.png";

    public Coin() {
        this.setImage(_PATH);
        this.resetPosition();
    }

    public void handleSpeed(GraphicsContext gc) {
        this.setSpeed(0, Constants.getStartingSpeed());
        this.updateSpeed();
        this.render(gc);
    }

    public void resetPosition() {
        double x = ((Constants.getScreenWidth() - Constants.getStadiumBorder()) * Math.random());
        double y = - Constants.getScreenHeight() * Math.random();
        this.setPosition(x, y);
    }
}
