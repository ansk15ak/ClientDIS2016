package sdk.Models;

/**
 * Skabelon for et studie.
 */
public class Study {
    private int id;
    private String name;
    private String shortname;

    public Study(int id, String name, String shortname) {
        this.id = id;
        this.name = name;
        this.shortname = shortname;
    }

    public int getId() { return this.id; }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() { return this.name; }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortname() {
        return this.shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }
}
