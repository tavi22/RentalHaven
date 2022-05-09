package model.product;

public interface Product {


    String getCode();
    double getRentalPrice();
    boolean isAvailable();
    void rent_vehicle();
    void return_vehicle();

    default String description() {
        return this.toString();
    }
}
