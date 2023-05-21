package in.spdev.blogapp.service;

import in.spdev.blogapp.dto.UserDTO;
import in.spdev.blogapp.entity.User;
import in.spdev.blogapp.exception.ResourceNotFoundException;
import in.spdev.blogapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = dtoToUser(userDTO);
        User savedUser = userRepository.save(user);
        return userToDTO(savedUser);
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO, Integer userId) {
        //check user is available or not, if not throw an Exception
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));

        //Update the User
        user.setName(userDTO.name());
        user.setPassword(userDTO.password());
        user.setEmail(userDTO.email());
        user.setAbout(userDTO.about());

        User updatedUser = userRepository.save(user);

        //convert updated user to DTO & return
        return userToDTO(user);
    }

    @Override
    public UserDTO getUserById(Integer userId) {
        //if user available return as DTO , or else throw an Exception
        return userToDTO(userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId)));
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream().map(UserServiceImpl::userToDTO).toList();
    }

    @Override
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    private static User dtoToUser(UserDTO userDTO) {
        return User.builder()
                .name(userDTO.name())
                .password(userDTO.password())
                .email(userDTO.email())
                .about(userDTO.about())
                .build();
    }

    private static UserDTO userToDTO(User user){
        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .password(user.getPassword())
                .email(user.getEmail())
                .about(user.getAbout())
                .build();
    }
}
