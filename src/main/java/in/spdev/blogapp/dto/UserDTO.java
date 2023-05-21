package in.spdev.blogapp.dto;

import lombok.*;

@Builder
public record UserDTO(int id, String name, String password, String email, String about) {}
