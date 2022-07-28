package com.nocodenobug.billsharing.security;

import com.nocodenobug.billsharing.exceptions.ResourceNotFoundException;
import com.nocodenobug.billsharing.model.entity.User;
import com.nocodenobug.billsharing.repository.UserRepository;
import com.nocodenobug.billsharing.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
        return UserDetailsImpl.build(user);
    }

//    @Transactional
//    public UserDetails loadUserById(Long id) {
//        User user = userRepository.findById(id).orElseThrow(
//                () -> new ResourceNotFoundException("User", "id", id)
//        );
//
////        return UserPrincipal.create(user);
//        return new JwtUtils().generateJwtCookie(user);
//    }

}
