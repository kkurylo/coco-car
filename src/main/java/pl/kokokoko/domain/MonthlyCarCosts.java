package pl.kokokoko.domain;

import io.spring.guides.gs_producing_web_service.Car;
import io.spring.guides.gs_producing_web_service.Fuel;
import io.spring.guides.gs_producing_web_service.Town;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.kokokoko.helper.CarConverter;
import pl.kokokoko.persistance.CarEntity;
import pl.kokokoko.persistance.CarRepository;

import javax.xml.datatype.DatatypeConfigurationException;
import java.util.List;

@Component
public class MonthlyCarCosts {

    private final CarRepository carRepository;

    @Autowired
    public MonthlyCarCosts(CarRepository carRepository) {
        this.carRepository = carRepository;
    }


    public float calculateMonthlyCarPrice(Long id, Town town) throws DatatypeConfigurationException {
        List<CarEntity> cars = carRepository.findCar(id, null, null, null, null, null,
                null, null);
        CarEntity carEntity = cars.get(0);
        CarConverter converter = new CarConverter();
        Car car = converter.convertToCar(carEntity);
        Float avgCostPerMonth = calculateFuelConsumptionCostPerMonth(car);
        if (town != null) {
            return calculateFuelConsumptionCostPerMonthDepentOfTown(avgCostPerMonth, town);
        } else {
            return avgCostPerMonth;
        }
    }

    private Float calculateFuelConsumptionCostPerMonth(Car car) {
        Fuel fuel = car.getFuel();
        Float cost = 0f;
        switch (fuel) {
            case PETROL:
                cost = car.getFuelConsumption() * AVGFuelPrice.PETROL.value();
                break;
            case DIESEL:
                cost = car.getFuelConsumption() * AVGFuelPrice.DIESEL.value();
                break;
            case LPG:
                cost = car.getFuelConsumption() * AVGFuelPrice.LPG.value();

                break;

        }
        return cost;
    }

    private Float calculateFuelConsumptionCostPerMonthDepentOfTown(Float avgCost, Town town) {
        Float cost = 0f;
        switch (town) {
            case WARSAW:
                cost = avgCost + (avgCost * TownFuelPrice.WARSAW.value());
                break;
            case GDANSK:
                cost = avgCost + (avgCost * TownFuelPrice.GDANSK.value());
                break;
            case KATOWICE:
                cost = avgCost + (avgCost * TownFuelPrice.KATOWICE.value());
                break;
        }
        return cost;
    }

}
