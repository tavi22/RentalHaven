package model.customer.comparators;

import model.customer.Customer;

import java.util.Comparator;

public class SortCustomersByReviews implements Comparator<Customer> {
    @Override
    public int compare(Customer o1, Customer o2) {
        return o1.getReviews().size() - o2.getReviews().size();
    }
}
