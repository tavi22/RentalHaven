package model.product;

public abstract class Vehicle {
    protected Make make;
    protected String color;
    protected int horsepower, topSpeed;
    protected double manufacturerPrice;
    protected double pricePerDay;
    protected boolean available = true;


    public Vehicle() {}

    public Vehicle(Make make, String color, int horsepower, int topSpeed, double price, double pricePerDay, boolean available) {
        this.make = make;
        this.color = color;
        this.horsepower = horsepower;
        this.topSpeed = topSpeed;
        this.manufacturerPrice = price;
        this.pricePerDay = pricePerDay;
        this.available = available;
    }

    public Make getMake() {
        return make;
    }

    public String getColor() {
        return color;
    }

    public int getHorsepower() {
        return horsepower;
    }

    public int getTopSpeed() {
        return topSpeed;
    }

    public double getVehiclePrice() {
        return manufacturerPrice;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setHorsepower(int horsepower) {
        this.horsepower = horsepower;
    }

    public void setTopSpeed(int topSpeed) {

        this.topSpeed = topSpeed;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public boolean isAvailable() {
        return available;
    }

    public void rent_vehicle() {
        this.available = false;
    }

    public void return_vehicle() {
        this.available = true;
    }

    public String getCode() {
        return String.format("rental_haven#" + hashCode());
    }

    public String description() {
        return "Rental Vehicle";
    }

    public String getId() {
        return "Vehicle";
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "make=" + make +
                ", color='" + color + '\'' +
                ", horsepower=" + horsepower +
                ", topSpeed=" + topSpeed +
                ", manufacturerPrice=" + manufacturerPrice +
                ", pricePerHour=" + pricePerDay +
                ", available=" + available +
                '}';
    }
}
