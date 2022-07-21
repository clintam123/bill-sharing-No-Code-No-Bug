package com.nocodenobug.billsharing.repository;

import com.nocodenobug.billsharing.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByPhone(String phone);

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);
//    Boolean existsByEmail(String email);
}


