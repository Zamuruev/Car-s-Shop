package CarShop.services.Interf;

import CarShop.models.dtos.UserDTO;

import java.util.List;

public interface UserService {
    void addUser(UserDTO userDTO);
    void deleteUser(String id);
    void deleteAllUsers();
    List<UserDTO> allUsers();
    UserDTO getUserById(String id);
    UserDTO getUserByLogin(String login);
    void registerUser(UserDTO userDTO);
    void updateOnline(String login, boolean active);
    void deleteById(String id);
}
