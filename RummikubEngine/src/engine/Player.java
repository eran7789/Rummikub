/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import java.util.List;


public abstract class Player implements Comparable<Player>{
    
    private final int START_OF_INDEX = 0;
    
    //private int sumOfTilesValue;
    protected String name;
    protected PlayerBoard myBoard;
    protected boolean isPlaying;
    protected Game game;
    protected boolean isFirstTurn;
    protected int playerID;

    
   
    public Player (String name, Game game, PlayerBoard playerBoard) {
        this.name = name;
        this.game = game;
        this.myBoard = playerBoard;
        this.isPlaying = true;
        this.isFirstTurn=true;
    }
    
    public void setGame (Game game) {
        this.game = game;
    }
    
    public boolean hasWon () {
        return myBoard.isEmpty();
    }
    
    public int getPlayerID () {
        return playerID;
    }
    
    public String getName () {
        return name;
    }
    
    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }
    
    public Tile[] getTilesArrayByIndexList (List<Integer> indexes) 
            throws IndexOutOfBoundsException{
        Tile[] tiles = new Tile[indexes.size()];
        int tilesCurrentIndex = 0;
        for (int i : indexes) {
            if (i>=this.myBoard.size() || i < START_OF_INDEX) 
                throw new IndexOutOfBoundsException("Index " + i);
            tiles[tilesCurrentIndex] = this.myBoard.getTile(i);
            tilesCurrentIndex++;
        }
        return tiles;
    }
    
    public abstract void playTurn ();//Abstract!!

    public Tile getTileByIndex(int index) throws IndexOutOfBoundsException {
        if (index >= myBoard.size() || index < START_OF_INDEX) {
            throw new IndexOutOfBoundsException();
        }
        
        Tile tile = myBoard.getTile(index);
        return tile;
    }
    
    public void addTile (Tile tile) {
        myBoard.addTile(tile);
    }
    
    public boolean removeTiles (Tile[] tiles) {
        return myBoard.removeTiles(tiles);
    }
    
    public boolean removeTile (Tile tile) {
        return myBoard.removeTile(tile);
    }
    
    public PlayerBoard getPlayerBoard() {
        return myBoard;
    }
    
    public List<Tile> getTiles() {
        return myBoard.getTiles();
    }
    
    public void changeBoardToThis(PlayerBoard board)
    {
        this.myBoard = board;
    }
    
    public boolean isFirstTurn(){
        return isFirstTurn;
    }
    
    public void setFirstTurnFlagOff(){
        isFirstTurn=false;
    }
    
    public void setPlayerBoard(PlayerBoard board) {
        this.myBoard = board;
    }
    
    public int getNumOfTiles() {
        return myBoard.size();
    }
    
    @Override
    public int compareTo(Player player) {
       return (player.getPlayerBoard().sum() - this.getPlayerBoard().sum());
               
    }
} 