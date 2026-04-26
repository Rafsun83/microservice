package com.example.self_management.utils;

import com.example.self_management.model.dto.user.AuthenticatedUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public final class SecurityUtils {

    //Prevent Object creation
    public SecurityUtils(){

    }
    public static AuthenticatedUser getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof AuthenticatedUser user) {
            return user;
        }
        throw new RuntimeException("No authenticated user found");
    }
//    public static Long getLoggedUserId(){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication == null ||  !authentication.isAuthenticated()){
//            return null;
//        }
//        Object principle = authentication.getPrincipal();
//        if (principle instanceof Long){
//            return (Long) principle;
//        }
//
//        // if you use UserDetails or CustomUserDetails
//        // return ((CustomUserDetails) principal).getId();
//        throw new IllegalStateException("User ID not found in Security Context");
//    }
}
