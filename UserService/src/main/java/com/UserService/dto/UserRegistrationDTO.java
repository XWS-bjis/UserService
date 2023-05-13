package com.UserService.dto;

import com.UserService.model.Address;
import com.UserService.model.Role;
import lombok.Data;

import java.util.List;
@Data
public class UserRegistrationDTO {
    private String username;
    private String password;
    private String email;
    private String name;
    private String surname;
    private Address address;
    private String role;
}
