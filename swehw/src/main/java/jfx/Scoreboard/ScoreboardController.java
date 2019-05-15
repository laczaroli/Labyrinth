package jfx.Scoreboard;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import jfx.Menu.MenuController;
import result.GameResult;

import java.util.List;

/**
 * The scoreboard controller makes commands to the view.
 */
public class ScoreboardController {
    MenuController mc = new MenuController();
    Scoreboard scoreBoard = new Scoreboard(this);

    private Scene scene = new Scene(scoreBoard, 400, 400);
    private Stage stage;

    /**
     * This listener handle the button click on the
     * scoreboard.
     * @param btn a button
     */
    public void addListener(Button btn) {
        btn.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            if(btn.getText().equals("Vissza"))
                mc.buildView(stage);
        });
    }

    public void buildScoreBoard(Stage stage) {
        scoreBoard.view(10);
        this.stage = stage;
        stage.setScene(scene);
    }

    public List<GameResult> getScoreboardResults(int numberOfQuery) {
        return mc.getTheToplist(10);
    }
}
