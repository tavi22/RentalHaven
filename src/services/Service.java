package services;

import model.product.comparators.VehicleSortByHP;
import model.product.comparators.VehicleSortByHourlyRate;
import model.product.comparators.VehicleSortByManufacturerPrice;
import model.customer.*;
import model.product.*;
import model.product.types.BicycleType;
import model.product.types.CarType;
import model.product.types.MotorcycleType;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

public class Service {
    public static Service singleton = null;
    public static Service getInstance() {
        if (singleton == null)
            singleton = new Service();
        return singleton;
    }

    Audit audit = Audit.getInstance();

    private Set<Vehicle> vehicles;
    private Set<Car> cars;
    private Set<Motorcycle> motorcycles;
    private Set<Bicycle> bicycles;

    private Set<Customer> rental_customers;
    private Set<Review> rental_reviews;
    private List<Contract> rental_contracts;
    private List<Accident> accidents;

    Service() {
        this.vehicles = new LinkedHashSet<>();
        this.cars = new TreeSet<>(new VehicleSortByHP());
        this.motorcycles = new TreeSet<>(new VehicleSortByManufacturerPrice());
        this.bicycles = new TreeSet<>(new VehicleSortByHourlyRate());
        this.rental_customers = new LinkedHashSet<>();
        this.rental_reviews = new LinkedHashSet<>();
        this.rental_contracts = new LinkedList<>();
        this.accidents = new LinkedList<>();

    }

    // Getters for each list/set
    public Set<Vehicle> getVehicles() {
        return vehicles;
    }

    public Set<Car> getCars() {
        return cars;
    }

    public Set<Motorcycle> getMotorcycles() {
        return motorcycles;
    }

    public Set<Bicycle> getBicycles() {
        return bicycles;
    }

    public Set<Customer> getRental_customers() {
        return rental_customers;
    }

    public Set<Review> getRental_reviews() {
        return rental_reviews;
    }

    public List<Contract> getRental_contracts() {
        return rental_contracts;
    }

    public List<Accident> getAccidents() {
        return accidents;
    }


    // Add and show services for each list of objects
    public void addVehicle(Vehicle v) throws IOException {
        this.vehicles.add(v);
        audit.logServiceAction("addVehicle");
    }
    public void showVehicles() {
        for(Vehicle vehicle : this.vehicles) {
            System.out.println(vehicle);
            System.out.println("====================");
        }
    }

    public void addCar(Car c) throws IOException {
        this.cars.add(c);
        this.vehicles.add(c);
        audit.logServiceAction("addCar");
    }
    public void showCars() {
        for(Car car : this.cars) {
            System.out.println(car);
            System.out.println("====================");
        }
    }

    public void addMotorcycle(Motorcycle m) throws IOException {
        this.motorcycles.add(m);
        this.vehicles.add(m);
        audit.logServiceAction("addMotorcycle");
    }
    public void showMotorcycles() {
        for(Motorcycle motorcycle : this.motorcycles) {
            System.out.println(motorcycle);
            System.out.println("====================");
        }
    }

    public void addBicycle(Bicycle b) throws IOException {
        this.bicycles.add(b);
        this.vehicles.add(b);
        audit.logServiceAction("addBicycle");

    }
    public void showBicycles() {
        for(Bicycle bicycle : this.bicycles) {
            System.out.println(bicycle);
            System.out.println("====================");
        }
    }

    public void addCustomer(Customer c) throws IOException {
        this.rental_customers.add(c);
        audit.logServiceAction("addCustomer");

    }
    public void showCustomers() {
        for(Customer customer : this.rental_customers) {
            System.out.println(customer);
            System.out.println("====================");
        }
    }

    public void addReview(Review r) throws IOException {
        this.rental_reviews.add(r);
        audit.logServiceAction("addReview");

    }
    public void showReviews() {
        for(Review review : this.rental_reviews) {
            System.out.println(review);
            System.out.println("====================");
        }
    }

    public void addContract(Contract c) throws IOException {
        this.rental_contracts.add(c);
        audit.logServiceAction("addContract");
    }
    public void showContract() {
        for(Contract contract : this.rental_contracts) {
            System.out.println(contract);
            System.out.println("====================");
        }
    }

    public void addAccident(Accident a) { this.accidents.add(a); }
    public void showAccidents() {
        for(Accident accident : this.accidents) {
            System.out.println(accident);
            System.out.println("====================");
        }
    }

    // Create new instance for each object by reading the info from console
    public Car readNewCar() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Make name: "); String name = scanner.nextLine();
        System.out.println("Model: "); String model = scanner.nextLine();
        System.out.println("Base: "); String base = scanner.nextLine();
        System.out.println("Year: "); int year = scanner.nextInt();
        Make newMake = new Make(name, model, base, year);
        System.out.println("Color: "); scanner.next(); String color = scanner.nextLine();
        System.out.println("Horsepower: "); int horsepower = scanner.nextInt();
        System.out.println("TopSpeed: "); int top = scanner.nextInt();
        System.out.println("Price: "); double price = scanner.nextDouble();
        System.out.println("Type: "); String type = scanner.nextLine();
        System.out.println("Engine: "); String engine = scanner.nextLine();
        System.out.println("Fuel Consumption: "); double fuelC = scanner.nextDouble();
        System.out.println("Seats: "); int seats = scanner.nextInt();
        System.out.println("Price Per Hour: "); double pHour = scanner.nextDouble();


        CarType tp = switch (type.toUpperCase()) {
            case "MINI" -> CarType.MINI;
            case "HATCHBACK" -> CarType.HATCHBACK;
            case "COUPE" -> CarType.COUPE;
            case "SEDAN" -> CarType.SEDAN;
            case "SUV" -> CarType.SUV;
            case "PICKUP" -> CarType.PICKUP;
            case "VAN" -> CarType.VAN;
            case "TRUCK" -> CarType.TRUCK;
            default -> null;
        };

        audit.logServiceAction("readNewCar");


        return new Car(newMake,color, horsepower,top, price, pHour, true, tp, engine, fuelC, seats);
    }

    public Motorcycle readNewMotorcycle() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Make name: "); String name = scanner.nextLine();
        System.out.println("Model: "); String model = scanner.nextLine();
        System.out.println("Base: "); String base = scanner.nextLine();
        System.out.println("Year: "); int year = scanner.nextInt();
        Make newMake = new Make(name, model, base, year);
        System.out.println("Color: "); String color = scanner.nextLine();
        System.out.println("Horsepower: "); int horsepower = scanner.nextInt();
        System.out.println("TopSpeed: "); int top = scanner.nextInt();
        System.out.println("Price: "); double price = scanner.nextDouble();
        System.out.println("Type: "); String type = scanner.nextLine();
        System.out.println("Engine: "); String engine = scanner.nextLine();
        System.out.println("Wheels: "); int wheels = scanner.nextInt();
        System.out.println("Price Per Hour: "); double pHour = scanner.nextDouble();


        MotorcycleType tp = switch (type.toUpperCase()) {
            case "STANDARD" -> MotorcycleType.STANDARD;
            case "CRUISER" -> MotorcycleType.CRUISER;
            case "SPORT" -> MotorcycleType.SPORT;
            case "TOURING" -> MotorcycleType.TOURING;
            case "SCOOTER" -> MotorcycleType.SCOOTER;
            case "MOPED" -> MotorcycleType.MOPED;
            case "OFF_ROAD" -> MotorcycleType.OFF_ROAD;
            default -> null;
        };

        audit.logServiceAction("readNewMotorcycle");


        return new Motorcycle(newMake,color, horsepower,top, price, pHour,true, tp, engine, wheels);
    }

    public Bicycle readNewBicycle() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Make name: "); String name = scanner.nextLine();
        System.out.println("Model: "); String model = scanner.nextLine();
        System.out.println("Base: "); String base = scanner.nextLine();
        System.out.println("Year: "); int year = scanner.nextInt();
        Make newMake = new Make(name, model, base, year);
        System.out.println("Color: "); scanner.next(); String color = scanner.nextLine();
        System.out.println("Horsepower: "); int horsepower = scanner.nextInt();
        System.out.println("TopSpeed: "); int top = scanner.nextInt();
        System.out.println("Price: "); double price = scanner.nextDouble();
        System.out.println("Type: "); scanner.next(); String type = scanner.nextLine();
        System.out.println("Propulsion: "); String propulsion = scanner.nextLine();
        System.out.println("Price Per Hour: "); double pHour = scanner.nextDouble();

        BicycleType tp = switch (type.toUpperCase()) {
            case "ROAD" -> BicycleType.ROAD;
            case "FIXED_GEAR" -> BicycleType.FIXED_GEAR;
            case "MOUNTAIN" -> BicycleType.MOUNTAIN;
            case "BMX" -> BicycleType.BMX;
            case "RECUMBENT" -> BicycleType.RECUMBENT;
            case "FOLDING" -> BicycleType.FOLDING;
            default -> null;
        };

        audit.logServiceAction("readNewBicycle");
        return new Bicycle(newMake,color, horsepower,top, price, pHour,true, tp, propulsion);
    }

    public Customer readNewCustomer() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Full Name: "); String name = scanner.nextLine();
        System.out.println("Phone: "); String phone = scanner.nextLine();
        System.out.println("Street: "); String street = scanner.nextLine();
        System.out.println("Number: "); int nr = scanner.nextInt();
        System.out.println("City: "); scanner.next(); String city = scanner.nextLine();
        System.out.println("County: "); String county = scanner.nextLine();
        System.out.println("Postal Code: "); String postal = scanner.nextLine();

        Address address = new Address(street, nr, city, county, postal);
        audit.logServiceAction("readNewCustomer");
        return new Customer(name, phone, address);
    }

    public Review readNewReview(Customer customer) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Review Body: "); String body = scanner.nextLine();
        System.out.println("Rating: "); int rating = scanner.nextInt();
        System.out.println("Satisfied: (true/false)"); boolean sat = scanner.nextBoolean();

        audit.logServiceAction("readNewReview");

        return new Review(customer, body, rating, sat);
    }

    public Contract readNewContract(Customer customer, Vehicle vehicle) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Days Rented: "); int nr = scanner.nextInt();
        double total = vehicle.getPricePerDay() * nr;

        audit.logServiceAction("readNewContract");

        return new Contract(customer, vehicle, LocalDate.now(), LocalDate.now().plusDays(nr), total);
    }

    public Accident readNewAccident(double gravity) {
        return new Accident(gravity, LocalDate.now());
    }

    // Rent and return vehicle services
    public Contract rentVehicle(Customer customer, Vehicle vehicle, int daysRented) throws IOException {
        double pr = 0;
        if (vehicle.isAvailable() && daysRented > 0) {
            pr = vehicle.getPricePerDay() * daysRented;
            if (customer.getAccidents() != null) {
                double mean = 0;
                for (Accident acc : customer.getAccidents()) {
                    mean += acc.getGravity();
                }
                mean /= customer.getAccidents().size();
                pr *= mean;
            }

            vehicle.rent_vehicle();
            System.out.println("!!Vehicle " + vehicle.getCode() + " rented.\n");
            System.out.println("====================");
            System.out.println("New Contract Information:\n" +
                    "Customer Name: " + customer.getFullName() + "\n" +
                    "Vehicle ID: " + vehicle.getId() + "\n" +
                    "Rent Date: " + LocalDate.now() + "\n" +
                    "Due Date: " + LocalDate.now().plusDays(daysRented) + "\n" +
                    "Total: " + pr + "\n");
            System.out.println("====================");
            System.out.println("Vehicle Information:\n" + vehicle.description() + "\n");
            System.out.println("====================");
            audit.logServiceAction("rentVehicle");

            return new Contract(customer, vehicle, LocalDate.now(), LocalDate.now().plusDays(daysRented), pr);

        }
        System.out.println("!Error renting vehicle!");
        return null;
    }

    public double returnVehicle(Customer customer, Vehicle vehicle, Contract contract) throws IOException {
        if (!vehicle.isAvailable()) {
            double fee = 0;
            if (contract.getDueDate().isBefore(LocalDate.now())) {
                Period period = Period.between(contract.getDueDate(), LocalDate.now());
                int daysElapsed = period.getDays();
                fee = vehicle.getPricePerDay() / 5 * daysElapsed;
            }

            System.out.println("!!Vehicle " + vehicle.getCode() + " returned.\n" +
                    "Late Fee: " + fee + "\n" +
                    "Have a nice day!\n");
            System.out.println("====================");
            audit.logServiceAction("returnVehicle");

            return fee;

        }
        return 0;
    }


    public Vehicle findVehicleByID(String id, Set<Vehicle> vehicles) {
        for (Vehicle vehicle : vehicles) {
            if (Objects.equals(vehicle.getId(), id)) {
                System.out.println("!!Vehicle Found!!");
                return vehicle;
            }
        }
        System.out.println("!!Vehicle Not Found!!");
        return null;
    }

    public Customer findCustomerByID(String id) {
        for(Customer customer : rental_customers) {
            if (Objects.equals(customer.getId(), id)) {
                System.out.println("!!Customer Found!!");
                return customer;
            }
        }
        System.out.println("!!Customer Not Found!!");
        return null;
    }

    public Contract findContractByID(String id) {
        for(Contract contract : rental_contracts) {
            if (Objects.equals(contract.getContract_id(), id)) {
                System.out.println("!!Contract Found!!");
                return contract;
            }
        }
        System.out.println("!!Contract Not Found!!");
        return null;
    }

    public Review findReviewByID(String id) {
        for(Review review : rental_reviews) {
            if (Objects.equals(review.getId(), id)) {
                System.out.println("!!Review Found!!");
                return review;
            }
        }
        System.out.println("!!Review Not Found!!");
        return null;
    }


}
