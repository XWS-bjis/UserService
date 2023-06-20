package com.UserService.service;

import com.UserService.dto.LoginRequest;
import com.UserService.exception.UserNotFoundException;
import com.UserService.mapper.UserMapper;
import com.UserService.dto.UserRegistrationDTO;
import com.UserService.model.User;
import com.UserService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    public User register(UserRegistrationDTO userRegistrationDTO) {
        User user = userMapper.create(userRegistrationDTO);
        return userRepository.save(user);
    }

    public UserRegistrationDTO findById(String id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User by id" + id + "was not found"));
        return userMapper.EntityToDto(user);
    }


    public  UserRegistrationDTO update(String id, UserRegistrationDTO userRegistrationDTO){
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()) {
            User userUpdate = userMapper.DtoToEntity(user.get(), userRegistrationDTO);
            userRepository.save(userUpdate);
            UserRegistrationDTO updatedUser = userMapper.EntityToDto(userUpdate);
            return updatedUser;
        }
        return null;
    }

    public void delete(String id){
        Optional<User> user = userRepository.findById(id);
        userRepository.delete(user.get());
    }

    public User findByUsernameAndPassword(LoginRequest loginRequest){
        Optional<User> user = userRepository.findByUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword());
        if(user.isPresent()){
           // UserRegistrationDTO foundUser = userMapper.EntityToDto(user.get());
            return user.get();
        }
        return null;
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public List<String> getAllFeaturedHosts(){
        List<User> users = userRepository.findAll();
        List<String> highlightedHost = new ArrayList<>();
        for (User user: users){
            if(user.isHighlightedHost() == true){
                highlightedHost.add(user.getId());
            }
        }
        return highlightedHost;
    }

    public void updateFeaturedHost(String id){
        User user = userRepository.findById(id).orElse(null);
        if(user != null){
            if(user.getAvgGrade() >= 4.7){
                user.setHighlightedHost(true);
                userRepository.save(user);
            }else{
                user.setHighlightedHost(false);
                userRepository.save(user);
            }
        }
    }

    public List<User> getRatedHostsByGuest(String id){
        String reservationControllerURL = "http://accommodation:8080/accommodation/host-identifiers/guest/"+id;
        ResponseEntity<List<String>> response = restTemplate.exchange(
                reservationControllerURL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<String>>() {}
        );
        List<User> ratedHosts = new ArrayList<>();
        if(response.getBody().size() > 0){
            List<User> hosts = userRepository.findAll();
            for(String hostId: response.getBody()){
                for(User user: hosts){
                    if(user.getId().equals(hostId)){
                        ratedHosts.add(user);
                    }
                }
            }
        }
        return ratedHosts;

    }
}
