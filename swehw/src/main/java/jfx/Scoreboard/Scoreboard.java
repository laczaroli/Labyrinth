package jfx.Scoreboard;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import result.GameResult;

import java.util.List;

/**
 * A view for the scoreboard.
 */
public class Scoreboard extends GridPane {
    ScoreboardController controller;
    List<GameResult> gameResultList;

    /**
     * A constructor that set the controller and make a request
     * for the controller.
     * @param controller the view's controller
     */
    public Scoreboard(ScoreboardController controller) {
        this.controller = controller;
        gameResultList = controller.getScoreboardResults(10);
    }

    /**
     * Building the view, fills the scoreboard with datas
     * @param numberOfQuery tells number of queries do we need.
     */
    public void view(int numberOfQuery) {

        GridPane header = addHeaderLabel();
        this.add(header,0,4);

        for(int i = 0; i<numberOfQuery && i<gameResultList.size(); i++) {
            GridPane gp = addLabels(i);
            this.add(gp,0,i+5);
        }

        Button btn = new Button("Vissza");

        controller.addListener(btn);;
        this.getChildren().add(btn);

    }

    /**
     * Adds labels to a {@code GridPane}.
     * @return the modified {@code GridPane}
     */
    public GridPane addHeaderLabel() {
        GridPane sp = new GridPane();
        Label tempLabel;

        tempLabel = new Label("Játékos neve");
        tempLabel.setMinWidth(100);
        sp.add(tempLabel,0,0);

        tempLabel = new Label("Körök száma");
        tempLabel.setMinWidth(100);
        sp.add(tempLabel,1,0);

        tempLabel = new Label("Dátum");
        sp.add(tempLabel,2,0);
        tempLabel.setMinWidth(100);

        tempLabel = new Label("Játék időtartama");
        sp.add(tempLabel,3,0);
        tempLabel.setMinWidth(100);

        return sp;
    }

    /**
     * Adds labels to a {@code GridPane}.
     * @param gameResultListIndex result's index.
     * @return the modified {@code GridPane}
     */
    public GridPane addLabels(int gameResultListIndex) {
        GridPane sp = new GridPane();
        Label tempLabel;

        tempLabel = new Label(gameResultList.get(gameResultListIndex).getPlayerName());
        tempLabel.setMinWidth(100);
        sp.add(tempLabel,0,0);

        tempLabel = new Label(String.valueOf(gameResultList.get(gameResultListIndex).getRoundCount()));
        tempLabel.setMinWidth(100);
        sp.add(tempLabel,1,0);

        tempLabel = new Label(gameResultList.get(gameResultListIndex).getDateTime().getMonth().toString() + " " +  gameResultList.get(gameResultListIndex).getDateTime().getDayOfMonth());
        sp.add(tempLabel,2,0);
        tempLabel.setMinWidth(100);

        tempLabel = new Label(String.valueOf(gameResultList.get(gameResultListIndex).getGameDuration()));
        sp.add(tempLabel,3,0);
        tempLabel.setMinWidth(100);

        return sp;
    }
}
