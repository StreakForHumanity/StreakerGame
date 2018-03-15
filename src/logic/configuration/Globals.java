package logic.configuration;

public class Globals {
	
    private Globals(){
    }

    public static int TUNNELS_MODIFIER = 0;
    
    public static void setTunnelsModifier(double val) {
    	int difference = 0;
    	
    	if (val < 2.0) {
    		difference = -2;
    	}
    	else if (val < 4.0) {
    		difference = -1;
    	}
    	else if (val < 6.0) {
    		difference = 0;
    	}
    	else if (val < 8.0) {
    		difference = 1;
    	}
    	else {
    		difference = 2;
    	}
    	
    	TUNNELS_MODIFIER = difference;
    }
}
