package logic.models;

import java.util.ArrayList;
import java.util.List;

import logic.configuration.Constants;
import logic.configuration.Paths;

public class Coin extends WorldItem {

    public Coin() {
        this.setImage(Paths.COIN_PATH);
        this.resetPosition();
    }

    @Override
    public void updatePosition() {
    	this.setSpeed(0, Constants.STARTING_SPEED);
    	super.updatePosition();
    }

    public void resetPosition() {
        double x = (Constants.SCREEN_WIDTH - 2 * Constants.COIN_BOUNDS) * Math.random() + Constants.COIN_BOUNDS;
        double y = - Constants.SCREEN_HEIGHT * Math.random();
        this.setPosition(x, y);
    }

    public static List<Coin> createCoins() {
        List<Coin> coins = new ArrayList<>();
        for(int i = 0; i < Constants.NUM_COINS; i++) {
            Coin coin = new Coin();
            coins.add(coin);
        }
        return coins;
    }
}
