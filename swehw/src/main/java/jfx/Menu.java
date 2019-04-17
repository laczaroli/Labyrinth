package jfx;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;


public class Menu extends GridPane {

    MenuController control;
    public Menu(MenuController control) {
        this.control = control;
        //this.getStylesheets().add(this.getClass().getResource("menu.css").toExternalForm());
    }

    public void build() {
        this.setPadding(new Insets(10,10,10,10));
        this.setAlignment(Pos.CENTER);
        Button game = new Button("START NEW GAME");
        game.setPrefWidth(250);
        game.getStyleClass().add("btn");
        Button scoreboard = new Button("SCOREBOARD");
        scoreboard.setPrefWidth(250);
        scoreboard.getStyleClass().add("btn");

        control.addListener(game);
        control.addListener(scoreboard);

        this.add(game,0,0);
        this.add(scoreboard,0,1);
    }
}
