package com.nocodenobug.billsharing.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(
        description = "Customer controller",
        name = "Các api về user (dành cho admin)"
)
@RestController
@RequestMapping("/api/v1.0/admin/user")
public class UserController {
    
}
