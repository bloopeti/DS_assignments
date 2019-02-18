package ro.tuc.dsrl.ds.handson.assig.two.common.entities;

import java.io.Serializable;

/**
 * @Author: Technical University of Cluj-Napoca, Romania
 * Distributed Systems, http://dsrl.coned.utcluj.ro/
 * @Module: assignment-two-common-classes
 * @Since: Sep 1, 2015
 * @Description: Serializable car, abstraction of a Car.
 */
public class Car implements Serializable {

    private static final long serialVersionUID = 1L;
    private int year;
    private int engineCapacity;
    private double purchasingPrice;

    public Car() {
    }

    public Car(int year, int engineCapacity, int purchasingPrice) {
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + engineCapacity;
        result = prime * result + year;
        return result;
    }

    public double getPurchasingPrice() {
        return purchasingPrice;
    }

    public void setPurchasingPrice(double purchasingPrice) {
        this.purchasingPrice = purchasingPrice;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Car other = (Car) obj;
        if (engineCapacity != other.engineCapacity)
            return false;
        if (year != other.year)
            return false;
        if (purchasingPrice != other.purchasingPrice)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Car [year=" + year + ", engineCapacity=" + engineCapacity+  ", purchasingPrice=" + purchasingPrice + "]";
    }

}
