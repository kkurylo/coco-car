package pl.kokokoko.helper;

import io.spring.guides.gs_producing_web_service.Owner;
import org.springframework.transaction.annotation.Transactional;
import pl.kokokoko.persistance.CarEntity;
import pl.kokokoko.persistance.OwnerEntity;

import java.util.List;

public class OwnerConverter {

    public OwnerEntity convertToOwnerEntity(Owner o) {
        OwnerEntity oe = new OwnerEntity();
        oe.setId(o.getId());
        oe.setFirstName(o.getFirstName());
        oe.setLastName(o.getLastName());
        oe.setPhoneNumber(o.getPhoneNumber());
        return oe;
    }

    @Transactional
    public Owner convertToOwner(OwnerEntity oe) {
        Owner o = new Owner();
        o.setId(oe.getId());
        o.setFirstName(oe.getFirstName());
        o.setLastName(oe.getLastName());
        o.setPhoneNumber(oe.getPhoneNumber());
        List<Long> carId = o.getCarId();
        for (CarEntity carEntity : oe.getCars()) {
            carId.add(carEntity.getId());
        }
        return o;
    }
}
