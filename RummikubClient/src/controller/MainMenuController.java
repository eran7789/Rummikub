
package controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import rummikub.client.ws.RummikubWebService;


public class MainMenuController implements Initializable, Controller {
    
    private RummikubApp application;
    
    @FXML
    private Button loadGameButton;    
    @FXML
    private Button newGameButton;
    @FXML
    private Label errorLabel;
    @FXML
    private Button joinGameButton;
    @FXML
    private Button serverSettingsButton;
    @FXML
    private Label addressLabel;
    @FXML
    private Label portLabel;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {}    

    @FXML
    private void serverSettings() {
        application.loadServerSettingsScreen();
    }
    
    @FXML
    private void newGame(ActionEvent event) {
        application.loadNewGameMenu();
    }

    @FXML
    private void loadGame(ActionEvent event) {
        new Thread(new Runnable() {

            @Override
            public void run() {
                Platform.runLater(new Runnable() {

                    @Override
                    public void run() {
                        FileChooser fileChooser = new FileChooser();
                        fileChooser.setTitle("Choose A File To Load From");
                        fileChooser.getExtensionFilters().add
                            (new FileChooser.ExtensionFilter
                                ("An XML Game Representation", "*.xml"));
                        fileChooser.setInitialDirectory(
                                new File((this.getClass().getResource("/resources/savedGames").getFile())));
                        File file = fileChooser.showOpenDialog(application.getStage());
                        try {
                            RummikubWebService server = application.getClientService();
                            application.setGameName(server.createGameFromXML(
                                    file.getAbsolutePath()));
                            application.isNewGame = false;
                            application.loadMainGameScreen();
                            printErrorMessage("Game Created! you can now join!");
                        } catch (Exception e) {
                            printErrorMessage("Invalid File! Please Make Sure You Choose Only Valid Files!");
                        }
                    }
                });
            }
        }).start();
    }

    @FXML
    private void quit(ActionEvent event) {
        Platform.exit();
    }
    
    @Override
    public void setApp (RummikubApp app) {
        application = app;
    }

    @Override
    public void initGame() {
        addressLabel.textProperty().set(application.getAddress());
        portLabel.textProperty().set(application.getPort());
    }
    
    private void printErrorMessage(String msg) {
        new Thread(new Runnable() {

            @Override
            public void run() {
                Platform.runLater(new Runnable() {

                    @Override
                    public void run() {
                        errorLabel.setText(msg);
                    }
                });
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(GameBoardController.class.getName()).log(Level.SEVERE, null, ex);
                } 
                 Platform.runLater(new Runnable() {

                    @Override
                    public void run() {
                        errorLabel.setText("");
                    }
                });
            }
        }).start();
    }

    @FXML
    private void joinGame(ActionEvent event) {
        application.loadJoinGameScreen();
    }
}
