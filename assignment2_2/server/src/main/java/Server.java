import serviceInterfaces.ISellingPriceComputationService;
import serviceInterfaces.ITaxComputationService;
import services.SellingPriceComputationService;
import services.TaxComputationService;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server {
    public Server() {
    }

    public static void main(String args[]) {
        try {
            Registry registry = LocateRegistry.createRegistry(1099);

            TaxComputationService taxComputationService = new TaxComputationService();
            ITaxComputationService skeleton1 = (ITaxComputationService) UnicastRemoteObject.exportObject(taxComputationService, 1099);

            registry.rebind("TaxComputationService", skeleton1);
            System.out.println("TaxComputationService bound");

            SellingPriceComputationService sellingPriceComputationService = new SellingPriceComputationService();
            ISellingPriceComputationService skeleton2 = (ISellingPriceComputationService) UnicastRemoteObject.exportObject(sellingPriceComputationService, 1099);
            registry.rebind("SellingPriceComputationService", skeleton2);
            System.out.println("SellingPriceComputationService bound");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
