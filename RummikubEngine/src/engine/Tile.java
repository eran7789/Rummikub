package engine;


public class Tile implements Comparable <Tile> {
    
    private final Colors color;
    private final int number;
    
    
    public enum Colors{
        BLACK, 
        RED, 
        BLUE,
        YELLOW
    }
    
    
    public Tile(Colors color, int number){
        this.color= color;
        this.number=number;
    }
    
    public Tile(Tile tile){
        color = tile.getColor();
        number = tile.getNum();
    }
    
    public int getNum()
    {
        return number;
    }
    
    public Colors getColor()
    {
        return color;
    }

    @Override
    public int compareTo(Tile tile) {
       return this.number - tile.getNum();
    }
    
    public boolean isJoker(){
        return (number == 0 );
    }
    
    public boolean isEqualTo(Tile tile){
        
        return (this.color == tile.getColor() &&
                this.number == tile.getNum());
    }
    
}



