package com.UserService.mapper;

import com.UserService.dto.UserRegistrationDTO;
import com.UserService.model.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

//    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }

    public User DtoToEntity(User user, UserRegistrationDTO userRegistrationDTO){
        user.setName(userRegistrationDTO.getName());
        user.setSurname(userRegistrationDTO.getSurname());
        user.setUsername(userRegistrationDTO.getUsername());
       // user.setPassword(passwordEncoder().encode(userRegistrationDTO.getPassword()));
        user.setPassword(userRegistrationDTO.getPassword());
        user.setEmail(userRegistrationDTO.getEmail());
        user.setAddress(userRegistrationDTO.getAddress());
        return user;
    }

    private User setRoleAndCancellationNumber(User user){
        user.setCancellationNumber(0);
        //TO DO: find role from database
        return user;
    }

    public User create(UserRegistrationDTO userRegistrationDTO){
        User user = new User();
        User profileInfo = DtoToEntity(user, userRegistrationDTO);
        return setRoleAndCancellationNumber(profileInfo);
    }
}
