package jfx;

import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.HPos;
import javafx.geometry.VPos;

class GameView extends GridPane {

    //GameController control = new GameController();

    GameController controller;

    int[][] table;


    public GameView(GameController controller) {
        this.controller = controller;
        this.getStylesheets().add(this.getClass().getResource("gameView.css").toExternalForm());
        table = controller.getModelTable();
    }


    public void build() {
        this.setAlignment(Pos.CENTER);
        updateTable(table);
        setStarterPosition();
        updateListeners();
    }

    public void updateListeners() {
        for(int i = 0; i<this.getChildren().size(); i++) {
            controller.addListener((StackPane) this.getChildren().get(i));
        }
    }

    public void setStarterPosition() {
        this.getChildren().get(0).setStyle("-fx-background-color: red");
        drawNextStep();
    }

    public void drawNextStep() {
        int[] nextStep = controller.drawNextStep(controller.getCurrentModel());
        for(int i = 0; i<nextStep.length; i++)
            if(nextStep[i] != -1) {
                this.getChildren().get(nextStep[i]).setStyle("-fx-background-color: green;");
            }

    }

    public void refreshColours(int index) {
        for(int i = 0; i<table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                this.getChildren().get((i * 8) + j).setStyle("-fx-background-color: white; " +
                        "-fx-border-style: solid; " +
                        "-fx-border-color: black;");
            }
        }
        drawNextStep();
        this.getChildren().get(controller.getModelPos(controller.getNextPlayer())).setStyle("-fx-background-color: yellow;");
        this.getChildren().get(index).setStyle("-fx-background-color: red;");
    }

    public void updateTable(int[][] table) {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Label label = new Label(String.valueOf(table[row][col]));
                StackPane square = new StackPane();
                square.getStyleClass().add("columns");
                square.getChildren().add(label);
                this.add(square, col, row);
            }
            this.getColumnConstraints().add(new ColumnConstraints(5, Control.USE_COMPUTED_SIZE, Double.POSITIVE_INFINITY, Priority.ALWAYS, HPos.CENTER, true));
            this.getRowConstraints().add(new RowConstraints(5, Control.USE_COMPUTED_SIZE, Double.POSITIVE_INFINITY, Priority.ALWAYS, VPos.CENTER, true));
        }

    }

}
