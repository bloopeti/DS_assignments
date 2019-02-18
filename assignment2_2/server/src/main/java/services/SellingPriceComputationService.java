package services;

import entities.Car;
import serviceInterfaces.ISellingPriceComputationService;

import java.rmi.RemoteException;

public class SellingPriceComputationService implements ISellingPriceComputationService {
    public SellingPriceComputationService() {
    }

    public double computeSellingPrice(Car car) throws RemoteException {
        if (2018 - car.getYear() < 7)
            return (car.getPurchasingPrice() - car.getPurchasingPrice() / 7 * (2018 - car.getYear()));
        return 0;
    }
}
