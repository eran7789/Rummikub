/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import engine.Tile.Colors;
import java.util.ArrayList;
import java.util.List;
import java.lang.IndexOutOfBoundsException;

/**
 *
 * @author Eran Keren
 */
public class GameBoard {
    
    public static final int START_OF_INDEX = 0;
    
    private List<Sequence> sequences;
    
    public GameBoard () {
        sequences = new ArrayList<>();
    }
    
    // copy const'
    public GameBoard (GameBoard board) {
        this.sequences = new ArrayList<>();
        for (Sequence sequence : board.sequences) {
            this.sequences.add(new Sequence (sequence));
        }
    }
    
    public Sequence getSequence (int index) {
        return sequences.get(index);
    }
    
    public void addSequence (Sequence seq) {
        sequences.add(seq);
    }
    
    public void deleteSequence (int sequenceNumber) {

        sequences.remove(sequences.get(sequenceNumber));
    }
    
    public void addTileToSequence (Tile tile, int sequenceIndex, int sequencePosition){
        
        if ( sequences.get(sequenceIndex).size() > sequencePosition)
           addDummyTiles(sequencePosition - sequences.get(sequenceIndex).size() , sequences.get(sequenceIndex));
        
        if (sequences.get(sequenceIndex).size() == sequencePosition)
            sequences.get(sequenceIndex).addTile(tile);
        else
            sequences.get(sequenceIndex).addTile(tile, sequencePosition);
    }
    
    public boolean removeTileFromSequence (Tile tile, int sequenceIndex) throws IndexOutOfBoundsException {
        if (sequenceIndex >= sequences.size())
            throw new IndexOutOfBoundsException("sequenceIndex");
        
        return sequences.get(sequenceIndex).removeTile(tile);
                
    }
    
    public Boolean isValidChangeInBoard (boolean isFirstTurn , GameBoard boardCopy) {
        if (this.isValidBoard()){
            try {
                if (isFirstTurn && this.sum() - boardCopy.sum() < 30 && this.sum() - boardCopy.sum() > 0)
                    return false;
                return true;
            } catch (Exception e) {}
        }
        return false;
    }
    
    public Boolean isValidBoard () {
        for (Sequence sq : sequences)
            if (!sq.isValidSequence())
                return false;
        return true;
    }
   
    public int sum()
    {
        int sum=0;
        for (Sequence seq : sequences){
            sum += seq.sum();
        }
        return sum;
    }
    
    public int size() {
        return sequences.size();
    }
    
    public int getSequenceSize (int index) {
        return sequences.get(index).size();
    }
    
    public void moveTile (int playerID, 
        int sourceSequenceIndex, int sourceSequencePosition,
        int targetSequenceIndex, int targetSequencePosition) {
        
        Tile tile = sequences.get(sourceSequenceIndex).getTileByIndex(sourceSequencePosition);
        
        if (targetSequenceIndex == sequences.size()) {
            sequences.add(new Sequence());
        }
        sequences.get(sourceSequenceIndex).removeTile(tile);
        sequences.get(targetSequenceIndex).addTile(tile, targetSequencePosition);
        if (sequences.get(sourceSequenceIndex).isEmpty())
            sequences.remove(sequences.get(sourceSequenceIndex));
    }
    
    public void addSequences(ArrayList<engine.Sequence> seqlist){
        for (Sequence seq : seqlist)
            sequences.add(seq);
    }
    
    public List<Sequence> sequences () {
        return sequences;
    }
    
    public Tile getTile(int x, int y){
        return getSequence(x).getTileByIndex(y);
    }

    boolean isValidSequncePosition(int sequenceIndex, int sequencePosition) {
        return (sequencePosition <= sequences.get(sequenceIndex).size());
    }
    
    public void addLines(int linesToAdd) {
        Sequence seq;
        for (int i = 0; i < linesToAdd; i++) {
            seq = new Sequence();
            sequences.add(seq);
        }
    }

    private void addDummyTiles(int dummyTiles, Sequence seq) {
        Tile dummyTile;
        for (int i = 0; i < dummyTiles; i++) {
            dummyTile = new Tile (Colors.BLACK, 30);
            seq.addTile(dummyTile);
        }  
    }
}
