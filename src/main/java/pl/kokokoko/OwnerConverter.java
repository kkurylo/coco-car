package pl.kokokoko;

import io.spring.guides.gs_producing_web_service.Owner;
import pl.kokokoko.domain.OwnerEntity;

public class OwnerConverter {

    public OwnerEntity convertToOwnerEntity(Owner o) {
        OwnerEntity oe = new OwnerEntity();
        oe.setId(o.getId());
        oe.setFirstName(o.getFirstName());
        oe.setLastName(o.getLastName());
        oe.setPhoneNumber(o.getPhoneNumber());
        return oe;
    }

    public Owner convertToOwner(OwnerEntity oe) {
        Owner o = new Owner();
        o.setId(oe.getId());
        o.setFirstName(oe.getFirstName());
        o.setLastName(oe.getLastName());
        o.setPhoneNumber(oe.getPhoneNumber());
        return o;
    }
}
