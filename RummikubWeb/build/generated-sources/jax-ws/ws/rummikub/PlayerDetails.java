
package ws.rummikub;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for playerDetails complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="playerDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numberOfTiles" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="playedFirstSequence" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="status" type="{http://rummikub.ws/}playerStatus" minOccurs="0"/>
 *         &lt;element name="tiles" type="{http://rummikub.ws/}tile" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="type" type="{http://rummikub.ws/}playerType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "playerDetails", propOrder = {
    "name",
    "numberOfTiles",
    "playedFirstSequence",
    "status",
    "tiles",
    "type"
})
public class PlayerDetails {

    protected String name;
    protected int numberOfTiles;
    protected boolean playedFirstSequence;
    protected PlayerStatus status;
    @XmlElement(nillable = true)
    protected List<Tile> tiles;
    protected PlayerType type;

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the numberOfTiles property.
     * 
     */
    public int getNumberOfTiles() {
        return numberOfTiles;
    }

    /**
     * Sets the value of the numberOfTiles property.
     * 
     */
    public void setNumberOfTiles(int value) {
        this.numberOfTiles = value;
    }

    /**
     * Gets the value of the playedFirstSequence property.
     * 
     */
    public boolean isPlayedFirstSequence() {
        return playedFirstSequence;
    }

    /**
     * Sets the value of the playedFirstSequence property.
     * 
     */
    public void setPlayedFirstSequence(boolean value) {
        this.playedFirstSequence = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link PlayerStatus }
     *     
     */
    public PlayerStatus getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link PlayerStatus }
     *     
     */
    public void setStatus(PlayerStatus value) {
        this.status = value;
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
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link PlayerType }
     *     
     */
    public PlayerType getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link PlayerType }
     *     
     */
    public void setType(PlayerType value) {
        this.type = value;
    }

}
