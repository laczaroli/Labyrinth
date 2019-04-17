package jfx;

import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.HPos;
import javafx.geometry.VPos;

class GameView extends GridPane {

    //GameController control = new GameController();

    GameController controller;

    private int table[][]  = new int[][] {{3,5,0,2,1,2,3,4},
            {1,2,2,1,4,5,2,0},
            {2,0,1,3,4,3,2,1},
            {4,4,0,2,3,0,5,2},
            {4,1,0,3,3,2,4,3},
            {1,0,2,2,3,0,1,0},
            {4,0,2,2,1,4,0,1},
            {2,2,0,4,3,5,4,0}};


    public GameView(GameController controller) {
        this.controller = controller;
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
                square.setStyle("-fx-background-color: white;");
                square.setStyle(("-fx-border-style: solid;"));
                square.setStyle("-fx-border-color: black;");
                square.getChildren().add(label);
                this.add(square, col, row);
            }
            this.getColumnConstraints().add(new ColumnConstraints(5, Control.USE_COMPUTED_SIZE, Double.POSITIVE_INFINITY, Priority.ALWAYS, HPos.CENTER, true));
            this.getRowConstraints().add(new RowConstraints(5, Control.USE_COMPUTED_SIZE, Double.POSITIVE_INFINITY, Priority.ALWAYS, VPos.CENTER, true));
        }

    }

}
