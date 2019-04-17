package jfx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class SWEGame extends Application {
    public void start(Stage primaryStage) {
        GameController game = new GameController();
        MenuController menu = new MenuController();
        menu.buildView(primaryStage);

        primaryStage.setTitle("Házifeladat");
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
