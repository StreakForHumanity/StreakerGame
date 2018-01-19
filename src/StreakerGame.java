import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

// Animation of Earth rotating around the sun. (Hello, world!)
public class StreakerGame extends Application 
{

    private static final int ScreenHeight = 600;
    private static final int movingSpeed = 8;

    public static void main(String[] args) 
    {
        launch(args);
    }

    @Override
    public void start(Stage theStage) 
    {
        theStage.setTitle( "Streaker" );

        Group root = new Group();
        Scene theScene = new Scene( root );
        theStage.setScene( theScene );

        //create background images to loop
        Sprite background = new Sprite();
        Sprite background2 = new Sprite();

        //Load images and set position        
        background.setImage("background.png");
        background.setPosition(0, ScreenHeight - background.getH());
        background2.setImage("background.png");
        background2.setPosition(0, ScreenHeight -(2 * background.getH()));
 
        //create a canvas --- canvas width must equal background width and screen height
        Canvas canvas = new Canvas( background.getW(), ScreenHeight );
        root.getChildren().add( canvas );
 
        //list of inputted keys (left right up down)
        ArrayList<String> input = new ArrayList<String>();

        //add to input string when key is held down
        theScene.setOnKeyPressed(
            new EventHandler<KeyEvent>()
            {
                public void handle(KeyEvent e)
                {
                    String code = e.getCode().toString();
                    if ( !input.contains(code) )
                        input.add( code );
                }
            });

        //remove inputted key when key is released
        theScene.setOnKeyReleased(
            new EventHandler<KeyEvent>()
            {
                public void handle(KeyEvent e)
                {
                    String code = e.getCode().toString();
                    input.remove( code );
                }
            });

        //create graphics context in canvas
        GraphicsContext gc = canvas.getGraphicsContext2D(); 
       
        //create player's animated character
        AnimatedImage character = new AnimatedImage();
        Image[] imageArray = new Image[4];
        imageArray[0] = new Image( "guyForward.png" );
        imageArray[1] = new Image( "guyLeft.png" );
        imageArray[2] = new Image( "guyForward.png" );
        imageArray[3] = new Image( "guyRight.png" );
        character.setFrame(imageArray);
        character.duration = 0.150;
        character.setPosition((background.getW() / 2) - 40, ScreenHeight/2 );

        // create coins
        ArrayList<Sprite> coins = new ArrayList<Sprite>();

        for( int i = 0; i < 4; i++)
        {
            Sprite coin = new Sprite();
            coin.setImage("tempCoin.png");
            double x = background.getW() * Math.random();
            double y = - background.getH() * Math.random();
            coin.setPosition(x, y);
            coins.add(coin);
         }

        IntValue collected = new IntValue(0);

        //get initial time values
        LongValue lastNanoTime = new LongValue( System.nanoTime() );
        final long startNanoTime = System.nanoTime();


        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                // calculate time since last update.
                double elapsedTime = (currentNanoTime - lastNanoTime.value) / 1000000000.0;
                lastNanoTime.value = currentNanoTime;

                //calculate time since started
                double nanot = currentNanoTime - startNanoTime; 
                double t = nanot / 1000000000.0;

                //loop backgrounds
                if (background.getY() > ScreenHeight)
                   background.setPosition(0, background2.getY() - background.getH());
                if (background2.getY() > ScreenHeight)
                   background2.setPosition(0, background.getY() - background2.getH());

                //set up speed bacground moves and display to graphics context
                background.setSpeed(0,movingSpeed);
                background.updateS();
                background.render( gc );
                background2.setSpeed(0,movingSpeed);
                background2.updateS();
                background2.render( gc ); 
              
                //set velocity for which character will move, 0 when no keys are pressed else velocity > 0
                character.setVelocity(0,0);
                if (input.contains("LEFT"))
                    character.addVelocity(-550,0);
                if (input.contains("RIGHT"))
                    character.addVelocity(550,0);
                if (input.contains("UP"))
                    character.addVelocity(0,-550);
                if (input.contains("DOWN"))
                    character.addVelocity(0,550);
             
                //reset character's position if character moves off screen        
                if ( character.getX() < 0 )
                    character.setPosition(0, character.getY());
                if ( character.getX() > (background.getW() - character.getW()) )
                    character.setPosition((background.getW() - character.getW()), character.getY());  
                if ( character.getY() < 0 )
                    character.setPosition(character.getX(), 0);
                if ( character.getY() > (ScreenHeight - character.getH()) )
                    character.setPosition(character.getX(), ScreenHeight - character.getH());
                
               //update characters position based on time elapsed
               character.updateV(elapsedTime);
               //add character to screen --- frame is based on time t
               character.render(gc, t);

               //if coins go out of view, replace
               Iterator<Sprite> coinsIter = coins.iterator();
                while ( coinsIter.hasNext() )
                {
                    Sprite coin = coinsIter.next();
                    // reset coins if they go off screen
                    if ( coin.getY() > ScreenHeight )
                    {
                        double x = background.getW() * Math.random();
                        double y = - background.getH() * Math.random();
                        coin.setPosition(x, y);
                    }
                    //reset coins if they are collected
                    if ( character.intersects(coin.getBoundary()) )
                    {
                        double x = background.getW() * Math.random();
                        double y = - background.getH() * Math.random();
                        coin.setPosition(x, y);
                        collected.value += 10;
                    }
                }


                for (Sprite coin : coins )
                {
                    coin.setSpeed(0,movingSpeed);
                    coin.updateS();
                    coin.render( gc);
                }


               String hms = String.format("%02d:%02d:%02d", TimeUnit.NANOSECONDS.toHours((long)nanot),
                TimeUnit.NANOSECONDS.toMinutes((long)nanot) - TimeUnit.HOURS.toMinutes(TimeUnit.NANOSECONDS.toHours((long)nanot)),
                TimeUnit.NANOSECONDS.toSeconds((long)nanot) - TimeUnit.MINUTES.toSeconds(TimeUnit.NANOSECONDS.toMinutes((long)nanot)));
                
               gc.fillText( hms, background.getW() - 100, 20 );
               gc.strokeText( hms , background.getW() - 100, 20 );    

               String coinStr = "Coins: " + collected.value;
               gc.fillText( coinStr, background.getW() - 100, 40 );
               gc.strokeText( coinStr , background.getW() - 100, 40 );


            }
        }.start();
        
        //display stage
        theStage.show();
    }
}
