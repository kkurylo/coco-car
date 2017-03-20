package pl.kokokoko.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.kokokoko.persistance.CarEntity;
import pl.kokokoko.persistance.CarRepository;
import pl.kokokoko.persistance.OwnerEntity;
import pl.kokokoko.persistance.OwnerRepository;

import java.util.List;

@Component
public class RelationshipOwnerCar {

    private final CarRepository carRepository;
    private final OwnerRepository ownerRepository;

    @Autowired
    public RelationshipOwnerCar(CarRepository carRepository, OwnerRepository ownerRepository) {
        this.carRepository = carRepository;
        this.ownerRepository = ownerRepository;
    }

    public void assignCarToOwner(Long car_id, Long owner_id) {
        List<CarEntity> cars = carRepository.findCar(car_id, null, null, null, null, null,
                null, null);
        CarEntity car = cars.get(0);
        List<OwnerEntity> owners = ownerRepository.findOwner(owner_id, null, null, null);
        OwnerEntity owner = owners.get(0);
        car.setOwner(owner);
        carRepository.editCar(car);
    }
}
