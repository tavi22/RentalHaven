package model.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedHashSet;
import java.util.Set;

public class VehicleDatabase {
    private final Connection connection;

    public VehicleDatabase(Connection connection) {
        this.connection = connection;
    }

    public Set<Vehicle> read() {
        Set<Vehicle> vehicles = new LinkedHashSet<>();
        try{
            Statement statement = connection.createStatement();
            ResultSet result1 = statement.executeQuery("SELECT * FROM vehicle");
            while(result1.next()) {
                Make make = new Make(result1.getString("Make"), result1.getString("Model"),
                        result1.getString("Base"), result1.getInt("Year"));

                Vehicle current = new Vehicle(make, result1.getString("Color"),
                        result1.getInt("Horsepower"), result1.getInt("TopSpeed"),
                        result1.getDouble("Price"), result1.getDouble("PricePerDay"),
                        result1.getBoolean("Available"));
                vehicles.add(current);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vehicles;
    }

    public void update(Vehicle vehicle){
        try{
            String query = "UPDATE vehicle SET PricePerDay = ? WHERE id = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setDouble(1, vehicle.getPricePerDay());
            preparedStmt.setString(2, vehicle.getId());
            preparedStmt.executeUpdate();
            preparedStmt.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void create(Vehicle vehicle){
        try{
            String query = "INSERT INTO vehicle  (id, veh_id, Make, Model, Base, Year, Color, Horsepower," +
                    "TopSpeed, Price, PricePerDay, Available) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, vehicle.get_count());
            preparedStmt.setString(2, vehicle.getId());
            preparedStmt.setString(3, vehicle.getMake().getName());
            preparedStmt.setString(4, vehicle.getMake().getModel());
            preparedStmt.setString(5, vehicle.getMake().getBase());
            preparedStmt.setInt(6, vehicle.getMake().getYear());
            preparedStmt.setString(7, vehicle.getColor());
            preparedStmt.setInt(8, vehicle.getHorsepower());
            preparedStmt.setInt(9, vehicle.getTopSpeed());
            preparedStmt.setDouble(10, vehicle.getVehiclePrice());
            preparedStmt.setDouble(11, vehicle.getPricePerDay());
            preparedStmt.setBoolean(12, vehicle.isAvailable());

            preparedStmt.execute();
            preparedStmt.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void delete(Vehicle vehicle){
        try{
            String query = "DELETE FROM vehicle WHERE veh_id = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, vehicle.getId());
            preparedStmt.execute();
            preparedStmt.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
