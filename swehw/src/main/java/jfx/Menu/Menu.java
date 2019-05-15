package jfx.Menu;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;


/**
 * The view class of the menu.
 */
public class Menu extends GridPane {

    boolean active = false;
    Label resultLabel = new Label();
    Button game = new Button("START NEW GAME");
    Button scoreboard = new Button("SCOREBOARD");
    Button quit = new Button("QUIT");
    TextField name = new TextField();

    MenuController control;

    /**
     * The menu view contructor, that links the control and the
     * {@code CSS} file to the class.
     * @param control the controller
     */
    public Menu(MenuController control) {
        this.control = control;
        this.getStylesheets().add(this.getClass().getResource("menu.css").toExternalForm());
    }

    /**
     * Builds the menu, placing some buttons and labels.
     */
    public void build() {

        resultLabel.getStyleClass().add("resultLabel");
        resultLabel.setMinHeight(50);
        game.getStyleClass().add("btn");
        scoreboard.getStyleClass().add("btn");
        quit.getStyleClass().add("btn");

        control.addListener(game);
        control.addListener(scoreboard);
        control.addListener(quit);

        if(!active) {
            this.add(resultLabel, 0, 0);
            this.add(new Label("\tA játék elkezdése előtt kérlek írd be a neved"), 0, 1);
            this.add(name,0,2);
            this.add(game, 0, 3);
            this.add(scoreboard, 0, 4);
            this.add(quit, 0, 5);

            active = true;
        }
    }

    /**
     * Sets the label's opacity
     * @param opacity
     */
    public void setLabelOpacity(int opacity) {
        resultLabel.setOpacity(opacity);
    }

    /**
     * Sets the label's text.
     * @param text the label's text
     */
    public void setText(String text) {
        resultLabel.setText(text);
    }

    /**
     * Set the label's size.
     * @param size the label's size
     */
    public void setSize(double size) {
        resultLabel.setStyle("-fx-font-size: " + size + "em");
    }

    /**
     * Gets the player's name.
     * @return the player's name
     */
    public String getPlayerName() {
        return name.getText();
    }
}
