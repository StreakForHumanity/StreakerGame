import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public class Coin extends WorldItem {
    private static final String _PATH = "./assets/coin.png";
    private static Constants constants;

    public Coin() {
        this.setImage(_PATH);
        this.resetPosition();
    }

    public void handleSpeed(GraphicsContext gc) {
        this.setSpeed(0, constants.getMovingSpeed());
        this.updateSpeed();
        this.render(gc);
    }

    public void resetPosition() {
        double x = ((constants.getBackground().getWidth() - 100) * Math.random()) + 50;
        double y = - constants.getBackground().getHeight() * Math.random();
        this.setPosition(x, y);
    }

    public static ArrayList<Coin> createCoins() {
        ArrayList<Coin> coins = new ArrayList<Coin>();
        for(int i = 0; i < constants.getNumCoins(); i++) {
            Coin coin = new Coin();
            coins.add(coin);
        }
        return coins;
    }

}
