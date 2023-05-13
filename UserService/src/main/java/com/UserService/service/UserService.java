package com.UserService.service;

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
        //TO DO: Check database, if username already exists return null
        return userRepository.save(userMapper.create(userRegistrationDTO));
    }

    public  User update(String id, UserRegistrationDTO userRegistrationDTO){
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()) {
            User userUpdate = userMapper.DtoToEntity(user.get(), userRegistrationDTO);
            userRepository.save(userUpdate);
            return userUpdate;
        }

        return null;
    }
}
