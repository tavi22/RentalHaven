package model.customer.comparators;

import model.customer.Contract;

import java.time.Period;
import java.util.Comparator;

public class SortContractsByPeriodLength implements Comparator<Contract> {
    @Override
    public int compare(Contract o1, Contract o2) {
        Period p1 = Period.between(o1.getDate(), o1.getDueDate());
        int d1 = p1.getDays();
        Period p2 = Period.between(o2.getDate(), o2.getDueDate());
        int d2 = p2.getDays();

        return d1 - d2;
    }
}
