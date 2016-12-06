import Logic.ConfigLoader;
import Logic.Controller;

/**
 * Denne klasse starter klienten.
 */
public class Main {

    public static void main(String[] args) {

        // Konfigurationen indlæses
        ConfigLoader.parseConfig();

        // Vis Login view
        Controller controller = new Controller();
        controller.showLoginView();
    }
}