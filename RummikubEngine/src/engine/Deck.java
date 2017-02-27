package engine;

import java.util.ArrayList;
import java.util.List;
import engine.Tile.Colors;
import java.util.Collections;
import java.util.Iterator;
import java.util.ListIterator;



public class Deck {
    
    public final int MAX_TILE_NUM = 13;
    public final int TOTAL_TILES_NUMBER = 106;
    
    private List<Tile> tiles;
    private int topCardIndex;
    
    public Deck () {
        createNewDeck();
    }
    
    private void createNewDeck () {
        
        tiles = new ArrayList (TOTAL_TILES_NUMBER);
        
        for (int number=1; number<=MAX_TILE_NUM; number++) {
            for (Colors color : Colors.values()) {
                tiles.add(new Tile(color, number));
                tiles.add(new Tile(color, number));
            }
        }
        
        tiles.add(new Tile(Colors.BLACK,0)); // adding Black Joker
        tiles.add(new Tile(Colors.RED,0)); // adding Red Joker
        
        
        Collections.shuffle(tiles);
        Collections.shuffle(tiles);
        Collections.shuffle(tiles);
        topCardIndex = 0;
    }
    
    public Deck (List<Tile> tilesOnBoard) {
        if (tilesOnBoard.size() == 0) {
            createNewDeck();
            return;
        }
        
        tiles = new ArrayList<>(TOTAL_TILES_NUMBER);
        
        Collections.sort(tilesOnBoard, (t1, t2) -> t1.getNum() - t2.getNum());
        Iterator<Tile> iterator = tilesOnBoard.iterator();
        Tile currentExistingTile = iterator.next();
        while (currentExistingTile.getNum() == 0) {
            if (iterator.hasNext()) 
                currentExistingTile = iterator.next();
        }
        
        for (int number=1; number<=MAX_TILE_NUM; number++) {
            boolean[] hadBlack = {false, false};
            boolean[] hadRed = {false, false};
            boolean[] hadYellow = {false, false};
            boolean[] hadBlue = {false, false};
            
            
            if (currentExistingTile.getNum() > number) {
                for (Colors color : Colors.values()) {
                    tiles.add(new Tile(color, number));
                    tiles.add(new Tile(color, number));
                }
            }
            else if (currentExistingTile.getNum() == number){
                while (currentExistingTile.getNum() == number) {
                    switch (currentExistingTile.getColor()) {
                        case BLACK:
                            if (hadBlack[0])
                                hadBlack[1] = true;
                            else 
                                hadBlack[0] = true;
                            break;
                        case RED:
                            if (hadRed[0])
                                hadRed[1] = true;
                            else
                                hadRed[0] = true;
                            break;
                        case YELLOW:
                            if (hadYellow[0])
                                hadYellow[1] = true;
                            else
                                hadYellow[0] = true;
                            break;
                        case BLUE:
                            if (hadBlue[0])
                                hadBlue[1] = true;
                            else
                                hadBlue[0] = true;
                            break;
                    }
                    if (iterator.hasNext())
                        currentExistingTile = iterator.next();
                    else 
                        break;
                }
                if (!hadBlack[0]) {
                    tiles.add(new Tile(Colors.BLACK, number));
                    tiles.add(new Tile(Colors.BLACK, number));
                } else if (!hadBlack[1]) {
                    tiles.add(new Tile (Colors.BLACK, number));
                }
                if (!hadRed[0]) {
                    tiles.add(new Tile(Colors.RED, number));
                    tiles.add(new Tile(Colors.RED, number));
                } else if (!hadRed[1]) {
                    tiles.add(new Tile(Colors.RED, number));
                }
                if (!hadYellow[0]) {
                    tiles.add(new Tile (Colors.YELLOW, number));
                    tiles.add(new Tile (Colors.YELLOW, number));
                } else if (!hadYellow[1]) {
                    tiles.add(new Tile(Colors.YELLOW, number));
                }
                if (!hadBlue[0]) {
                    tiles.add(new Tile (Colors.BLUE, number));
                    tiles.add(new Tile (Colors.BLUE, number));
                } else if (!hadBlue[1]) {
                    tiles.add(new Tile (Colors.BLUE, number));
                }
            }
        }
        
        if (tilesOnBoard.get(0).getNum() != 0) {
            tiles.add(new Tile(Colors.BLACK,0)); // adding Black Joker
            tiles.add(new Tile(Colors.RED,0)); // adding Red Joker
        }
        else if (tilesOnBoard.get(1).getNum() != 0) {
            tiles.add(new Tile(Colors.BLACK, 0));
        }
        
        Collections.shuffle(tiles);
        Collections.shuffle(tiles);
        Collections.shuffle(tiles);
        topCardIndex = tiles.size() - 1;
        for (Tile tile : tilesOnBoard) 
            tiles.add(tile);
    }
    
    public Tile getTile () {
        return tiles.get(topCardIndex++);
    }
    
    public boolean isEmpty () {
        return topCardIndex == TOTAL_TILES_NUMBER;
    }
    
    public int getNumOfTiles() {
        return TOTAL_TILES_NUMBER - topCardIndex;
    }
    
    public void addTiles(List<Tile> tiles) {
        for (Tile tileInList : tiles) {
            for (Tile tileInDeck : this.tiles) {
                if (tileInDeck == tileInList) {
                    this.tiles.remove(tileInDeck);
                    this.tiles.add(tileInDeck);
                    topCardIndex--;
                }
            }
        }
    }
}
