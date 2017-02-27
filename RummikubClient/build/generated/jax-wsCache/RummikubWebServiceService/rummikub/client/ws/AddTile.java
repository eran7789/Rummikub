
package rummikub.client.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for addTile complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="addTile">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="playerId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="tile" type="{http://rummikub.ws/}tile" minOccurs="0"/>
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
@XmlType(name = "addTile", propOrder = {
    "playerId",
    "tile",
    "sequenceIndex",
    "sequencePosition"
})
public class AddTile {

    protected int playerId;
    protected Tile tile;
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
     * Gets the value of the tile property.
     * 
     * @return
     *     possible object is
     *     {@link Tile }
     *     
     */
    public Tile getTile() {
        return tile;
    }

    /**
     * Sets the value of the tile property.
     * 
     * @param value
     *     allowed object is
     *     {@link Tile }
     *     
     */
    public void setTile(Tile value) {
        this.tile = value;
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
