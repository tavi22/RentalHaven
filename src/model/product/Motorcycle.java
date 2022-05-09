package model.product;

import model.product.types.MotorcycleType;

public class Motorcycle extends Vehicle implements Product {
    private static int counter = 0;
    private final String id;
    private final MotorcycleType motorcycleType;
    private final String engineType;
    private final int no_wheels;

    public Motorcycle(Make make, String color, int horsepower, int topSpeed, double price, double pricePerHour, boolean available, MotorcycleType motorcycleType, String engineType, int no_wheels) {
        super(make, color, horsepower, topSpeed, price, pricePerHour, available);
        this.motorcycleType = motorcycleType;
        this.engineType = engineType;
        this.no_wheels = no_wheels;

        this.id = "RH_RM" + counter;
        counter++;
    }

    public MotorcycleType getMotorcycleType() {
        return motorcycleType;
    }

    public String getEngineType() {
        return engineType;
    }

    public int getNo_wheels() {
        return no_wheels;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getCode() {
        return String.format("rental_haven_%s_%s_%s_%s_%d", super.getMake().getName(), super.getMake().getModel(), this.motorcycleType, this.engineType, hashCode());
    }

    @Override
    public double getRentalPrice() {
        return this.getPricePerDay();
    }

    @Override
    public String description() {
        return "Rental Motorcycle" +
                "\nType: " + this.motorcycleType +
                "\nEngine Type: " + this.engineType +
                "\nNumber of wheels: " + this.no_wheels +
                "\nCost to rent: " + this.getRentalPrice() + " per day";
    }

    @Override
    public String toString() {
        return this.description();
    }
}
