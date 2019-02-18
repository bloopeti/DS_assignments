package serviceInterfaces;

import entities.Car;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ISellingPriceComputationService extends Remote {
    double computeSellingPrice(Car car) throws RemoteException;
}
