package controllers;

import entities.Car;
import serviceInterfaces.ISellingPriceComputationService;
import serviceInterfaces.ITaxComputationService;
import views.CarDataCalculatorView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class CarDataCalculatorController {
    private CarDataCalculatorView carDataCalculatorView;

    public CarDataCalculatorController() {
        carDataCalculatorView = new CarDataCalculatorView();
        carDataCalculatorView.setVisible(true);

        carDataCalculatorView.addBtnTaxActionListener(new TaxActionListener());
        carDataCalculatorView.addBtnSellingPriceActionListener(new SellingPriceActionListener());
    }

    public void printResult(String s) {
        if (s != null) {
            carDataCalculatorView.printResult(s);
        }
    }

    class TaxActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int year = Integer.parseInt(carDataCalculatorView.getCarYear());
            int engineCap = Integer.parseInt(carDataCalculatorView.getCarEngineCapacity());
            int buyPrice = Integer.parseInt(carDataCalculatorView.getCarPurchasingPrice());

            Car car = new Car(year, engineCap, buyPrice);

            try {
                Registry registry = LocateRegistry.getRegistry(1099);
                ITaxComputationService stub = (ITaxComputationService) registry.lookup("TaxComputationService");

                printResult(String.valueOf("Tax: " + stub.computeTax(car)));
            } catch (RemoteException e1) {
                e1.printStackTrace();
            } catch (NotBoundException e1) {
                e1.printStackTrace();
            }
        }
    }

    class SellingPriceActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int year = Integer.parseInt(carDataCalculatorView.getCarYear());
            int engineCap = Integer.parseInt(carDataCalculatorView.getCarEngineCapacity());
            int buyPrice = Integer.parseInt(carDataCalculatorView.getCarPurchasingPrice());

            Car car = new Car(year, engineCap, buyPrice);

            try {
                Registry registry = LocateRegistry.getRegistry(1099);
                ISellingPriceComputationService stub = (ISellingPriceComputationService) registry.lookup("SellingPriceComputationService");

                printResult("Selling price: " + String.valueOf(stub.computeSellingPrice(car)));
            } catch (RemoteException e1) {
                e1.printStackTrace();
            } catch (NotBoundException e1) {
                e1.printStackTrace();
            }
        }
    }
}
