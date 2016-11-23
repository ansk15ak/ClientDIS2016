package sdk.Models;

import java.util.Date;

/**
 * Created by Junineskov on 14/11/2016.
 */
public class Lecture {

    private int id;
    private int courseId;
    private String type;
    private String description;
    private String location;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourse_Id(int courseId) {
        this.courseId = courseId;
    }
}