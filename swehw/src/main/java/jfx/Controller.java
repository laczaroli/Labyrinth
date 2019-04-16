package jfx;


import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class Controller {

    private Model model = new Model();
    private View view = new View();

    private Stage mainStage = new Stage();
    private Scene scene = new Scene(view,400,400);
    public Controller() {}

    public void buildView(Stage stage) {
        view.build(model);
        this.mainStage = stage;

        stage.setScene(scene);
        stage.setTitle("Házifeladat");
        stage.show();
    }

    public int[] drawNextStep() {
        return model.nextMove();
    }

    public void addListener(GridPane gp, StackPane sp) {
        sp.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                int[] nextMove = getModelNextMove();
                Node source = (Node) event.getSource();
                int col = GridPane.getColumnIndex(source);
                int row = GridPane.getRowIndex(source);

                setModelPosX(row);
                setModelPosY(col);
                System.out.println(getModelPosX());
                System.out.println(getModelPosY());
                refreshView();
            }
        });
    }

    public void refreshView() {
        view.getChildren().get(model.getPos()).setStyle("-fx-background-color: red; ");
    }



    public int[][] getModelTable() {
        return model.getTable();
    }

    public int getModelPosX() {
        return model.getPosX();
    }

    public int getModelPosY() {
        return model.getPosY();
    }

    public void setModelSize(int size) {
        model.setSize(size);
    }

    public void setModelPosX(int posX) {
        model.setPosX(posX);
    }

    public void setModelPosY(int posY) {
        model.setPosY(posY);
    }

    public int[] getModelNextMove() {
        return model.nextMove();
    }

    public int getModelSize() {
        return model.getSize();
    }


    public static void main(String[] args) {
    }

}
