
package ws.rummikub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for takeBackTile complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="takeBackTile">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="playerId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="sequenceIndex" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="sequencePosition" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "takeBackTile", propOrder = {
    "playerId",
    "sequenceIndex",
    "sequencePosition"
})
public class TakeBackTile {

    protected int playerId;
    protected int sequenceIndex;
    protected int sequencePosition;

    /**
     * Gets the value of the playerId property.
     * 
     */
    public int getPlayerId() {
        return playerId;
    }

    /**
     * Sets the value of the playerId property.
     * 
     */
    public void setPlayerId(int value) {
        this.playerId = value;
    }

    /**
     * Gets the value of the sequenceIndex property.
     * 
     */
    public int getSequenceIndex() {
        return sequenceIndex;
    }

    /**
     * Sets the value of the sequenceIndex property.
     * 
     */
    public void setSequenceIndex(int value) {
        this.sequenceIndex = value;
    }

    /**
     * Gets the value of the sequencePosition property.
     * 
     */
    public int getSequencePosition() {
        return sequencePosition;
    }

    /**
     * Sets the value of the sequencePosition property.
     * 
     */
    public void setSequencePosition(int value) {
        this.sequencePosition = value;
    }

}
