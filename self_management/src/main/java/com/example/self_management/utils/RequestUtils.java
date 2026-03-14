package com.example.self_management.utils;

import jakarta.servlet.http.HttpServletRequest;

public class RequestUtils {
    public static String getClientIp(HttpServletRequest request) {
        String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null) {
            return request.getRemoteAddr();
        }
        return xfHeader.split(",")[0];
    }
}
