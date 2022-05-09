package model.customer;

import java.time.LocalDate;

public class Accident {
    private double gravity;
    private LocalDate date;

    public Accident(){}

    public Accident(double gravity, LocalDate date) {
        this.gravity = gravity;
        this.date = date;
    }

    public double getGravity() {
        return gravity;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Accident{" +
                "gravity=" + gravity +
                ", date=" + date +
                '}';
    }
}
