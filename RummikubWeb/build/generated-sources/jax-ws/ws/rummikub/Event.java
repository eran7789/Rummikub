
package ws.rummikub;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for event complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="event">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="playerName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sourceSequenceIndex" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="sourceSequencePosition" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="targetSequenceIndex" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="targetSequencePosition" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="tiles" type="{http://rummikub.ws/}tile" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="timeout" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="type" type="{http://rummikub.ws/}eventType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "event", propOrder = {
    "id",
    "playerName",
    "sourceSequenceIndex",
    "sourceSequencePosition",
    "targetSequenceIndex",
    "targetSequencePosition",
    "tiles",
    "timeout",
    "type"
})
public class Event {

    protected int id;
    protected String playerName;
    protected int sourceSequenceIndex;
    protected int sourceSequencePosition;
    protected int targetSequenceIndex;
    protected int targetSequencePosition;
    @XmlElement(nillable = true)
    protected List<Tile> tiles;
    protected int timeout;
    protected EventType type;

    /**
     * Gets the value of the id property.
     * 
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setId(int value) {
        this.id = value;
    }

    /**
     * Gets the value of the playerName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * Sets the value of the playerName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlayerName(String value) {
        this.playerName = value;
    }

    /**
     * Gets the value of the sourceSequenceIndex property.
     * 
     */
    public int getSourceSequenceIndex() {
        return sourceSequenceIndex;
    }

    /**
     * Sets the value of the sourceSequenceIndex property.
     * 
     */
    public void setSourceSequenceIndex(int value) {
        this.sourceSequenceIndex = value;
    }

    /**
     * Gets the value of the sourceSequencePosition property.
     * 
     */
    public int getSourceSequencePosition() {
        return sourceSequencePosition;
    }

    /**
     * Sets the value of the sourceSequencePosition property.
     * 
     */
    public void setSourceSequencePosition(int value) {
        this.sourceSequencePosition = value;
    }

    /**
     * Gets the value of the targetSequenceIndex property.
     * 
     */
    public int getTargetSequenceIndex() {
        return targetSequenceIndex;
    }

    /**
     * Sets the value of the targetSequenceIndex property.
     * 
     */
    public void setTargetSequenceIndex(int value) {
        this.targetSequenceIndex = value;
    }

    /**
     * Gets the value of the targetSequencePosition property.
     * 
     */
    public int getTargetSequencePosition() {
        return targetSequencePosition;
    }

    /**
     * Sets the value of the targetSequencePosition property.
     * 
     */
    public void setTargetSequencePosition(int value) {
        this.targetSequencePosition = value;
    }

    /**
     * Gets the value of the tiles property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the tiles property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTiles().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Tile }
     * 
     * 
     */
    public List<Tile> getTiles() {
        if (tiles == null) {
            tiles = new ArrayList<Tile>();
        }
        return this.tiles;
    }

    /**
     * Gets the value of the timeout property.
     * 
     */
    public int getTimeout() {
        return timeout;
    }

    /**
     * Sets the value of the timeout property.
     * 
     */
    public void setTimeout(int value) {
        this.timeout = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link EventType }
     *     
     */
    public EventType getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link EventType }
     *     
     */
    public void setType(EventType value) {
        this.type = value;
    }

}
