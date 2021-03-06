package jfx.Menu;

import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import jfx.Scoreboard.ScoreboardController;
import jfx.Game.GameController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import result.GameResult;
import result.GameResultDao;
import util.guice.PersistenceModule;

import java.util.List;

/**
 * The controller that communicates with the {@code GameController} and view.
 */
public class MenuController {

    GameController gc = new GameController();
    Menu view = new Menu(this);

    Injector injector = Guice.createInjector(new PersistenceModule("game"));
    GameResultDao gameDao = injector.getInstance( GameResultDao.class);

    Logger logger = LoggerFactory.getLogger(MenuController.class);

    Stage stage;
    Scene menuScene = new Scene(view,400,400);

    /**
     * Builds the view.
     * @param stage the stage that contains the scene.
     */
    public void buildView(Stage stage) {
        view.build();
        this.stage = stage;
        stage.setScene(menuScene);
    }

    /**
     * A listener handler function that reacts to the
     * player's input.
     * @param btn a button from the menu
     */
    public void addListener(Button btn) {
        ScoreboardController sc = new ScoreboardController();
        btn.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            if(btn.getText().equals("START NEW GAME")) {
                if (view.getPlayerName().length() > 0) {
                    gc.buildView(stage);
                    gc.setPlayerName(view.getPlayerName());
                    logger.info("Have fun {}!",view.getPlayerName());
                } else {
                    setTextSize(1);
                    setLabelText("You must write your name in the box below!");
                    setLabelOpacity(100);
                    logger.error("PLease write your name in the TextBox");
                }
            }

            if(btn.getText().equals("QUIT")) {
                logger.debug("The player closed the game.");
                closeApp();
            }


            if(btn.getText().equals("SCOREBOARD")) {
                sc.buildScoreBoard(stage);
                logger.debug("Clicked on the Scoreboard tab.");
            }

        });
    }

    /**
     * Sets the label text via view.
     * @param s the string that displays on the label.
     */
    public void setLabelText(String s) {
        view.setText(s);
    }

    /**
     * Sends a command to the view to set the text size.
     * @param size the text size
     */
    public void setTextSize(double size) { view.setSize(size); }

    /**
     * Closes the application.
     */
    public void closeApp() {
        stage.close();
    }

    /**
     * Sends a command to the view to show the label.
     */
    public void setLabelOpacity(int opacity) {
        view.setLabelOpacity(opacity);
    }

    /**
     * Gets the best n players data.
     * @param numberOfPlayers tells how many player's results do you want to see
     * @return a list of a {@code GameResult} object, that contains
     * the player's results.
     */

}
