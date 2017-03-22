package pl.kokokoko.helper;

import io.spring.guides.gs_producing_web_service.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.kokokoko.persistance.CarEntity;
import pl.kokokoko.persistance.CarRepository;
import pl.kokokoko.persistance.OwnerEntity;

import java.util.ArrayList;
import java.util.List;

@Component
public class OwnerConverter {

    @Autowired
    private CarRepository carRepository;

    public OwnerEntity convertToOwnerEntity(Owner o) {
        OwnerEntity oe = new OwnerEntity();
        oe.setId(o.getId());
        oe.setFirstName(o.getFirstName());
        oe.setLastName(o.getLastName());
        oe.setPhoneNumber(o.getPhoneNumber());

        List<CarEntity> carEntityList = new ArrayList<>();
        for (Long car_id : o.getCarId()) {
            List<CarEntity> carsEntity = carRepository.findCar(car_id, null, null, null, null,
                    null, null, null);
            CarEntity carEntity = carsEntity.get(0);
            carEntityList.add(carEntity);
        }
        oe.setCars(carEntityList);
        return oe;
    }

    public Owner convertToOwner(OwnerEntity oe) {
        Owner o = new Owner();
        o.setId(oe.getId());
        o.setFirstName(oe.getFirstName());
        o.setLastName(oe.getLastName());
        o.setPhoneNumber(oe.getPhoneNumber());
        List<Long> carId = o.getCarId();
        oe.getCars().forEach(carEntity -> carId.add(carEntity.getId()));
        return o;
    }
}
