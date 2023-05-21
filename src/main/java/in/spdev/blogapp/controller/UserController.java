package in.spdev.blogapp.controller;

import in.spdev.blogapp.dto.UserDTO;
import in.spdev.blogapp.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {

    private IUserService userService;

    @Autowired
    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping(
            value = "/save",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDTO){
        UserDTO user = userService.createUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PutMapping("/save/{userId}")
    public ResponseEntity<UserDTO> updateUser(
            @RequestBody UserDTO userDTO, @PathVariable Integer userId
    ){
        UserDTO updatedUser = userService.updateUser(userDTO, userId);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> deletedUser(@PathVariable Integer userId){
        userService.deleteUser(userId);
        return ResponseEntity.ok("User deleted " + userId);
    }

    @GetMapping("/fetch")
    public ResponseEntity<List<UserDTO>> fetchAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/fetch/{userId}")
    public ResponseEntity<UserDTO> fetchUser(@PathVariable Integer userId){
        return ResponseEntity.ok(userService.getUserById(userId));
    }
}
