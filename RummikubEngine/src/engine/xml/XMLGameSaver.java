/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine.xml;

import engine.ComputerPlayer;
import engine.Game;
import engine.GameBoard;
import engine.Player;
import engine.PlayerBoard;
import engine.Sequence;
import engine.Tile;
import generated.Color;
import generated.ObjectFactory;
import generated.PlayerType;
import generated.Players;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 *
 * @author Eran Keren
 */
public class XMLGameSaver {
    
    JAXBContext context;
    Marshaller marshller;
    ObjectFactory factory;

    public XMLGameSaver() throws JAXBException {
        this.context = JAXBContext.newInstance(generated.Rummikub.class);
        factory = new ObjectFactory();
    }
    
    public void saveGame (String fileName, Game game) 
            throws JAXBException, FileNotFoundException, IOException {
        marshller = context.createMarshaller();
        
        generated.Rummikub genGame = factory.createRummikub();
        genGame.setBoard(convetToGenBoard(game.getGameBoard()));
        genGame.setCurrentPlayer(game.getCurrentPlayer().getName());
        genGame.setName(game.getGameName());
        generated.Players genPlayers = factory.createPlayers();
        addPlayers(genPlayers.getPlayer(), game);
        genGame.setPlayers(genPlayers);
        
        
        File file = new File(fileName);
        if (file.createNewFile())
            marshller.marshal(genGame, file);
        else 
            throw new IOException();
    }
    
    public generated.Board convetToGenBoard(GameBoard board) {
        generated.Board genBoard = factory.createBoard();
        List<generated.Board.Sequence> sequences = genBoard.getSequence();
        addSequencesToGenBoard(sequences, board);
        return genBoard;
    }
    
    public void addSequencesToGenBoard 
        (List<generated.Board.Sequence> sequences, GameBoard board) {
        for (int i=0; i<board.size(); i++) {
            sequences.add(convertToGenSequence(board.getSequence(i)));
        }
    }
        
        public generated.Board.Sequence convertToGenSequence (Sequence sequence) {
            generated.Board.Sequence genSequence = factory.createBoardSequence();
            List <generated.Tile> genTiles = genSequence.getTile();
            for (int i=0; i<sequence.size(); i++) {
                genTiles.add(convertToGenTile(sequence.getTileByIndex(i)));
            }
            return genSequence;
        }
        
        public generated.Tile convertToGenTile (Tile tile) {
            generated.Tile genTile = factory.createTile();
            switch (tile.getColor()) {
                case BLACK:
                    genTile.setColor(Color.BLACK);
                    break;
                case RED:
                    genTile.setColor(Color.RED);
                    break;
                case YELLOW:
                    genTile.setColor(Color.YELLOW);
                    break;
                case BLUE:
                    genTile.setColor(Color.BLUE);
                    break;
            }
            genTile.setValue(tile.getNum());
            return genTile;
        }
        
        public void addPlayers(List<Players.Player> genPlayers, Game game) {
            for (Player player : game.getPlayers()) {
                generated.Players.Player genPlayer = 
                        factory.createPlayersPlayer();
                genPlayer.setName(player.getName());
                genPlayer.setPlacedFirstSequence(!player.isFirstTurn());
                if (player instanceof ComputerPlayer)
                    genPlayer.setType(PlayerType.COMPUTER);
                else
                    genPlayer.setType(PlayerType.HUMAN);
                genPlayer.setTiles
                    (convertPlayerBoardToTiles(player.getPlayerBoard()));
                genPlayers.add(genPlayer);
            }
        }
        
        public generated.Players.Player.Tiles 
            convertPlayerBoardToTiles (PlayerBoard board) {
            generated.Players.Player.Tiles genTiles = 
                        factory.createPlayersPlayerTiles();
            List<generated.Tile> genTileList = genTiles.getTile();
            for (int i=0; i<board.size(); i++) {
                genTileList.add(convertToGenTile(board.getTile(i)));
            }
            return genTiles; 
       }
}
