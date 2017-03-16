package pl.kokokoko;

import io.spring.guides.gs_producing_web_service.Car;
import io.spring.guides.gs_producing_web_service.Fuel;
import io.spring.guides.gs_producing_web_service.Town;
import org.springframework.stereotype.Component;

@Component
public class MonthlyCarCosts {

    public float calculateMonthlyCarPrice(Car car, Town town) {
        Float avgCostPerMonth = calculateFuelConsumptionCostPerMonth(car);
        return calculateFuelConsumptionCostPerMonthDepentOfTown(avgCostPerMonth, town);
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
