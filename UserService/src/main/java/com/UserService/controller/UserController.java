package com.UserService.controller;

import com.UserService.dto.LoginRequest;
import com.UserService.dto.LoginResponse;
import com.UserService.dto.UserRegistrationDTO;
import com.UserService.model.User;
import com.UserService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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
    public ResponseEntity<UserRegistrationDTO> findById(@PathVariable("id") String id){
        UserRegistrationDTO user = userService.findById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserRegistrationDTO> update(@PathVariable("id") String id, @RequestBody UserRegistrationDTO userRegistrationDTO){
        UserRegistrationDTO user = userService.update(id, userRegistrationDTO);
        return new ResponseEntity(user,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id){
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping ("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){
        User user = userService.findByUsernameAndPassword(loginRequest);
        if(user != null){
            LoginResponse loginResponse = new LoginResponse(user.getId(), user.getRole().getType());
            return new ResponseEntity<>(loginResponse, HttpStatus.OK);
        }
        return new ResponseEntity<>(new LoginResponse(), HttpStatus.BAD_REQUEST);
    }
}
