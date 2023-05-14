package com.UserService.service;

import com.UserService.dto.LoginRequest;
import com.UserService.exception.UserNotFoundException;
import com.UserService.mapper.UserMapper;
import com.UserService.dto.UserRegistrationDTO;
import com.UserService.model.User;
import com.UserService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRepository userRepository;

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
}
