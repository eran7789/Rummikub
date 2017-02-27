
package engine;

import java.util.ArrayList;
import java.util.Collections;


public class GameOptions{
    
    private ArrayList<SequencesSet>  options;
    
    public class SequencesSet implements Comparable <SequencesSet> {
        
        private ArrayList<engine.Sequence> sequences;
        private int sum;
       
        public SequencesSet(engine.Sequence seqToAdd){
            sequences = new ArrayList<engine.Sequence>();
            
            this.sequences.add(seqToAdd);
            this.sum =seqToAdd.sum();
        }
        
        public int sum(){
            return sum;
        }
        
        public void addSequnce(engine.Sequence seqToAdd){
            
            boolean toAdd=true;
            
            for (engine.Sequence seq: sequences){
               if (seq.hasMutualTiles(seqToAdd))
                   toAdd=false;
            }
            
            if (toAdd){
                sequences.add(seqToAdd);
                sum += seqToAdd.sum();
            }
        }
        
        @Override
        public int compareTo(SequencesSet set) {
            return  this.sum() - set.sum();
        }
        
        public ArrayList<engine.Sequence> sequences()
        {
            return sequences;
        }
    }
    
    public GameOptions (engine.PlayerBoard playerBoard){
        options = new ArrayList<SequencesSet>();
        
        createAndaddGroups(playerBoard);
        createAndaddSets(playerBoard);
    }
    
    private void createAndaddGroups(engine.PlayerBoard playerBoard){
        
        engine.Sequence seq;
        
        playerBoard.sortByColor();
        playerBoard.sortByNumber();
        
        for (Tile tile : playerBoard.tiles())
            if (!tile.isJoker()){
                seq =creategroup(playerBoard, tile.getNum());
                if (seq != null)
                    addSequence(seq);  
            }
    }
    
    private void createAndaddSets(engine.PlayerBoard playerBoard){
        engine.Sequence seq;
        
        playerBoard.sortByNumber();
        playerBoard.sortByColor();
        
        for (Tile tile : playerBoard.tiles()) 
            
            if (!tile.isJoker()){
                seq =createSet(playerBoard,tile);
                if (seq != null)
                    addSequence(seq);  
            }
    }
    
    private void addSequence(engine.Sequence seqToAdd)
    {
        options.add(new SequencesSet(seqToAdd));
        
        for (SequencesSet set : options)
            set.addSequnce(seqToAdd);
    }
    
    private engine.Sequence creategroup(engine.PlayerBoard playerBoard, int num)
    {
        engine.Sequence seq = new engine.Sequence();
        
        Tile Joker1 = playerBoard.getJoker();
        Tile Joker2=null;
        
        if (Joker1 != null)
            Joker2 = playerBoard.getJoker2(Joker1);
        
        boolean red = false;
        boolean blue = false;
        boolean yellow = false;
        boolean black = false;
        int groupNumber = num;
        
        for (Tile tile : playerBoard.tiles()){
            if (!tile.isJoker()){
                if (tile.getNum() == groupNumber)
                {
                    if (tile.getColor() == Tile.Colors.RED){
                        if (!red)
                            seq.addTile(tile);
                        red=true;
                    }
                    
                    if (tile.getColor() == Tile.Colors.BLUE){
                        if (!blue)
                            seq.addTile(tile);
                        blue=true;
                    }
                    
                    if (tile.getColor() == Tile.Colors.YELLOW){
                        if (!yellow)
                            seq.addTile(tile);
                        yellow=true;
                    }
                    
                    if (tile.getColor() == Tile.Colors.BLACK){
                        if (!black)
                            seq.addTile(tile);
                        black=true;
                    }
                }
            }
            
        }
        
        if (seq.isGroup())
            return seq;
        
        if (Joker1 != null)
            seq.tryAddToSet(Joker1);
        
        if (seq.isGroup())
            return seq;
        return null;
    }
    
    public SequencesSet getBestMove()
    {
        if (!options.isEmpty()){
            Collections.sort(options);
            return options.get(options.size()-1);
        }
        return null;
    }
    
    public engine.Sequence createSet (PlayerBoard board, Tile firstTile){
        
        Tile Joker1 = board.getJoker();
        Tile Joker2=null;
        
        if (Joker1 != null)
            Joker2 = board.getJoker2(Joker1);
        
        boolean Joker1Used = false;
        boolean Joker2Used = false;
        
       
        
        int lastNum = firstTile.getNum();
        
        engine.Sequence seq = new engine.Sequence();
        seq.addTile(firstTile);
        
        for (Tile tile : board.tiles()){
            if (!tile.isJoker()){
                if(tile.getColor() == firstTile.getColor()){
                    if (tile.getNum() - lastNum == 1){
                        seq.addTile(tile);
                        lastNum = tile.getNum();
                    }
                    else if (tile.getNum() - lastNum == 2)
                    {
                        if (Joker1 != null && !Joker1Used){
                            seq.addTile(Joker1);
                            seq.addTile(tile);
                            Joker1Used = true;
                        }
                        else if (Joker2 != null && !Joker2Used){
                            seq.addTile(Joker2);
                            seq.addTile(tile);
                            Joker2Used = true;
                        }
                    }
                }
            }
        }
        
        if (seq.isSet())
            return seq;
     
        if (Joker1 != null && !Joker1Used)
            seq.tryAddToSet(Joker1);
        
        if (seq.isSet())
            return seq;
        return null;
    }
}
