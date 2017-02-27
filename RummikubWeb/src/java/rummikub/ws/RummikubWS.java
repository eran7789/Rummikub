/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rummikub.ws;

import engine.ComputerPlayer;
import engine.Game;
import engine.GameBoard;
import engine.GameOptions;
import engine.HumanPlayer;
import engine.Player;
import engine.PlayerBoard;
import engine.Sequence;
import engine.Tile;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import javax.jws.WebService;
import java.util.List;
import javax.jws.WebMethod;
import ws.rummikub.CreateGameResponse;
import ws.rummikub.DuplicateGameName_Exception;
import ws.rummikub.Event;
import ws.rummikub.GameDetails;
import ws.rummikub.GameDoesNotExists_Exception;
import ws.rummikub.InvalidParameters_Exception;
import ws.rummikub.InvalidXML_Exception;
import ws.rummikub.ObjectFactory;
import ws.rummikub.PlayerDetails;
import ws.rummikub.PlayerStatus;
import ws.rummikub.PlayerType;


 
@WebService(serviceName = "RummikubWebServiceService", portName = "RummikubWebServicePort", 
        endpointInterface = "ws.rummikub.RummikubWebService", targetNamespace = "http://rummikub.ws/", 
        wsdlLocation = "/conf/xml-resources/web-services/RummikubWS/wsdl/RummikubWebServiceService.wsdl")
public class RummikubWS {
    
    public enum ComputerPlayerNames {
        John,
        Paul,
        Ringo
    }
    
    private static int newPlayerId = 0;

    private List<Game> games = new ArrayList<>();
    private HashMap<Integer, Game> playerIdMap = new HashMap<>();
    private HashMap<Game, List<Event>> gameEventsMap = new HashMap<>();
    private final ExceptionThrower exceptionThrower = new ExceptionThrower();
    private final EventCreater eventCreater = new EventCreater();
    
    private HashMap<Game, GameBoard> gameBoardCopyMap = new HashMap<>();
    private HashMap<Integer, PlayerBoard> playerBoardCopyMap = new HashMap<>();
    
    public List<Event> getEvents(int playerId, int eventId) throws InvalidParameters_Exception {
        List<Event> eventsToReturn = new ArrayList<>();
        boolean isFoundAlready = false;
        Game rummikub = getGameByPlayerId(playerId);
        List<Event> gameEvents = getEventsByGame(rummikub);
        
        if (rummikub == null) {
            exceptionThrower.throwInvalidPlayerId(playerId);
        }
        if (gameEvents.size() < eventId || eventId < 0) {
            exceptionThrower.throwInvalidEventID(eventId);
        }
        
        // for the first call by tthe client
        if (eventId == 0) {
            return gameEvents;
        }
        
        for(Event event : gameEvents) {
            if (isFoundAlready) {
                eventsToReturn.add(event);
            }
            else if (event.getId() == eventId) {
                isFoundAlready = true;
            }
        }
        
        return eventsToReturn;
    }

    public String createGameFromXML(String xmlData) throws InvalidXML_Exception, 
            DuplicateGameName_Exception, InvalidParameters_Exception {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public List<PlayerDetails> getPlayersDetails(String gameName)
            throws GameDoesNotExists_Exception {
        
        Game game = getGameByName(gameName);
        if (game == null)
            exceptionThrower.throwGameNotExists(gameName);
        
        return getPlayersDetailsFromGame(game);
    }

    public void createGame(String name, int humanPlayers, int computerizedPlayers) 
            throws DuplicateGameName_Exception, InvalidParameters_Exception {
        final boolean notFromXml = false;
        
        for (Game game : games) {
            if (game.getGameName().equals(name)) {
                exceptionThrower.throwDuplicateGame(name);
            }
        }
        if (humanPlayers <= 0)
            exceptionThrower.throwNotEnoughHumanPlayer(humanPlayers);
        
        if (humanPlayers + computerizedPlayers < 2 || 
                humanPlayers + computerizedPlayers > 4) {
            exceptionThrower.throwInvalidPlayersNum(humanPlayers + computerizedPlayers);            
        }
        
        Game game = Game.createGame(name, humanPlayers, computerizedPlayers, notFromXml);
        for (int i = 0; i < computerizedPlayers; i++) {
            if (game.verifyName(ComputerPlayerNames.John.toString())) {
                game.addComputerPlayer(ComputerPlayerNames.John.toString(), newPlayerId);
            } else if (game.verifyName(ComputerPlayerNames.Paul.toString())) {
                game.addComputerPlayer(ComputerPlayerNames.Paul.toString(), newPlayerId);
            } else {
                game.addComputerPlayer(ComputerPlayerNames.Ringo.toString(), newPlayerId);
            }
            playerIdMap.put(newPlayerId++, game);
        }
        games.add(game);
        gameEventsMap.put(game, new ArrayList<>());
        gameBoardCopyMap.put(game, new GameBoard());
    }

    public GameDetails getGameDetails(java.lang.String gameName) 
            throws GameDoesNotExists_Exception {
        Game game = getGameByName(gameName);
        if (game != null)
            return gameDetails(game);
        else{
            exceptionThrower.throwGameNotExists(gameName);
            return null;
        }

    }
    
    
    
    public List<String> getWaitingGames() {
        List<String> gameNames = new ArrayList<>();
        
        for (Game game : games) {
            if (game.getStatus() == Game.GameStatus.Wait)
                gameNames.add(game.getGameName());
        }
        
        return gameNames;
    }

    public int joinGame(String gameName, String playerName)
            throws InvalidParameters_Exception, GameDoesNotExists_Exception {
        Game game = getGameByName(gameName);
        
        if (game == null || game.getStatus() != Game.GameStatus.Wait) 
            exceptionThrower.throwGameNotExists(gameName);
        if (!game.isCreatedFromXml()) {
            if (!game.verifyName(playerName)) {
                exceptionThrower.throwInvalidPlayerName(playerName);
            }
        } else {
            if (game.verifyName(playerName)) {
                exceptionThrower.throwInvalidPlayerName(playerName);
            }
            for (Player player : game.getPlayers()) {
                if (player.getName().equals(playerName)) {
                    if (player instanceof ComputerPlayer) {
                        exceptionThrower.throwInvalidPlayerName(playerName);
                    }
                }
            }
        }
        
        game.addHumanPlayer(playerName, newPlayerId);
        playerIdMap.put(newPlayerId, game);
        playerBoardCopyMap.put(newPlayerId, new PlayerBoard(
                getPlayerByPlayerId(newPlayerId).getPlayerBoard()));
        if (game.getNumberOfPlayers() == game.getNumOfActivePlayers()) {
            game.setStatus(Game.GameStatus.Active);
            getEventsByGame(game).add(eventCreater.createGameStartedEvent(0));
            getEventsByGame(game).add(eventCreater.createPlayerTurn(1, game.getCurrentPlayer().getName()));
            gameBoardCopyMap.put(game, new GameBoard(game.getGameBoard()));
            if (game.getCurrentPlayer() instanceof ComputerPlayer)
                playComputerTurn(game.getCurrentPlayer().getPlayerID());
        }
        return newPlayerId++;
    }

    public PlayerDetails getPlayerDetails(int playerId) 
            throws GameDoesNotExists_Exception, InvalidParameters_Exception {
        Game game = getGameByPlayerId(playerId);
        PlayerDetails details = null;
        if (game != null){
            Player player = getPlayerByPlayerId(playerId);
            details = getDetailsByPlayer(player, player.getPlayerBoard().tiles());
        } else {
            exceptionThrower.throwInvalidPlayerId(playerId);
        }
        return details;
    }

    public void createSequence(int playerId, List<ws.rummikub.Tile> tiles) 
            throws InvalidParameters_Exception {
        Game game = getGameByPlayerId(playerId);
        int eventID = getEventsByGame(game).size();
        Tile[] tilesArray = new Tile[tiles.size()];
        int i = 0;
        String playerName = getPlayerNameByID(playerId);
        
        for (ws.rummikub.Tile tile : tiles) {
            tilesArray[i] = createTileFromGenerated(tile);
            i++;
        }
        
        game.createSequences(playerId, tilesArray);
        getEventsByGame(game).add(eventCreater.createSequenceCreated
                                                (eventID, playerName, tiles));
        
    }

    public void addTile(int playerId, ws.rummikub.Tile tile, int sequenceIndex, int sequencePosition)
            throws InvalidParameters_Exception {
        Game game = getGameByPlayerId(playerId);
        Tile newTile = createTileFromGenerated(tile);
        int eventID = getEventsByGame(game).size();
        String playerName = getPlayerNameByID(playerId);
        
        try {
            game.addTile(playerId, newTile, sequenceIndex, sequencePosition);
            getPlayerByPlayerId(playerId).removeTile(newTile);
            getEventsByGame(game).add(eventCreater.createTileAdded
                (eventID, playerName, tile, sequenceIndex, sequencePosition));
        } catch (Exception e) {
            if (sequenceIndex >= game.getGameBoard().size() || sequenceIndex < 0)
                exceptionThrower.throwInvalidSequenceIndex(sequenceIndex);
            if (sequencePosition > game.getGameBoard().getSequenceSize(sequencePosition) ||
                    sequencePosition < 0)
                exceptionThrower.throwInvalidSequencePosition(sequenceIndex);
        }
    }

    
    public void takeBackTile(int playerId, int sequenceIndex, int sequencePosition)
            throws InvalidParameters_Exception {
        try {
            getGameByPlayerId(playerId).takeBackTile(playerId, sequenceIndex, sequencePosition);
        } catch (IndexOutOfBoundsException e) {
            if (e.getMessage().equals("sequence index"))
                exceptionThrower.throwInvalidSequenceIndex(sequenceIndex);
            else if (e.getMessage().equals("sequence position"))
                exceptionThrower.throwInvalidSequencePosition(sequencePosition);
        }
    }

    public void moveTile(int playerId, int sourceSequenceIndex, 
            int sourceSequencePosition, int targetSequenceIndex, int targetSequencePosition)
            throws InvalidParameters_Exception {
        
        Game game = getGameByPlayerId(playerId);
        GameBoard gameBoardCopy = getGameBoardCopy(game);
        int eventID = getEventsByGame(game).size();
        String playerName = getPlayerNameByID(playerId);
        
        if (game == null) 
            exceptionThrower.throwInvalidPlayerId(playerId);
        try {
            game.moveTile(playerId, sourceSequenceIndex, sourceSequencePosition, 
                    targetSequenceIndex, targetSequencePosition, gameBoardCopy);
        } catch (IndexOutOfBoundsException e) {
            if (e.getMessage().equals("source sequence index"))
                exceptionThrower.throwInvalidSequenceIndex(sourceSequenceIndex);
            else if (e.getMessage().equals("target sequence index"))
                exceptionThrower.throwInvalidSequenceIndex(targetSequenceIndex);
            else if (e.getMessage().equals("source sequence position"))
                exceptionThrower.throwInvalidSequencePosition(sourceSequencePosition);
            else if (e.getMessage().equals("target sequence position"))
                exceptionThrower.throwInvalidSequencePosition(targetSequencePosition);
        }
        getEventsByGame(game).add(eventCreater.createTileMoved
            (eventID, playerName, sourceSequenceIndex, sourceSequencePosition, 
                    targetSequenceIndex, targetSequencePosition));
    }

    public void finishTurn(int playerId) throws InvalidParameters_Exception, GameDoesNotExists_Exception {
        Game game = getGameByPlayerId(playerId);
        int eventID = getEventsByGame(game).size();
        String playerName = getPlayerNameByID(playerId);
        GameBoard gameBoardCopy = getGameBoardCopy(game);
        
        
        boolean isFirstTurn = getPlayerDetails(playerId).isPlayedFirstSequence();
        
        if (!game.getGameBoard().isValidChangeInBoard(isFirstTurn, gameBoardCopy))
        {
            cancelMoves(game, playerId);
            getEventsByGame(game).add(eventCreater.createRevert(eventID, playerName));
            for (int i=0; i < 3; i++)
                game.dealCardToPlayer(playerId);
        }
        else if(game.getGameBoard().sum() == gameBoardCopy.sum())
        {
            game.dealCardToPlayer(playerId);
        }
        else
        {
            if (game.gameIsOver(gameBoardCopy.sum()))
            {
                String winnerName = "";
                game.finishGame();
                for (Player player : game.getPlayers()) {
                    if (player.hasWon())
                        winnerName = player.getName();
                }
                getEventsByGame(game).add(eventCreater.createGameWinner(eventID, winnerName));
                getEventsByGame(game).add(eventCreater.createGameOver(++eventID));
            } else {
                gameBoardCopyMap.put(game, new GameBoard(game.getGameBoard()));
                game.setGameBoard(new GameBoard());
                if(isFirstTurn)
                {
                    getPlayerByPlayerId(playerId).setFirstTurnFlagOff();
                }
            }
        }
        
        getEventsByGame(game).add(
                eventCreater.createPlayerFinishedTurn(eventID, playerName));
        game.moveToNextPlayer();
        getEventsByGame(game).add(
                eventCreater.createPlayerTurn(
                        getEventsByGame(game).size(), game.getCurrentPlayer().getName()));
        if (game.getCurrentPlayer() instanceof ComputerPlayer)
            playComputerTurn(playerId);
    }

    public void resign(int playerId) throws InvalidParameters_Exception {
        if (getPlayerByPlayerId(playerId) == null)
            exceptionThrower.throwInvalidPlayerId(playerId);
        Game game = getGameByPlayerId(playerId);
        List<Event> events = getEventsByGame(game);
        int eventID = events.size();
        
        game.resign(playerId);
        events.add(eventCreater.createPlayerResigned(eventID++, getPlayerNameByID(playerId)));
        if (!game.isGameActive()) {
            String winnerName = "";
            events.add(eventCreater.createGameOver(eventID++));
            for (Player player : game.getPlayers()) {
                if (player.hasWon())
                    winnerName = player.getName();
            }
            getEventsByGame(game).add(eventCreater.createGameWinner(++eventID, winnerName));
        }
    }
    
    private boolean isValidPlayerID(int playerID) throws InvalidParameters_Exception{
        Game rummikub = getGameByPlayerId(playerID);
        
        for (Player player : rummikub.getPlayers()) {
            if (player.getPlayerID() == playerID) {
                return true;
            }
        }
        
        return false;
    }
    
    
    
    private Game getGameByPlayerId(int playerId) throws InvalidParameters_Exception{
        if (!playerIdMap.containsKey(playerId))
            exceptionThrower.throwInvalidPlayerId(playerId);
        return playerIdMap.get(playerId);
    }
    
    private List<Event> getEventsByGame(Game game) {
        if (!gameEventsMap.containsKey(game))
            return null;
        return gameEventsMap.get(game);
    }
    
    private Game getGameByName(String gameName) {
        for (Game game : games) {
            if (game.getGameName().equals(gameName))
                return game;
        }
        return null;
    }
    
    private Tile createTileFromGenerated(ws.rummikub.Tile genTile) 
            throws InvalidParameters_Exception {
        Tile.Colors color = Tile.Colors.BLACK;
        int value = genTile.getValue();

        if (genTile.getColor() == ws.rummikub.Color.BLACK)
            color = Tile.Colors.BLACK;
        else if (genTile.getColor() == ws.rummikub.Color.BLUE) 
            color = Tile.Colors.BLUE;
        else if (genTile.getColor() == ws.rummikub.Color.RED)
            color = Tile.Colors.RED;
        else if (genTile.getColor() == ws.rummikub.Color.YELLOW)
            color = Tile.Colors.YELLOW;
        else 
            exceptionThrower.throwInvalidColor(genTile.getColor());

        if (value < 0 || value > 13)
            exceptionThrower.throwInvalidTileValue(value);
        
        return new Tile(color, value);
    }
    
    private String getPlayerNameByID(int playerID) throws InvalidParameters_Exception{
        Game game = getGameByPlayerId(playerID);
        for (Player player : game.getPlayers()) {
            if (player.getPlayerID() == playerID) {
                return player.getName();
            }
        }
        return "";
    }
    
    private GameDetails gameDetails(Game game){
        
        GameDetails details = new GameDetails();
        
        details.setComputerizedPlayers(game.getNumOfComputerPlayer());
        details.setHumanPlayers(game.getNumOfHumanPlayer());
        details.setJoinedHumanPlayers(game.getNumOfHumansAdded());
        details.setStatus(genereteGameStatus(game.getStatus()));
        details.setName(game.getGameName());
        
        return details;
    }
    
    private List<PlayerDetails> getPlayersDetailsFromGame(Game game){
        
        List<PlayerDetails> playersDetails = new ArrayList<>();
        
        for (Player player : game.getPlayers()){
            PlayerDetails details = new PlayerDetails(); 
            details = getDetailsByPlayer(player, null);
            playersDetails.add(details);
        }

        return playersDetails;
    }
    
    private PlayerDetails getDetailsByPlayer(Player player, List<engine.Tile> tiles){
        PlayerDetails details = new PlayerDetails();
            
            details.setName(player.getName());
            
            if (player instanceof HumanPlayer)
                details.setType(PlayerType.HUMAN);
            else
                details.setType(PlayerType.COMPUTER);
            
            if(player.isFirstTurn())
                details.setPlayedFirstSequence(true);
            else
                details.setPlayedFirstSequence(false);
            
            setPlayerBoardDetails(details, tiles);
            details.setNumberOfTiles(player.getNumOfTiles());
            details.setStatus(PlayerStatus.ACTIVE);
        
        return details;
    }
    
    private Player getPlayerByPlayerId(int playerId) 
            throws InvalidParameters_Exception{
        int currentPlayerID;
        for (Player player: getGameByPlayerId(playerId).getPlayers()) {
            currentPlayerID = player.getPlayerID();
            if (currentPlayerID == playerId)
                return player;
        }
        return null;
    }
    
    private void setPlayerBoardDetails(PlayerDetails details,List<engine.Tile> tiles){
        if (tiles != null){
           for (engine.Tile tile: tiles)
               details.getTiles().add(generateTile(tile));
        }
    }
    
    private ws.rummikub.Tile generateTile(engine.Tile tile){
        ws.rummikub.Tile gTile = new ws.rummikub.Tile();
        
        gTile.setValue(tile.getNum());
        gTile.setColor(genereteColor(tile.getColor()));
        
        return gTile;
    }
    
    private ws.rummikub.Color genereteColor(engine.Tile.Colors color){
        if (color == engine.Tile.Colors.RED)
            return ws.rummikub.Color.RED;
        else if (color == engine.Tile.Colors.BLACK)
            return ws.rummikub.Color.BLACK;
        else if (color == engine.Tile.Colors.BLUE)
            return ws.rummikub.Color.BLUE;
        else
            return ws.rummikub.Color.YELLOW;
    }
    
    private ws.rummikub.GameStatus genereteGameStatus(engine.Game.GameStatus status){
        if (status == engine.Game.GameStatus.Active)
            return ws.rummikub.GameStatus.ACTIVE;
        else if (status == engine.Game.GameStatus.Done)
            return ws.rummikub.GameStatus.FINISHED;
        
        else return ws.rummikub.GameStatus.WAITING;
    }
    
    private GameBoard getGameBoardCopy(Game game) {
        return gameBoardCopyMap.get(game);
    }
    
    private void cancelMoves(Game game, int playerID) {
        PlayerBoard boardCopy = playerBoardCopyMap.get(playerID);
        game.getCurrentPlayer().changeBoardToThis(new PlayerBoard(boardCopy));
        playerBoardCopyMap.put(playerID, new PlayerBoard(game.getCurrentPlayer().getPlayerBoard()));
        game.setGameBoard(new GameBoard(getGameBoardCopy(game)));
    }
    
    private void playComputerTurn(int playerID) throws InvalidParameters_Exception, GameDoesNotExists_Exception {
        GameOptions.SequencesSet set;
        Game game = getGameByPlayerId(playerID);
        Player currentPlayer = game.getCurrentPlayer();
        
        currentPlayer.getPlayerBoard().sortByColor();
        currentPlayer.getPlayerBoard().sortByNumber();

        set = getBestSequence(currentPlayer.getPlayerBoard());

        if (set != null) {
            if (currentPlayer.isFirstTurn()) {
                if (set.sum() > 30) {
                    createSequencesEvents(game, set.sequences());
                    currentPlayer.getPlayerBoard().removeSequences(set.sequences());
                    currentPlayer.setFirstTurnFlagOff();
                }
            } else {
                createSequencesEvents(game, set.sequences());
                currentPlayer.getPlayerBoard().removeSequences(set.sequences());
            }
        }
        finishTurn(game.getCurrentPlayer().getPlayerID());
    }
    
    private GameOptions.SequencesSet getBestSequence(PlayerBoard playerBoard) {

        GameOptions options = new engine.GameOptions(playerBoard);

        GameOptions.SequencesSet set = options.getBestMove();
        return set;

    }

    private void createSequencesEvents(Game game, ArrayList<Sequence> sequences) {
        List<ws.rummikub.Tile> tilesInSequence;
        Tile[] tilesForGameUse;
        int i = 0;
        
        for (Sequence seq : sequences) {
            tilesInSequence = new ArrayList<>();
            tilesForGameUse = new Tile[seq.size()];
            i = 0;
            for (Tile tile : seq.tiles()) {
                tilesInSequence.add(generateTile(tile));
                tilesForGameUse[i] = tile;
                i++;
            }
            game.createSequences(newPlayerId, tilesForGameUse);
            getEventsByGame(game).add(eventCreater.createSequenceCreated
                (getEventsByGame(game).size(), game.getCurrentPlayer().getName(), tilesInSequence));
        }
    }
}
