package com.nocodenobug.billsharing.utils;

import com.nocodenobug.billsharing.security.UserDetailsImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class CurrentUserUtils {
    public static UserDetailsImpl getCurrentUserDetails() {
        return (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public static void setCurrentUserDetails(Authentication authentication) {
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

//    public static boolean isAdmin() {
//        UserDetailsImpl userDetails = getCurrentUserDetails();
//        return userDetails.getAuthorities().stream().findFirst().get().getAuthority().equals("ADMIN");
//    }
}
