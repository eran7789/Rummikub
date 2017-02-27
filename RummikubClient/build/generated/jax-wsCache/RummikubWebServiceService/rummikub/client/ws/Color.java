
package rummikub.client.ws;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for color.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="color">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="BLACK"/>
 *     &lt;enumeration value="BLUE"/>
 *     &lt;enumeration value="RED"/>
 *     &lt;enumeration value="YELLOW"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "color")
@XmlEnum
public enum Color {

    BLACK,
    BLUE,
    RED,
    YELLOW;

    public String value() {
        return name();
    }

    public static Color fromValue(String v) {
        return valueOf(v);
    }

}
