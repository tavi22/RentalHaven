package main;

import model.customer.Contract;
import model.customer.Customer;
import model.customer.Review;
import model.product.Bicycle;
import model.product.Car;
import model.product.Motorcycle;
import model.product.Vehicle;
import services.Service;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Service service = Service.getInstance();


        //------------------------------------------------------------------------------------------

        Scanner in = new Scanner(System.in);
        while(true)
        {
            Menu();
            System.out.println("Chose an option: ");
            int choice = in.nextInt();
            switch (choice)
            {
                case 0:
                    return;
                case 1: Vehicle vehicle = service.readNewVehicle(); service.addVehicle(vehicle); break;
                case 2: Car car = service.readNewCar(); service.addCar(car); break;
                case 3: Motorcycle motorcycle = service.readNewMotorcycle(); service.addMotorcycle(motorcycle); break;
                case 4: Bicycle bicycle = service.readNewBicycle(); service.addBicycle(bicycle); break;
                case 5: Customer customer = service.readNewCustomer(); service.addCustomer(customer); break;
                case 6: Review review = service.readNewReview(); service.addReview(review); break;
                case 7: Contract contract = service.readNewContract(); service.addContract(contract); break;
                case 8: service.showVehicles(); break;
                case 9: service.showCars(); break;
                case 10: service.showMotorcycles(); break;
                case 11: service.showBicycles(); break;
                case 12: service.showCustomers(); break;
                case 13: service.showReviews(); break;
                case 14: service.showContract(); break;
                case 15: service.rentVehicle(); break;
                case 16: service.returnVehicle(); break;
                default : System.out.println("Error! Try again with a valid choice!"); Menu(); break;
            }

            Menu();
            choice = in.nextInt();
        }
    }

    public static void Menu()
    {
        System.out.println("""
                Meniu:
                0) Exit
                1) Add New Vehicle
                2) Add New Car
                3) Add New Motorcycle
                4) Add New Bicycle
                5) Add New Customer
                6) Add New Review
                7) Add New Contract
                8) Show Vehicles
                9) Show Cars
                10) Show Motorcycles
                11) Show Bicycles
                12) Show Customers
                13) Show Reviews
                14) Show Contracts
                15) Rent Vehicle
                16) Return Vehicle
                """);
    }
}
