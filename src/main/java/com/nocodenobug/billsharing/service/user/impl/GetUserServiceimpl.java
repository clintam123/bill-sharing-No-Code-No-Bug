package com.nocodenobug.billsharing.service.user.impl;

import com.nocodenobug.billsharing.exceptions.NotFoundException;
import com.nocodenobug.billsharing.model.dto.UserDto;
import com.nocodenobug.billsharing.model.entity.User;
import com.nocodenobug.billsharing.payload.response.UserInfoResponse;
import com.nocodenobug.billsharing.repository.UserRepository;
import com.nocodenobug.billsharing.repository.VendorRepository;
import com.nocodenobug.billsharing.service.user.GetUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GetUserServiceimpl implements GetUserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VendorRepository vendorRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public Page<UserDto> getAllUser(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<User> customerPage = userRepository.findAll(pageable);
        return customerPage.map(user -> mapper.map(user, UserDto.class));

    }

    @Override
    public UserDto getById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return mapper.map(user.get(), UserDto.class);
        }
        throw new NotFoundException(HttpStatus.BAD_REQUEST.value(), "Id user NotFound");
    }

    @Override
    public List<UserInfoResponse> getByRoleAndUsernameOrPhone(String role, String username, String phone) {
        System.out.println(role + " " + username + " " + phone);
        List<User> users = null;
        if (username == null && phone == null) {
            users = userRepository.findAllByRole(role);
        } else if (username == null) {
            users = userRepository.findAllByRoleAndPhone(role, phone);
        } else {
            users = userRepository.findAllByRoleAndUsernameContaining(role, username);
        }

        if (users.isEmpty()) {
            throw new NotFoundException(404, "user not found");
        }
        List<UserInfoResponse> userInfoResponses = new ArrayList<>();
        for (User user : users) {
            UserInfoResponse response = mapper.map(user, UserInfoResponse.class);
            response.setFullName(user.getLastName() + " " + user.getFirstName());
            if (user.getRole() == "ROLE_VENDOR") {
                response.setVendorId(vendorRepository.findByUserId(user.getId()).getId());
            }
            userInfoResponses.add(response);
        }
        return userInfoResponses;
    }

    @Override
    public User findUserByEmail(String email) {
        Optional<User> user = userRepository.findUserByEmail(email);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new NotFoundException(HttpStatus.NOT_FOUND.value(), "Email user NotFound");
        }
    }

}
