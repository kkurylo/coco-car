package pl.kokokoko.persistance;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "Car")
public class CarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;
    private String make;
    private String model;
    private Integer year;
    private BigDecimal price;
    private Integer doors;
    private String color;
    private String fuel;
    private Date firstRegistration;
    private Float fuelConsumption;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private OwnerEntity owner;

    public CarEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getDoors() {
        return doors;
    }

    public void setDoors(Integer doors) {
        this.doors = doors;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public Date getFirstRegistration() {
        return firstRegistration;
    }

    public void setFirstRegistration(Date firstRegistration) {
        this.firstRegistration = firstRegistration;
    }

    public Float getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(Float fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    public OwnerEntity getOwner() {
        return owner;
    }

    public void setOwner(OwnerEntity owner) {
        this.owner = owner;
    }
}
