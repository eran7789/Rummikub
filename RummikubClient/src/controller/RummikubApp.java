/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import engine.Game;
import java.io.File;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import rummikub.client.ws.RummikubWebService;
import rummikub.client.ws.RummikubWebServiceService;

/**
 *
 * @author Eran Keren
 */
public class RummikubApp extends Application {
    
    public final static String FXMLResourcesFolder = "/ui/fxml/";
    public final static String ImagesResourcesFolder = "/resources/images/";
    public final static String Resources = "/resources/";
    public final static String ADDRESS = "address:";
    public final static String PORT = "port:";
    private final String RummikubWebString = "/RummikubWeb/RummikubWebServiceService";
    private final String ConfigurationFileName = "confg.txt";
    private final String mainMenuScreen = "MainMenu.fxml";
    private final String newGameScreen = "NewGameMenu.fxml";
    private final String mainGameScreen = "GameBoard.fxml";
    private final String instructionsScreen = "Instructions.fxml";
    private final String gameOverScreen = "GameOver.fxml";
    private final String joinGameScreen = "JoinGameMenu.fxml";
    private final String serverSettingsScreen = "ServerSettings.fxml";
    
    private String gameName;
    private int clientID;
    private Stage primaryStage;
    private Controller currentController;
    public boolean isNewGame;
    private RummikubWebService clientService;
    private RummikubWebServiceService service;
    private String port;
    private String address;
    private String winnerName;
    
    public static void main(String[] args) {
        launch();
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Rummikub Game");
        primaryStage.getIcons().add(new Image("/resources/images/Icon.png"));
        primaryStage.setResizable(false);
        if (getIpAndPort()) {
            try {
                createWSClient();
                loadMainMenu();
            } catch (Exception e) {
                loadServerSettingsScreen();
            }
        } else {
            loadServerSettingsScreen();
        }
    }
    
    public void loadMainMenu() {
        primaryStage.setHeight(500);
        primaryStage.setWidth(800);
        FXMLLoader loader = new FXMLLoader(getClass().getResource
            (FXMLResourcesFolder + mainMenuScreen));
        loadAndShow(loader);
    }
    
    public void loadNewGameMenu() {
        primaryStage.setHeight(300);
        primaryStage.setWidth(475);
        FXMLLoader loader = new FXMLLoader(getClass().getResource
            (FXMLResourcesFolder + newGameScreen));
        loadAndShow(loader);
    }
    
    public void loadMainGameScreen() {
        primaryStage.setHeight(600);
        primaryStage.setWidth(800);
        FXMLLoader loader = new FXMLLoader(getClass().getResource
            (FXMLResourcesFolder + mainGameScreen));
        loadAndShow(loader);
    }
    
    public void showInstructions() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource
            (FXMLResourcesFolder + instructionsScreen));
        Stage stage = new Stage();
        stage.setHeight(400);
        stage.setWidth(600);
        stage.getIcons().add(new Image("/resources/images/Icon.png"));
        try {   
            Parent root = (Parent) loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {}
        
    }
    
    public void loadServerSettingsScreen() {
        primaryStage.setHeight(250);
        primaryStage.setWidth(500);
        FXMLLoader loader = new FXMLLoader(getClass().getResource
            (FXMLResourcesFolder + serverSettingsScreen));
        loadAndShow(loader);
    }
    
    public void loadGameOverScreen() {
        primaryStage.setHeight(500);
        primaryStage.setWidth(800);
        FXMLLoader loader = new FXMLLoader(getClass().getResource
            (FXMLResourcesFolder + gameOverScreen));
        loadAndShow(loader);
    }
    
     public void loadJoinGameScreen() {
        primaryStage.setHeight(500);
        primaryStage.setWidth(800);
        FXMLLoader loader = new FXMLLoader(getClass().getResource
            (FXMLResourcesFolder + joinGameScreen));
        loadAndShow(loader);
    }
    
    private void loadAndShow(FXMLLoader loader) {
        try {
            Parent root = (Parent) loader.load();
            currentController = (Controller)loader.getController();
            currentController.setApp(this);
            currentController.initGame();
            Scene scene = new Scene(root);
            
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void setGameName(String gameName) {
        this.gameName = gameName;
    }
    
    public  String getGameName() {
        return gameName;
    }
    
    public Stage getStage() {
        return primaryStage;
    }
    
    public RummikubWebService getClientService() {
        return clientService;
    }
    
    public void createWSClient() 
            throws MalformedURLException {
        URL url = new URL("http://" + address + ":" + port + RummikubWebString);
        service = new RummikubWebServiceService(url);
        clientService = service.getRummikubWebServicePort();
    }
    
    private boolean getIpAndPort() {
        InputStream stream;
        stream = getClass().getResourceAsStream(Resources + ConfigurationFileName);
        String addressLine;
        String portLine;
        
        try (Scanner scanner = new Scanner(stream)) {
            addressLine = scanner.nextLine();
            portLine = scanner.nextLine();
        } catch (Exception e) {
            return false;
        }
        if (!addressLine.contains(ADDRESS) || !portLine.contains(PORT)) {
            return false;
        } else {
            address = addressLine.substring(addressLine.lastIndexOf(ADDRESS) + ADDRESS.length());
            address = address.trim();
            port = portLine.substring(portLine.lastIndexOf(PORT) + PORT.length());
            port = port.trim();
        }
        
        return true;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public void setPort(String port) {
        this.port = port;
    }
    
    public String getAddress() {
        return address;
    }
    
    public String getPort() {
        return port;
    }
    
    public void setClientID(int clientID) {
        this.clientID = clientID;
    }
    
    public int getClientID() {
        return clientID;
    }
    
    public String getWinnerName() {
        return winnerName;
    }
    
    public void setWinnerName(String name) {
        winnerName = name;
    }
}
