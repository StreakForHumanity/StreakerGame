import javafx.scene.canvas.GraphicsContext;
import java.util.ArrayList;

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
        double x = (Constants.SCREEN_WIDTH - 2 * Constants.COIN_BOUNDS) * Math.random() + Constants.COIN_BOUNDS;
        double y = - Constants.SCREEN_HEIGHT * Math.random();
        this.setPosition(x, y);
    }

    public static ArrayList<Coin> createCoins() {
        ArrayList<Coin> coins = new ArrayList<Coin>();
        for(int i = 0; i < Constants.NUM_COINS; i++) {
            Coin coin = new Coin();
            coins.add(coin);
        }
        return coins;
    }
}
