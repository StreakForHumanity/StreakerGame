package logic.configuration;

public class Globals {
	
    private Globals(){
    }

    public static int TUNNELS_MODIFIER = 0;
    public static String DIFFICULTY = "Medium";
    
    public static void setTunnelsModifier(double val) {
    	int difference = 0;
    	
    	if (val < 2.0) {
    		difference = -2;
    		DIFFICULTY = "Beginner";
    	}
    	else if (val < 4.0) {
    		difference = -1;
    		DIFFICULTY = "Easy";
    	}
    	else if (val < 6.0) {
    		difference = 0;
    		DIFFICULTY = "Medium";
    	}
    	else if (val < 8.0) {
    		difference = 1;
    		DIFFICULTY = "Hard";
    	}
    	else {
    		difference = 2;
    		DIFFICULTY = "Expert";
    	}
    	
    	TUNNELS_MODIFIER = difference;
    }
}
