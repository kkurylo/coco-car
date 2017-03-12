//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.03.11 at 11:45:08 PM CET 
//


package io.spring.guides.gs_producing_web_service;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;


/**
 * <p>Java class for car complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="car">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="type" type="{http://spring.io/guides/gs-producing-web-service}type"/>
 *         &lt;element name="make" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="model" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="year" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="price" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="doors" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="color" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fuel" type="{http://spring.io/guides/gs-producing-web-service}fuel"/>
 *         &lt;element name="firstRegistration" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="fuelConsumption" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "car", propOrder = {
        "id",
        "type",
        "make",
        "model",
        "year",
        "price",
        "doors",
        "color",
        "fuel",
        "firstRegistration",
        "fuelConsumption"
})
public class Car {

    protected long id;
    @XmlElement(required = true)
    protected Type type;
    @XmlElement(required = true)
    protected String make;
    @XmlElement(required = true)
    protected String model;
    protected int year;
    @XmlElement(required = true)
    protected BigDecimal price;
    protected int doors;
    @XmlElement(required = true)
    protected String color;
    @XmlElement(required = true)
    protected Fuel fuel;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar firstRegistration;
    protected float fuelConsumption;

    /**
     * Gets the value of the id property.
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     */
    public void setId(long value) {
        this.id = value;
    }

    /**
     * Gets the value of the type property.
     *
     * @return possible object is
     * {@link Type }
     */
    public Type getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     *
     * @param value allowed object is
     *              {@link Type }
     */
    public void setType(Type value) {
        this.type = value;
    }

    /**
     * Gets the value of the make property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getMake() {
        return make;
    }

    /**
     * Sets the value of the make property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setMake(String value) {
        this.make = value;
    }

    /**
     * Gets the value of the model property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getModel() {
        return model;
    }

    /**
     * Sets the value of the model property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setModel(String value) {
        this.model = value;
    }

    /**
     * Gets the value of the year property.
     */
    public int getYear() {
        return year;
    }

    /**
     * Sets the value of the year property.
     */
    public void setYear(int value) {
        this.year = value;
    }

    /**
     * Gets the value of the price property.
     *
     * @return possible object is
     * {@link BigDecimal }
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Sets the value of the price property.
     *
     * @param value allowed object is
     *              {@link BigDecimal }
     */
    public void setPrice(BigDecimal value) {
        this.price = value;
    }

    /**
     * Gets the value of the doors property.
     */
    public int getDoors() {
        return doors;
    }

    /**
     * Sets the value of the doors property.
     */
    public void setDoors(int value) {
        this.doors = value;
    }

    /**
     * Gets the value of the color property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getColor() {
        return color;
    }

    /**
     * Sets the value of the color property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setColor(String value) {
        this.color = value;
    }

    /**
     * Gets the value of the fuel property.
     *
     * @return possible object is
     * {@link Fuel }
     */
    public Fuel getFuel() {
        return fuel;
    }

    /**
     * Sets the value of the fuel property.
     *
     * @param value allowed object is
     *              {@link Fuel }
     */
    public void setFuel(Fuel value) {
        this.fuel = value;
    }

    /**
     * Gets the value of the firstRegistration property.
     *
     * @return possible object is
     * {@link XMLGregorianCalendar }
     */
    public XMLGregorianCalendar getFirstRegistration() {
        return firstRegistration;
    }

    /**
     * Sets the value of the firstRegistration property.
     *
     * @param value allowed object is
     *              {@link XMLGregorianCalendar }
     */
    public void setFirstRegistration(XMLGregorianCalendar value) {
        this.firstRegistration = value;
    }

    /**
     * Gets the value of the fuelConsumption property.
     */
    public float getFuelConsumption() {
        return fuelConsumption;
    }

    /**
     * Sets the value of the fuelConsumption property.
     */
    public void setFuelConsumption(float value) {
        this.fuelConsumption = value;
    }

}