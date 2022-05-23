package model.product;

import model.product.types.CarType;

public class Car extends Vehicle implements Product {
    private static int counter = 0;
    private String id;
    private final CarType carType;
    private String engineType;
    private final double fuelConsumption;
    private final int seats;

    public Car(Make make, String color, int horsepower, int topSpeed, double price, double pricePerDay, boolean available, CarType carType, String engineType, double fuelConsumption, int seats) {
        super(make, color, horsepower, topSpeed, price, pricePerDay, available);
        this.carType = carType;
        this.engineType = engineType;
        this.fuelConsumption = fuelConsumption;
        this.seats = seats;

        this.id = "RH_RC#" + counter;
        counter++;
    }

    public CarType getCarType() {
        return carType;
    }

    public String getEngineType() {
        return engineType;
    }

    public double getFuelConsumption() {
        return fuelConsumption;
    }

    public int getSeats() {
        return seats;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getCode() {
        return String.format("rental_haven_%s_%s_%s_%s_%d", super.getMake().getName(), super.getMake().getModel(), this.carType, this.engineType, hashCode());
    }

    @Override
    public double getRentalPrice() {
        return this.getPricePerDay();
    }


    @Override
    public String description() {
        return "Rental Car\n" +
                super.toString() +
                "\nType: " + this.carType +
                "\nEngine Type: " + this.engineType +
                "\nFuel Consumption: " + this.fuelConsumption +
                "\nNumber of seats: " + this.seats +
                "\nCost to rent: $" + this.getRentalPrice() + " per day";
    }

    @Override
    public String toString() {
        return this.description();
    }
}
