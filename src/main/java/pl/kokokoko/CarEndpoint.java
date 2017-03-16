package pl.kokokoko;

import io.spring.guides.gs_producing_web_service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import pl.kokokoko.domain.CarEntity;
import pl.kokokoko.domain.CarRepository;

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
        List<CarEntity> cars = carRepository.findCar(request.getId(), request.getType(), request.getMake(),
                request.getYearFrom(), request.getYearTo(), request.getPriceFrom(), request.getPriceTo(),
                request.getColor());
        List<Car> responseCars = response.getCars();
        for (CarEntity carEntity : cars) {
            CarConverter converter = new CarConverter();
            Car car = converter.convertToCar(carEntity);
            responseCars.add(car);
        }
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addCarRequest")
    @ResponsePayload
    public AddCarResponse addCar(@RequestPayload AddCarRequest request) throws DatatypeConfigurationException {
        AddCarResponse response = new AddCarResponse();
        CarConverter converter = new CarConverter();
        CarEntity carEntity = converter.convertToCarEntity(request.getCar());
        CarEntity ce = carRepository.addCar(carEntity);
        Car c = converter.convertToCar(ce);
        response.setCar(c);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "editCarRequest")
    @ResponsePayload
    public EditCarResponse editCar(@RequestPayload EditCarRequest request) throws DatatypeConfigurationException {
        EditCarResponse response = new EditCarResponse();
        CarConverter converter = new CarConverter();
        CarEntity carEntity = converter.convertToCarEntity(request.getCar());
        CarEntity ce = carRepository.editCar(carEntity);
        Car c = converter.convertToCar(ce);
        response.setCar(c);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteCarRequest")
    @ResponsePayload
    public void deleteCar(@RequestPayload DeleteCarRequest request) {
        carRepository.deleteCar(request.getId());
    }


}




