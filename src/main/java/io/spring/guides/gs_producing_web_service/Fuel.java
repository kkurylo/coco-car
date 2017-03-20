//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.03.20 at 09:12:49 PM CET 
//


package io.spring.guides.gs_producing_web_service;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for fuel.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="fuel">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Petrol"/>
 *     &lt;enumeration value="Diesel"/>
 *     &lt;enumeration value="LPG"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "fuel")
@XmlEnum
public enum Fuel {

    @XmlEnumValue("Petrol")
    PETROL("Petrol"),
    @XmlEnumValue("Diesel")
    DIESEL("Diesel"),
    LPG("LPG");
    private final String value;

    Fuel(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Fuel fromValue(String v) {
        for (Fuel c : Fuel.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
