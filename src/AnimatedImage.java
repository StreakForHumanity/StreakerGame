import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;
import javafx.geometry.Rectangle2D;

public class AnimatedImage
{
    private double positionX;
    private double positionY;    
    private double velocityX;
    private double velocityY;
    private double width;
    private double height;
    private double speedX;
    private double speedY;

    // assumes animation loops,
    //  each image displays for equal time
    public Image[] frames;
    public double duration;

    public AnimatedImage()
    {
        positionX = 0;
        positionY = 0;    
        velocityX = 0;
        velocityY = 0;
    }

    public Image getFrame(double time)
    {
        int index = (int)((time % (frames.length * duration)) / duration);
        return frames[index];
    }

    public double getX()
    {
        return positionX;
    }

    public double getY()
    {
        return positionY;
    }

    public double getW() 
    {
        return width;
    }

    public double getH() 
    {
        return height;
    }
    public void setFrame(Image[] i)
    {
        frames = i;
        width = i[0].getWidth();
        height = i[0].getHeight();
    }

    public void setPosition(double x, double y)
    {
        positionX = x;
        positionY = y;
    }

    public void setVelocity(double x, double y)
    {
        velocityX = x;
        velocityY = y;
    }

    public void addVelocity(double x, double y)
    {
        velocityX += x;
        velocityY += y;
    }

    public void updateV(double dependant)
    {
        positionX += velocityX * dependant;
        positionY += velocityY * dependant;
    }

    public void setSpeed(double x, double y)
    {
        speedX = x;
        speedY = y;
    }

    public void updateS()
    {
        positionX += speedX;
        positionY += speedY;
    }

    public void render(GraphicsContext gc, double time)
    {
        gc.drawImage( getFrame(time), positionX, positionY );
    }

    public Rectangle2D getBoundary()
    {
        return new Rectangle2D(positionX,positionY,width,height);
    }

    public boolean intersects(Rectangle2D rect)
    {
        return rect.intersects( this.getBoundary() );
    }
    
    public String toString()
    {
        return " Position: [" + positionX + "," + positionY + "]" 
        + " Velocity: [" + velocityX + "," + velocityY + "]";
    }
}

