package serviceInterfaces;

import entities.Car;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ITaxComputationService extends Remote {
    double computeTax(Car car) throws RemoteException;
}
