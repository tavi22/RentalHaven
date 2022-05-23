package model.customer;

import java.util.LinkedHashSet;
import java.util.List;

public class Customer {
    private static int counter = 0;
    private final String id;
    private String fullName;
    private String phoneNumber;

    private Address address;

    private LinkedHashSet<Contract> contracts = null;

    private List<Accident> accidents = null;

    private LinkedHashSet<Review> reviews = null;

    public Customer(String fullName, String phoneNumber, Address address) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.address = address;

        this.id = "RH_CUSTOMER_" + this.fullName + "#" + counter;
        counter++;
    }

    public Customer(String fullName, String phoneNumber, Address address, LinkedHashSet<Contract> contracts, List<Accident> accidents, LinkedHashSet<Review> reviews) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.contracts = contracts;
        this.accidents = accidents;
        this.reviews = reviews;

        this.id = "RH_CUSTOMER_" + this.fullName + "#" + counter;
        counter++;
    }

    public int get_count() {
        return counter;
    }

    public String getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LinkedHashSet<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(LinkedHashSet<Contract> contracts) {
        this.contracts = contracts;
    }

    public List<Accident> getAccidents() {
        return accidents;
    }

    public void setAccidents(List<Accident> accidents) {
        this.accidents = accidents;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public LinkedHashSet<Review> getReviews() {
        return reviews;
    }

    public void setReviews(LinkedHashSet<Review> reviews) {
        this.reviews = reviews;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", fullName='" + fullName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address +
                '}';
    }
}
