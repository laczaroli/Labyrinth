package jfx;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class MenuController {
    GameController gc = new GameController();
    Menu view = new Menu(this);

    Stage stage;

    Scene scene = new Scene(view,400,400);

    public void buildView(Stage stage) {
        view.build();
        this.stage = stage;
        stage.setScene(scene);
    }

    public void addListener(Button sp) {
        sp.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            Node source = (Node) e.getSource();
            int col = GridPane.getRowIndex(source);


            if(sp.getText().equals("START NEW GAME")) { //rákattintott a start gamere
                gc.buildView(stage);
            }
        });
    }

    public void showText() {
        view.showText();
    }
}
