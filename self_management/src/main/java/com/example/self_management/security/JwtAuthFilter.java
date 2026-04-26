package com.example.self_management.security;

import com.example.self_management.model.dto.user.AuthenticatedUser;
import com.example.self_management.service.JwtService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    //I can use @Autowired instead of this above private final JwtService jwtService;
    public JwtAuthFilter(JwtService jwtService){
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);
        //String userId = jwtService.extractUserId(token);
        //Long userId = Long.parseLong(jwtService.extractUserId(token));
        Claims claims = jwtService.extractAllClaims(token);
        Long userId = claims.get("userId", Long.class);
        String email = claims.get("email", String.class);
        String userName = claims.get("username", String.class);
        String name = claims.get("name", String.class);


        if (userId != null) {
            AuthenticatedUser authenticatedUser = new AuthenticatedUser(userId,email, userName, name);

            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                            authenticatedUser,
                            null,
                            Collections.emptyList()
                    );
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }
}
