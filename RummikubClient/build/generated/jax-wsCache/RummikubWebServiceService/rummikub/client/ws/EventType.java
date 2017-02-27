
package rummikub.client.ws;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for eventType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="eventType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="GameStart"/>
 *     &lt;enumeration value="GameOver"/>
 *     &lt;enumeration value="GameWinner"/>
 *     &lt;enumeration value="PlayerTurn"/>
 *     &lt;enumeration value="PlayerFinishedTurn"/>
 *     &lt;enumeration value="PlayerResigned"/>
 *     &lt;enumeration value="SequenceCreated"/>
 *     &lt;enumeration value="TileAdded"/>
 *     &lt;enumeration value="TileReturned"/>
 *     &lt;enumeration value="TileMoved"/>
 *     &lt;enumeration value="Revert"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "eventType")
@XmlEnum
public enum EventType {

    @XmlEnumValue("GameStart")
    GAME_START("GameStart"),
    @XmlEnumValue("GameOver")
    GAME_OVER("GameOver"),
    @XmlEnumValue("GameWinner")
    GAME_WINNER("GameWinner"),
    @XmlEnumValue("PlayerTurn")
    PLAYER_TURN("PlayerTurn"),
    @XmlEnumValue("PlayerFinishedTurn")
    PLAYER_FINISHED_TURN("PlayerFinishedTurn"),
    @XmlEnumValue("PlayerResigned")
    PLAYER_RESIGNED("PlayerResigned"),
    @XmlEnumValue("SequenceCreated")
    SEQUENCE_CREATED("SequenceCreated"),
    @XmlEnumValue("TileAdded")
    TILE_ADDED("TileAdded"),
    @XmlEnumValue("TileReturned")
    TILE_RETURNED("TileReturned"),
    @XmlEnumValue("TileMoved")
    TILE_MOVED("TileMoved"),
    @XmlEnumValue("Revert")
    REVERT("Revert");
    private final String value;

    EventType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EventType fromValue(String v) {
        for (EventType c: EventType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
