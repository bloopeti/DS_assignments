package entities;

import java.io.Serializable;

public class Car implements Serializable {
    private static final long serialVersionUID = 1L;
    private int year;
    private int engineCapacity;
    private double purchasingPrice;

    public Car() {
    }

    public Car(int year, int engineCapacity, double purchasingPrice) {
        this.year = year;
        this.engineCapacity = engineCapacity;
        this.purchasingPrice = purchasingPrice;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(int engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public double getPurchasingPrice() {
        return purchasingPrice;
    }

    public void setPurchasingPrice(double purchasingPrice) {
        this.purchasingPrice = purchasingPrice;
    }
}
