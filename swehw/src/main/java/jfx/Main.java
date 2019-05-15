package jfx;

import javafx.application.Application;
import javafx.stage.Stage;
import jfx.Menu.MenuController;
import jfx.Game.GameController;

/**
 * The main class that calls the basic functions.
 */
public class Main extends Application {
    /**
     * A JavaFX function, that is the beginning of our
     * application display.
     * @param primaryStage the primary stage
     */
    public void start(Stage primaryStage) {
        GameController game = new GameController();
        MenuController menu = new MenuController();
        menu.buildView(primaryStage);

        primaryStage.setTitle("Labyrinth");
        primaryStage.show();

    }

    /**
     * Calls a JavaFX function.
     * @param args the main arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
