/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rummikub.ws;

import java.util.List;
import ws.rummikub.Event;
import ws.rummikub.EventType;
/**
 *
 * @author Eran Keren
 */
public class EventCreater {
    
    public Event createGameStartedEvent(int eventID) {
        Event event = new Event();
        event.setId(eventID);
        event.setType(EventType.GAME_START);
        return event;
    }
    
    public Event createSequenceCreated(int eventID, String playerName, List<ws.rummikub.Tile> tiles) {
        Event event =  new Event();
        List<ws.rummikub.Tile> eventTiles = event.getTiles();
        event.setId(eventID);
        event.setType(EventType.SEQUENCE_CREATED);
        event.setPlayerName(playerName);
        for (ws.rummikub.Tile tile : tiles) {
            eventTiles.add(tile);
        }
        
        return event;
    }
    
    public Event createTileMoved(int eventID, String playerName, int sourceSequenceIndex, 
            int sourceSequencePosition, int targetSequenceIndex, int targetSequencePosition) {
        Event event = new Event();
        event.setId(eventID);
        event.setPlayerName(playerName);
        event.setType(EventType.TILE_MOVED);
        event.setSourceSequenceIndex(sourceSequenceIndex);
        event.setSourceSequencePosition(sourceSequencePosition);
        event.setTargetSequenceIndex(targetSequenceIndex);
        event.setTargetSequencePosition(targetSequencePosition);
        
        return event;
    }
    
    public Event createTileAdded(int eventID, String playerName, 
            ws.rummikub.Tile tile, int sequenceIndex, int sequencePosition) {
        Event event = new Event();
        event.setId(eventID);
        event.setType(EventType.TILE_ADDED);
        event.setPlayerName(playerName);
        event.getTiles().add(tile);
        event.setTargetSequenceIndex(sequenceIndex);
        event.setSourceSequencePosition(sequencePosition);
        
        return event;
    }
    
    public Event createTileReturned(int eventID, String playerName, 
            int sequenceIndex, int sequencePosition) {
        Event event = new Event();
        event.setId(eventID);
        event.setType(EventType.TILE_RETURNED);
        event.setPlayerName(playerName);
        event.setSourceSequenceIndex(sequenceIndex);
        event.setSourceSequencePosition(sequencePosition);
        
        return event;
    }
    
    public Event createGameOver(int eventID) {
        Event event = new Event();
        event.setId(eventID);
        event.setType(EventType.GAME_OVER);
        
        return event;
    }
    
    public Event createGameWinner(int eventID, String playerName) {
        Event event = new Event();
        event.setId(eventID);
        event.setType(EventType.GAME_OVER);
        event.setPlayerName(playerName);
        
        return event;
    }
    
    public Event createPlayerTurn(int eventID, String playerName) {
        Event event = new Event();
        event.setId(eventID);
        event.setType(EventType.PLAYER_TURN);
        event.setPlayerName(playerName);
        
        return event;
    }
    
    public Event createPlayerFinishedTurn(int eventID, String playerName) {
        Event event = new Event();
        event.setId(eventID);
        event.setType(EventType.PLAYER_FINISHED_TURN);
        event.setPlayerName(playerName);
        
        return event;
    }
    
    public Event createPlayerResigned(int eventID, String playerName) {
        Event event = new Event();
        event.setId(eventID);
        event.setType(EventType.PLAYER_RESIGNED);
        event.setPlayerName(playerName);
        
        return event;
    }
    
    public Event createRevert(int eventID, String playerName) {
        Event event = new Event();
        event.setId(eventID);
        event.setType(EventType.REVERT);
        event.setPlayerName(playerName);
        
        return event;
    }
}
