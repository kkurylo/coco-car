package pl.kokokoko;

import io.spring.guides.gs_producing_web_service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.xml.datatype.DatatypeConfigurationException;
import java.text.ParseException;
import java.util.List;

@Endpoint
public class CarEndpoint {
    private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

    private CarRepository carRepository;

    @Autowired
    public CarEndpoint(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "findCarRequest")
    @ResponsePayload
    public FindCarResponse findCar(@RequestPayload FindCarRequest request) throws ParseException, DatatypeConfigurationException {
        FindCarResponse response = new FindCarResponse();
        List<Car> cars = carRepository.findCar(request.getId(), request.getType(), request.getMake(),
                request.getYearFrom(), request.getYearTo(), request.getPriceFrom(), request.getPriceTo(),
                request.getColor());
        List<Car> responseCars = response.getCars();
        responseCars.addAll(cars);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteCarRequest")
    @ResponsePayload
    public void deleteCar(@RequestPayload DeleteCarRequest request) {
        carRepository.deleteCar(request.getId());
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addCarRequest")
    @ResponsePayload
    public AddCarResponse addCar(@RequestPayload AddCarRequest request) {
        AddCarResponse response = new AddCarResponse();
        Car car = carRepository.addCar(request.getType(), request.getMake(), request.getModel(), request.getYear(),
                request.getPrice(), request.getDoors(), request.getColor(), request.getFuel(), request.getFirstRegistration(),
                request.getFuelConsumption());
        response.setCar(car);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "editCarRequest")
    @ResponsePayload
    public EditCarResponse editCar(@RequestPayload EditCarRequest request) {
        EditCarResponse response = new EditCarResponse();
        Car car = carRepository.editCar(request.getId(), request.getType(), request.getMake(), request.getModel(),
                request.getYear(), request.getPrice(), request.getDoors(), request.getColor(), request.getFuel(),
                request.getFirstRegistration(), request.getFuelConsumption());
        response.setCar(car);
        return response;
    }

}




