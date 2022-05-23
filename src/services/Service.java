package services;

import model.customer.*;
import model.product.*;
import model.product.types.BicycleType;
import model.product.types.CarType;
import model.product.types.MotorcycleType;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

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

    private VehicleDatabase vehicleDatabase = null;
    private CarDatabase carDatabase = null;
    private MotoDatabase motoDatabase = null;
    private BikeDatabase bikeDatabase = null;
    private CustomerDatabase customerDatabase = null;
    private ReviewDatabase reviewDatabase = null;
    private ContractDatabase contractDatabase = null;



    Service() {
        Connection connection;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_pao", "root", "paopassword");
            this.vehicleDatabase = new VehicleDatabase(connection);
            this.carDatabase = new CarDatabase(connection);
            this.motoDatabase = new MotoDatabase(connection);
            this.bikeDatabase = new BikeDatabase(connection);
            this.customerDatabase = new CustomerDatabase(connection);
            this.reviewDatabase = new ReviewDatabase(connection);
            this.contractDatabase = new ContractDatabase(connection);

        } catch (SQLException s) {
            s.printStackTrace();
        }

        this.vehicles = vehicleDatabase.read();
        this.cars = carDatabase.read();
        this.motorcycles = motoDatabase.read();
        this.bicycles = bikeDatabase.read();
        this.rental_customers = customerDatabase.read();
        this.rental_reviews = reviewDatabase.read();
        this.rental_contracts = contractDatabase.read();

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

    public void setVehicles(Set<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }

    public void setMotorcycles(Set<Motorcycle> motorcycles) {
        this.motorcycles = motorcycles;
    }

    public void setBicycles(Set<Bicycle> bicycles) {
        this.bicycles = bicycles;
    }

    public void setRental_customers(Set<Customer> rental_customers) {
        this.rental_customers = rental_customers;
    }

    public void setRental_reviews(Set<Review> rental_reviews) {
        this.rental_reviews = rental_reviews;
    }

    public void setRental_contracts(List<Contract> rental_contracts) {
        this.rental_contracts = rental_contracts;
    }


    // Add and show services for each list of objects
    public void addVehicle(Vehicle v) throws IOException {
        this.vehicles.add(v);
        this.vehicleDatabase.create(v);
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
        Scanner scanner = new Scanner(System.in);
        System.out.println("Chose what type of vehicle the car is (Type -1 for new Vehicle): ");
        int vehicle_id = scanner.nextInt();
        scanner.next();
        if (vehicle_id == -1) {
            Vehicle newVehicle = this.readNewVehicle();
            this.addVehicle(newVehicle);
            vehicle_id = newVehicle.get_count();
        }
        this.carDatabase.create(c, vehicle_id);
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
        Scanner scanner = new Scanner(System.in);
        System.out.println("Chose what type of vehicle the motorcycle is (Type -1 for new Vehicle): ");
        int vehicle_id = scanner.nextInt();
        scanner.next();
        if (vehicle_id == -1) {
            Vehicle newVehicle = this.readNewVehicle();
            this.addVehicle(newVehicle);
            vehicle_id = newVehicle.get_count();
        }
        this.motoDatabase.create(m, vehicle_id);
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
        Scanner scanner = new Scanner(System.in);
        System.out.println("Chose what type of vehicle the bicycle is (Type -1 for new Vehicle): ");
        int vehicle_id = scanner.nextInt();
        scanner.next();

        if (vehicle_id == -1) {
            Vehicle newVehicle = this.readNewVehicle();
            this.addVehicle(newVehicle);
            vehicle_id = newVehicle.get_count();
        }
        this.bikeDatabase.create(b, vehicle_id);
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
        this.customerDatabase.create(c);
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
        Scanner scanner = new Scanner(System.in);
        System.out.println("Chose the user who added the review (Type -1 for new user): ");
        int customer_id = scanner.nextInt();
        scanner.next();

        if (customer_id == -1) {
            Customer newCustomer = this.readNewCustomer();
            customer_id = newCustomer.get_count();
        }
        this.reviewDatabase.create(r, customer_id);
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
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose customer and vehicle ids (Type -1 for new user/vehicle): ");
        int customer_id = scanner.nextInt();
        scanner.next();

        if (customer_id == -1) {
            Customer newCustomer = this.readNewCustomer();
            customer_id = newCustomer.get_count();
        }
        int vehicle_id = scanner.nextInt();
        scanner.next();

        if (vehicle_id == -1) {
            Vehicle newVehicle = this.readNewVehicle();
            this.addVehicle(newVehicle);
            vehicle_id = newVehicle.get_count();
        }
        this.contractDatabase.create(c, customer_id, vehicle_id);
        audit.logServiceAction("addContract");
    }
    public void showContract() {
        for(Contract contract : this.rental_contracts) {
            System.out.println(contract);
            System.out.println("====================");
        }
    }

    // Create new instance for each object by reading the info from console
    public Vehicle readNewVehicle() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Make name: "); String name = scanner.nextLine();
        System.out.println("Model: "); String model = scanner.nextLine();
        System.out.println("Base: "); String base = scanner.nextLine();
        System.out.println("Year: "); int year = scanner.nextInt();
        Make make = new Make(name, model, base, year);
        System.out.println("Color: "); scanner.next(); String color = scanner.nextLine();
        System.out.println("Horsepower: "); int horsepower = scanner.nextInt();
        System.out.println("TopSpeed: "); int top = scanner.nextInt();
        System.out.println("Price: "); double price = scanner.nextDouble();
        System.out.println("PricePerDay: "); double pDay = scanner.nextDouble();

        return new Vehicle(make, color, horsepower, top, price, pDay, true);

    }
    public Car readNewCar() throws IOException {
        Scanner scanner = new Scanner(System.in);
        Vehicle vehicle = readNewVehicle();
        System.out.println("Type: "); String type = scanner.nextLine();
        System.out.println("Engine: "); String engine = scanner.nextLine();
        System.out.println("Fuel Consumption: "); double fuelC = scanner.nextDouble();
        System.out.println("Seats: "); int seats = scanner.nextInt();


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


        return new Car(vehicle.getMake(),vehicle.getColor(), vehicle.getHorsepower(),
                vehicle.getTopSpeed(), vehicle.getVehiclePrice(), vehicle.getPricePerDay(),
                true, tp, engine, fuelC, seats);
    }

    public Motorcycle readNewMotorcycle() throws IOException {
        Scanner scanner = new Scanner(System.in);
        Vehicle vehicle = readNewVehicle();

        System.out.println("Type: "); String type = scanner.nextLine();
        System.out.println("Engine: "); String engine = scanner.nextLine();
        System.out.println("Wheels: "); int wheels = scanner.nextInt();



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


        return new Motorcycle(vehicle.getMake(),vehicle.getColor(), vehicle.getHorsepower(),
                vehicle.getTopSpeed(), vehicle.getVehiclePrice(), vehicle.getPricePerDay(),
                true, tp, engine, wheels);
    }

    public Bicycle readNewBicycle() throws IOException {
        Scanner scanner = new Scanner(System.in);
        Vehicle vehicle = readNewVehicle();
        System.out.println("Type: "); scanner.next(); String type = scanner.nextLine();
        System.out.println("Propulsion: "); String propulsion = scanner.nextLine();

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
        return new Bicycle(vehicle.getMake(),vehicle.getColor(), vehicle.getHorsepower(),
                vehicle.getTopSpeed(), vehicle.getVehiclePrice(), vehicle.getPricePerDay(),
                true, tp, propulsion);
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

    public Review readNewReview() throws IOException {
        Scanner scanner = new Scanner(System.in);
        Customer customer;
        System.out.println("Enter user id (Type -1 for new user): "); int id = scanner.nextInt();
        if (id == -1) {
            customer = readNewCustomer();
            this.addCustomer(customer);
        } else
            customer = findCustomerByID(id);

        System.out.println("Review Body: "); String body = scanner.nextLine();
        System.out.println("Rating: "); int rating = scanner.nextInt();
        System.out.println("Satisfied: (true/false)"); boolean sat = scanner.nextBoolean();
        audit.logServiceAction("readNewReview");

        return new Review(customer, body, rating, sat);
    }

    public Contract readNewContract() throws IOException {
        Scanner scanner = new Scanner(System.in);
        Customer customer;
        Vehicle vehicle;
        System.out.println("Enter user and vehicle ids (Type -1 for new user/vehicle): ");
        int customer_id = scanner.nextInt();
        scanner.next();

        if (customer_id == -1) {
            customer = readNewCustomer();
            this.addCustomer(customer);
        } else
            customer = findCustomerByID(customer_id);

        int vehicle_id = scanner.nextInt();
        scanner.next();

        if (vehicle_id == -1) {
            vehicle = readNewVehicle();
            this.addVehicle(vehicle);
        } else
            vehicle = findVehicleByID(vehicle_id);

        System.out.println("Days Rented: "); int nr = scanner.nextInt();
        scanner.next();
        double total = vehicle.getPricePerDay() * nr;

        audit.logServiceAction("readNewContract");

        return new Contract(customer, vehicle, LocalDate.now(), LocalDate.now().plusDays(nr), total);
    }

    public Accident readNewAccident(double gravity) {
        return new Accident(gravity, LocalDate.now());
    }

    // Rent and return vehicle services
    public Contract rentVehicle() throws IOException {
        double pr = 0;
        Customer customer;
        Vehicle vehicle;
        int daysRented;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter user/vehicle ids (Type -1 for new user/vehicle): ");
        int customer_id = scanner.nextInt();
        scanner.next();

        if (customer_id == -1) {
            customer = readNewCustomer();
            this.addCustomer(customer);
        } else
            customer = findCustomerByID(customer_id);

        int vehicle_id = scanner.nextInt();
        scanner.next();

        if (vehicle_id == -1) {
            vehicle = readNewVehicle();
            this.addVehicle(vehicle);
        } else
            vehicle = findVehicleByID(vehicle_id);

        System.out.println("Enter days rented: ");
        daysRented = scanner.nextInt();
        scanner.next();


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

    public void returnVehicle() throws IOException {
        Contract contract;
        Customer customer;
        Vehicle vehicle;
        int daysRented;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter user/vehicle/contract ids: ");
        int customer_id = scanner.nextInt();
        scanner.next();

        customer = findCustomerByID(customer_id);
        int vehicle_id = scanner.nextInt();
        scanner.next();

        vehicle = findVehicleByID(vehicle_id);
        int contract_id = scanner.nextInt();
        scanner.next();

        contract = findContractByID(contract_id);

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

        }
    }


    public Vehicle findVehicleByID(int id) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.get_count() == id) {
                System.out.println("!!Vehicle Found!!");
                return vehicle;
            }
        }
        System.out.println("!!Vehicle Not Found!!");
        return null;
    }

    public Customer findCustomerByID(int id) {
        for(Customer customer : rental_customers) {
            if (customer.get_count() == id) {
                System.out.println("!!Customer Found!!");
                return customer;
            }
        }
        System.out.println("!!Customer Not Found!!");
        return null;
    }

    public Contract findContractByID(int id) {
        for(Contract contract : rental_contracts) {
            if (contract.get_count() == id) {
                System.out.println("!!Contract Found!!");
                return contract;
            }
        }
        System.out.println("!!Contract Not Found!!");
        return null;
    }

    public Review findReviewByID(int id) {
        for(Review review : rental_reviews) {
            if (review.get_count() == id) {
                System.out.println("!!Review Found!!");
                return review;
            }
        }
        System.out.println("!!Review Not Found!!");
        return null;
    }


}
