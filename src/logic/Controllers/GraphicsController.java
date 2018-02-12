package logic.Controllers;

import javafx.scene.canvas.GraphicsContext;
import logic.Model.Constants;

import java.util.concurrent.TimeUnit;

public class GraphicsController {

    private GraphicsContext gc;

    public GraphicsController(GraphicsContext gc){
        this.gc = gc;
    }

    public void showTime(double nanot) {
        String hms = String.format("%02d:%02d:%02d ", TimeUnit.NANOSECONDS.toHours((long)nanot),
                TimeUnit.NANOSECONDS.toMinutes((long)nanot) - TimeUnit.HOURS.toMinutes(TimeUnit.NANOSECONDS.toHours((long)nanot)),
                TimeUnit.NANOSECONDS.toSeconds((long)nanot) - TimeUnit.MINUTES.toSeconds(TimeUnit.NANOSECONDS.toMinutes((long)nanot)));

        gc.fillText(hms,  Constants.SCREEN_WIDTH - 150, 40);
        gc.strokeText(hms , Constants.SCREEN_WIDTH - 150, 40);
    }
}
