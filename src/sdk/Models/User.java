package sdk.Models;

/**
 * Skabelon for en bruger.
 */
public class User {

    private int id; // Identifier på bruger
    private String cbsMail; // Brugerens mail
    private String password; // Brugerens password
    private String type; // Brugertype

    public User() {

    }

    public User(int id, String cbsMail, String password, String type) {
        this.id = id;
        this.cbsMail = cbsMail;
        this.password = password;
        this.type = type;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCbsMail() { return this.cbsMail; }

    public void setCbsMail(String cbsMail) {
        this.cbsMail = cbsMail;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() { return this.type; }

    public void setType(String type) {
        this.type = type;
    }
}
