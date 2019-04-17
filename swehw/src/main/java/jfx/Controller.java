package jfx;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Controller implements ViewDelegate {

    private Model model;
    private Model model2;
    private View view = new View(model, this);

    //KOORDINÁCIÓS BEÁLLÍTÁSOK
    int playerTurn = 0; //alapjáraton az első játékos kezd --> nullás id
    int stepCounter = 0;
    //..

    private Scene scene = new Scene(view, 400, 400);

    public Controller(Model model) {
        this.model = model;
    }

    void buildView(Stage stage) {
        view.build();
        stage.setScene(scene);
        stage.setTitle("Házifeladat");
        stage.show();
    }

    protected int[] drawNextStep() {
        return model.nextMove();
    }

    @Override
    public void addListener(StackPane sp) {
        sp.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            int[] nextMove = drawNextStep();
            Node source = (Node) mouseEvent.getSource();
            int col = GridPane.getColumnIndex(source);
            int row = GridPane.getRowIndex(source);

            int pos = calculatePos(col, row);

            for (int i = 0; i < nextMove.length; i++) {
                if (nextMove[i] == pos) {
                    setModelPosX(row);
                    setModelPosY(col);
                    commandColorRefresh();
                    System.out.println("Sikeres lépés!");
                    break;
                }
            }

            System.out.println("Sikertelen lépés!");

            for (int a : nextMove)
                System.out.println(a);

        });
    }

    private void commandColorRefresh() {
        view.refreshColours(getModelPos());
    }

    private int calculatePos(int col, int row) {
        return (row * getModelSize()) + col;
    }


    /*public int[][] getModelTable() {
        return model.getTable();
    }*/

    public int getModelPosX() {
        return model.getPosX();
    }

    public int getModelPosY() {
        return model.getPosY();
    }

    public int getModelPos() {
        return model.getPos();
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

    public int getModelSize() {
        return model.getSize();
    }


    public static void main(String[] args) {
    }

}
