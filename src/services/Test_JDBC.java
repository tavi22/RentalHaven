package services;

import model.product.MotoDatabase;
import model.product.Motorcycle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Set;

public class Test_JDBC {
    public static void main(String[] args) {

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_pao", "root", "paopassword");

            MotoDatabase motoDatabase = new MotoDatabase(connection);
            Set<Motorcycle> motorcycles = motoDatabase.read();
            System.out.println(motorcycles.size());

            for (Motorcycle m : motorcycles) {
                System.out.println(m + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }





    }
}
