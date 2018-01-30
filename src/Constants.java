public class Constants {

    private static final int SCREEN_HEIGHT = 600;
    private static final double FRAME_DURATION = 0.150;
    private static final int NUM_COINS = 4;
    private static final int CHARACTER_VELOCITY = 550;
    private static final double _PRECISION = 1000000000.0;
    private static final int STADIUM_BORDER = 80;
    private static final int STARTING_SPEED = 7;
    private BackgroundItem background;

    public Constants(BackgroundItem backgroundItem){
        this.setBackground(backgroundItem);
    }

    public static int getScreenHeight() {
        return SCREEN_HEIGHT;
    }

    public static double getFrameDuration() {
        return FRAME_DURATION;
    }

    public static int getNumCoins() {
        return NUM_COINS;
    }

    public static int getCharacterVelocity() {
        return CHARACTER_VELOCITY;
    }

    public static double getPrecision() {
        return _PRECISION;
    }

    public static int getStadiumBorder() {
        return STADIUM_BORDER;
    }

    public static int getStartingSpeed() {
        return STARTING_SPEED;
    }

    public BackgroundItem getBackground() {
        return background;
    }

    public void setBackground(BackgroundItem background) {
        this.background = background;
    }
}
