package sdk.Models;

import java.util.Date;

/**
 * Skabelon for en lektion.
 */
public class Lecture {

    private int id; // Identifier på lektionen
    private int courseId; // Identifier på kurset
    private String type; // Brugertype
    private String description; //Navn på kurset
    private String location; // Lokale placering

    public Lecture() {

    }

    public Lecture(int id, int courseId, String type, String description, String location) {
        this.id = id;
        this.courseId = courseId;
        this.type = type;
        this.description = description;
        this.location = location;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() { return this.location; }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getCourseId() {
        return this.courseId;
    }

    public void setCourse_Id(int courseId) {
        this.courseId = courseId;
    }
}