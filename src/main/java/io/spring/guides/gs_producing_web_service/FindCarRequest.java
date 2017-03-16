//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.03.16 at 11:22:46 PM CET 
//


package io.spring.guides.gs_producing_web_service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.math.BigDecimal;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="type" type="{http://spring.io/guides/gs-producing-web-service}type" minOccurs="0"/>
 *         &lt;element name="make" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="yearFrom" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="yearTo" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="priceFrom" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="priceTo" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="color" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "id",
        "type",
        "make",
        "yearFrom",
        "yearTo",
        "priceFrom",
        "priceTo",
        "color"
})
@XmlRootElement(name = "findCarRequest")
public class FindCarRequest {

    protected Long id;
    protected Type type;
    protected String make;
    protected Integer yearFrom;
    protected Integer yearTo;
    protected BigDecimal priceFrom;
    protected BigDecimal priceTo;
    protected String color;

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setId(Long value) {
        this.id = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link Type }
     *     
     */
    public Type getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link Type }
     *     
     */
    public void setType(Type value) {
        this.type = value;
    }

    /**
     * Gets the value of the make property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMake() {
        return make;
    }

    /**
     * Sets the value of the make property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMake(String value) {
        this.make = value;
    }

    /**
     * Gets the value of the yearFrom property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getYearFrom() {
        return yearFrom;
    }

    /**
     * Sets the value of the yearFrom property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setYearFrom(Integer value) {
        this.yearFrom = value;
    }

    /**
     * Gets the value of the yearTo property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getYearTo() {
        return yearTo;
    }

    /**
     * Sets the value of the yearTo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setYearTo(Integer value) {
        this.yearTo = value;
    }

    /**
     * Gets the value of the priceFrom property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPriceFrom() {
        return priceFrom;
    }

    /**
     * Sets the value of the priceFrom property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPriceFrom(BigDecimal value) {
        this.priceFrom = value;
    }

    /**
     * Gets the value of the priceTo property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPriceTo() {
        return priceTo;
    }

    /**
     * Sets the value of the priceTo property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPriceTo(BigDecimal value) {
        this.priceTo = value;
    }

    /**
     * Gets the value of the color property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getColor() {
        return color;
    }

    /**
     * Sets the value of the color property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setColor(String value) {
        this.color = value;
    }

}
