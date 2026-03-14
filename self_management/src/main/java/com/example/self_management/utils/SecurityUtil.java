package com.example.self_management.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public final class SecurityUtil {

    //Prevent Object creation
    public SecurityUtil(){

    }

    public static Long getLoggedUserId(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null ||  !authentication.isAuthenticated()){
            return null;
        }
        Object principle = authentication.getPrincipal();
        if (principle instanceof Long){
            return (Long) principle;
        }

        // if you use UserDetails or CustomUserDetails
        // return ((CustomUserDetails) principal).getId();
        throw new IllegalStateException("User ID not found in Security Context");
    }
}
