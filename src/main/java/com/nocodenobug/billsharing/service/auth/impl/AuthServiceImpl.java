package com.nocodenobug.billsharing.service.auth.impl;

import com.cloudinary.Cloudinary;
import com.nocodenobug.billsharing.constants.FolderConstants;
import com.nocodenobug.billsharing.exceptions.BadRequestException;
import com.nocodenobug.billsharing.exceptions.NotFoundException;
import com.nocodenobug.billsharing.model.entity.Role;
import com.nocodenobug.billsharing.model.entity.User;
import com.nocodenobug.billsharing.payload.request.LoginRequest;
import com.nocodenobug.billsharing.payload.request.SignupRequest;
import com.nocodenobug.billsharing.payload.response.UserInfoResponse;
import com.nocodenobug.billsharing.repository.RoleRepository;
import com.nocodenobug.billsharing.repository.UserRepository;
import com.nocodenobug.billsharing.security.UserDetailsImpl;
import com.nocodenobug.billsharing.service.auth.AuthService;
import com.nocodenobug.billsharing.payload.request.ChangePasswordRequest;
import com.nocodenobug.billsharing.utils.CurrentUserUtils;
import com.nocodenobug.billsharing.utils.JwtUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public UserInfoResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        CurrentUserUtils.setCurrentUserDetails(authentication);
        UserDetailsImpl userDetails = CurrentUserUtils.getCurrentUserDetails();

        return new UserInfoResponse(userDetails.getId(), userDetails.getUsername(),
                userDetails.getEmail(), userDetails.getAuthorities().toString());
    }

    @Override
    public UserInfoResponse signUp(SignupRequest signupRequest) {
        System.out.println("signup");
        if (userRepository.existsByUsername(signupRequest.getUsername())) {
            throw new BadRequestException("Username đã được sử dụng!");
        }
//        if (Objects.equals(signupRequest.getRole(), "ROLE_ADMIN")) {
//            throw new AccessDeniedException("Không được phép tạo tài khoản admin!");
//        }
        User mapUser = modelMapper.map(signupRequest, User.class);
        mapUser.setPasswordHash(encoder.encode(signupRequest.getPassword()));
        mapUser.setImageUrl(cloudinary.url().generate(FolderConstants.AVATAR_DEFAULT_IMAGE_PUBLIC_ID));
        System.out.println(mapUser);
        User newUser = userRepository.save(mapUser);

        return new UserInfoResponse(newUser.getId(), newUser.getUsername(), newUser.getEmail(), signupRequest.getRole());
    }

    @Override
    public void changeMyPassword(ChangePasswordRequest userChangePassword) {
        UserDetailsImpl userDetails = CurrentUserUtils.getCurrentUserDetails();
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userChangePassword.getCurrentPassword()));
        if (!userChangePassword.getNewPassword().equals(userChangePassword.getNewPasswordConfirm())) {
            throw new BadRequestException("Xác nhận lại mật khẩu mới!");
        }
        User user = userRepository.findById(userDetails.getId()).get();
        user.setPasswordHash(encoder.encode(userChangePassword.getNewPassword()));
        userRepository.save(user);
    }

    @Override
    public ResponseCookie generateJwtCookie() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return jwtUtils.generateJwtCookie(userDetails);
    }

    @Override
    public ResponseCookie cleanJwtCookie() {
        return jwtUtils.getCleanJwtCookie();
    }
}
