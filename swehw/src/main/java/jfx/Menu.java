package jfx;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;


public class Menu extends GridPane {

    Label resultLabel = new Label("GAME OVER");
    Button game = new Button("START NEW GAME");
    Button scoreboard = new Button("SCOREBOARD");
    Button quit = new Button("QUIT");

    MenuController control;
    public Menu(MenuController control) {
        this.control = control;
        this.getStylesheets().add(this.getClass().getResource("menu.css").toExternalForm());
    }

    public void build() {

        resultLabel.getStyleClass().add("resultLabel");
        game.getStyleClass().add("btn");
        scoreboard.getStyleClass().add("btn");
        quit.getStyleClass().add("btn");

        control.addListener(game);
        control.addListener(scoreboard);
        control.addListener(quit);

        this.add(resultLabel,0,0);
        this.add(game,0,1);
        this.add(scoreboard,0,2);
        this.add(quit,0,3);
    }

    public void showText() {
        this.getChildren().get(0).setOpacity(100);
    }

    public void setText(String text) {
        resultLabel.setText(text);
    }
}
