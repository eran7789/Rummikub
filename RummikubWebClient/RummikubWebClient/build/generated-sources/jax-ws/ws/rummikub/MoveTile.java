
package ws.rummikub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for moveTile complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="moveTile">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="playerId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="sourceSequenceIndex" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="sourceSequencePosition" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="targetSequenceIndex" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="targetSequencePosition" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "moveTile", propOrder = {
    "playerId",
    "sourceSequenceIndex",
    "sourceSequencePosition",
    "targetSequenceIndex",
    "targetSequencePosition"
})
public class MoveTile {

    protected int playerId;
    protected int sourceSequenceIndex;
    protected int sourceSequencePosition;
    protected int targetSequenceIndex;
    protected int targetSequencePosition;

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

}
