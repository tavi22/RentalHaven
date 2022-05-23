package model.customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedHashSet;
import java.util.Set;

public class ReviewDatabase {
    private final Connection connection;

    public ReviewDatabase(Connection connection) {
        this.connection = connection;
    }

    public Set<Review> read() {
        Set<Review> reviews = new LinkedHashSet<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet result1 = statement.executeQuery("SELECT * FROM review r " +
                                                        "INNER JOIN customer c ON r.c_id = c.id");
            while (result1.next()) {
                Review review = new Review(new Customer(result1.getString("fullName"),
                        result1.getString("phoneNumber"), new Address(result1.getString("street"),
                        result1.getInt("street_number"), result1.getString("city"),
                        result1.getString("county"), result1.getString("postal"))),
                        result1.getString("body"),
                        result1.getInt("rating"), result1.getBoolean("satisfied"));

                reviews.add(review);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reviews;
    }

    public void update(Review review) {
        try {
            String query = "UPDATE review SET body = ?, rating = ? WHERE rev_id = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, review.getBody());
            preparedStmt.setInt(2, review.getRating());
            preparedStmt.setString(1, review.getId());

            preparedStmt.executeUpdate();
            preparedStmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void create(Review review, int c_id) {
        try {
            String query = "INSERT INTO review" +
                    "  (rev_id, body, rating, satisfied, c_id)" +
                    " VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, review.getId());
            preparedStmt.setString(2, review.getBody());
            preparedStmt.setInt(3, review.getRating());
            preparedStmt.setBoolean(4, review.isSatisfied());
            preparedStmt.setInt(5, c_id);
            preparedStmt.execute();
            preparedStmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(Review review) {
        try {
            String query = "DELETE FROM review WHERE rev_id = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, review.getId());
            preparedStmt.execute();
            preparedStmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
