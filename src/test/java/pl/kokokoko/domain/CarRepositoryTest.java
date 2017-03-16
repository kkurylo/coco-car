package pl.kokokoko.domain;

import io.spring.guides.gs_producing_web_service.Fuel;
import io.spring.guides.gs_producing_web_service.Type;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import javax.xml.datatype.DatatypeConfigurationException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CarRepositoryTest {

    @Autowired
    private CarRepository carRepository;

    @Before
    public void initDatabaseWithCars() throws ParseException, DatatypeConfigurationException {
        CarEntity car1 = new CarEntity();
        car1.setType(returnStringFromType(Type.LUXURY));
        car1.setMake("BMW");
        car1.setModel("Seria 3 E90");
        car1.setYear(2007);
        car1.setPrice(new BigDecimal("77000.00"));
        car1.setDoors(2);
        car1.setColor("Gold");
        car1.setFuel(returnStringFromFuel(Fuel.PETROL));
        car1.setFirstRegistration(returnDateFromString("20.06.2007 14:00:00"));
        car1.setFuelConsumption(14.0f);
        carRepository.addCar(car1);

        CarEntity car2 = new CarEntity();
        car2.setType(returnStringFromType(Type.SUV));
        car2.setMake("Jeep");
        car2.setModel("Grand Cherokee IV");
        car2.setYear(2011);
        car2.setPrice(new BigDecimal("95000.00"));
        car2.setDoors(5);
        car2.setColor("Silver");
        car2.setFuel(returnStringFromFuel(Fuel.PETROL));
        car2.setFirstRegistration(returnDateFromString("15.05.2012 12:00:00"));
        car2.setFuelConsumption(12.0f);
        carRepository.addCar(car2);

        CarEntity car3 = new CarEntity();
        car3.setType(returnStringFromType(Type.MICROCAR));
        car3.setMake("Fiat");
        car3.setModel("500");
        car3.setYear(2010);
        car3.setPrice(new BigDecimal("14700.00"));
        car3.setDoors(3);
        car3.setColor("Red");
        car3.setFuel(returnStringFromFuel(Fuel.PETROL));
        car3.setFirstRegistration(returnDateFromString("15.02.2010 12:30:00"));
        car3.setFuelConsumption(4.0f);
        carRepository.addCar(car3);
    }

    @Test
    public void shouldReturnAllCars() {
        List<CarEntity> cars = carRepository.findCar(null, null, null, null, null, null,
                null, null);
        Assert.assertEquals(3, cars.size());
    }

    @Test
    public void shouldFindCar() {
        List<CarEntity> cars = carRepository.findCar(null, null, "Jeep", null, null, null,
                null, null);
        CarEntity jeep = cars.get(0);

        Assert.assertEquals("Silver", jeep.getColor());
    }
    @Test
//    @Rollback(false)
    public void shouldAddCar() throws ParseException, DatatypeConfigurationException {
        CarEntity car = new CarEntity();
//        car1.setId(1L);
        car.setType(returnStringFromType(Type.HATCHBACK));
        car.setMake("Volkswagen");
        car.setModel("Golf VII");
        car.setYear(2013);
        car.setPrice(new BigDecimal("65000.00"));
        car.setDoors(5);
        car.setColor("White");
        car.setFuel(returnStringFromFuel(Fuel.DIESEL));
        car.setFirstRegistration(returnDateFromString("01.03.2013 14:00:00"));
        car.setFuelConsumption(6.4f);
        carRepository.addCar(car);

        Assert.assertEquals(4, (carRepository.findCar(null, null, null, null, null,
                null, null, null)).size());
    }

    @Test
    public void shouldEditCar() {
        List<CarEntity> cars = carRepository.findCar(null, null, "Fiat", null, null,
                null, null, null);
        CarEntity fiat = cars.get(0);
        Long id = fiat.getId();
        fiat.setColor("Pink");
        carRepository.editCar(fiat);

        List<CarEntity> cars2 = carRepository.findCar(id, null, null, null, null, null, null,
                null);
        String color = cars2.get(0).getColor();

        Assert.assertEquals("Pink", color);
    }

    @Test
    public void shouldDeleteCar() {
        List<CarEntity> cars = carRepository.findCar(null, null, null, null, null, null,
                null, null);
        Long id = cars.get(0).getId();
        carRepository.deleteCar(id);
        Assert.assertEquals(2, (carRepository.findCar(null, null, null, null, null,
                null, null, null)).size());
    }

    private Date returnDateFromString(String dateTime) throws ParseException, DatatypeConfigurationException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy kk:mm:ss");
        Date parseDate = simpleDateFormat.parse(dateTime);
        return parseDate;
    }

    private String returnStringFromFuel(Fuel fuel) {
        return fuel.value();
    }

    private String returnStringFromType(Type type) {
        return type.value();
    }

}