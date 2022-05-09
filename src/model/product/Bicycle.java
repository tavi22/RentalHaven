package model.product;

import model.product.types.BicycleType;

public class Bicycle extends Vehicle implements Product {

    private static int counter = 0;
    private final String id;
    private final BicycleType bicycleType;
    private String propulsion = "Normal";

    public Bicycle(Make make, String color, int horsepower, int topSpeed, double price, double pricePerHour, boolean available, BicycleType bicycleType, String propulsion) {

        super(make, color, horsepower, topSpeed, price, pricePerHour, available);
        this.bicycleType = bicycleType;
        this.propulsion = propulsion;

        this.id = "RH_RB" + counter;
        counter++;
    }

    public BicycleType getBicycleType() {
        return bicycleType;
    }

    public String getPropulsion() {
        return propulsion;
    }

    @Override
    public String getId() {
        return id;
    }

    public void setPropulsion(String propulsion) {
        this.propulsion = propulsion;
    }


    @Override
    public String getCode() {
        return String.format("rental_haven_%s_%s_%s_%s_%d", super.getMake().getName(), super.getMake().getModel(), this.bicycleType, this.propulsion, hashCode());
    }

    @Override
    public double getRentalPrice() {
        return this.getPricePerDay();
    }

    @Override
    public String description() {
        return "Rental Bicycle" +
                "\nType: " + this.bicycleType +
                "\nPropulsion: " + this.propulsion +
                "\nCost to rent: " + this.getRentalPrice() + " per day";

    }

    @Override
    public String toString() {
        return this.description();
    }
}
