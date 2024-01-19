package CarShop.services.Impl;

import CarShop.models.dtos.UserDTO;
import CarShop.models.entities.Basket;
import CarShop.models.entities.User;
import CarShop.models.enums.Role;
import CarShop.repositories.BasketRepository;
import CarShop.repositories.UserRepository;
import CarShop.services.Interf.UserService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;
    private UserRepository userRepository;
    private BasketRepository basketRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setBasketRepository(BasketRepository basketRepository) {
        this.basketRepository = basketRepository;
    }

    @Override
    public void addUser(UserDTO userDTO) {
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        User user = modelMapper.map(userDTO, User.class);
        user.setCreated(LocalDateTime.now());
        user.setModified(LocalDateTime.now());
        user = userRepository.save(user);
        basketRepository.save(new Basket(user));
    }

    @Override
    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void deleteAllUsers() {
        List<User> users = userRepository.findAllByRole(Role.User);
        for(User user : users) {
            basketRepository.deleteByUser(user);
        }
        userRepository.deleteAllByRole(Role.User);
    }

    @Override
    public List<UserDTO> allUsers() {
        return userRepository.findAllOrderByActive().stream().map((user) -> modelMapper.map(user, UserDTO.class)).toList();
    }

    @Override
    public UserDTO getUserById(String id) {
        return modelMapper.map(userRepository.findById(id), UserDTO.class);
    }

    @Override
    public UserDTO getUserByLogin(String login) {
        return modelMapper.map(userRepository.findByLogin(login), UserDTO.class);
    }

    @Override
    public void registerUser(UserDTO userDTO) {
        userDTO.setRole(Role.User.name());
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        User user = modelMapper.map(userDTO, User.class);
        user.setCreated(LocalDateTime.now());
        user.setModified(LocalDateTime.now());
        user = userRepository.save(user);
        basketRepository.save(new Basket(user));
    }

    @Override
    public void updateOnline(String login, boolean active) {
        userRepository.updateOnline(login,active);
    }

    @Override
    public void deleteById(String id) {
        basketRepository.deleteByUserId(id);
        userRepository.deleteById(id);
    }
}
