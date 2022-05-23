package model.customer;

public class Review {
    private static int counter = 0;
    private final String id;
    private final Customer customer;
    private String body;
    private int rating;
    private boolean satisfied;

    public Review(Customer customer, String body, int rating, boolean satisfied) {
        this.customer = customer;
        this.body = body;
        this.rating = rating;
        this.satisfied = satisfied;

        this.id = "RH_REVIEW#" + counter;
        counter++;
    }

    public int get_count() {
        return counter;
    }

    public Customer getCustomer() {
        return customer;
    }

    public String getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    public int getRating() {
        return rating;
    }

    public boolean isSatisfied() {
        return satisfied;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setSatisfied(boolean satisfied) {
        this.satisfied = satisfied;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id='" + id + '\'' +
                ", customer=" + customer +
                ", body='" + body + '\'' +
                ", rating=" + rating +
                ", satisfied=" + satisfied +
                '}';
    }
}
