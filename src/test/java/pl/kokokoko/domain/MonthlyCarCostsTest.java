package pl.kokokoko.domain;

import io.spring.guides.gs_producing_web_service.Car;
import io.spring.guides.gs_producing_web_service.Fuel;
import io.spring.guides.gs_producing_web_service.Town;
import io.spring.guides.gs_producing_web_service.Type;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import pl.kokokoko.helper.CarConverter;
import pl.kokokoko.persistance.CarEntity;
import pl.kokokoko.persistance.CarRepository;

import javax.transaction.Transactional;
import javax.xml.datatype.DatatypeConfigurationException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class MonthlyCarCostsTest {

    @Autowired
    private MonthlyCarCosts monthlyCarCosts;
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private CarConverter carConverter;

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
    }

    @Test
    public void shouldCalculateForGoldBMW() throws DatatypeConfigurationException {
        List<CarEntity> cars = carRepository.findCar(null, null, "BMW", null, null, null,
                null, null);
        CarEntity car = cars.get(0);

        Car bmw = carConverter.convertToCar(car);

        float cost = monthlyCarCosts.calculateMonthlyCarPrice(bmw.getId(), Town.WARSAW);
        double cost1 = cost;

        assertThat(cost1).isCloseTo(25.090, offset(0.001));
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