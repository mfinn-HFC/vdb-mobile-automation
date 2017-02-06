package api;

/**
 * Created by matt-hfc on 1/23/17.
 */
public class User {

    private String email;
    private boolean active;

    public void setEmail(String email) {
        this.email = email;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getEmail() {
        return email;
    }

    public boolean isActive() {
        return active;
    }
}
