package GUI;

/**
 * Class which handles the launching of the application to circumvent some JavaFX build
 * issues resulting from lack of fx:deploy in Java 11
 *
 * by: Will DeBernardi
 */
public class Launcher {
    /**
     * Launches the main method of the GUI class
     * @param args Console commands
     */
    public static void main(String[] args) {
        GUI.main(args);
    }
}
