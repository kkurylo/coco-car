package pl.kokokoko.controller;

import io.spring.guides.gs_producing_web_service.AssignCarToOwnerRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import pl.kokokoko.domain.RelationshipOwnerCar;

@Endpoint
@Transactional
public class RelationshipOwnerCarEndpoint {

    private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

    private final RelationshipOwnerCar ownerCars;

    @Autowired
    public RelationshipOwnerCarEndpoint(RelationshipOwnerCar ownerCars) {
        this.ownerCars = ownerCars;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "assignCarToOwnerRequest")
    @ResponsePayload
    public void assignCarToOwner(@RequestPayload AssignCarToOwnerRequest request) {
        ownerCars.assignCarToOwner(request.getCarId(), request.getOwnerId());
    }
}
