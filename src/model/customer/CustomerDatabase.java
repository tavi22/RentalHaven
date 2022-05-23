package model.customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedHashSet;
import java.util.Set;

public class CustomerDatabase {
    private final Connection connection;

    public CustomerDatabase(Connection connection) {
        this.connection = connection;
    }

    public Set<Customer> read() {
        Set<Customer> customers = new LinkedHashSet<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet result1 = statement.executeQuery("SELECT * FROM customer");
            while (result1.next()) {
               Customer customer = new Customer(result1.getString("fullName"),
                       result1.getString("phoneNumber"), new Address(result1.getString("street"),
                       result1.getInt("street_number"), result1.getString("city"),
                       result1.getString("county"), result1.getString("postal")));

                customers.add(customer);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customers;
    }

    public void update(Customer customer) {
        try {
            String query = "UPDATE customer SET phoneNumber = ? WHERE customer_id = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, customer.getPhoneNumber());
            preparedStmt.setString(2, customer.getId());
            preparedStmt.executeUpdate();
            preparedStmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void create(Customer customer) {
        try {
            String query = "INSERT INTO customer" +
                    "  (id, customer_id, fullName, phoneNumber, street, street_number, city, county, postal)" +
                    " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, customer.get_count());
            preparedStmt.setString(2, customer.getId());
            preparedStmt.setString(3, customer.getFullName());
            preparedStmt.setString(4, customer.getPhoneNumber());
            preparedStmt.setString(5, customer.getAddress().getStreet());
            preparedStmt.setInt(6, customer.getAddress().getStreetNumber());
            preparedStmt.setString(7, customer.getAddress().getCity());
            preparedStmt.setString(8, customer.getAddress().getCounty());
            preparedStmt.setString(9, customer.getAddress().getPostalCode());
            preparedStmt.execute();
            preparedStmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(Customer customer) {
        try {
            String query = "DELETE FROM customer WHERE customer_id = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, customer.getId());
            preparedStmt.execute();
            preparedStmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
