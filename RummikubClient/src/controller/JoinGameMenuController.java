/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import engine.Game;
import rummikub.client.ws.GameDetails;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import rummikub.client.ws.GameDoesNotExists_Exception;
import rummikub.client.ws.InvalidParameters_Exception;

/**
 * FXML Controller class
 *
 * @author assafyehudai
 */
public class JoinGameMenuController implements Initializable, Controller {
    
    private rummikub.client.ws.RummikubWebService server;
    private RummikubApp application;
    private String gameNameToJoin;
    @FXML
    private ScrollPane gamesScroll;
    
    @FXML
    private VBox waitingScroll;
    @FXML
    private TextField playersName;
    @FXML
    private Label ErrorLine;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gamesScroll.hbarPolicyProperty().set(ScrollPane.ScrollBarPolicy.NEVER);
        gamesScroll.vbarPolicyProperty().set(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        gamesScroll.setContent(waitingScroll);
        VBox.setVgrow(waitingScroll, Priority.ALWAYS);
    }    

    @FXML
    private void joinGame(ActionEvent event) throws GameDoesNotExists_Exception, InvalidParameters_Exception {
        
        if (playersName.textProperty().get().isEmpty())
                showError();
        else {
            try {
                application.setClientID(server.joinGame(gameNameToJoin, playersName.textProperty().get()));
                application.setGameName(gameNameToJoin);
                application.loadMainGameScreen();
            } catch (InvalidParameters_Exception ex) {
                System.out.println(ex.toString());
            }
        }
    }

    @FXML
    private void MainMenu(ActionEvent event) {
        application.loadMainMenu();
    }

    @Override
    public void setApp(RummikubApp app) {
        application = app;
    }

    @Override
    public void initGame() {
        List<String> gameNames;
        server = application.getClientService();
        gameNames = server.getWaitingGames();

        Pane waitingGameLabel = null;

        for(String gameName : application.getClientService().getWaitingGames()){
            try {
                waitingGameLabel = createLabel(gameName);
            } catch (GameDoesNotExists_Exception ex) {
                System.out.println(ex.toString());
            }
            waitingScroll.getChildren().add(waitingGameLabel);
        }
    }
    
    private Pane createLabel(String gameName) throws GameDoesNotExists_Exception{
        GameDetails details = server.getGameDetails(gameName);
        Label gameLabel;
        Pane container = new Pane();
        
        int numOfPlayer = details.getComputerizedPlayers() + details.getJoinedHumanPlayers();
        String imgSrc = "resources/images/" + Integer.toString(numOfPlayer) + "PlayersGame.png";
        String text = details.getName() + " - Number Of Joind Players: ";
        
        gameLabel = new Label(text, new ImageView(imgSrc));
        gameLabel.setContentDisplay(ContentDisplay.RIGHT);
        container.getChildren().add(gameLabel);
        container.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                gameNameToJoin = gameName;
                t.consume();
            }
        });
        
        return container;
    
    }
    
    private void showError() {
        new Thread(()->{
            Platform.runLater(()->{
                String msg = "Erroe: Please Enter Your Name Or choose A Game !!!";
                ErrorLine.setText(msg);
            });
            try {
                    Thread.sleep(5000);
                } catch (Exception e) {}
            Platform.runLater(()->{
                ErrorLine.setText("");
            });
        }).start();
    }
}   
