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

@Component
public class MonthlyCarCosts {

    private final CarRepository carRepository;
    private final CarConverter carConverter;

    @Autowired
    public MonthlyCarCosts(CarRepository carRepository, CarConverter carConverter) {
        this.carRepository = carRepository;
        this.carConverter = carConverter;
    }

    public float calculateMonthlyCarPrice(Long id, Town town) throws DatatypeConfigurationException {
        CarEntity carEntity = carRepository.findById(id);
        Car car = carConverter.convertToCar(carEntity);
        Float avgCostPerMonth = calculateFuelConsumptionCostPerMonth(car);
        if (town != null) {
            return calculateFuelConsumptionCostPerMonthDependOfTown(avgCostPerMonth, town);
        } else {
            return avgCostPerMonth;
        }
    }

    private Float calculateFuelConsumptionCostPerMonth(Car car) {
        Fuel fuel = car.getFuel();
        Float cost;
        switch (fuel) {
            case PETROL:
                cost = car.getFuelConsumption() * AverageFuelPrice.PETROL.value();
                break;
            case DIESEL:
                cost = car.getFuelConsumption() * AverageFuelPrice.DIESEL.value();
                break;
            case LPG:
                cost = car.getFuelConsumption() * AverageFuelPrice.LPG.value();
                break;
            default:
                throw new RuntimeException("Unsupported fuel: " + fuel);
        }
        return cost;
    }

    private Float calculateFuelConsumptionCostPerMonthDependOfTown(Float avgCost, Town town) {
        Float cost;
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
            default:
                throw new RuntimeException("Unsupported town: " + town);
        }
        return cost;
    }

}
