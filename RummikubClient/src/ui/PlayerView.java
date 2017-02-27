/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import controller.RummikubApp;
import engine.HumanPlayer;
import engine.Player;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import rummikub.client.ws.PlayerDetails;
import rummikub.client.ws.PlayerType;

/**
 *
 * @author Eran Keren
 */
public class PlayerView extends GridPane {
    
    private PlayerDetails playerDetails;
    private final Border notCurrentPlayerBorder = new Border(new BorderStroke
            (Paint.valueOf("White"), BorderStrokeStyle.SOLID, new CornerRadii(10), new BorderWidths(3)));
    private final Border currentPlayerBorder = new Border(new BorderStroke
            (Paint.valueOf("Blue"), BorderStrokeStyle.SOLID, new CornerRadii(10), new BorderWidths(3)));
    private Label numOfTiles;
    private final String numOfTilesString = "Num of tiles: ";
    
    public PlayerView (PlayerDetails playerDetails) {
        super();
        this.playerDetails = playerDetails;
        Image image;
        if (playerDetails.getType() == PlayerType.HUMAN)
            image = new Image(
                    getClass().getResourceAsStream(
                            RummikubApp.ImagesResourcesFolder + "humanIcon.png"));
        else 
            image = new Image(
                    getClass().getResourceAsStream(
                            RummikubApp.ImagesResourcesFolder + "computerIcon.png"));
        ImageView imageView = new ImageView(image);
        imageView.fitHeightProperty().set(50);  
        imageView.preserveRatioProperty().set(true);
        Label name = new Label(playerDetails.getName());
        numOfTiles = new Label(numOfTilesString + playerDetails.getNumberOfTiles());
        name.fontProperty().set(Font.font("Arial", 12));
        numOfTiles.fontProperty().set(Font.font("Arial", 12));
        name.textFillProperty().set(Paint.valueOf("White"));
        numOfTiles.textFillProperty().set(Paint.valueOf("White"));
        super.add(imageView, 1, 1, 1, 2);
        super.add(name, 2, 1);
        super.add(numOfTiles, 2, 2); // player.getPlayerBoard().size()
        super.paddingProperty().set(new Insets(12));
        super.alignmentProperty().set(Pos.BOTTOM_CENTER);
        super.getColumnConstraints().add(new ColumnConstraints(0));
        super.getColumnConstraints().add(new ColumnConstraints(35));
        super.getColumnConstraints().add(new ColumnConstraints(105));
        super.getRowConstraints().add(new RowConstraints(0));
        super.getRowConstraints().add(new RowConstraints(20));
        super.getRowConstraints().add(new RowConstraints(20));
        super.borderProperty().set(notCurrentPlayerBorder);
    }
    
    public PlayerDetails getPlayerDetails() {
        return playerDetails;
    }
    
    public void setCurrent() {
        super.borderProperty().set(currentPlayerBorder);
    }
    
    public void setNotCurrentPlayer() {
        super.borderProperty().set(notCurrentPlayerBorder);
    }
    
    public void updateNumOfTiles(int numOfTiles) {
        this.numOfTiles.setText(numOfTilesString + numOfTiles);
    }
}
