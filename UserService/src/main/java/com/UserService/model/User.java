package com.UserService.model;

import lombok.Data;
import java.util.List;

@Data
public class User {
    private String id;
    private String username;
    private String password;
    private String email;
    private String name;
    private String surname;
    private Address address;
    private Role role;
    private List<String> reservationId;
    private Integer cancellationNumber;
}
