package jfx.Game;


import com.google.inject.Guice;

import com.google.inject.Injector;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import jfx.Menu.MenuController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import result.GameResult;
import result.GameResultDao;
import util.guice.PersistenceModule;

/**
 * A class that communicate with the model and view.
 */
public class GameController {

    private MarkerModel player1 = new MarkerModel(0, 0, 8);
    private MarkerModel player2 = new MarkerModel(0, 0, 8);

    private GameView view = new GameView(this);
    private MenuController mc;

    Logger logger = LoggerFactory.getLogger(GameController.class);

    Injector injector = Guice.createInjector(new PersistenceModule("game"));
    GameResultDao gameDao = injector.getInstance( GameResultDao.class);

    long start;
    long end;


    /**
     * Current marker of the player.
     */
    private boolean playerTurn = false;
    /**
     * Counts every round.
     */
    private int roundCount = 0;
    /**
     * Check whether it's the last round.
     */
    private boolean lastRound = false;

    private boolean solved = false;

    private String playerName;

    private Scene scene = new Scene(view, 400, 400);
    private Stage stage;

    /**
     * An empty constructor for my game controller.
     */
    public GameController() { }

    /**
     * Sends a command to its view.
     * @param stage the view's stage
     */
    public void buildView(Stage stage) {
        this.stage = stage;
        view.build();
        stage.setScene(scene);
        startTimer();
    }

    /**
     * Sends a command to the view to draw the next step.
     * @param model the current marker
     * @return an array from the model
     */
    protected int[] drawNextStep(MarkerModel model) {
        return model.nextMove();
    }

    /**
     * A listener that handle inputs from the player.
     * @param sp is the columns of the playground
     */
    public void addListener(StackPane sp) {
        sp.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            int[] nextMove = drawNextStep(getCurrentMarker());
            Node source = (Node) mouseEvent.getSource();
            int col = GridPane.getColumnIndex(source);
            int row = GridPane.getRowIndex(source);

            int pos = calculatePos(row, col);
            logger.debug("You are clicked the {} row and the {} column.",row,col);

            for (possibleStepDirections dir : possibleStepDirections.values()) {
                if (nextMove[dir.getValue()] == pos) {
                    setNextStepDirection(performNextStep(dir));
                    setMarkerPosition(row, col);
                    isSolved();
                    playerTurn = !playerTurn;
                    refreshColors();
                    logger.debug("The current marker's position is: ({},{})",getMarkerPosX(getCurrentMarker()), getMarkerPosY(getCurrentMarker()));
                    break;
                }
            }
        });
    }

    /**
     * The second marker's move is locked, it moves the first player's
     * perpendicular direction.
     * @param dir the direction where the player steps.
     * @return the next player's direction.
     */
    public Directions performNextStep(possibleStepDirections dir) {
        if (getNextMarker() == player2) {
            if (dir == possibleStepDirections.DOWN || dir == possibleStepDirections.UP)
                return Directions.HORIZONTAL;
            else if (dir == possibleStepDirections.RIGHT || dir == possibleStepDirections.LEFT)
                return Directions.VERTICAL;
        } else if (getNextMarker() == player1) {
            roundCount++;
            return Directions.DEFAULT;
        }
        return Directions.DEFAULT;
    }

    /**
     * It navigates the player to the main menu if the
     * game ends.
     * @param result the type of ending a game.
     */
    private void endGame(Result result) {
        mc = new MenuController();
        if (result == Result.GAMEOVER) {
            mc.buildView(stage);
            mc.setLabelOpacity(100);
            mc.setTextSize(2.5);
            mc.setLabelText("GAME OVER");
            logger.info("The player couldn't get out from the labyrinth.");
        } else if (result == Result.WIN) {
            mc.buildView(stage);
            mc.setLabelOpacity(100);
            mc.setTextSize(2.5);
            mc.setLabelText("CONGRATULATIONS!");
            logger.info("The player is passed the labyrinth!");
            solved = true;
        }

        GameResult res = getCurrentMarker().setResults(playerName,stopTimer(), roundCount, solved);

        gameDao.persist(res);
    }

    /**
     * Set the current marker's position.
     * @param row the row on the table
     * @param col the column on the table
     */
    public void setMarkerPosition(int row, int col) {
        if(row >= getPlaygroundSizeSize() || col >= getPlaygroundSizeSize())
            throw new IndexOutOfBoundsException();
        setMarkerPosX(getCurrentMarker(), row);
        setMarkerPosY(getCurrentMarker(), col);
    }

    /**
     * Check whether the game is solved, and the player reaches
     * the goal state.
     * @return {@code true} if the player is at the goal state
     * {@code false} if the player couldn't reach the goal state.
     */
    public boolean isSolved() {
        if (getMarkerPosX(getCurrentMarker()) == 7 && getMarkerPosY(getCurrentMarker()) == 7) {
            if(lastRound) {
                endGame(Result.WIN);
                return true;
            }
            lastRound = true;
        } else if(lastRound || getModelTable()[getMarkerPosX(getCurrentMarker())][getMarkerPosY(getCurrentMarker())] == 0) {
            endGame(Result.GAMEOVER);
            return false;
        }
        return lastRound;
    }

    /**
     * Start the timer that checks the game duration.
     */
    public void startTimer() {
        start = System.currentTimeMillis();
    }

    /**
     * Stop the timer that checks the game duration.
     * @return the game duration in seconds
     */
    public long stopTimer() {
        end = System.currentTimeMillis();
        return (end-start)/1000;
    }

    /**
     * Get the player's current marker.
     * @return the current marker's object
     */
    public MarkerModel getCurrentMarker() {
        if (!playerTurn)
            return player1;
        return player2;
    }

    /**
     * Sets the player name.
     * @param playerName the current player's name
     */
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    /**
     * Get the player's next marker.
     * @return the next marker's object.
     */
    public MarkerModel getNextMarker() {
        return (getCurrentMarker() == player1) ? player2 : player1;
    }

    /**
     * Calls a function in the model class, that calculates
     * the next step's direction.
     * @param direction the next step direction.
     */
    public void setNextStepDirection(Directions direction) {
        getNextMarker().nextStepDirection(direction);
    }


    /**
     * This function sends a command to the view to
     * refresh the colors on the playground.
     */
    public void refreshColors() {
        view.refreshColours(getModelPos(getCurrentMarker()));
    }

    /**
     * Calculating the position on the playground by rows and columns
     * for array indexes.
     * @param row the table's rows
     * @param col the table's columns
     * @return the calculated position
     */
    public int calculatePos(int row, int col) {
        if(row > getPlaygroundSizeSize() || col > getPlaygroundSizeSize())
            throw new IllegalArgumentException();
        return (row * getPlaygroundSizeSize()) + col;
    }

    /**
     * Gets the array of the playground.
     * @return a table array
     */
    public int[][] getModelTable() {
        return player1.getTable();
    }

    /**
     * Gets a marker's X coordinate.
     * @param model a marker
     * @return the marker's X coordinate
     */
    public int getMarkerPosX(MarkerModel model) {
        return model.getPosX();
    }

    /**
     * Gets a marker's Y coordinates.
     * @param model a marker
     * @return the marker's Y coordinate
     */
    public int getMarkerPosY(MarkerModel model) {
        return model.getPosY();
    }

    /**
     * Get the marker's position.
     * @param model a marker
     * @return the marker's position on the table
     */
    public int getModelPos(MarkerModel model) {
        return model.getPos();
    }

    /**
     * Sets the marker's X coordinate.
     * @param model a marker
     * @param posX an X coordinate
     */
    public void setMarkerPosX(MarkerModel model, int posX) {
        model.setPosX(posX);
    }

    /**
     * Sets the marker's Y coordinate.
     * @param model a marker
     * @param posY an Y coordinate
     */
    public void setMarkerPosY(MarkerModel model, int posY) {
        model.setPosY(posY);
    }

    /**
     * Gets the player's table size.
     * @return the player's table size.
     */
    public int getPlaygroundSizeSize() {
        return getCurrentMarker().getSize();
    }

}
