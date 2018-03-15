package logic.configuration;

public class Paths {

    private Paths(){
    }

    public static final String HIGHSCORES_PATH = "./assets/highscores.dat";
    public static final String BACKGROUND_PATH = "./assets/stadium_large.png";
    public static final String HELP_KEYS_PATH = "./assets/arrow_keys_with_movement.png";
    public static final String MAINMENU_BACKGROUND_PATH = "./assets/main_menu.png";
    public static final String SETTINGS_BACKGROUND_PATH = "./assets/settings.png";
    public static final String HELP_BACKGROUND_PATH = "./assets/help.png";
    public static final String GAME_OVER_PATH = "./assets/game_over.png";
    public static final String COIN_PATH = "./assets/coin.png";
    public static final String TERRAIN_PATH = "./assets/terrain.png";
    
    private static final String[] GUARD_PATHS = {
            "./assets/guardForward.png",
            "./assets/guardLeft.png",
            "./assets/guardForward.png",
            "./assets/guardRight.png"
    };
    public static String[] getGuardPaths() {
    	return GUARD_PATHS;
    }
    
    private static final String[] STREAKER_PATHS = {
            "./assets/guyForward.png",
            "./assets/guyLeft.png",
            "./assets/guyForward.png",
            "./assets/guyRight.png",
            "./assets/guyJump.png"
    };
    public static String[] getStreakerPaths() {
    	return STREAKER_PATHS;
    }
    
    private static final String[] TUNNEL_PATHS = {
        "./assets/tunnelLeft.png",
        "./assets/tunnelRight.png"
    };
    public static String[] getTunnelPaths() {
    	return TUNNEL_PATHS;
    }
    
    private static final String[] GAME_OVER_BUTTONS = {
    	"./assets/button_newGame.png",
    	"./assets/button_mainMenu.png",
    	"./assets/button_exitGame.png"
    };
    public static String[] getGameOverButtons() {
    	return GAME_OVER_BUTTONS;
    }
    
    private static final String[] GAME_OVER_BUTTONS_PRESSED = {
        "./assets/button_newGame_pressed.png",
        "./assets/button_mainMenu_pressed.png",
        "./assets/button_exitGame_pressed.png"
    };
    public static String[] getGameOverButtonsPressed() {
    	return GAME_OVER_BUTTONS_PRESSED;
    }
    
    private static final String[] MAIN_MENU_BUTTONS = {
        "./assets/button_start_main.png",
        "./assets/button_settings.png",
        "./assets/button_help.png"
    };
    public static String[] getMainMenuButtons() {
    	return MAIN_MENU_BUTTONS;
    }
    
    private static final String[] SETTINGS_BUTTONS = {
        "./assets/button_back.png"
    };
    public static String[] getSettingsButtons() {
    	return SETTINGS_BUTTONS;
    }
    
    private static final String[] HELP_BUTTONS = {
        "./assets/button_back.png"
    };
    public static String[] getHelpButtons() {
    	return HELP_BUTTONS;
    }
}
