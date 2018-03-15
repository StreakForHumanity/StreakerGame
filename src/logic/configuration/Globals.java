package logic.configuration;

public class Globals {
	
    private Globals(){
    }

    private static int tunnelsModifier = 0;
    private static String difficulty = "Medium";
    
    public static void setTunnelsModifier(double val) {
    	int difference = 0;
    	
    	if (val < 2.0) {
    		difference = -2;
    		difficulty = "Beginner";
    	}
    	else if (val < 4.0) {
    		difference = -1;
    		difficulty = "Easy";
    	}
    	else if (val < 6.0) {
    		difference = 0;
    		difficulty = "Medium";
    	}
    	else if (val < 8.0) {
    		difference = 1;
    		difficulty = "Hard";
    	}
    	else {
    		difference = 2;
    		difficulty = "Expert";
    	}
    	
    	tunnelsModifier = difference;
    }
    
    public static int getTunnelsModifier() {
    	return tunnelsModifier;
    }
    
    public static String getDifficulty() {
    	return difficulty;
    }
}
