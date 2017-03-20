package pl.kokokoko.persistance;

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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OwnerRepositoryTest {

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private CarRepository carRepository;

    @Before
    public void initDatabaseWithOwners() throws ParseException, DatatypeConfigurationException {

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

        List<CarEntity> owners1cars = new ArrayList<>();
        List<CarEntity> bmw = carRepository.findCar(null, null, "BMW", null, null,
                null, null, null);
        owners1cars.add(bmw.get(0));
        List<CarEntity> jeep = carRepository.findCar(null, null, "Jeep", null, null,
                null, null, null);
        owners1cars.add(jeep.get(0));
        List<CarEntity> owners2cars = new ArrayList<>();
        List<CarEntity> fiat = carRepository.findCar(null, null, "Fiat", null, null,
                null, null, null);
        owners2cars.add(fiat.get(0));

        OwnerEntity owner1 = new OwnerEntity();
        owner1.setFirstName("Adam");
        owner1.setLastName("Mickiewicz");
        owner1.setPhoneNumber("734567299");
        owner1.setCars(owners1cars);
        ownerRepository.addOwner(owner1);

        OwnerEntity owner2 = new OwnerEntity();
        owner2.setFirstName("Juliusz");
        owner2.setLastName("Słowacki");
        owner2.setPhoneNumber("723498444");
        owner2.setCars(owners2cars);
        ownerRepository.addOwner(owner2);
    }

    @Test
    public void shouldReturnAllOwners() {
        List<OwnerEntity> owners = ownerRepository.findOwner(null, null, null, null);
        Assert.assertEquals(2, owners.size());
    }

    @Test
    public void shouldFindOwner() {
        List<OwnerEntity> owners = ownerRepository.findOwner(null, "Juliusz", null, null);
        OwnerEntity juliusz = owners.get(0);

        Assert.assertEquals("Słowacki", juliusz.getLastName());
    }

    @Test
    public void shouldAddOwner() {
        OwnerEntity owner = new OwnerEntity();
        owner.setFirstName("Fiodor");
        owner.setLastName("Dostojewski");
        owner.setPhoneNumber("502567345");
        ownerRepository.addOwner(owner);

        Assert.assertEquals(3, (ownerRepository.findOwner(null, null, null,
                null)).size());
    }

    @Test
    public void shouldEditOwner() {
        List<OwnerEntity> owners = ownerRepository.findOwner(null, "Adam", null,
                null);
        OwnerEntity adam = owners.get(0);
        Long id = adam.getId();
        adam.setPhoneNumber("510456834");
        ownerRepository.editOwner(adam);

        List<OwnerEntity> owners2 = ownerRepository.findOwner(id, null, null, null);
        OwnerEntity owner = owners2.get(0);
        String phoneNumber = owner.getPhoneNumber();

        Assert.assertEquals("510456834", phoneNumber);
    }

    @Test
    public void shouldDeleteOwner() {
        List<OwnerEntity> owners = ownerRepository.findOwner(null, "Juliusz", null,
                null);
        OwnerEntity juliusz = owners.get(0);
        Long id = juliusz.getId();
        ownerRepository.deleteOwner(id);

        Assert.assertEquals(1, (ownerRepository.findOwner(null, null, null,
                null)).size());
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