package main;

import model.customer.Contract;
import model.customer.Customer;
import model.product.*;
import model.product.types.MotorcycleType;
import org.w3c.dom.ls.LSOutput;
import services.Service;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import model.product.CarDatabase;

public class Testing {
    public static void main(String[] args) throws IOException {
        Service new_serv = Service.getInstance();
        List<Vehicle> vehicleList = new LinkedList<>();

        for (int i = 0; i < 2; i++) {
            Motorcycle car;
            if (i == 1) {
                car = new Motorcycle(new Make("Fake", "Focus", "USA", 2010),"nn",100,100,100,150, true, MotorcycleType.SPORT,"internal_combustion", 2);

            } else
                car = new Motorcycle(new Make("Ford", "Focus", "USA", 2010),"nn",100,100,100,150, true, MotorcycleType.SPORT,"internal_combustion", 2);

            vehicleList.add(car);
            new_serv.addMotorcycle(car);
            new_serv.readNewBicycle();
        }
        System.out.println(vehicleList.get(0).getClass().getSimpleName());

        Motorcycle moto = (Motorcycle) vehicleList.get(0);
        System.out.println("TYPE: " + moto.getMotorcycleType());

        System.out.println("SIZE: " + vehicleList.size());


    }



}
