package com.nocodenobug.billsharing.controller;

import com.nocodenobug.billsharing.model.dto.UserDto;
import com.nocodenobug.billsharing.payload.request.UserChangeInfoRequest;
import com.nocodenobug.billsharing.payload.response.DefaultPagingResponse;
import com.nocodenobug.billsharing.payload.response.DefaultResponse;
import com.nocodenobug.billsharing.payload.response.SampleResponse;
import com.nocodenobug.billsharing.service.user.CreateUserService;
import com.nocodenobug.billsharing.service.user.DeleteUserService;
import com.nocodenobug.billsharing.service.user.GetUserService;
import com.nocodenobug.billsharing.service.user.UpdateUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
        return ResponseEntity.ok(SampleResponse.builder()
                .success(true)
                .message("delete customer success")
                .data("")
                .build()
        );
    }
    @Operation(summary = "Tìm kiếm customer theo SDT", description = "Tìm kiếm customer theo SDT")
    @GetMapping("/search-customerByPhone/{phone}")
    public ResponseEntity<?> searchUserByPhone(
            @PathVariable String phone
    ){
        UserDto user=getUserService.findUserBySdt(phone);
        return ResponseEntity.ok(DefaultResponse.success(user));
    }


}
