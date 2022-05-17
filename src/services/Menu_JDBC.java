package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Menu_JDBC {
    public static void main(String[] args) {

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_pao", "root", "paopassword");

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM vehicle");

            while (resultSet.next()) {
                System.out.println(resultSet.getString("Make"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
