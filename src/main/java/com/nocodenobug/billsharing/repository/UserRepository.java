package com.nocodenobug.billsharing.repository;

import com.nocodenobug.billsharing.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    List<User> findAllByRoleAndUsernameContaining(String role, String username);

    List<User> findAllByRoleAndPhone(String role, String phone);

    List<User> findAllByRole(String role);

    Boolean existsByUsername(String username);

    User findUserByVerificationCode(String verificationCode);

    Optional<User> findUserByEmail(String email);

    User getReferenceById(Long id);
}


