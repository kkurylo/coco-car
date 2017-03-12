package pl.kokokoko;

import io.spring.guides.gs_producing_web_service.Car;
import io.spring.guides.gs_producing_web_service.Fuel;
import io.spring.guides.gs_producing_web_service.Type;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Component
public class CarRepository {
    static final List<Car> cars = new ArrayList<>();
    private static long currentCarId = 5;

    @PostConstruct
    public void initData() throws ParseException, DatatypeConfigurationException {
        cars.clear();

        Car c1 = new Car();
        c1.setId(1);
        c1.setType(Type.HATCHBACK);
        c1.setMake("Volkswagen");
        c1.setModel("Golf VII");
        c1.setYear(2013);
        c1.setPrice(new BigDecimal("65000.00"));
        c1.setDoors(5);
        c1.setColor("White");
        c1.setFuel(Fuel.DIESEL);
        c1.setFirstRegistration(returnXMLDateTime("01.03.2013 14:00:00"));
        c1.setFuelConsumption(6.4f);

        cars.add(c1);

        Car c2 = new Car();
        c2.setId(2);
        c2.setType(Type.SUV);
        c2.setMake("Jeep");
        c2.setModel("Grand Cherokee IV");
        c2.setYear(2011);
        c2.setPrice(new BigDecimal("95000.00"));
        c2.setDoors(5);
        c2.setColor("Silver");
        c2.setFuel(Fuel.PETROL);
        c2.setFirstRegistration(returnXMLDateTime("15.05.2012 12:00:00"));
        c2.setFuelConsumption(12.0f);

        cars.add(c2);

        Car c3 = new Car();
        c3.setId(3);
        c3.setType(Type.LUXURY);
        c3.setMake("BMW");
        c3.setModel("Seria 3 E90");
        c3.setYear(2007);
        c3.setPrice(new BigDecimal("77000.00"));
        c3.setDoors(2);
        c3.setColor("Gold");
        c3.setFuel(Fuel.PETROL);
        c3.setFirstRegistration(returnXMLDateTime("20.06.2007 14:00:00"));
        c3.setFuelConsumption(14.0f);

        cars.add(c3);

        Car c4 = new Car();
        c4.setId(4);
        c4.setType(Type.MICROCAR);
        c4.setMake("Fiat");
        c4.setModel("500");
        c4.setYear(2010);
        c4.setPrice(new BigDecimal("14700.00"));
        c4.setDoors(3);
        c4.setColor("Red");
        c4.setFuel(Fuel.PETROL);
        c4.setFirstRegistration(returnXMLDateTime("15.02.2010 12:30:00"));
        c4.setFuelConsumption(4.0f);

        cars.add(c4);
    }

    private XMLGregorianCalendar returnXMLDateTime(String dateTime) throws ParseException, DatatypeConfigurationException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy kk:mm:ss");
        Date parseDate = simpleDateFormat.parse(dateTime);
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(parseDate);
        XMLGregorianCalendar xmlDateTime = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);
        return xmlDateTime;
    }

    public List<Car> findCar(Long id, Type type, String make, Integer yearFrom, Integer yearTo, BigDecimal priceFrom,
                             BigDecimal priceTo, String color) throws ParseException, DatatypeConfigurationException {
        List<Car> foundCars = new ArrayList<>();

        for (Car car : cars) {
            if ((id == null || id.equals(car.getId())) &&
                    (type == null || type.equals(car.getType())) &&
                    (make == null || make.equals(car.getMake())) &&
                    (yearFrom == null || yearFrom <= car.getYear()) &&
                    (yearTo == null || yearTo >= car.getYear()) &&
                    (priceFrom == null || priceFrom.compareTo(car.getPrice()) == 0 || priceFrom.compareTo(car.getPrice()) == 1) &&
                    (priceTo == null || priceTo.compareTo(car.getPrice()) == 0 || priceTo.compareTo(car.getPrice()) == -1) &&
                    (color == null || color.equals(car.getColor()))) {
                foundCars.add(car);
            }
        }
        return foundCars;
    }

    public void deleteCar(long id) {
        for (Car car : cars) {
            if (id == car.getId()) {
                int indexOf = cars.indexOf(car);
                cars.remove(indexOf);
            }
        }
    }

    public Car addCar(Type type, String make, String model, int year, BigDecimal price, int doors, String color,
                      Fuel fuel, XMLGregorianCalendar firstRegistration, Float fuelConsumption) {
        Car car = new Car();
        car.setId(currentCarId++);
        car.setType(type);
        car.setMake(make);
        car.setModel(model);
        car.setYear(year);
        car.setPrice(price);
        car.setDoors(doors);
        car.setColor(color);
        car.setFuel(fuel);
        car.setFirstRegistration(firstRegistration);
        car.setFuelConsumption(fuelConsumption);
        cars.add(car);
        return car;
    }

    public Car editCar(long id, Type type, String make, String model, Integer year, BigDecimal price, Integer doors, String color,
                       Fuel fuel, XMLGregorianCalendar firstRegistration, Float fuelConsumption) {
        for (Car car : cars) {
            if (id == car.getId()) {
                if (type != null) car.setType(type);
                if (make != null) car.setMake(make);
                if (model != null) car.setModel(model);
                if (year != null) car.setYear(year);
                if (price != null) car.setPrice(price);
                if (doors != null) car.setDoors(doors);
                if (color != null) car.setColor(color);
                if (fuel != null) car.setFuel(fuel);
                if (firstRegistration != null) car.setFirstRegistration(firstRegistration);
                if (fuelConsumption != null) car.setFuelConsumption(fuelConsumption);
                return car;
            }
        }
        return null;
    }
}
