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

    @GetMapping("/find/{id}")
    public ResponseEntity<User> findById(@PathVariable("id") String id){
        User user = userService.findById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Optional<User>> update(@PathVariable("id") String id, @RequestBody UserRegistrationDTO userRegistrationDTO){
        User user = userService.update(id, userRegistrationDTO);
        return new ResponseEntity(user,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id){
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
