package model.customer.comparators;

import model.customer.Review;

import java.util.Comparator;

public class SortReviewsByRating implements Comparator<Review> {
    @Override
    public int compare(Review o1, Review o2) {
        return o1.getRating() - o2.getRating();
    }
}
