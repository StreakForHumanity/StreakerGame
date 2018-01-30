import javafx.scene.canvas.GraphicsContext;

import java.util.concurrent.TimeUnit;

public class GraphicsController {

    private GraphicsContext gc;
    private static Constants constants;

    public GraphicsController(GraphicsContext gc){
        this.gc = gc;
    }

    public void showTime(double nanot) {
        String hms = String.format("%02d:%02d:%02d ", TimeUnit.NANOSECONDS.toHours((long)nanot),
                TimeUnit.NANOSECONDS.toMinutes((long)nanot) - TimeUnit.HOURS.toMinutes(TimeUnit.NANOSECONDS.toHours((long)nanot)),
                TimeUnit.NANOSECONDS.toSeconds((long)nanot) - TimeUnit.MINUTES.toSeconds(TimeUnit.NANOSECONDS.toMinutes((long)nanot)));

        gc.fillText(hms,  constants.getBackground().getWidth() - 150, 40);
        gc.strokeText(hms , constants.getBackground().getWidth() - 150, 40);
    }
}
