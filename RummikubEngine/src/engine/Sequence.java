/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import engine.Tile.Colors;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sequence {

    private List<Tile> tiles;

    public static final int SMALL_SEQUENCE_SIZE = 3;
    public static final int BIG_SEQUENCE_SIZE = 4;

    public Sequence() {
        tiles = new ArrayList<>();
    }

    // copy const'
    public Sequence(Sequence sequence) {
        this.tiles = new ArrayList<>();
        for (Tile tile : sequence.tiles) {
            tiles.add(tile);
        }
    }
    
    public Sequence (List<Tile> tiles) {
        this.tiles = tiles;
    }

    public Sequence(Tile[] tiles) throws NullPointerException {
        if (tiles == null) {
            throw new NullPointerException();
        }
        this.tiles = new ArrayList<>(tiles.length);
        this.tiles.addAll(Arrays.asList(tiles));
    }

    public int sum() {
        int sum = 0;
        for (int i = 0; i < tiles.size(); i++) {
            if (!tiles.get(i).isJoker()) {
                sum += tiles.get(i).getNum();
            } else {
                sum += findJokerValue(i);
            }

        }
        return sum;
    }

    private int findJokerValue(int index) {
        if (index == Game.START_OF_INDEX) {
            Tile nextTile = tiles.get(index + 1),
                    nextNextTile = tiles.get(index + 2);
            if (nextTile.getNum() == nextNextTile.getNum()) {
                return nextTile.getNum();
            } else {
                return nextTile.getNum() - 1;
            }
        } else if (index == tiles.size() - 1) {
            Tile prevTile = tiles.get(index - 1),
                    prevPrevTile = tiles.get(index - 2);
            if (prevTile.getNum() == prevPrevTile.getNum()) {
                return prevTile.getNum();
            } else {
                return prevTile.getNum() + 1;
            }
        } else {
            Tile nextTile = tiles.get(index + 1),
                    prevTile = tiles.get(index - 1);
            if (nextTile.getNum() == prevTile.getNum()) {
                return nextTile.getNum();
            } else {
                return nextTile.getNum() - 1;
            }
        }
    }

    public int size() {
        return tiles.size();
    }

    public void addTile(Tile tile, int position) {
        tiles.add(position, tile);
    }

    public boolean isEmpty() {
        return tiles.isEmpty();
    }

    public boolean removeTile(Tile tile) {
        boolean isRemoved = false;
        for (Tile t : tiles) {
            if (tile == t) {
                tiles.remove(t);
                isRemoved = true;
                return isRemoved;
            }
        }
        return isRemoved;
    }

    // validates a sequence 
    public boolean isValidSequence() {

        return (this.isGroup() || this.isSet());
    }

    public boolean isGroup() {

        boolean red = false;
        boolean blue = false;
        boolean yellow = false;
        boolean black = false;
        int groupNumber = 0;

        int size = tiles.size();
        if (size <= 4 && size > 2) {

            for (engine.Tile tile : tiles) {
                if (!tile.isJoker()) {
                    groupNumber = tile.getNum();
                    break;
                }
            }

            for (engine.Tile tile : tiles) {
                if (!tile.isJoker()) {

                    if (tile.getNum() != groupNumber) {
                        return false;
                    }

                    if (tile.getColor() == Colors.RED) {
                        if (red) {
                            return false;
                        }
                        red = true;
                    } else if (tile.getColor() == Colors.BLUE) {
                        if (blue) {
                            return false;
                        }
                        blue = true;
                    } else if (tile.getColor() == Colors.YELLOW) {
                        if (yellow) {
                            return false;
                        }
                        yellow = true;
                    } else if (tile.getColor() == Colors.BLACK) {
                        if (black) {
                            return false;
                        }
                        black = true;
                    }
                }
            }
            return true;
        }
        return false;
    }

    public boolean isSet() {

        Colors setColor = Colors.BLACK; //defult for compiling reasons

        int size = tiles.size();
        if (size <= 13 && size > 2) {

            for (engine.Tile tile : tiles) {
                if (!tile.isJoker()) {
                    setColor = tile.getColor();
                    break;
                }
            }

            if (tiles.get(0).isJoker() && tiles.get(1).getNum() == 1) /// looking for 1 after joker
            {
                return false;
            }
            if (tiles.get(tiles.size() - 1).isJoker() && tiles.get(tiles.size() - 2).getNum() == 13) // looking for joker after 13 
            {
                return false;
            }

            for (int i = 0; i < size - 1; i++) {
                if (tiles.get(i).isJoker()) {
                    if ((i != 0) && (i != size - 1)) {
                        if (tiles.get(i + 1).getNum() - tiles.get(i - 1).getNum() != 2) {
                            return false;
                        }
                    }
                } else if (tiles.get(i + 1).getNum() - tiles.get(i).getNum() != 1
                        || tiles.get(i).getColor() != setColor
                        || tiles.get(i + 1).getColor() != setColor) {

                    if (!tiles.get(i + 1).isJoker()) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }

    public boolean validateSequencePosition(int position) {
        if (position < 0 || position >= size()) {
            return false;
        } else {
            return true;
        }
    }

    public Tile getTileByIndex(int index) {
        return tiles.get(index);
    }
    
    public boolean hasMutualTiles(Sequence seq){
        for (Tile tile: seq.tiles)
            if (this.hasTile(tile))
                return true;
        
        return false;
    }
    
    public boolean hasTile(Tile tiletofind){
        for (Tile tile: this.tiles)
            if (tile == tiletofind)
                return true;
        return false;
    }
    
    public void addTile (Tile tile) {
        tiles.add(tile);
    }
    
    public List<Tile> tiles(){
        return tiles;
    }

    public boolean tryAddToGroup(Tile tileToAdd){
       
        int groupNumber= getGroupNumber();
        
        if (this.size() == 3){
            
            if (tileToAdd.isJoker()){
                addTile(tileToAdd);
                return true;
            }
            
            if(tileToAdd.getNum() == groupNumber){
                
                for (Tile tile : this.tiles){
                    if (tile.isEqualTo(tileToAdd))
                        return false;
                }
                
                addTile(tileToAdd);
                return true;
            }
        }
        return false;
    }
    
    public boolean tryAddToSet(Tile tileToAdd){
        
        int min= findMintNum();
        int max= findMaxtNum();
        
        Colors setColor = getGroupColor();
        
        if (this.size() < 13){
            
            if (tileToAdd.isJoker()){
                
                if (max < 13)
                    addTile(tileToAdd);
              
                else if (min > 2)
                    addTile(tileToAdd,0);
                
                return true;   
            }
            if (tileToAdd.getColor() == setColor){
            
                if (min - tileToAdd.getNum() == 1){
                
                    addTile(tileToAdd,0);
                
                    return true;
                }
            
                else if (tileToAdd.getNum()- max == 1){
                
                    addTile(tileToAdd);
                
                    return true;
                }
            }
        }
        return false;
    }
    
    public int findMintNum(){
        int min=13;
        int joker ,index;
        
        for (Tile tile : tiles) {
            if (!tile.isJoker())
                if (tile.getNum() < min)
                    min= tile.getNum();
        }
        
        index = hasJoker();
        if (index != -1){
            joker = findJokerValue(index);
            if (joker < min)
                min = joker;
        }
        
            
        return min;
    }
    
    public int findMaxtNum(){
        int max=0;
        int index,joker;
        for (Tile tile : tiles) {
            if (!tile.isJoker())
                if (tile.getNum() > max)
                    max= tile.getNum();
        }
        index = hasJoker();
        if (index != -1){
            joker = findJokerValue(index);
            if (joker > max)
                max = joker;
        }
        
        return max;
    }
    
    private int hasJoker(){
        for (int i = 0 ; i < tiles.size() ; i++) {
            if (tiles.get(i).isJoker())
                return i;
        }
        return -1;
    }
    
    private int getGroupNumber(){
        
        for (Tile tile : tiles) 
            if (!tile.isJoker()) 
                   return tile.getNum();
        
        return 1; 
    }
    
    private Colors getGroupColor(){
        
        Colors setColor = Colors.BLACK ;
        
        for (engine.Tile tile : tiles)
                if (!tile.isJoker())
                    return tile.getColor();
                    
        return setColor;
    }
}
