/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rummikub.ws;

import engine.Tile;
import ws.rummikub.DuplicateGameName;
import ws.rummikub.DuplicateGameName_Exception;
import ws.rummikub.GameDoesNotExists;
import ws.rummikub.GameDoesNotExists_Exception;
import ws.rummikub.InvalidParameters;
import ws.rummikub.InvalidParameters_Exception;
import ws.rummikub.RummikubFault;

/**
 *
 * @author Eran Keren
 */
public class ExceptionThrower {
    public void throwGameNotExists(String gameName)
            throws GameDoesNotExists_Exception {
        GameDoesNotExists notExist = new GameDoesNotExists();
        RummikubFault fault = new RummikubFault();
        fault.setFaultCode("GameDoesntExist");
        fault.setFaultString(gameName);
        notExist.setFaultInfo(fault);
        notExist.setMessage("invalid game name");
        throw new GameDoesNotExists_Exception("Name does not exist in the waiting games!", notExist);
    }
    
    public void throwNotEnoughHumanPlayer(int humanPlayers)
            throws InvalidParameters_Exception {
        InvalidParameters parameters = new InvalidParameters();
        RummikubFault fault = new RummikubFault();
        fault.setFaultCode("NotEnoughHumanPlayers");
        fault.setFaultString(Integer.toString(humanPlayers));
        parameters.setFaultInfo(fault);
        parameters.setMessage("invalid human player number");
        throw new InvalidParameters_Exception("Must have at least 1 human player!", parameters);
    }
    
    public void throwInvalidPlayersNum(int numOfPlayers)
            throws InvalidParameters_Exception {
        InvalidParameters parameters = new InvalidParameters();
        RummikubFault fault = new RummikubFault();
        fault.setFaultCode("InvalidPlayersNum");
        fault.setFaultString(Integer.toString(numOfPlayers));
        parameters.setFaultInfo(fault);
        parameters.setMessage("invalid number of players");
        throw new InvalidParameters_Exception("Number of Players must be between 2-4!", parameters);
    }
    
    public void throwDuplicateGame(String name) 
            throws DuplicateGameName_Exception{
        DuplicateGameName duplicat = new DuplicateGameName();
        RummikubFault fault = new RummikubFault();
        fault.setFaultCode("DuplicateGameName");
        fault.setFaultString(name);
        duplicat.setFaultInfo(fault);
        throw new DuplicateGameName_Exception
            ("The game name already exists!", duplicat);
    }
    
    public void throwInvalidPlayerId(int playerID) 
            throws InvalidParameters_Exception {
        InvalidParameters parameters = new InvalidParameters();
        RummikubFault fault = new RummikubFault();
        fault.setFaultCode("PlayerID");
        fault.setFaultString(Integer.toString(playerID));
        parameters.setFaultInfo(fault);
        throw new InvalidParameters_Exception("The player id does not exists!", parameters);
    }
    
    public void throwInvalidPlayerName(String playerName) throws InvalidParameters_Exception {
        InvalidParameters parameters = new InvalidParameters();
        RummikubFault fault = new RummikubFault();
        fault.setFaultCode("PlayerName");
        fault.setFaultString(playerName);
        parameters.setFaultInfo(fault);
        throw new InvalidParameters_Exception("The player name is invalid!", parameters);
    }
    
    public void throwInvalidEventID(int eventID) 
            throws InvalidParameters_Exception {
        InvalidParameters parameters = new InvalidParameters();
        RummikubFault fault = new RummikubFault();
        fault.setFaultCode("EventID");
        fault.setFaultString(Integer.toString(eventID));
        parameters.setFaultInfo(fault);
        throw new InvalidParameters_Exception("The event id does not exists!", new InvalidParameters());
    }
    
    public void throwInvalidColor(ws.rummikub.Color color) 
            throws InvalidParameters_Exception {
        InvalidParameters parameters = new InvalidParameters();
        RummikubFault fault = new RummikubFault();
        fault.setFaultCode("TileColor");
        fault.setFaultString(color.toString());
        parameters.setFaultInfo(fault);
        throw new InvalidParameters_Exception
            ("The tile color is invalid!", new InvalidParameters());
    }
    
    public void throwInvalidTileValue(int value) 
            throws InvalidParameters_Exception {
        InvalidParameters parameters = new InvalidParameters();
        RummikubFault fault = new RummikubFault();
        fault.setFaultCode("TileValue");
        fault.setFaultString(Integer.toString(value));
        parameters.setFaultInfo(fault);
        throw new InvalidParameters_Exception
            ("The tile value is invalid!", new InvalidParameters());
    }
    
    public void throwInvalidSequencePosition(int value) 
            throws InvalidParameters_Exception {
        InvalidParameters parameters = new InvalidParameters();
        RummikubFault fault = new RummikubFault();
        fault.setFaultCode("SequencePosition");
        fault.setFaultString(Integer.toString(value));
        parameters.setFaultInfo(fault);
        throw new InvalidParameters_Exception
            ("The sequence position is invalid!", new InvalidParameters());
    }
    
    public void throwInvalidSequenceIndex(int value) 
            throws InvalidParameters_Exception {
        InvalidParameters parameters = new InvalidParameters();
        RummikubFault fault = new RummikubFault();
        fault.setFaultCode("SequenceIndex");
        fault.setFaultString(Integer.toString(value));
        parameters.setFaultInfo(fault);
        throw new InvalidParameters_Exception
            ("The sequence index is invalid!", new InvalidParameters());
    }
}
