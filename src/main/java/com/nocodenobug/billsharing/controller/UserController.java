package com.nocodenobug.billsharing.controller;

import com.nocodenobug.billsharing.constants.EmailConstants;
import com.nocodenobug.billsharing.model.dto.UserDto;
import com.nocodenobug.billsharing.model.entity.User;
import com.nocodenobug.billsharing.payload.request.EmailDetails;
import com.nocodenobug.billsharing.payload.request.UserChangeInfoRequest;
import com.nocodenobug.billsharing.payload.response.DefaultPagingResponse;
import com.nocodenobug.billsharing.payload.response.DefaultResponse;
import com.nocodenobug.billsharing.repository.UserRepository;
import com.nocodenobug.billsharing.service.email.SendEmailService;
import com.nocodenobug.billsharing.service.user.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Tag(
        description = "Customer controller",
        name = "Các api về user (dành cho admin)"
)
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private CreateUserService createUserService;

    @Autowired
    private UpdateUserService updateUserService;

    @Autowired
    private DeleteUserService deleteUserService;

    @Autowired
    private GetUserService getUserService;

    @Autowired
    private VerificationUserService verificationUserService;

    @Autowired
    private UploadUserImage uploadUserImage;

    @Autowired
    private SendEmailService sendEmailService;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserRepository userRepository;

    @Operation(summary = "Lấy tất cả User", description =
            "page: trang hiện tại (bắt đầu từ 0), page_size: số record trong trang hiện tại,"
    )
    @GetMapping("/get-all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAll(
            @RequestParam(value = "page") int page,
            @RequestParam(value = "page_size") int pageSize
    ) {
        Page<UserDto> userDtoPage = getUserService.getAllUser(page, pageSize);

        if (userDtoPage == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(DefaultPagingResponse.success(userDtoPage));

    }
    @Operation(summary = "Verification", description =
            "Xác thực tài khoản "
    )
    @GetMapping("/{verificationCode}")
    public ResponseEntity<?> Verification(
            @PathVariable(name = "verificationCode") String verificationCode
    ) {
        UserDto userDto=verificationUserService.getUserByVerification(verificationCode);
        userDto.setStatus(1);
        return ResponseEntity.ok(DefaultResponse.success(createUserService.createUser(userDto)));

    }
    @GetMapping("/forgot-password/{email}")
    public ResponseEntity<?> forgot(@PathVariable (name = "email") String email){
        User user=getUserService.finUserByEmail(email);
        String newPassword=RandomString.make(32);
        user.setPasswordHash(encoder.encode(newPassword));
        EmailDetails emailDetails=new EmailDetails();
        emailDetails.setRecipient(email);
        emailDetails.setSubject(String.valueOf(EmailConstants.SUBJECT));

        emailDetails.setMsgBody("Your password:"+newPassword);
        sendEmailService.sendSimpleMail(emailDetails);

        return   ResponseEntity.ok(DefaultResponse.success(userRepository.save(user)));
    }

    @Operation(summary = "Lấy user theo Id", description = "Lấy user theo Id")
    @GetMapping("/get-user/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('CUSTOMER')")
    public ResponseEntity<?> getAll(
            @PathVariable Long id
    ) {
        UserDto userDto = getUserService.getById(id);
        return ResponseEntity.ok(DefaultResponse.success(userDto));
    }



    @Operation(summary = "Update user", description = "Update user")
    @PutMapping("/update-user/{id}")
    @PreAuthorize("hasRole('CUSTOMER') or hasRole('ADMIN')")
    public ResponseEntity<?> updateUserInfo(@PathVariable Long id, @Validated @RequestBody UserChangeInfoRequest newUser) {

        return ResponseEntity.ok(DefaultResponse.success(updateUserService.updateUser(id, newUser)));
    }

    @Operation(summary = "Xóa customer", description = "Xóa customer")
    @DeleteMapping("/delete-user/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteUser(
            @PathVariable Long id
    ) {
        deleteUserService.deleteUser(id);
        return ResponseEntity.ok(DefaultResponse.success("Xóa user thành công"));
    }

    @Operation(summary = "Tìm kiếm customer theo SDT", description = "Tìm kiếm customer theo SDT")
    @GetMapping("/search-customerByPhone/{phone}")
    public ResponseEntity<?> searchUserByPhone(
            @PathVariable String phone
    ){
        UserDto user=getUserService.findUserBySdt(phone);
        return ResponseEntity.ok(DefaultResponse.success(user));
    }

    @Operation(summary = "Thay ảnh đại diện", description ="Thay ảnh đại diện")
    @PostMapping("/change-avatar/{user-id}")
    public ResponseEntity<?> changeAvatar(
            @PathVariable("user-id") Long userId,
            @RequestBody MultipartFile file
    ){
        return ResponseEntity.ok(DefaultResponse.success("Thay ảnh đại diện thành công", uploadUserImage.uploadAvatar(userId, file)));
    }
}
