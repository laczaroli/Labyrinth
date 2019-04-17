package jfx;

import javafx.application.Application;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.layout.*;

public class SWEGame extends Application {
    public void start(Stage primaryStage) {
        Model model = getAllDatas();

        Controller control = new Controller(model);
        control.buildView(primaryStage);
    }

    public static Model getAllDatas() {
        Model model = new Model();
        model.setPosX(0);
        model.setPosY(0);
        model.setSize(8);
        return model;
    }


    public static void main(String[] args) {
        launch(args);
    }
}
