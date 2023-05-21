package in.spdev.blogapp.service;

import in.spdev.blogapp.dto.UserDTO;

import java.util.List;

public interface IUserService {
    UserDTO createUser(UserDTO userDTO);
    UserDTO updateUser(UserDTO userDTO,Integer userId);
    UserDTO getUserById(Integer userId);
    List<UserDTO> getAllUsers();
    void deleteUser(Integer id);
}
