package engine;

import engine.xml.RummikubGameException;
import engine.xml.XMLGameLoader;
import engine.xml.XMLGameSaver;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.xml.bind.JAXBException;

public class Game {
    
    public static final int START_OF_INDEX = 0;
    public enum GameStatus {
        Wait,
        Active,
        Done
    }
    
    int numOfHumanPlayers;
    int numOfComputerPlayers;
    String gameName;
    List <Player> players;
    GameBoard gameBoard;
    Deck deck;
    GameStatus status;
    int turnsWithNoMove;
    int currentPlayerindex;
    boolean isFromXml;
    
    
    private Game(String gamename, int humanplayers, int computerplayers)
    {
        gameName = gamename;
        status = GameStatus.Wait;
        numOfComputerPlayers = computerplayers;
        numOfHumanPlayers = humanplayers;
        players = new ArrayList<>();
        currentPlayerindex = 0;
        gameBoard = new GameBoard ();
        deck = new Deck();
        turnsWithNoMove = 0;
    }
    
    
    public static Game createGame(String gamename, int humanplayers, 
            int computerplayers, boolean isFromXml) {
        Game newGame = new Game(gamename,humanplayers,computerplayers); 
        newGame.isFromXml = isFromXml;
        return newGame;
    }
    
    private List<Tile> dillFourteenTiles(){
        
        List<Tile> fourteenTilesForPlayer= new ArrayList<>();
        
        for(int i=0 ; i<14 ;i++)
            fourteenTilesForPlayer.add(deck.getTile());
        
        return fourteenTilesForPlayer;
    }
    
    public GameStatus getStatus() {
        return status;
    }
    
    public boolean isCreatedFromXml() {
        return isFromXml;
    }
    
    public void setStatus(GameStatus state) {
        status = state;
    }
    
    public void addHumanPlayer(String newPlayerName, int playerID){
        PlayerBoard newboard= new PlayerBoard(dillFourteenTiles());
        Player player = new HumanPlayer(newPlayerName, this, newboard);
        player.setPlayerID(playerID);
        players.add(player);
        checkAndUpdateStatus();
    }
    
    public void addPlayer (Player player) {
        players.add(player);
        player.setPlayerBoard(new PlayerBoard(dillFourteenTiles()));
        checkAndUpdateStatus();
    }
    
    public void setGameName (String name) {
        this.gameName = name;
    }

    public int getNumOfActivePlayers() {
        return players.size();
    }
    
    public void setCurrentPlayer (String name) {
        for (int i=0; i<players.size(); i++) {
            if (players.get(i).getName().equals(name)) {
                currentPlayerindex = i;
                return;
            }
        }
    }
    
    public void setDeck (Deck deck) {
        this.deck = deck;
    }
    
    public int getNumOfTilesInDeck() {
        return deck.getNumOfTiles();
    }
    
    public List<Tile> getDealedTilesList () {
        List<Tile> tilesList = new ArrayList<>();
        
        for (int i=0; i<gameBoard.size(); i++) {
            Sequence sequence = gameBoard.getSequence(i);
            for (int j=0; j<sequence.size(); j++) {
                tilesList.add(sequence.getTileByIndex(j));
            }
        }
        
        for (Player player : players) {
            PlayerBoard board = player.getPlayerBoard();
            for (int i=0; i<board.size(); i++) {
                tilesList.add(board.getTile(i));
            }
        }
        
        return tilesList;
    }
    
    public boolean verifyName (String name) {
        for (Player player : players) {
            if (player.getName().equals(name))
                return false;
        }
        return true;
    }
    
    public void addComputerPlayer(String newPlayerName, int playerID){
        PlayerBoard board = new PlayerBoard(dillFourteenTiles());
        Player player = new ComputerPlayer(newPlayerName, this, board);
        player.setPlayerID(playerID);
        players.add(player);
        checkAndUpdateStatus();
    }
    
    public boolean isGameActive()
    {
        return status == GameStatus.Active;
    }
    
    public void finishGame()
    {
        status = GameStatus.Done;
    }
    
    public int getNumberOfPlayers()
    {
        return numOfComputerPlayers + numOfHumanPlayers;
    }
    
    public void moveToNextPlayer()
    {
        currentPlayerindex = (currentPlayerindex + 1) % getNumberOfPlayers();
    }
    
    public Player getCurrentPlayer()
    {
        return players.get(currentPlayerindex);
    }
    
    public GameBoard getGameBoard() {
        return gameBoard;
    }
 
    public void dealCardToPlayer (int PlayerID) {
        Player player = findPlayerByID(PlayerID);
        if (player != null && !deck.isEmpty()) {
            player.addTile(deck.getTile());
        }
    }

    public void saveGame(String fileName) {
        try {
            XMLGameSaver saver = new XMLGameSaver();
            saver.saveGame(fileName, this);
        } catch  (JAXBException | IOException e) {
            System.out.println("Save failed!");
        }
        
    }
    
    public void createSequences (int playerID, Tile[] tiles) 
            throws IncompatibleClassChangeError {
        Sequence sequence = new Sequence(tiles);        
        if (getCurrentPlayer().removeTiles(tiles))
            gameBoard.addSequence(sequence);
        else 
            throw new IncompatibleClassChangeError();
    }
    
    public void takeBackTile(int playerId, int sequenceIndex, int sequencePosition) {
        if (sequenceIndex < 0 || sequenceIndex >= gameBoard.size()) {
            throw new IndexOutOfBoundsException("sequence index");
        }
        if (sequencePosition < 0 || 
                sequencePosition >= gameBoard.getSequenceSize(sequenceIndex)) {
            throw new IndexOutOfBoundsException("sequence position");
        }
        
        Tile tileToTake = 
                gameBoard.getSequence(sequenceIndex).getTileByIndex(sequencePosition);
        
        getCurrentPlayer().addTile(tileToTake);
        gameBoard.removeTileFromSequence(tileToTake, sequenceIndex);
    }
    
    public String getGameName () {
        return gameName;
    } 
    
    public List<Player> getPlayers () {
        return players;
    }
    
    public void addTile (int playerID, Tile tile, int sequenceIndex, int sequencePosition) 
                throws IndexOutOfBoundsException {
        Sequence seq;
        
        if (!isValidSequenceIndex(sequenceIndex)) {
            seq = new Sequence();
            seq.addTile(tile);
            gameBoard.addSequence(seq);
        }
        else
            gameBoard.addTileToSequence(tile, sequenceIndex, sequencePosition);   
    }    
    
    public void moveTile (int playerID, 
        int sourceSequenceIndex, int sourceSequencePosition,
        int targetSequenceIndex, int targetSequencePosition, GameBoard boardCopy) 
            throws IndexOutOfBoundsException {
        
        Tile tile = new Tile (boardCopy.getTile(sourceSequenceIndex, sourceSequencePosition));
        if (targetSequenceIndex >= gameBoard.size())
        { 
            gameBoard.addLines(targetSequenceIndex - gameBoard.size());
        }
 
        gameBoard.addTileToSequence(tile, targetSequenceIndex, targetSequencePosition);
    }

    
    public Player findPlayerByID (int playerID) {
        for (Player player : players) {
            if (player.getPlayerID() == playerID)
                return player;
        }
        return null;
    }
    
    private boolean isValidSequenceIndex (int index){

            return ( index >= gameBoard.size());
    }
    
    public void setGameBoard (GameBoard board) {
        this.gameBoard = board;
    }
    
    public void validate () throws RummikubGameException{
        if (!gameBoard.isValidBoard())
            throw new RummikubGameException("game board is not a valid board!");
        for (Player player : players) {
            if (player.hasWon())
                throw new RummikubGameException("the game is finished already!");
        }
        if (deck.isEmpty())
            throw new RummikubGameException("there are too many tiles!");
    }
    
    public static Game createGameFromXML(String XmlData) 
            throws JAXBException, NullPointerException, RummikubGameException{
        XMLGameLoader loader = new XMLGameLoader();
        return loader.loadGame(XmlData);
    }
    
    public boolean gameIsOver (int boardsum){
        
        if (!deck.isEmpty() || gameBoard.sum() - boardsum != 0)
            turnsWithNoMove=0;
        
        if (deck.isEmpty() && gameBoard.sum() - boardsum == 0)
            turnsWithNoMove++;
        
        if (getCurrentPlayer().hasWon() || turnsWithNoMove == getNumberOfPlayers()){
            Collections.sort(players);
            return true;
        }
        return false;
    }
    
    public void resign(int playerID) {
        Player player = getPlayerById(playerID);
        
        deck.addTiles(player.getTiles());
        players.remove(player);
        if (getCurrentPlayer() == player) {
            moveToNextPlayer();
        }
        if (players.size() < 2) {
            finishGame();
        }
    }
    
    public int currentPlayerIndex(){
        return currentPlayerindex;
    }
    
    public List <Player> players(){
        return players;
    }
    
    
    public int getNumOfHumanPlayer()
    {
        return numOfHumanPlayers;
    }
    
    public int getNumOfComputerPlayer()
    {
        return numOfComputerPlayers;
    }
    
    public int getNumOfHumansAdded()
    {
        return players.size() - numOfComputerPlayers;
    }
    
    private Player getPlayerById(int playerID) {
        for (Player player : players) {
            if (player.getPlayerID() == playerID) {
                return player;
            }
        }
        return null;
    }
    
    private void checkAndUpdateStatus() {
        if (players.size() == numOfComputerPlayers + numOfHumanPlayers) {
            status = GameStatus.Active;
        }
    }

    @Override
    public int hashCode() {
        return gameName.hashCode();
    }
}
    

