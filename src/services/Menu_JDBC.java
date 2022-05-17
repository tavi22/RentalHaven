package services;

import model.product.Car;
import model.product.CarDatabase;
import model.product.Make;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Set;

public class Menu_JDBC {
    public static void main(String[] args) {

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_pao", "root", "paopassword");

            CarDatabase carDatabase = new CarDatabase(connection);
            Car car = new Car(new Make("", "", "", 1), "", 1,
                    1,2,2,true, null,"electric",1,1);
            car.setId("test");
            carDatabase.update(car);
            Set<Car> cars = carDatabase.read();
            for (Car c : cars) {
                System.out.println(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }





    }
}
