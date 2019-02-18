package ro.tuc.dsrl.ds.handson.assig.two.server.services;

import ro.tuc.dsrl.ds.handson.assig.two.common.entities.Car;
import ro.tuc.dsrl.ds.handson.assig.two.common.serviceinterfaces.ISellingPriceService;

public class SellingPriceService implements ISellingPriceService {
    public double computeSellingPrice(Car c) {
        if (c.getPurchasingPrice() <= 0)
            throw new IllegalArgumentException("Purchasing price must be positive.");
        if (2018 - c.getYear() < 7)
            return (c.getPurchasingPrice() - c.getPurchasingPrice() / 7 * (2018 - c.getYear()));
        return 0;
    }
}
