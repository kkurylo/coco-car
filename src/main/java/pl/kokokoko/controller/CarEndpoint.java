package pl.kokokoko.controller;

import io.spring.guides.gs_producing_web_service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import pl.kokokoko.domain.MonthlyCarCosts;
import pl.kokokoko.helper.CarConverter;
import pl.kokokoko.persistance.CarEntity;
import pl.kokokoko.persistance.CarRepository;

import javax.xml.datatype.DatatypeConfigurationException;
import java.text.ParseException;
import java.util.List;

@Endpoint
public class CarEndpoint {
    private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

    private final CarRepository carRepository;
    private final MonthlyCarCosts monthlyCarCosts;
    private final CarConverter carConverter;

    @Autowired
    public CarEndpoint(CarRepository carRepository, MonthlyCarCosts monthlyCarCosts, CarConverter carConverter) {
        this.carRepository = carRepository;
        this.monthlyCarCosts = monthlyCarCosts;
        this.carConverter = carConverter;
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
            Car car = carConverter.convertToCar(carEntity);
            responseCars.add(car);
        }
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addCarRequest")
    @ResponsePayload
    public AddCarResponse addCar(@RequestPayload AddCarRequest request) throws DatatypeConfigurationException {
        AddCarResponse response = new AddCarResponse();

        CarEntity carEntity = carConverter.convertToCarEntity(request.getCar());
        CarEntity ce = carRepository.addCar(carEntity);
        Car c = carConverter.convertToCar(ce);
        response.setCar(c);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "editCarRequest")
    @ResponsePayload
    public EditCarResponse editCar(@RequestPayload EditCarRequest request) throws DatatypeConfigurationException {
        EditCarResponse response = new EditCarResponse();

        CarEntity carEntity = carConverter.convertToCarEntity(request.getCar());
        CarEntity ce = carRepository.editCar(carEntity);
        Car c = carConverter.convertToCar(ce);
        response.setCar(c);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteCarRequest")
    @ResponsePayload
    public void deleteCar(@RequestPayload DeleteCarRequest request) {
        carRepository.deleteCar(request.getId());
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "calculateMonthlyCarPriceRequest")
    @ResponsePayload
    public CalculateMonthlyCarPriceResponse calculateMonthlyCarPrice(@RequestPayload CalculateMonthlyCarPriceRequest request) throws DatatypeConfigurationException {
        CalculateMonthlyCarPriceResponse response = new CalculateMonthlyCarPriceResponse();
        float monthlyCarPrice = monthlyCarCosts.calculateMonthlyCarPrice(request.getId(), request.getTown());
        response.setMonthlyCarPrice(monthlyCarPrice);
        return response;
    }


}




