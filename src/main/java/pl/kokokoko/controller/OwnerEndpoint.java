package pl.kokokoko.controller;

import io.spring.guides.gs_producing_web_service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import pl.kokokoko.helper.OwnerConverter;
import pl.kokokoko.persistance.OwnerEntity;
import pl.kokokoko.persistance.OwnerRepository;

import java.util.List;

@Endpoint
public class OwnerEndpoint {

    private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

    private OwnerRepository ownerRepository;
    private OwnerConverter ownerConverter;

    @Autowired
    public OwnerEndpoint(OwnerRepository ownerRepository, OwnerConverter ownerConverter) {
        this.ownerRepository = ownerRepository;
        this.ownerConverter = ownerConverter;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "findOwnerRequest")
    @ResponsePayload
    public FindOwnerResponse findOwner(@RequestPayload FindOwnerRequest request) {
        FindOwnerResponse response = new FindOwnerResponse();
        List<OwnerEntity> owners = ownerRepository.findOwner(request.getId(), request.getFirstName(),
                request.getLastName(), request.getPhoneNumber());
        List<Owner> responseOwner = response.getOwner();
        for (OwnerEntity ownerEntity : owners) {
            Owner owner = ownerConverter.convertToOwner(ownerEntity);
            responseOwner.add(owner);
        }
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addOwnerRequest")
    @ResponsePayload
    public AddOwnerResponse addOwner(@RequestPayload AddOwnerRequest request) {
        AddOwnerResponse response = new AddOwnerResponse();
        OwnerEntity ownerEntity = ownerConverter.convertToOwnerEntity(request.getOwner());
        OwnerEntity oe = ownerRepository.addOwner(ownerEntity);
        Owner owner = ownerConverter.convertToOwner(oe);
        response.setOwner(owner);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "editOwnerRequest")
    @ResponsePayload
    public EditOwnerResponse editOwner(@RequestPayload EditOwnerRequest request) {
        EditOwnerResponse response = new EditOwnerResponse();
        OwnerEntity ownerEntity = ownerConverter.convertToOwnerEntity(request.getOwner());
        OwnerEntity oe = ownerRepository.editOwner(ownerEntity);
        Owner owner = ownerConverter.convertToOwner(oe);
        response.setOwner(owner);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteOwnerRequest")
    @ResponsePayload
    public void deleteOwner(@RequestPayload DeleteOwnerRequest request) {
        ownerRepository.deleteOwner(request.getId());
    }
}
