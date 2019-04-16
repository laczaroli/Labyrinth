package jfx;

import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.HPos;
import javafx.geometry.VPos;

public class View extends GridPane {

    //Controller control = new Controller();

    Model model = new Model();

    public void build(Model model) {
        this.setAlignment(Pos.CENTER);
        updateTable(model.getTable());

        this.model = model;

        System.out.println(model.getPos());

    }

    public void updateTable(int[][] table) {
        Controller control = new Controller();
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Label label = new Label(String.valueOf(table[row][col]));
                StackPane square = new StackPane();
                square.setStyle("-fx-background-color: white;");
                square.setStyle(("-fx-border-style: solid"));
                square.setStyle("-fx-border-color: black;");
                square.getChildren().add(label);
                this.add(square, col, row);
                control.addListener(this,square);

            }
            this.getColumnConstraints().add(new ColumnConstraints(5, Control.USE_COMPUTED_SIZE, Double.POSITIVE_INFINITY, Priority.ALWAYS, HPos.CENTER, true));
            this.getRowConstraints().add(new RowConstraints(5, Control.USE_COMPUTED_SIZE, Double.POSITIVE_INFINITY, Priority.ALWAYS, VPos.CENTER, true));
        }

    }

}
