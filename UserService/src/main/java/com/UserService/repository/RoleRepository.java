package com.UserService.repository;

import com.UserService.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, String> {

    Optional<Role> findOneByType(String type);
}
