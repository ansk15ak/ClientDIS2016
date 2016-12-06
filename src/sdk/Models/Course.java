package sdk.Models;

/**
 * Skabelon for et kursus.
 */
public class Course {

    private String id; // Identifier på kurset
    private String code; // Navnet på kurset
    private String displaytext; // Kode på kurset

    public Course(String id, String code, String displaytext) {
        this.id = id;
        this.code = code;
        this.displaytext = displaytext;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDisplaytext() {
        return this.displaytext;
    }

    public void setDisplaytext(String name) {
        this.displaytext = displaytext;
    }
}
