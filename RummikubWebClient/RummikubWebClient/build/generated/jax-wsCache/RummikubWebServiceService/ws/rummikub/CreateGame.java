
package ws.rummikub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for createGame complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="createGame">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="humanPlayers" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="computerizedPlayers" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "createGame", propOrder = {
    "name",
    "humanPlayers",
    "computerizedPlayers"
})
public class CreateGame {

    protected String name;
    protected int humanPlayers;
    protected int computerizedPlayers;

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
     * Gets the value of the humanPlayers property.
     * 
     */
    public int getHumanPlayers() {
        return humanPlayers;
    }

    /**
     * Sets the value of the humanPlayers property.
     * 
     */
    public void setHumanPlayers(int value) {
        this.humanPlayers = value;
    }

    /**
     * Gets the value of the computerizedPlayers property.
     * 
     */
    public int getComputerizedPlayers() {
        return computerizedPlayers;
    }

    /**
     * Sets the value of the computerizedPlayers property.
     * 
     */
    public void setComputerizedPlayers(int value) {
        this.computerizedPlayers = value;
    }

}
