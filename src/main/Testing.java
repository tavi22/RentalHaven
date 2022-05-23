package main;

import model.product.Make;
import model.product.Motorcycle;
import model.product.types.MotorcycleType;
import services.Service;

import java.io.IOException;

public class Testing {
    public static void main(String[] args) throws IOException {
        Service new_serv = Service.getInstance();

        Motorcycle car;

        car = new Motorcycle(new Make("Kawasaki", "Turbo", "Japan", 2006),"red",125,210,12000,85, true, MotorcycleType.OFF_ROAD,"internal_combustion", 2);

        new_serv.addMotorcycle(car);



    }



}
