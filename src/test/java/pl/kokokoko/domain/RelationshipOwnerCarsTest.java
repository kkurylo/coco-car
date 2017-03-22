package pl.kokokoko.domain;

import io.spring.guides.gs_producing_web_service.Fuel;
import io.spring.guides.gs_producing_web_service.Type;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import pl.kokokoko.persistance.CarEntity;
import pl.kokokoko.persistance.CarRepository;
import pl.kokokoko.persistance.OwnerEntity;
import pl.kokokoko.persistance.OwnerRepository;

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
@ActiveProfiles("test")
public class RelationshipOwnerCarsTest {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private RelationshipOwnerCar ownerCars;

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
        carRepository.add(car1);

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
        carRepository.add(car2);

        OwnerEntity owner1 = new OwnerEntity();
        owner1.setFirstName("Adam");
        owner1.setLastName("Mickiewicz");
        owner1.setPhoneNumber("734567299");
        ownerRepository.add(owner1);

        OwnerEntity owner2 = new OwnerEntity();
        owner2.setFirstName("Juliusz");
        owner2.setLastName("Słowacki");
        owner2.setPhoneNumber("723498444");
        ownerRepository.add(owner2);
    }

    @Test
    public void shouldAssignCar1ToOwner1() {
        List<OwnerEntity> owners = ownerRepository.find(null, "Adam", null, null);
        OwnerEntity ownerEntity = owners.get(0);
        Long owner_id = ownerEntity.getId();

        List<CarEntity> cars = carRepository.find(null, null, "BMW", null, null, null,
                null, null);
        CarEntity carEntity = cars.get(0);
        Long car_id = carEntity.getId();

        ownerCars.assignCarToOwner(car_id, owner_id);

        List<CarEntity> cars1 = carRepository.find(car_id, null, null, null, null, null,
                null, null);
        CarEntity car1 = cars1.get(0);
        OwnerEntity owner = car1.getOwner();

        Assert.assertEquals(owner_id, owner.getId());
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