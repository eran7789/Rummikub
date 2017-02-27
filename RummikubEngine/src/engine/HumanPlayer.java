
package engine;

import engine.Player;

public class HumanPlayer extends Player {
    
    public HumanPlayer (String name, Game game, engine.PlayerBoard playerBoard) {
        super (name, game, playerBoard);
    }

    @Override
    public void playTurn() {}
}
