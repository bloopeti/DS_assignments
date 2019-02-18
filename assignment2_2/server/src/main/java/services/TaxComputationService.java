package services;

import entities.Car;
import serviceInterfaces.ITaxComputationService;

import java.rmi.RemoteException;

public class TaxComputationService implements ITaxComputationService {
    public TaxComputationService() {
    }

    public double computeTax(Car car) throws RemoteException {
        int sum = 8;
        if (car.getEngineCapacity() > 1601) sum = 18;
        if (car.getEngineCapacity() > 2001) sum = 72;
        if (car.getEngineCapacity() > 2601) sum = 144;
        if (car.getEngineCapacity() > 3001) sum = 290;
        return car.getEngineCapacity() / 200.0 * sum;
    }
}
