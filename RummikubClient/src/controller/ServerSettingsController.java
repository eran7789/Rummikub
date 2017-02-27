/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import engine.Game;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Eran Keren
 */
public class ServerSettingsController implements Initializable, Controller {
    @FXML
    private TextField addressTextField;
    @FXML
    private TextField portTextField;
    @FXML
    private Label errorLine;
    
    
    private RummikubApp app;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void confirm(ActionEvent event) {
        app.setAddress(addressTextField.textProperty().get());
        app.setPort(portTextField.textProperty().get());
        try {
            app.createWSClient();
            app.loadMainMenu();
        } catch (Exception e) {
            showErrorLine();
        }
    }

    @FXML
    private void cancel(ActionEvent event) {
        app.loadMainGameScreen();
    }

    @Override
    public void setApp(RummikubApp app) {
        this.app = app;
    }

    @Override
    public void initGame() {}
    
    private void showErrorLine() {
        new Thread(()->{
            Platform.runLater(()->{
                errorLine.visibleProperty().set(true);
            });
            try {
                Thread.sleep(2500);
            } catch (Exception e) {}
            Platform.runLater(()->{
                errorLine.visibleProperty().set(false);
            });
        }).start();
    }
}
