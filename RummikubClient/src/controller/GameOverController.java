/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import engine.Player;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import rummikub.client.ws.GameDoesNotExists_Exception;
import rummikub.client.ws.PlayerDetails;

public class GameOverController implements Initializable,Controller {
    
    private RummikubApp application;
    private List<PlayerDetails> playersDetails;
    
    @FXML
    private VBox playersView;
    @FXML
    private Label winnerMsg;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void toMainMenu(ActionEvent event) {
        application.loadMainMenu();
    }

    @Override
    public void setApp(RummikubApp app) {
        this.application = app;
    }

    @Override
    public void initGame() {
        try {
            playersDetails = application.getClientService().
                    getPlayersDetails(application.getGameName());
        } catch (GameDoesNotExists_Exception ex) {
            Logger.getLogger(GameOverController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (application.getWinnerName().isEmpty())
        {
           winnerMsg.setText("No Winner!"); 
        }
 
        
        for(PlayerDetails playerDetails: playersDetails)
        {
            GridPane pane = new GridPane();
            
            pane.setPadding(new Insets(5));
            Label l1 = new Label();
            Label l2 = new Label();
            
            l1.setFont(new Font(30));
            l2.setFont(new Font(30));
            
            l1.setTextFill(Color.WHEAT);
            l2.setTextFill(Color.WHEAT);
            
            if(playerDetails.getName().equals(application.getWinnerName()))
                l1.setText(" winner!! -  " + playerDetails.getName() + "  ");
            else
                l1.setText(playerDetails.getName() + "  ");
            
            String str = Integer.toString(points(playerDetails));
            l2.setText(str);
            
            pane.add(l1, 0, 0);
            pane.add(l2, 1, 0); 
            
            
            playersView.getChildren().add(pane);
        }
        
    }
    
    private int points(PlayerDetails playerDetails)
    {
        int sum = sumOfTiles(playerDetails.getTiles());
        if (playerDetails.getName().equals(application.getWinnerName()))
        {
            for(PlayerDetails details : playersDetails){
                if (!details.getName().equals(playerDetails.getName()))
                    sum += sumOfTiles(details.getTiles());
            }
            return sum;
        }
        else
            return (-1 * sum);
    }
    
    private int sumOfTiles(List<rummikub.client.ws.Tile> tiles) {
        int sum = 0;
        
        for (rummikub.client.ws.Tile tile : tiles) {
            sum += tile.getValue();
        }
        
        return sum;
    }
}
