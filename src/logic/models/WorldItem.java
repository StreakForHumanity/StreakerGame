package logic.models;

import javafx.scene.image.Image;
import javafx.geometry.Rectangle2D;

public class WorldItem {
    private Image image;
    protected double positionX;
    protected double positionY;
    protected double width;
    protected double height;

    private double velocityX;
    private double velocityY;
    private double speedX;
    private double speedY;
    
    protected WorldItem() {}
    
    public WorldItem(int test) {}

    public void setImage(Image i) {
        image = i;
        width = i.getWidth();
        height = i.getHeight();
    }

    public void setImage(String filename) {
        Image i = new Image(filename);
        setImage(i);
    }
    
    /* added for use in GraphicsController rendering*/
    public Image getImage() {
    	return image;
    }

    public void setPosition(double x, double y) {
        positionX = x;
        positionY = y;
    }
    
    // increments position of WorldItem with respect to preset speed values.
    public void updatePosition() {
    		positionX += speedX;
    		positionY += speedY;
    }

    public double getX() {
        return positionX;
    }

    public double getY() {
        return positionY;
    }
    
    public double getSpeedX() {
    	return speedX;
    }
    
    public double getSpeedY() {
    	return speedY;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public Rectangle2D getBoundary() {
        return new Rectangle2D(positionX, positionY, width, height);
    }

    public boolean intersects(Rectangle2D s) {
        return s.intersects(this.getBoundary());
    }

    public void setVelocity(double x, double y) {
        velocityX = x;
        velocityY = y;
    }

    public void incrementVelocity(double x, double y) {
        velocityX += x;
        velocityY += y;
    }

    public void applyVelocity(double time) {
        positionX += velocityX * time;
        positionY += velocityY * time;
    }

    public void setSpeed(double x, double y) {
        speedX = x;
        speedY = y;
    }

    public void incrementSpeed(double x, double y) {
        speedX += x;
        speedY += y;
    }
    
    public double getVelocityX() {
    		return velocityX;
    }
    
    public double getVelocityY() {
		return velocityX;
}

	public double getSpeedX() {
		return speedX;
	}

	public double getSpeedY() {
		return speedY;
	}
}
