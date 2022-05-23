package model.product;

import model.product.comparators.VehicleSortByHourlyRate;
import model.product.types.BicycleType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Set;
import java.util.TreeSet;

public record BikeDatabase(Connection connection) {

    public Set<Bicycle> read() {
        Set<Bicycle> bicycles = new TreeSet<>(new VehicleSortByHourlyRate());
        try {
            Statement statement = connection.createStatement();
            ResultSet result1 = statement.executeQuery("SELECT * FROM vehicle v JOIN bicycle b\n" +
                                                            "ON v.id = b.v_id;");
            while (result1.next()) {
                Make make = new Make(result1.getString("Make"), result1.getString("Model"),
                        result1.getString("Base"), result1.getInt("Year"));
                BicycleType tp = switch (result1.getString("bike_type")) {
                    case "ROAD" -> BicycleType.ROAD;
                    case "FIXED_GEAR" -> BicycleType.FIXED_GEAR;
                    case "MOUNTAIN" -> BicycleType.MOUNTAIN;
                    case "BMX" -> BicycleType.BMX;
                    case "RECUMBENT" -> BicycleType.RECUMBENT;
                    case "FOLDING" -> BicycleType.FOLDING;

                    default -> null;
                };

                Bicycle current = new Bicycle(make, result1.getString("Color"),
                        result1.getInt("Horsepower"), result1.getInt("TopSpeed"),
                        result1.getDouble("Price"), result1.getDouble("PricePerDay"),
                        result1.getBoolean("Available"),
                        tp, result1.getString("propulsion"));
                bicycles.add(current);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bicycles;
    }

    public void update(Bicycle bicycle) {
        try {
            String query = "UPDATE bicycle SET propulsion = ? WHERE bike_id = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, bicycle.getPropulsion());
            preparedStmt.setString(2, bicycle.getId());
            preparedStmt.executeUpdate();
            preparedStmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void create(Bicycle bicycle, int v_id) {
        try {
            String query = "INSERT INTO bicycle  (bike_id, bike_type, propulsion, v_id) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, bicycle.getId());
            preparedStmt.setString(2, bicycle.getBicycleType().toString());
            preparedStmt.setString(3, bicycle.getPropulsion());
            preparedStmt.setInt(4, v_id);
            preparedStmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(Bicycle bicycle) {
        try {
            String query = "DELETE FROM bicycle WHERE bike_id = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, bicycle.getId());
            preparedStmt.execute();
            preparedStmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
