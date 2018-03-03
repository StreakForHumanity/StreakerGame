package logic.models;

import logic.configuration.Paths;
import logic.configuration.Constants;
import java.util.Random;

public class Terrain extends WorldItem {
	
	public Terrain() {
		Random rand = new Random();
		this.setImage(Paths.TERRAIN_PATH);
		double maxXVal = Constants.SCREEN_WIDTH - 2*Constants.STADIUM_STANDS_MARGIN - this.getWidth();
		this.setPosition(rand.nextInt((int)maxXVal) + Constants.STADIUM_STANDS_MARGIN, - Constants.SCREEN_HEIGHT);
	}
	
	@Override
    public void updatePosition() {
    	this.setSpeed(0, Constants.STARTING_SPEED);
    	super.updatePosition();
    }
}
