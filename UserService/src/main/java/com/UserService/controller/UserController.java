package com.UserService.controller;

import com.UserService.dto.LoginRequest;
import com.UserService.dto.LoginResponse;
import com.UserService.dto.UserRegistrationDTO;
import com.UserService.model.User;
import com.UserService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RestTemplate restTemplate;


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


    @GetMapping("/reservation/{id}")
    public ResponseEntity getClaimPropertyById(@PathVariable String id){
        String claimsControllerUrl = "http://reservation-service:8083/api/reservation/user/"+id;
        ResponseEntity<List<Object>> response = restTemplate.exchange(
                claimsControllerUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Object>>() {}
        );
        return response;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/featured-hosts")
    public ResponseEntity<List<String>> getAllFeaturedHosts(){
        System.out.println("Pozdravvvvvvv");
        return new ResponseEntity<>(userService.getAllFeaturedHosts(), HttpStatus.OK);
    }

    @PutMapping("/featured-host/{id}")
    public ResponseEntity updateFeaturedHost(@PathVariable("id") String id){
        userService.updateFeaturedHost(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}/visited-hosts")
    public ResponseEntity<List<User>> getAllHostsByGuest(@PathVariable("id") String id){
//        String reservationControllerURL = "http://accommodation:8080/accommodation/host-identifiers/guest/"+id;
//        ResponseEntity<List<String>> response = restTemplate.exchange(
//                reservationControllerURL,
//                HttpMethod.GET,
//                null,
//                new ParameterizedTypeReference<List<String>>() {}
//        );
//        return response;
        return new ResponseEntity<>(userService.getRatedHostsByGuest(id), HttpStatus.OK);


    }
}
