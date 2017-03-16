//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.03.15 at 11:55:13 PM CET 
//


package io.spring.guides.gs_producing_web_service;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="type">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Microcar"/>
 *     &lt;enumeration value="Hatchback"/>
 *     &lt;enumeration value="Family car"/>
 *     &lt;enumeration value="Luxury"/>
 *     &lt;enumeration value="SUV"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "type")
@XmlEnum
public enum Type {

    @XmlEnumValue("Microcar")
    MICROCAR("Microcar"),
    @XmlEnumValue("Hatchback")
    HATCHBACK("Hatchback"),
    @XmlEnumValue("Family car")
    FAMILY_CAR("Family car"),
    @XmlEnumValue("Luxury")
    LUXURY("Luxury"),
    SUV("SUV");
    private final String value;

    Type(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Type fromValue(String v) {
        for (Type c : Type.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
