/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine.xml;

import engine.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author Eran Keren
 */
public class XMLGameLoader {

    JAXBContext context;
    Unmarshaller unMarshller;

    public XMLGameLoader() throws JAXBException {
        this.context = JAXBContext.newInstance(generated.Rummikub.class);
    }

    public Game loadGame(String fileName) throws 
            JAXBException, RummikubGameException {
        
        unMarshller = context.createUnmarshaller();
        generated.Rummikub genGame = (generated.Rummikub) unMarshller.unmarshal(new File(fileName));
        Game game;
        final boolean isFromXml = true;
        
        List<Player> humanPlayers = 
                convertToPlayersList(genGame.getPlayers(), generated.PlayerType.HUMAN);
        List<Player> computerPlayers = 
                convertToPlayersList(genGame.getPlayers(), generated.PlayerType.COMPUTER);
        GameBoard gameBoard = convertToGameBoard(genGame.getBoard());
        String currentPlayer = genGame.getCurrentPlayer();
        String gameName = genGame.getName();
        game = Game.createGame(gameName, humanPlayers.size(), 
                computerPlayers.size(), isFromXml);
        for (Player player : computerPlayers) {
            player.setGame(game);
            game.addPlayer(player);
        }
        for (Player player : humanPlayers) {
            player.setGame(game);
            game.addPlayer(player);
        }
        game.setGameName(gameName);
        game.setGameBoard(gameBoard);
        game.setCurrentPlayer(currentPlayer);
        Deck d = new Deck (game.getDealedTilesList());
        game.setDeck (d);
        game.validate();
        
        return game;
    }

    public GameBoard convertToGameBoard(generated.Board genBoard) {
        GameBoard gameBoard = new GameBoard();
        for (generated.Board.Sequence seq : genBoard.getSequence()) {
            gameBoard.addSequence(convertToSequence(seq));
        }
        return gameBoard;
    }

    public Sequence convertToSequence(generated.Board.Sequence seq) {
        Sequence seqToReturn = new Sequence ();
        for (generated.Tile tile : seq.getTile()) {
            seqToReturn.addTile(convertToTile(tile));
        }
        return seqToReturn;
    }
    
    public Tile convertToTile (generated.Tile tile) {
        int tileValue = tile.getValue();
        Tile.Colors color = Tile.Colors.BLACK;
        generated.Color genColor = tile.getColor();
        
        if (genColor == generated.Color.BLACK) 
            color = Tile.Colors.BLACK;
        else if (genColor == generated.Color.BLUE)
            color = Tile.Colors.BLUE;
        else if (genColor == generated.Color.RED)
            color = Tile.Colors.RED;
        else if (genColor == generated.Color.YELLOW)
            color = Tile.Colors.YELLOW;
        
        return new Tile (color, tileValue);
        
    }
    
    public List<Player> convertToPlayersList 
        (generated.Players genPlayers, generated.PlayerType type) {
        List<Player> players = new ArrayList<>();
        for (generated.Players.Player genPlayer : genPlayers.getPlayer()) {
            if (genPlayer.getType() == type)
                players.add(convertToPlayer(genPlayer));
        }
        return players;
    }
    
    public Player convertToPlayer (generated.Players.Player genPlayer) {
        String name = genPlayer.getName();
        List<Tile> tiles = convetTiles(genPlayer.getTiles().getTile());
        PlayerBoard board = new PlayerBoard(tiles);
        Player player;
        boolean playedFirstTurn = genPlayer.isPlacedFirstSequence();
        
        if (genPlayer.getType() == generated.PlayerType.COMPUTER) {
            player = new ComputerPlayer(name, null, board);
        } else {
            player = new HumanPlayer (name, null, board);
        }
        if (playedFirstTurn)
            player.setFirstTurnFlagOff();
        
        return player;
    }
    
    public List<Tile> convetTiles (List<generated.Tile> genTiles) {
        List<Tile> tiles = new ArrayList<>();
        for (generated.Tile genTile : genTiles) {
            Tile tile = convertToTile(genTile);
            tiles.add(tile);
        }
        return tiles;
    }
}
