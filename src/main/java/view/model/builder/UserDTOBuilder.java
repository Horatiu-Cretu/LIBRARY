package view.model.builder;

import view.model.UserDTO;

public class UserDTOBuilder {
    private UserDTO userDTO;

    public UserDTOBuilder() {
        userDTO = new UserDTO(null, null); // Initialize with null values
    }

    public UserDTOBuilder setId(String id) {
        userDTO.setId(id);
        return this;
    }

    public UserDTOBuilder setUsername(String username) {
        userDTO.setUsername(username);
        return this;
    }

    public UserDTO build() {
        return userDTO;
    }
}
