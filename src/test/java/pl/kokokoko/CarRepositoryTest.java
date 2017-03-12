package pl.kokokoko;

import io.spring.guides.gs_producing_web_service.Car;
import io.spring.guides.gs_producing_web_service.Fuel;
import io.spring.guides.gs_producing_web_service.Type;
import org.junit.Before;
import org.junit.Test;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CarRepositoryTest {
    @Before
    public void byleco() throws ParseException, DatatypeConfigurationException {
        CarRepository carRepository = new CarRepository();
        carRepository.initData();
    }

    @Test
    public void findCar() throws Exception {
        CarRepository carRepository = new CarRepository();
        List<Car> result = carRepository.findCar(null, null, "Jeep", 2005, null, null,
                null, "Silver");

        assertTrue(result.size() == 1);
        assertTrue(result.get(0).getId() == 2);
    }

    @Test
    public void findCar2() throws Exception {
        CarRepository carRepository = new CarRepository();
        List<Car> result = carRepository.findCar(null, null, null, null, null, null,
                null, null);

        assertTrue(result.size() == 4);
    }

    @Test
    public void deleteCar() throws Exception {
        CarRepository carRepository = new CarRepository();
        carRepository.deleteCar(3);

        assertEquals(3, CarRepository.cars.size());
    }

    @Test
    public void addCar() throws Exception {
        CarRepository carRepository = new CarRepository();
        carRepository.addCar(Type.FAMILY_CAR, "Toyota", "Picnic", 1998, new BigDecimal("8900"),
                5, "Blue", Fuel.DIESEL, returnXMLDateTime("20.01.1998 12:00:00"), 7.5f);

        assertEquals(5, CarRepository.cars.size());
    }

    @Test
    public void editCar() throws Exception {
        CarRepository carRepository = new CarRepository();
        carRepository.editCar(1, null, null, null, null, null, null, "Pink",
                null, null, null);

        assertTrue(CarRepository.cars.get(0).getColor().equals("Pink"));

    }

    private XMLGregorianCalendar returnXMLDateTime(String dateTime) throws ParseException, DatatypeConfigurationException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy kk:mm:ss");
        Date parseDate = simpleDateFormat.parse(dateTime);
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(parseDate);
        XMLGregorianCalendar xmlDateTime = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);
        return xmlDateTime;
    }

}