package model.product.comparators;

import model.product.Vehicle;

import java.util.Comparator;

public class VehicleSortByManufacturerPrice implements Comparator<Vehicle> {
    @Override
    public int compare(Vehicle o1, Vehicle o2) {
        return (int)(o1.getVehiclePrice() - o2.getVehiclePrice());
    }
}
