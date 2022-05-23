package model.customer;

import model.product.Vehicle;

import java.time.LocalDate;

public class Contract {
    private static int counter = 0;
    private final String contract_id;
    private final Customer customer;
    private final Vehicle vehicle;
    private final LocalDate date;
    private LocalDate dueDate;
    private final double totalPrice;
    private double lateFee = 0;

    public Contract(Customer customer, Vehicle vehicle, LocalDate date, LocalDate dueDate, double totalPrice) {
        this.customer = customer;
        this.vehicle = vehicle;
        this.date = date;
        this.dueDate = dueDate;
        this.totalPrice = totalPrice;

        this.contract_id = "CONTRACT_NUMBER#" + counter;
        counter++;

    }

    public static int getCounter() {
        return counter;
    }

    public String getContract_id() {
        return contract_id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }


    public LocalDate getDate() {
        return date;
    }

    public static void setCounter(int counter) {
        Contract.counter = counter;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public double getLateFee() {
        return lateFee;
    }

    public int get_count() {
        return counter;
    }

    public void setLateFee(double lateFee) {
        this.lateFee = lateFee;
    }

    @Override
    public String toString() {
        return "Contract{" +
                "contract_id='" + contract_id + '\'' +
                ", customer=" + customer +
                ", vehicle=" + vehicle +
                ", date=" + date +
                ", dueDate=" + dueDate +
                ", totalPrice=" + totalPrice +
                ", lateFee=" + lateFee +
                '}';
    }
}
