package view.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class UserDTO {
    private StringProperty id;
    private StringProperty username;

    // ID Property
    public void setId(String id) {
        idProperty().set(id);
    }

    public String getId() {
        return idProperty().get();
    }

    public StringProperty idProperty() {
        if (id == null) {
            id = new SimpleStringProperty(this, "id");
        }
        return id;
    }

    // Username Property
    public void setUsername(String username) {
        usernameProperty().set(username);
    }

    public String getUsername() {
        return usernameProperty().get();
    }

    public StringProperty usernameProperty() {
        if (username == null) {
            username = new SimpleStringProperty(this, "username");
        }
        return username;
    }

    // Constructor for initialization
    public UserDTO(String id, String username) {
        this.setId(id);
        this.setUsername(username);
    }

}
