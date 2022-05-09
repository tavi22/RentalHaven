package model.product.comparators;

import model.product.Vehicle;

import java.util.Comparator;

public class VehicleSortByNewest implements Comparator<Vehicle> {

    @Override
    public int compare(Vehicle o1, Vehicle o2) {
        return o1.getMake().getYear() - o2.getMake().getYear();
    }
}
