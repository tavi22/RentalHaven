package model.customer;

import model.product.Make;
import model.product.Vehicle;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class ContractDatabase {
    private final Connection connection;

    public ContractDatabase(Connection connection) {
        this.connection = connection;
    }

    public List<Contract> read() {
        List<Contract> contracts = new LinkedList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet result1 = statement.executeQuery("SELECT * FROM contract c " +
                                                "INNER JOIN customer cst ON c.customer_id = cst.id " +
                                                "INNER JOIN vehicle v on c.veh_id = v.id");
            while (result1.next()) {
                Contract contract = new Contract(
                        new Customer(result1.getString("fullName"),
                                result1.getString("phoneNumber"), new Address(result1.getString("street"),
                                result1.getInt("street_number"), result1.getString("city"),
                                result1.getString("county"), result1.getString("postal"))),
                        new Vehicle(new Make(result1.getString("Make"), result1.getString("Model"),
                                result1.getString("Base"), result1.getInt("Year")), result1.getString("Color"),
                                result1.getInt("Horsepower"), result1.getInt("TopSpeed"),
                                result1.getDouble("Price"), result1.getDouble("PricePerDay"),
                                result1.getBoolean("Available")), Date.valueOf(String.valueOf(result1.getDate("date"))).toLocalDate(),
                        Date.valueOf(String.valueOf(result1.getDate("due_date"))).toLocalDate(), result1.getDouble("total_price"));

                contracts.add(contract);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contracts;
    }

    public void update(Contract contract) {
        try {
            String query = "UPDATE contract SET due_date = ? WHERE con_id = ??";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setDate(1, Date.valueOf(contract.getDueDate()));
            preparedStmt.setString(2, contract.getContract_id());

            preparedStmt.executeUpdate();
            preparedStmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void create(Contract contract, int c_id, int v_id) {
        try {
            String query = "INSERT INTO contract" +
                    "  (id, con_id, customer_id, veh_id, date, due_date, total_price, fee)" +
                    " VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, contract.get_count());
            preparedStmt.setString(2, contract.getContract_id());
            preparedStmt.setInt(3, c_id);
            preparedStmt.setInt (4, v_id);
            preparedStmt.setDate(5, Date.valueOf(contract.getDate()));
            preparedStmt.setDate(6, Date.valueOf(contract.getDueDate()));
            preparedStmt.setDouble(7, contract.getTotalPrice());
            preparedStmt.setDouble(8, contract.getLateFee());
            preparedStmt.execute();
            preparedStmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(Contract contract) {
        try {
            String query = "DELETE FROM review WHERE rev_id = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, contract.getContract_id());
            preparedStmt.execute();
            preparedStmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
