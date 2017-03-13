package pl.kokokoko;

import io.spring.guides.gs_producing_web_service.Fuel;
import io.spring.guides.gs_producing_web_service.Type;
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

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CarRepositoryTest {

    @Autowired
    private CarRepository carRepository;

    @Test
    public void shouldAddCar() throws ParseException, DatatypeConfigurationException {
        CarEntity car1 = new CarEntity();
        car1.setId(1L);
        car1.setType(returnStringFromType(Type.HATCHBACK));
        car1.setMake("Volkswagen");
        car1.setModel("Golf VII");
        car1.setYear(2013);
        car1.setPrice(new BigDecimal("65000.00"));
        car1.setDoors(5);
        car1.setColor("White");
        car1.setFuel(returnStringFromFuel(Fuel.DIESEL));
        car1.setFirstRegistration(returnDateFromString("01.03.2013 14:00:00"));
        car1.setFuelConsumption(6.4f);
        carRepository.addCar(car1);
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