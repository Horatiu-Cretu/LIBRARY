package mapper;

import model.User;
import view.model.UserDTO;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    // Convert User to UserDTO
    public static UserDTO convertUserToUserDTO(User user) {
        return new UserDTO(
                String.valueOf(user.getId()), // Convert integer ID to String
                user.getUsername()
        );
    }

    // Convert UserDTO to User
    public static User convertUserDTOToUser(UserDTO userDTO) {
        User user = new User();
        user.setId((long) Integer.parseInt(userDTO.getId()));
        user.setUsername(userDTO.getUsername());
        return user;
    }

    // Convert List<User> to List<UserDTO>
    public static List<UserDTO> convertUserListToUserDTOList(List<User> users) {
        return users
                .parallelStream()
                .map(UserMapper::convertUserToUserDTO)
                .collect(Collectors.toList());
    }

    // Convert List<UserDTO> to List<User>
    public static List<User> convertUserDTOListToUserList(List<UserDTO> userDTOS) {
        return userDTOS
                .parallelStream()
                .map(UserMapper::convertUserDTOToUser)
                .collect(Collectors.toList());
    }
}
