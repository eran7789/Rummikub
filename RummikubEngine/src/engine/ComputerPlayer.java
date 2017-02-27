
package engine;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ComputerPlayer extends Player {

    
    public ComputerPlayer (String name,Game game, engine.PlayerBoard playerBoard){
        super (name, game, playerBoard);
    }
    
   @Override
    public void playTurn() {}
}
