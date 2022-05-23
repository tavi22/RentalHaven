package model.product;

import model.product.comparators.VehicleSortByManufacturerPrice;
import model.product.types.MotorcycleType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Set;
import java.util.TreeSet;

public record MotoDatabase(Connection connection) {

    public Set<Motorcycle> read() {
        Set<Motorcycle> motorcycles = new TreeSet<>(new VehicleSortByManufacturerPrice());
        try {
            Statement statement = connection.createStatement();
            ResultSet result1 = statement.executeQuery("SELECT * FROM vehicle v " +
                                                        "INNER JOIN motorcycle m ON v.id = m.v_id");
            while (result1.next()) {
                Make make = new Make(result1.getString("Make"), result1.getString("Model"),
                        result1.getString("Base"), result1.getInt("Year"));
                MotorcycleType tp = switch (result1.getString("moto_type")) {
                    case "STANDARD" -> MotorcycleType.STANDARD;
                    case "CRUISER" -> MotorcycleType.CRUISER;
                    case "SPORT" -> MotorcycleType.SPORT;
                    case "TOURING" -> MotorcycleType.TOURING;
                    case "SCOOTER" -> MotorcycleType.SCOOTER;
                    case "MOPED" -> MotorcycleType.MOPED;
                    case "OFF_ROAD" -> MotorcycleType.OFF_ROAD;
                    default -> null;
                };

                Motorcycle current = new Motorcycle(make, result1.getString("Color"),
                        result1.getInt("Horsepower"), result1.getInt("TopSpeed"),
                        result1.getDouble("Price"), result1.getDouble("PricePerDay"),
                        result1.getBoolean("Available"),
                        tp, result1.getString("engine"), result1.getInt("no_wheels"));
                motorcycles.add(current);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return motorcycles;
    }

    public void update(Motorcycle motorcycle) {
        try {
            String query = "UPDATE motorcycle SET engine = ? WHERE moto_id = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, motorcycle.getEngineType());
            preparedStmt.setString(2, motorcycle.getId());
            preparedStmt.executeUpdate();
            preparedStmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void create(Motorcycle motorcycle, int v_id) {
        try {
            String query = "INSERT INTO motorcycle  (moto_id, moto_type, engine, no_wheels, v_id) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, motorcycle.getId());
            preparedStmt.setString(2, motorcycle.getMotorcycleType().toString());
            preparedStmt.setString(3, motorcycle.getEngineType());
            preparedStmt.setInt(4, motorcycle.getNo_wheels());
            preparedStmt.setInt(5, v_id);
            preparedStmt.execute();
            preparedStmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(Motorcycle motorcycle) {
        try {
            String query = "DELETE FROM motorcycle WHERE moto_id = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, motorcycle.getId());
            preparedStmt.execute();
            preparedStmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
