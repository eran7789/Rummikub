package controller;

import engine.GameBoard;
import engine.PlayerBoard;
import engine.Sequence;
import engine.Tile;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import rummikub.client.ws.Event;
import rummikub.client.ws.EventType;
import rummikub.client.ws.GameDoesNotExists_Exception;
import rummikub.client.ws.InvalidParameters_Exception;
import rummikub.client.ws.PlayerDetails;
import rummikub.client.ws.RummikubWebService;
import ui.BoardGrid;
import ui.PlayerView;
import ui.TileView;


public class GameBoardController implements Initializable, Controller {


    
    
    private class Tilexy{
        int x;
        int y;
        
        
        public Tilexy(){}
        
        public int getX(){
            return x;
        }
        public int getY(){
            return y;
        }
        public void setX(int x){
            this.x = x;
        }
        
        public void setY(int y){
            this.y = y;
        }
  
    }
   
    private String gameName;
    
    private RummikubWebService server;
    private RummikubApp application;
    private Timer timer;
    private Timer turnTimer;
    
    public TileView currentlyDraggedTile;
    public Pane dragTargetPane;
    
    private BoardGrid boardGrid;
    private BoardGrid playerGrid;
    
    private GameBoard gameBoardCopy;
    private GameBoard newGameBoard;
    private PlayerBoard playerBoardCopy;
    private PlayerBoard playerBoard;
    private int numOfTilesInDeck;
    
    private String currentPlayerName;
    private int lastEventID;
    private boolean isMyTurn;
    private int myID;
    private String myName;
    private PlayerDetails myDetails;
    
    @FXML
    private HBox playersView;
    @FXML
    private Label ErrorLine;
    @FXML
    private Pane boardPane;
    @FXML
    private AnchorPane playerBoardPane;
    @FXML
    private Button deckButton;
    @FXML
    private Label waitingLabel;
    
 
// Controller Override functions:
//-------------------------------
    @Override
    public void initGame() {
        server = application.getClientService();
        myID = application.getClientID();
        gameName = application.getGameName();
        

        newGameBoard = new GameBoard();
        gameBoardCopy = new GameBoard(newGameBoard); 
        
        
        timer = new Timer();
        timer.scheduleAtFixedRate(new timerTask(), 1000, 2000);
    }
    
     
    public void startGame() {
        
        List<PlayerDetails> playersDetails;
        try {
            playersDetails = server.getPlayersDetails(gameName);
            setPlayersDetailsView(playersDetails);
            numOfTilesInDeck = numOfTilesInDeck();
            
        } catch (GameDoesNotExists_Exception ex) {
            System.out.println();
        }
        
        try {
            myDetails = server.getPlayerDetails(myID);
        } catch (GameDoesNotExists_Exception | InvalidParameters_Exception ex) {
            Logger.getLogger(GameBoardController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        
        playerBoard = generatePlayerBoard(myDetails.getTiles());
        playerBoardCopy = new PlayerBoard(playerBoard);
        myName = myDetails.getName();
        
        Platform.runLater(()->{
            updateCurrentPlayerView();
            setGameBoardView(newGameBoard);
            setPlayerBoardView();
            updateDeckButton();
        });
    }
    
    
    @Override
    public void setApp(RummikubApp app) {
        this.application = app;
        server = app.getClientService();
    }
    
    
  // initializebale Override functions:
  //-------------------------------
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    

    @Override
    protected void finalize() throws Throwable {
        timer.cancel();
        turnTimer.cancel();
        super.finalize();
    }
    
   
  // @FXML functions:
  //-----------------
    @FXML
    private void finishTurn(ActionEvent event) throws InvalidParameters_Exception{
        if (event == null)
            return;
        if (isMyTurn){
            TileView[][] matrix = boardGrid.getTileViewMatrix();
            GameBoard gameBoard  = matrixToBoard(matrix);
        
            createServerActions(gameBoard, gameBoardCopy);
            try {
                server.finishTurn(myID);
                if (gameBoard.sum() == gameBoardCopy.sum()) {
                    printErrorMessage("No Moves Occured. Got A Tile From Deck!");
                    numOfTilesInDeck--;
                    updateDeckButton();
                }
                myDetails = server.getPlayerDetails(myID);
            } catch (Exception e) {
                System.out.println(e.toString());}
        }
    }

    @FXML
    private void sortByGroups(ActionEvent event) {
        playerBoard.sortByNumber();
        playerGrid.getChildren().clear();
        setPlayerBoardView();
    }

    @FXML
    private void sortBySets(ActionEvent event) {
        playerBoard.sortByColor();
        playerGrid.getChildren().clear();
        setPlayerBoardView();
    }

    @FXML
    private void ShowInstructions(ActionEvent event) {
        application.showInstructions();
    }

    @FXML
    private void ExitGame(ActionEvent event) {
        application.loadMainMenu();
    }
    
    @FXML
    private void SaveGame(ActionEvent event) {
    }

    @FXML
    private void getATileFromDeck(ActionEvent event) throws InterruptedException {
        if (isMyTurn){
            
            try {
                finishTurn(new ActionEvent());
            } catch (Exception e) {}
            numOfTilesInDeck--;
            updateDeckButton();
            newGameBoard = new GameBoard(gameBoardCopy);
            boardGrid = new BoardGrid(newGameBoard, this);
            boardPane.getChildren().clear();
            boardPane.getChildren().add(boardGrid);
            
            playerBoard = generatePlayerBoard(myDetails.getTiles());
            setPlayerBoardView();
            setGameBoardView(newGameBoard);
        }
    }
    
    @FXML
    private void resetMoves(ActionEvent event) {
        playerBoard = new PlayerBoard(playerBoardCopy);
        setPlayerBoardView();
        application.isNewGame = false;
        newGameBoard = new GameBoard(gameBoardCopy);
        setGameBoardView(newGameBoard);
    }

    @FXML
    private void boardMenuButton(ActionEvent event) {
    }
    
    
    
// Local functions:
//-----------------
    private void printErrorMessage(String message) {
        new Thread(new Runnable() {

            @Override
            public void run() {
                Platform.runLater(new Runnable() {

                    @Override
                    public void run() {
                        ErrorLine.setText(message);
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
                        ErrorLine.setText("");
                    }
                });
            }
        }).start();
    }
    
    private void updateDeckButton() { 
        Platform.runLater(()->{
            deckButton.textProperty().set("Deck\n" +
                numOfTilesInDeck + " Left");
        });
    }
    
    private void updateCurrentPlayerView() {
        for (Node view : playersView.getChildren()) {
            PlayerView playerView = (PlayerView) view;
            if (playerView.getPlayerDetails().getName().equals(currentPlayerName)) {
                playerView.setCurrent();
            } else {
                playerView.setNotCurrentPlayer();
            }
            try {
                for (PlayerDetails details : server.getPlayersDetails(gameName)) {
                    if (details.getName().equals(playerView.getPlayerDetails().getName()))
                        playerView.updateNumOfTiles(details.getNumberOfTiles());
                }
            } catch (Exception e) {System.out.println(e);}
        }
    }
    
    public void completeDragOperation() {
        if (isDragFromPlayerToBoard()) {
            playerBoard.removeTile(currentlyDraggedTile.getTile());
        } else if (isDragFromBoardToPlayer()) {
            playerBoard.addTile(currentlyDraggedTile.getTile());
        }
        TileView targetTile = (TileView) dragTargetPane.getChildren().get(0);  
        targetTile.setTile(currentlyDraggedTile.getTile());
        targetTile.setBelongToPlayer(currentlyDraggedTile.belongsToPlayer());
        currentlyDraggedTile.makeEmpty();    
        updateCurrentPlayerView();
    }
    
    public boolean isValidDrag(TileView source, Pane target) {
        TileView targetTileView = (TileView) target.getChildren().get(0);
        if (source.isEmpty())
            return false;
        if (source == target.getChildren().get(0) ||
                    !targetTileView.isEmpty())
            return false;
        if (((TileView)target.getChildren().get(0)).belongsToPlayer()) {
            if (!source.belongsToPlayer())
                return false;
        }
        if (isDragFromPlayerToBoard() && !isMyTurn)
            return false;
        
        return true;
    }
    
    private boolean isDragFromPlayerToBoard() {
        boolean isSourceBelongToPlayer = 
                ((BoardGrid)currentlyDraggedTile.getParent().getParent()).isPlayer();
        boolean isTargetBelongToPlayer = 
                ((BoardGrid)dragTargetPane.getParent()).isPlayer();
        return isSourceBelongToPlayer && !isTargetBelongToPlayer;
    }
    
    
    private boolean isDragFromBoardToPlayer() {
        return !((BoardGrid)currentlyDraggedTile.getParent().getParent()).isPlayer() &&
                ((BoardGrid)dragTargetPane.getParent()).isPlayer();
    }
    
    private void setPlayersDetailsView(List<PlayerDetails> details){
        for (PlayerDetails playerDetails : details) {
            PlayerView view = new PlayerView(playerDetails);
            Platform.runLater(()->playersView.getChildren().add(view));
        }
    }
    
    private void setEmptyGameBoardView(){
        boardGrid = new BoardGrid(this);
        
        boardPane.getChildren().clear();
        boardPane.getChildren().add(boardGrid);
        boardPane.setLayoutX(50);
        boardPane.setLayoutY(85);
        boardGrid.gridLinesVisibleProperty().set(true);
    }
    
    private void setGameBoardView(GameBoard board) {
        boardGrid = new BoardGrid(board, this);
        
        boardPane.getChildren().clear();
        boardPane.getChildren().add(boardGrid);
        boardPane.setLayoutX(50);
        boardPane.setLayoutY(85);
        boardGrid.gridLinesVisibleProperty().set(true);
    }
    
    private void setPlayerBoardView(){
        PlayerBoard board = playerBoard;
        playerGrid = new BoardGrid(board, this);
        playerBoardPane.getChildren().clear();
        playerBoardPane.getChildren().add(playerGrid);
        boardGrid.gridLinesVisibleProperty().set(true);
    }
        
    private void moveToNextPlayer() {
        updateCurrentPlayerView();
    }
    
    private GameBoard matrixToBoard(TileView[][] matrix){
        GameBoard board = new GameBoard();
        Sequence seq = null;
        boolean building = false;
        
        for (int i = 0; i < 9; i++) {
            if(building)
            {
                board.addSequence(seq);
                building = false;
            }
            for (int j = 0; j < 20; j++) {
                
                if (!matrix[i][j].isEmpty() && !building ){
                    seq = new Sequence();
                    building = true;
                    seq.addTile(matrix[i][j].getTile());
                } else if (matrix[i][j].isEmpty() && building){
                    board.addSequence(seq);
                    building = false;
                } else if (!matrix[i][j].isEmpty() && building){
                    seq.addTile(matrix[i][j].getTile());
                }
            }  
        }
        return board;
    }
    
    private void lockMatrixBoard(TileView[][] matrix){
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 20; j++) {
                matrix[i][j].setBelongToPlayer(false);
            }
        }
    }
    
    private void cleanUnLockedTiles(TileView[][] matrix){
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 20; j++) {
                if (matrix[i][j].belongsToPlayer()){
                    matrix[i][j].makeEmpty();
                    matrix[i][j].setBelongToPlayer(false);
                }
                   
            }
        }
    }

    private void addTilesToMatrix(ArrayList<Sequence> sequences) {
        TileView[][] matrix = boardGrid.getTileViewMatrix();
        for (Sequence seq : sequences) {
            insertSeqToMatrix(seq.tiles(), matrix);
        }
        lockMatrixBoard(matrix);
    }

    private void insertSeqToMatrix(List<Tile> seq, TileView[][] matrix) {
        int size = seq.size();
        boolean needSpace = false;
        boolean spotIsGood;


        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 20 - size; j++) {
                if (matrix[i][j].isEmpty()) {
                    if (needSpace) {
                        j++;
                    }

                    spotIsGood = true;
                    for (int k = j; k < j + size && spotIsGood; k++) {
                        if (k == 20) {
                            spotIsGood = false;
                        } else if (!matrix[i][k].isEmpty()) {
                            spotIsGood = false;
                            j = k;
                        }
                    }
                    if (spotIsGood) {
                        for (Tile tile : seq) {
                            matrix[i][j].setTile(tile);
                            j++;
                        }
                        i = 9;
                        j = 20;
                    }
                } else {
                    needSpace = true;
                }
            }

        }
    }
    
    private Tile createTileFromGenerated(rummikub.client.ws.Tile genTile) {
        Tile.Colors color = Tile.Colors.BLACK;
        int value = genTile.getValue();

        if (genTile.getColor() == rummikub.client.ws.Color.BLACK)
            color = Tile.Colors.BLACK;
        else if (genTile.getColor() == rummikub.client.ws.Color.BLUE) 
            color = Tile.Colors.BLUE;
        else if (genTile.getColor() == rummikub.client.ws.Color.RED)
            color = Tile.Colors.RED;
        else if (genTile.getColor() == rummikub.client.ws.Color.YELLOW)
            color = Tile.Colors.YELLOW;
        
        return new Tile(color, value);
    }
    
    private int numOfTilesInDeck() throws GameDoesNotExists_Exception{
        List<PlayerDetails> details = server.getPlayersDetails(gameName);
        
        int numOfUsedTiles =0;
        for(PlayerDetails player: details){
            numOfUsedTiles += player.getNumberOfTiles();
        }
        numOfUsedTiles += newGameBoard.size();
        
        return 106 - numOfUsedTiles;
        
    }
    
    
    //################  Creating Server Events ################
    
    private void createServerActions(GameBoard gameBoard, GameBoard gameBoardCopy) 
            throws InvalidParameters_Exception {
        
        List<Sequence> board = gameBoard.sequences();
        List<Sequence> boardCopy = gameBoardCopy.sequences();

        createMoveTileEvents(board, boardCopy);
        createAddTileEvent(board, boardCopy);
    }
    
    private void createMoveTileEvents(List<Sequence> gameBoard, List<Sequence> gameBoardCopy)
            throws InvalidParameters_Exception {
        
        List<Tile> tiles;
        Tilexy copyTilexy, newTilexy;
        rummikub.client.ws.Tile  wsTile;
        
        int targetX = 0;
        int targetY = 0;

        for (Sequence seq: gameBoardCopy){
            tiles = seq.tiles();

            for(Tile tile: tiles)
            {
                copyTilexy = getTilexyFromBoard(tile, gameBoard);
                server.moveTile(myID, copyTilexy.getX(), copyTilexy.getY(), targetX, targetY); 
                targetY++;
            }
            targetX++;
        }
    }
    
    private void createAddTileEvent(List<Sequence> gameBoard, List<Sequence> gameBoardCopy)
            throws InvalidParameters_Exception{
        List<Tile> tiles;
        Tilexy copyTilexy, newTilexy;
        rummikub.client.ws.Tile  wsTile;
        
        
        int targetX = 0;
        int targetY = 0;

        for (Sequence seq: gameBoard){
            tiles = seq.tiles();

            for(Tile tile: tiles)
            {
                copyTilexy = getTilexyFromBoard(tile, gameBoardCopy);
                if (copyTilexy == null){
                    wsTile = generateTile(tile);
                    server.addTile(myID, wsTile, targetX, targetY);
                }
                targetY++;
            }
            targetX++;
        }
    }
    
    private Tilexy getTilexyFromBoard(Tile tile, List<Sequence> board){
        Tilexy txy = new Tilexy();
        
        int xBoard = 0;
        int yBoard = 0;
        List<Tile> tiles;
        
        for (Sequence seq: board){
            tiles = seq.tiles();
            for(Tile tile1: tiles)
            {
                if (tile1.isEqualTo(tile))
                {
                    txy.setX(xBoard);
                    txy.setY(yBoard);
                    return txy;
                }
                yBoard++;
            }
            xBoard++;
        }
        return null;
    }
    
    private rummikub.client.ws.Tile generateTile(engine.Tile tile){
        rummikub.client.ws.Tile gTile = new rummikub.client.ws.Tile();
        
        gTile.setValue(tile.getNum());
        gTile.setColor(genereteColor(tile.getColor()));
        
        return gTile;
    }
    
    private rummikub.client.ws.Color genereteColor(engine.Tile.Colors color){
        if (color == engine.Tile.Colors.RED)
            return rummikub.client.ws.Color.RED;
        else if (color == engine.Tile.Colors.BLACK)
            return rummikub.client.ws.Color.BLACK;
        else if (color == engine.Tile.Colors.BLUE)
            return rummikub.client.ws.Color.BLUE;
        else
            return rummikub.client.ws.Color.YELLOW;
    }
    //#########################################################
    
   private PlayerBoard generatePlayerBoard(List<rummikub.client.ws.Tile>  tiles){
        
        PlayerBoard newBoard = new PlayerBoard();
        engine.Tile newTile; 
                
        for (rummikub.client.ws.Tile tile : tiles){
            newTile = createTileFromGenerated(tile);
            newBoard.addTile(newTile);
        }
        
        return newBoard;
    }
    
    private Tile.Colors getTileColor(rummikub.client.ws.Tile tile) {
        rummikub.client.ws.Color tileColor = tile.getColor();
        Tile.Colors colorToReturn = Tile.Colors.BLACK;
        
        switch (tileColor) {
            case BLUE:
                colorToReturn = Tile.Colors.BLUE;
                break;
            case RED:
                colorToReturn = Tile.Colors.RED;
                break;
            case YELLOW:
                colorToReturn = Tile.Colors.YELLOW;
                break;
        }
        
        return colorToReturn;
    }
    
    private void getEventsFromServer() throws InvalidParameters_Exception, GameDoesNotExists_Exception{
        
        ArrayList<Event> events = (ArrayList) server.getEvents(myID, lastEventID);
        EventType type;
        for (Event event: events)
        {   
            type = event.getType();
            
            System.out.println(type.toString());
            switch (type){
                case GAME_START:
                {
                    currentPlayerName = event.getPlayerName();
                    startGame();
                    Platform.runLater(()->waitingLabel.setText(""));
                    break;
                }
                case GAME_OVER:
                {
                    application.loadGameOverScreen();
                    break;
                }
                case GAME_WINNER:
                {
                    application.setWinnerName(event.getPlayerName());
                    application.loadGameOverScreen();
                    break;
                }
                case PLAYER_TURN:
                {
                    currentPlayerName = event.getPlayerName();
                    Platform.runLater(()->{
                        updateCurrentPlayerView();
                        try {
                            numOfTilesInDeck = numOfTilesInDeck();
                        } catch (GameDoesNotExists_Exception ex) {
                            Logger.getLogger(GameBoardController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        if (event.getPlayerName().equals(myName)) {
                            isMyTurn = true;
                            playerBoard = generatePlayerBoard(myDetails.getTiles());
                            playerBoardCopy = new PlayerBoard(playerBoard);
                            setPlayerBoardView();
                            printErrorMessage("Your Turn Is Up!!!");
                        }
                        turnTimer = new Timer();
                        turnTimer.schedule(new turnTimerTask(), 90000);
                    });
                    break;
                }    
                case PLAYER_FINISHED_TURN:
                {
                    Platform.runLater(()->{
                        if (event.getPlayerName().equals(myName))
                            isMyTurn = false;
                        gameBoardCopy = new GameBoard(newGameBoard);
                        newGameBoard = new GameBoard();
                        setGameBoardView(gameBoardCopy);
                        playerBoard = generatePlayerBoard(myDetails.getTiles());
                        playerBoardCopy = new PlayerBoard(playerBoard);
                        setPlayerBoardView();
                    });
                    break;
                }
                case PLAYER_RESIGNED:
                {
                    List<PlayerDetails> playersDetails = server.getPlayersDetails(gameName);
                        Platform.runLater(()->{
                        setPlayersDetailsView(playersDetails);
                        updateCurrentPlayerView();
                    });
                    break;
                }
                case SEQUENCE_CREATED:
                {
                    Platform.runLater(()-> {
                        List<Tile> tiles = new ArrayList<>();
                        for (rummikub.client.ws.Tile tile : event.getTiles()) {
                            tiles.add(createTileFromGenerated(tile));
                        }
                        insertSeqToMatrix(tiles, boardGrid.getTileViewMatrix());
                        newGameBoard.addSequence(new Sequence(tiles));
                    });
                    break;
                }
                case TILE_ADDED:
                {
                    Platform.runLater(()->{handleAddTileEvent(event);});
                    break;
                }
                case TILE_RETURNED:
                {
                    ///
                    break;
                }
                case TILE_MOVED:
                {
                    Platform.runLater(()->{handleMoveTileEvent(event);});
                    break;
                }
                case REVERT:
                {
                    newGameBoard = new GameBoard(gameBoardCopy);
                    if (event.getPlayerName().equals(myName))
                        printErrorMessage("Invalid changes in the board! You got 3 cards penalty!");
                    else
                        printErrorMessage("Previous player got 3 cards! ");
                    Platform.runLater(()->{setGameBoardView(newGameBoard);});
                    try {
                        myDetails = server.getPlayerDetails(myID);
                    } catch (Exception e) {System.out.println(e);}
                    Platform.runLater(()->{setPlayerBoardView();});
                    break;
                }
            }
            lastEventID++;
            try {
                Thread.sleep(1000);
            } catch (Exception e) {System.out.println(e);}
        }
    }
    
    
    private void handleAddTileEvent(Event event){
        
        rummikub.client.ws.Tile wsTile = event.getTiles().get(0);
        engine.Tile tile = createTileFromGenerated(wsTile);
        
        int tileX = event.getTargetSequenceIndex();
        int tileY = event.getTargetSequencePosition();
        
        
        Sequence seq;
        
        if (tileX >= newGameBoard.size()) {
            seq = new Sequence();
            seq.addTile(tile);
            newGameBoard.addSequence(seq);
            return;
        }
        else
            newGameBoard.addTileToSequence(tile, tileX, tileY);
    }
    
    private void handleMoveTileEvent(Event event){
        int sourceX = event.getSourceSequenceIndex();
        int sourceY = event.getSourceSequencePosition();
        int targetX = event.getTargetSequenceIndex();
        int targetY = event.getTargetSequencePosition();
        
        engine.Tile tile = gameBoardCopy.getTile(sourceX, sourceY);
        if (targetX >= newGameBoard.size())
        { 
            newGameBoard.addLines(targetX - newGameBoard.size());
        }
        newGameBoard.addTileToSequence(tile,targetX,targetY);
    }
    
    public class timerTask extends TimerTask {
        @Override
        public void run() {
            try {
                getEventsFromServer();
            } catch (Exception e) {}
        }
        
    }
    
    public class turnTimerTask extends TimerTask {
        @Override
        public void run() {
            try {
                if(isMyTurn)
                    server.resign(myID);
            } catch (Exception e) {}
        }
        
    }
}

