/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import engine.Game;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import rummikub.client.ws.DuplicateGameName_Exception;
import rummikub.client.ws.GameDoesNotExists_Exception;
import rummikub.client.ws.InvalidParameters_Exception;

/**
 * FXML Controller class
 *
 * @author Eran Keren
 */
public class NewGameMenuController implements Initializable, Controller, ChangeListener<Object> {
    @FXML
    private TextField playerNameText;
    @FXML
    private TextField gameNameText;
    @FXML
    private Button cancelButton;
    @FXML
    private Button startGameButton;
    @FXML
    private RadioButton zeroComputerPlayer;
    @FXML
    private RadioButton oneComputerPlayer;
    @FXML
    private RadioButton twoComputerPlayer;
    @FXML
    private RadioButton threeComputerPlayer;
    @FXML
    private RadioButton oneHumanPlayer;
    @FXML
    private RadioButton twoHumanPlayer;
    @FXML
    private RadioButton threeHumanPlayer;
    @FXML
    private RadioButton fourHumanPlayer;
    @FXML
    private ToggleGroup computerPlayersNumber;
    @FXML
    private ToggleGroup humanPlayersNumber;
    @FXML
    private Label errorLabel;
    
    private final String newGameString = "game name here...";
    private final String playerNameString = "your name here...";
    
    private RummikubApp application;
    private int numOfHumanPlayers = 0;
    private int numOfComputerPlayer = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        playerNameText.focusedProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (playerNameText.textProperty().get().isEmpty()) {
                        playerNameText.textProperty().set(playerNameString);             
                    } else if (playerNameText.textProperty().get().equals(playerNameString)) {
                        playerNameText.textProperty().set("");
                    }
        });
        
        playerNameText.textProperty().addListener(
            (observable, oldValue, newValue) -> {
                checkStartButtonEnabling();
        });
                
        gameNameText.focusedProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (gameNameText.textProperty().get().isEmpty()) {
                        gameNameText.textProperty().set(newGameString); 
                    } else if (gameNameText.textProperty().get().equals(newGameString)) {
                        gameNameText.textProperty().set("");
                    }
        });
        
        gameNameText.textProperty().addListener(
              (observable, oldValue, newValue) -> {
                    checkStartButtonEnabling();
              });
        
        humanPlayersNumber.selectedToggleProperty().addListener(this);
        computerPlayersNumber.selectedToggleProperty().addListener(this);
    }

    @FXML
    private void goBackToMainMenu(ActionEvent event) {
        application.loadMainMenu();
    }
    
    @FXML
    private void startGame(ActionEvent event) {
        try {
            application.getClientService().createGame(gameNameText.getText(),
                                                      getNumOfHumanPlayers(),
                                                      getNumOfComputerPlayers());
            
            application.setClientID(application.getClientService().
                                    joinGame(gameNameText.getText(), playerNameText.getText()));
            application.setGameName(gameNameText.getText());
            application.loadMainGameScreen();
        } catch (DuplicateGameName_Exception | InvalidParameters_Exception | GameDoesNotExists_Exception e) {
            showError("Game Name Already Exists!");
        }
    }
    
    @Override
    public void setApp (RummikubApp app) {
        application = app;
    }

    @Override
    public void initGame() {}
    
    
    private void checkStartButtonEnabling() {
        if (gameNameText.textProperty().get().isEmpty() || 
                gameNameText.textProperty().get().equals(newGameString)
                || playerNameText.textProperty().get().isEmpty() || 
                playerNameText.textProperty().get().equals(playerNameString)) 
            startGameButton.disableProperty().set(true);
        else
            startGameButton.disableProperty().set(false);
    }
    
    private void checkComputerToggleEnabling() {
        if (humanPlayersNumber.selectedToggleProperty().get() == oneHumanPlayer) {
            setComputerToggles(true, true, true);
            oneHumanPlayer.setSelected(true);
        } else if (humanPlayersNumber.selectedToggleProperty().get() == twoHumanPlayer) {
            setComputerToggles(true, true, false);
            twoHumanPlayer.setSelected(true);
        } else if (humanPlayersNumber.selectedToggleProperty().get() == threeHumanPlayer) {
            setComputerToggles(true, false, false);
            threeHumanPlayer.setSelected(true);
        } else if (humanPlayersNumber.selectedToggleProperty().get() == fourHumanPlayer) {
            setComputerToggles(false, false, false);
            fourHumanPlayer.setSelected(true);
        } 
    }
        
    private void checkHumanToggleEnabling() {    
        if (computerPlayersNumber.selectedToggleProperty().get() == zeroComputerPlayer) {
            setHumanToggles(true, true, true);
            twoHumanPlayer.setSelected(true);
            oneHumanPlayer.setVisible(false);
            oneHumanPlayer.setDisable(true);
            zeroComputerPlayer.setSelected(true);
        } else if (computerPlayersNumber.selectedToggleProperty().get() == oneComputerPlayer) {
            setHumanToggles(true, true, false);
            oneComputerPlayer.setSelected(true);
        } else if (computerPlayersNumber.selectedToggleProperty().get() == twoComputerPlayer) {
            setHumanToggles(true, false, false);
            twoComputerPlayer.setSelected(true);
        } else if (computerPlayersNumber.selectedToggleProperty().get() == threeComputerPlayer) {
            setHumanToggles(false, false, false);
            threeComputerPlayer.setSelected(true);
        }
    }
    
    private void setComputerToggles(boolean isOnePlayerEnabled,
            boolean isTwoPlayerEnabled, boolean isThreePlayerEnabled) {
        oneComputerPlayer.disableProperty().set(!isOnePlayerEnabled);
        oneComputerPlayer.visibleProperty().set(isOnePlayerEnabled);
        twoComputerPlayer.disableProperty().set(!isTwoPlayerEnabled);
        twoComputerPlayer.visibleProperty().set(isTwoPlayerEnabled);
        threeComputerPlayer.disableProperty().set(!isThreePlayerEnabled);
        threeComputerPlayer.visibleProperty().set(isThreePlayerEnabled);
        zeroComputerPlayer.setSelected(true);
    }
    
    private void setHumanToggles(boolean isTwoPlayerEnabled,
            boolean isThreePlayerEnabled, boolean isFourPlayerEnabled) {
        twoHumanPlayer.disableProperty().set(!isTwoPlayerEnabled);
        twoHumanPlayer.visibleProperty().set(isTwoPlayerEnabled);
        threeHumanPlayer.disableProperty().set(!isThreePlayerEnabled);
        threeHumanPlayer.visibleProperty().set(isThreePlayerEnabled);
        fourHumanPlayer.disableProperty().set(!isFourPlayerEnabled);
        fourHumanPlayer.visibleProperty().set(isFourPlayerEnabled);
        oneHumanPlayer.setDisable(false);
        oneHumanPlayer.setVisible(true);
        oneHumanPlayer.setSelected(true);
    }

    @Override
    public void changed(ObservableValue<? extends Object> observable, Object oldValue, Object newValue) {
        if (((RadioButton)newValue).toggleGroupProperty().get() == computerPlayersNumber)
            checkHumanToggleEnabling();
        else
            checkComputerToggleEnabling();
    }
    
    private int getNumOfHumanPlayers() {
        if (oneHumanPlayer.isSelected())
            return 1;
        else if (twoHumanPlayer.isSelected())
            return 2;
        else if (threeHumanPlayer.isSelected())
            return 3;
        else 
            return 4;
    }
    
    private int getNumOfComputerPlayers() {
        if (oneComputerPlayer.isSelected())
            return 1;
        else if (twoComputerPlayer.isSelected())
            return 2;
        else if (threeComputerPlayer.isSelected())
            return 3;
        else 
            return 0;
    }
    
    private void showError(String errorMsg) {
        new Thread(()->{
            Platform.runLater(()->{
                errorLabel.textProperty().set(errorMsg);
            });
            try {
                Thread.sleep(5000);
            } catch (Exception e) {
                System.out.println("error line show exception");
            }
            Platform.runLater(()->{
                errorLabel.textProperty().set("");
            });
            
        }).start();
    }
}
