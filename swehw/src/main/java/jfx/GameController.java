package jfx;


import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class GameController {

    private PlayerModel player1 = new PlayerModel(0, 0, 8);
    private PlayerModel player2 = new PlayerModel(0, 0, 8);
    private GameView view = new GameView(this);
    private MenuController mc;

    private int[][] table = getModelTable();

    long start;
    long end;


    //KOORDINÁCIÓS BEÁLLÍTÁSOK
    private boolean playerTurn = false;
    private int roundCount = 0;
    private boolean lastRound = false;
    //..

    private Scene scene = new Scene(view, 400, 400);
    private Stage stage;

    public GameController() {
    }

    void buildView(Stage stage) {
        this.stage = stage;
        view.build();
        stage.setScene(scene);
        startTimer();
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

            int pos = calculatePos(row, col);

            for (possibleStepDirecitons dir : possibleStepDirecitons.values()) {
                if (nextMove[dir.getValue()] == pos) {
                    performNextStep(dir);
                    setPlayerPosition(row, col);
                    isGoalState();
                    playerTurn = !playerTurn;
                    commandColorRefresh();
                    break;
                }
            }
        });
    }

    public void performNextStep(possibleStepDirecitons dir) {
        if (getNextPlayer() == player2) {
            if (dir == possibleStepDirecitons.DOWN || dir == possibleStepDirecitons.UP)
                setNextStepDirection(Directions.HORIZONTAL);
            else if (dir == possibleStepDirecitons.RIGHT || dir == possibleStepDirecitons.LEFT)
                setNextStepDirection(Directions.VERTICAL);
        } else if (getNextPlayer() == player1) {
            setNextStepDirection(Directions.DEFAULT);
            roundCount++;
        }
    }

    private void endGame(Result result) {
        mc = new MenuController();
        if (result == Result.GAMEOVER) {
            mc.buildView(stage);
            mc.showText();
        } else if (result == Result.WIN) {
            mc.buildView(stage);
            mc.showText();
            mc.setLabelText("CONGRATULATIONS!");
        }
    }

    private void setPlayerPosition(int row, int col) {
        setModelPosX(getCurrentModel(), row);
        setModelPosY(getCurrentModel(), col);
    }

    private void isGoalState() {
        if (getModelPosX(getCurrentModel()) == 7 && getModelPosY(getCurrentModel()) == 7) {
            if(lastRound) {
                getCurrentModel().getResults(stopTimer(), roundCount);
                System.out.println("Szép volt!");
                endGame(Result.WIN);
            }
            lastRound = true;
        } else if(lastRound)
                endGame(Result.GAMEOVER);
    }

    public void startTimer() {
        start = System.currentTimeMillis();
    }

    public long stopTimer() {
        end = System.currentTimeMillis();
        return (end-start)/1000;
    }

    public PlayerModel getCurrentModel() {
        if (!playerTurn)
            return player1;
        return player2;
    }

    public PlayerModel getNextPlayer() {
        return (getCurrentModel() == player1) ? player2 : player1;
    }

    private void setNextStepDirection(Directions direction) {
        getNextPlayer().nextStepDirection(direction);
    }

    private void commandColorRefresh() {
        view.refreshColours(getModelPos(getCurrentModel()));
    }

    private int calculatePos(int row, int col) {
        return (row * getModelSize()) + col;
    }


    public int[][] getModelTable() {
        return player1.getTable();
    }

    private int getModelPosX(PlayerModel model) {
        return model.getPosX();
    }

    private int getModelPosY(PlayerModel model) {
        return model.getPosY();
    }

    public int getModelPos(PlayerModel model) {
        return model.getPos();
    }

    public void setModelSize(PlayerModel model, int size) {
        model.setSize(size);
    }

    private void setModelPosX(PlayerModel model, int posX) {
        model.setPosX(posX);
    }

    private void setModelPosY(PlayerModel model, int posY) {
        model.setPosY(posY);
    }

    private int getModelSize() {
        return getCurrentModel().getSize();
    }

}
