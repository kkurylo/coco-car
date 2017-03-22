package pl.kokokoko.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.kokokoko.persistance.CarEntity;
import pl.kokokoko.persistance.CarRepository;
import pl.kokokoko.persistance.OwnerEntity;
import pl.kokokoko.persistance.OwnerRepository;


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
        CarEntity car = carRepository.findById(car_id);
        OwnerEntity owner = ownerRepository.referenceById(owner_id);
        car.setOwner(owner);
        carRepository.update(car);
    }
}
