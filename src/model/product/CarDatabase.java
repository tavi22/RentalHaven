package model.product;

import model.product.comparators.VehicleSortByHP;
import model.product.types.CarType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Set;
import java.util.TreeSet;

public record CarDatabase(Connection connection) {

    public Set<Car> read() {
        Set<Car> cars = new TreeSet<>(new VehicleSortByHP());
        try {
            Statement statement = connection.createStatement();
            ResultSet result1 = statement.executeQuery("SELECT * FROM vehicle v " +
                                                    "INNER JOIN car b ON v.id = b.v_id");
            while (result1.next()) {
                Make make = new Make(result1.getString("Make"), result1.getString("Model"),
                        result1.getString("Base"), result1.getInt("Year"));
                CarType tp = switch (result1.getString("car_type")) {
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

                Car current = new Car(make, result1.getString("Color"),
                        result1.getInt("Horsepower"), result1.getInt("TopSpeed"),
                        result1.getDouble("Price"), result1.getDouble("PricePerDay"),
                        result1.getBoolean("Available"),
                        tp, result1.getString("engine"), result1.getDouble("fuel_consumption"),
                        result1.getInt("seats"));
                cars.add(current);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cars;
    }

    public void update(Car newCar) {
        try {
            String query = "UPDATE car SET engine = ? WHERE car_id = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, newCar.getEngineType());
            preparedStmt.setString(2, newCar.getId());
            preparedStmt.executeUpdate();
            preparedStmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void create(Car car, int v_id) {
        try {
            String query = "INSERT INTO car  (v_id, car_id, car_type, engine, fuel_consumption, seats)" +
                    " VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, v_id);
            preparedStmt.setString(2, car.getId());
            preparedStmt.setString(3, car.getCarType().toString());
            preparedStmt.setString(4, car.getEngineType());
            preparedStmt.setDouble(5, car.getFuelConsumption());
            preparedStmt.setInt(6, car.getSeats());
            preparedStmt.execute();
            preparedStmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(Car car) {
        try {
            String query = "DELETE FROM car WHERE car_id = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, car.getId());
            preparedStmt.execute();
            preparedStmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
