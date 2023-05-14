package com.UserService.mapper;

import com.UserService.dto.UserRegistrationDTO;
import com.UserService.model.Role;
import com.UserService.model.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import com.UserService.repository.RoleRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserMapper {

//    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
    private final RoleRepository roleRepository;

    public UserMapper(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public User DtoToEntity(User user, UserRegistrationDTO userRegistrationDTO){
        user.setName(userRegistrationDTO.getName());
        user.setSurname(userRegistrationDTO.getSurname());
        user.setUsername(userRegistrationDTO.getUsername());
        user.setPassword(userRegistrationDTO.getPassword());
        user.setEmail(userRegistrationDTO.getEmail());
        user.setAddress(userRegistrationDTO.getAddress());
        return user;
    }

    private User setRoleAndCancellationNumber(User user, String role){
        user.setCancellationNumber(0);
        Optional<Role> userRole = roleRepository.findOneByType(role);
        System.out.println("+++++++++++++++++++++++" + userRole + "++++++++++++++++");
        if(userRole.isPresent())
           user.setRole(userRole.get());
        return user;
    }

    public User create(UserRegistrationDTO userRegistrationDTO){
        User user = new User();
        User profileInfo = DtoToEntity(user, userRegistrationDTO);
        return setRoleAndCancellationNumber(profileInfo, userRegistrationDTO.getRole());
    }

    public UserRegistrationDTO EntityToDto(User userEntity){
        UserRegistrationDTO user = new UserRegistrationDTO();
        user.setName(userEntity.getName());
        user.setSurname(userEntity.getSurname());
        user.setUsername(userEntity.getUsername());
        user.setPassword(userEntity.getPassword());
        user.setEmail(userEntity.getEmail());
        user.setAddress(userEntity.getAddress());
        return user;
    }
}
