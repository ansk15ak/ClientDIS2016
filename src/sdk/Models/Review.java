package sdk.Models;

/**
 * Skabelon for et review.
 */
public class Review {

    private int id; // Identifier på review
    private int userId; // Identifier på bruger
    private int lectureId; // Identifier på lektion
    private int rating; // Score på lektion
    private String comment; // Kommentar på lektion

    public Review() {

    }

    public Review(int id, int userId, int lectureId, int rating, String comment) {
        this.id = id;
        this.userId = userId;
        this.lectureId = lectureId;
        this.rating = rating;
        this.comment = comment;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() { return this.userId; }

    public void setUserId(int userId) { this.userId = userId; }

    public int getLectureId() {
        return this.lectureId;
    }

    public void setLectureId(int lectureId) {
        this.lectureId = lectureId;
    }

    public int getRating() {
        return this.rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
