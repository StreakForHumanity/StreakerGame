

public class Character extends WorldItem {
    private double positionX;
    private double positionY;
    private double velocityX;
    private double velocityY;
    private double width;
    private double height;
    private double speedX;
    private double speedY;

    public Character() {
        positionX = 0;
        positionY = 0;
        velocityX = 0;
        velocityY = 0;
    }
    public void setVelocity(double x, double y) {
        velocityX = x;
        velocityY = y;
    }
    public void addVelocity(double x, double y) {
        velocityX += x;
        velocityY += y;
    }
    public void updateVelocity(double time) {
        positionX += velocityX * time;
        positionY += velocityY * time;
    }
    public void setSpeed(double x, double y) {
        speedX = x;
        speedY = y;
    }
    public void updateSpeed() {
        positionX += speedX;
        positionY += speedY;
    }
}
