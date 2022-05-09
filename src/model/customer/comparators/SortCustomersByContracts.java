package model.customer.comparators;

import model.customer.Customer;

import java.util.Comparator;

public class SortCustomersByContracts implements Comparator<Customer> {
    @Override
    public int compare(Customer o1, Customer o2) {
        return o1.getContracts().size() - o2.getContracts().size();
    }
}
