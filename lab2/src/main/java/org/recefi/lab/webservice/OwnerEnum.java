
package org.recefi.lab.webservice;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ownerEnum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ownerEnum"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="BLACK"/&gt;
 *     &lt;enumeration value="WHITE"/&gt;
 *     &lt;enumeration value="NONE"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "ownerEnum")
@XmlEnum
public enum OwnerEnum {

    BLACK,
    WHITE,
    NONE;

    public String value() {
        return name();
    }

    public static OwnerEnum fromValue(String v) {
        return valueOf(v);
    }

}
