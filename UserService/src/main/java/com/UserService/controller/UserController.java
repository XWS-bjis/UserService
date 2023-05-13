package com.UserService.controller;

import com.UserService.dto.UserRegistrationDTO;
import com.UserService.model.User;
import com.UserService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity register(@RequestBody UserRegistrationDTO userRegistrationDTO){
        User user = userService.register(userRegistrationDTO);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Optional<User>> update(@PathVariable("id") String id, @RequestBody UserRegistrationDTO userRegistrationDTO){
        User user = userService.update(id, userRegistrationDTO);
        return new ResponseEntity(user,HttpStatus.OK);
    }
}
