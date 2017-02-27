package engine;

import java.util.ArrayList;
import java.util.List;

public class PlayerBoard {
    
    public static final int TILE_NOT_FOUND = -1;
    
    private List <Tile> tiles;
    
    public PlayerBoard () {
        tiles = new ArrayList<>();
    }
    
    public PlayerBoard (List<Tile> tiles) {
        this.tiles= tiles;
    }
    
    public PlayerBoard (PlayerBoard board) {
        tiles = new ArrayList<>();
        for(Tile tile : board.tiles)
            tiles.add(tile);
    }
    
    public void addTile (Tile tile) {
        tiles.add(tile);
    }
    
    public boolean removeTile (Tile tile) {
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
    
    public void addTiles (Tile[] tiles) {
        for (Tile tile : tiles) {
            this.tiles.add(tile);
        }
    } 
    
    public boolean removeTiles (Tile[] tiles) {
        boolean[] isRemoved = new boolean [tiles.length];
        int lastIndex = this.tiles.size();
        for (int index=0; index<lastIndex; index++) {
            Tile thisTile = this.tiles.get(index);
            for (int tileForRemoveIndex=0; tileForRemoveIndex<tiles.length; tileForRemoveIndex++) {
                if (thisTile == tiles[tileForRemoveIndex]) {
                    this.tiles.remove(thisTile);    
                    isRemoved[tileForRemoveIndex] = true;
                    lastIndex--;
                    index--;
                }
            }
        }
        for (int i=0; i<isRemoved.length; i++) {
            if (isRemoved[i] == false) 
                return false;
        }
        return true;
    }
    
    public boolean isEmpty () {
        return tiles.isEmpty();
    }
    
    public int size() {
        return tiles.size();
    }
    
    public List<Tile> getTiles() {
        return tiles;
    }
    
    public Tile getTile (int index) {
        return tiles.get(index);
    }
    
    public void sortByColor () {
        tiles.sort((Tile t1, Tile t2) -> 
                t1.getColor().ordinal() - t2.getColor().ordinal());
    }
    
    public void sortByNumber () {
        tiles.sort((Tile t1, Tile t2) -> 
                t1.getNum() - t2.getNum());
    }
    
    public List<Tile> tiles () {
        return tiles;
    }
    
    public int getTileIndex(Tile tile) {
        for (int i=0; i<tiles.size(); i++) {
            Tile currentTile = tiles.get(i);
            if (currentTile == tile)
                return i;
            }
        return TILE_NOT_FOUND;
    }
    
    public int sum(){
        int sum=0;
        for (Tile tile : tiles)
            sum += tile.getNum();
        return sum;
    }
    
    public void removeSequences(ArrayList<engine.Sequence> list){
        for(engine.Sequence seq: list)
            for(engine.Tile tile:seq.tiles())
                removeTile(tile);
    }
    
     public Tile getJoker(){   
        for (Tile tile : tiles){
            if (tile.isJoker()){
                return tile;
            }
        }
        return null;
    }
     
     public Tile getJoker2(Tile Joker1) {   
        for (Tile tile : tiles){
            if (tile.isJoker() && tile != Joker1){
                return tile;
            }
        }
        return null;
    }
     
}
