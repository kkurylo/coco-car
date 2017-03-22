package pl.kokokoko.helper;

import io.spring.guides.gs_producing_web_service.Car;
import io.spring.guides.gs_producing_web_service.Fuel;
import io.spring.guides.gs_producing_web_service.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.kokokoko.persistance.CarEntity;
import pl.kokokoko.persistance.OwnerEntity;
import pl.kokokoko.persistance.OwnerRepository;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Component
public class CarConverter {

    @Autowired
    private OwnerRepository ownerRepository;

    public CarEntity convertToCarEntity(Car c) throws DatatypeConfigurationException {
        CarEntity ce = new CarEntity();
        ce.setId(c.getId());
        ce.setType(returnStringFromType(c.getType()));
        ce.setMake(c.getMake());
        ce.setModel(c.getModel());
        ce.setYear(c.getYear());
        ce.setPrice(c.getPrice());
        ce.setDoors(c.getDoors());
        ce.setColor(c.getColor());
        ce.setFuel(returnStringFromFuel(c.getFuel()));
        ce.setFirstRegistration(returnDateFromXMLDate(c.getFirstRegistration()));
        ce.setFuelConsumption(c.getFuelConsumption());
        if (c.getOwnerId() != null) {
            OwnerEntity ownerEntity = ownerRepository.findOwnerById(c.getOwnerId());
            ce.setOwner(ownerEntity);
        }
        return ce;
    }

    public Car convertToCar(CarEntity ce) throws DatatypeConfigurationException {
        Car c = new Car();
        c.setId(ce.getId());
        c.setType(returnTypeFromString(ce.getType()));
        c.setMake(ce.getMake());
        c.setModel(ce.getModel());
        c.setYear(ce.getYear());
        c.setPrice(ce.getPrice());
        c.setDoors(ce.getDoors());
        c.setColor(ce.getColor());
        c.setFuel(returnFuelFromString(ce.getFuel()));
        c.setFirstRegistration(returnXMLGregorianCalendarFromDate(ce.getFirstRegistration()));
        c.setFuelConsumption(ce.getFuelConsumption());
        if (ce.getOwner() != null) c.setOwnerId(ce.getOwner().getId());
        return c;
    }

    private Date returnDateFromXMLDate(XMLGregorianCalendar xmlDate) throws DatatypeConfigurationException {
        Date date = xmlDate.toGregorianCalendar().getTime();
        return date;
    }

    private XMLGregorianCalendar returnXMLGregorianCalendarFromDate(Date date) throws DatatypeConfigurationException {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(date);
        XMLGregorianCalendar xmlDateTime = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);
        return xmlDateTime;
    }

    private String returnStringFromFuel(Fuel fuel) {
        return fuel.value();
    }

    private Fuel returnFuelFromString(String fuel) {
        return Fuel.fromValue(fuel);
    }

    private String returnStringFromType(Type type) {
        return type.value();
    }

    private Type returnTypeFromString(String type) {
        return Type.fromValue(type);
    }

}


