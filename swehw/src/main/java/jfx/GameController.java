package jfx;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class GameController {

    private PlayerModel player1 = new PlayerModel(0,0,8);
    private PlayerModel player2 = new PlayerModel(0,0,8);
    private GameView view = new GameView(this);


    //KOORDINÁCIÓS BEÁLLÍTÁSOK
    private boolean playerTurn = false; //alapjáraton az első játékos kezd --> false érték
    private int roundCount = 0;
    //..

    private Scene scene = new Scene(view, 400, 400);

    public GameController() {
    }

    void buildView(Stage stage) {

        view.build();
        stage.setScene(scene);
    }

    protected int[] drawNextStep(PlayerModel model) {
        return model.nextMove();
    }

    public void addListener(StackPane sp) {
        sp.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            int[] nextMove = drawNextStep(getCurrentModel());
            Node source = (Node) mouseEvent.getSource();
            int col = GridPane.getColumnIndex(source);
            int row = GridPane.getRowIndex(source);

            int pos = calculatePos(col, row);

            for (int i = 0; i < nextMove.length; i++) {
                if (nextMove[i] == pos) {
                    if(getNextPlayer() == player2) {
                        if (i == 1 || i == 3)
                            setNextStepDirection("horizontal"); //kizárólag ellentétes irányokba léphet
                        else if (i == 0 || i == 2)
                            setNextStepDirection("vertical"); //kizárólag ellentétes irányokba léphet
                    } else if(getNextPlayer() == player1) {
                        setNextStepDirection("DEFAULT");
                        roundCount++;
                    }
                    if(pos == 0) endGame();
                    setModelPosX(getCurrentModel(), row);
                    setModelPosY(getCurrentModel(), col);
                    System.out.println("Sikeres lépés!");
                    playerTurn = !playerTurn;

                    System.out.println("Player1 pos: " + getModelPos(player1));
                    System.out.println("Player2 pos: " + getModelPos(player2));
                    commandColorRefresh();
                    break;
                }
            }
            for (int a : nextMove)
                System.out.println(a);

        });
    }

    public void endGame() {
        System.out.println("VÉGE A JÁTÉKNAK TESÓ, 0 RA LÉPTÉL!+!+!+!+");
    }

    public PlayerModel getCurrentModel() {
        if(!playerTurn)
            return player1;
        return player2;
    }

    public PlayerModel getNextPlayer() {
        return (getCurrentModel() == player1)? player2 : player1;
    }

    private void setNextStepDirection(String s) {
        getNextPlayer().nextStepDirection(s);
    }

    private void commandColorRefresh() {
        view.refreshColours(getModelPos(getCurrentModel()));
    }

    private int calculatePos(int col, int row) {
        return (row * getModelSize(getCurrentModel())) + col;
    }


    public int[][] getModelTable() {
        return player1.getTable();
    }

    public int getModelPosX(PlayerModel model) {
        return model.getPosX();
    }

    public int getModelPosY(PlayerModel model) {
        return model.getPosY();
    }

    public int getModelPos(PlayerModel model) {
        return model.getPos();
    }

    public void setModelSize(PlayerModel model, int size) {
        model.setSize(size);
    }

    public void setModelPosX(PlayerModel model, int posX) {
        model.setPosX(posX);
    }

    public void setModelPosY(PlayerModel model, int posY) {
        model.setPosY(posY);
    }

    public int getModelSize(PlayerModel model) {
        return model.getSize();
    }


    public static void main(String[] args) {
    }

}
