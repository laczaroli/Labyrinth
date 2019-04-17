package jfx;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;


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
        Label xd = new Label("GAME OVER");
        xd.setOpacity(0);
        xd.setStyle("-fx-font-size: 32px;"+"-fx-text-alignment: center");


        control.addListener(game);
        control.addListener(scoreboard);

        this.add(xd,0,0);
        this.add(game,0,1);
        this.add(scoreboard,0,2);
    }

    public void showText() {
        this.getChildren().get(0).setOpacity(100);
    }
}
