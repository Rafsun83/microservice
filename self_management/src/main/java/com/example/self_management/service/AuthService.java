package com.example.self_management.service;

import com.example.self_management.exception.UserAlreadyExistsException;
import com.example.self_management.model.domain.User;
import com.example.self_management.model.dto.auth.JwtResponse;
import com.example.self_management.model.dto.auth.LoginRequest;
import com.example.self_management.persistence.entity.RefreshTokenEntity;
import com.example.self_management.persistence.entity.UserEntity;
import com.example.self_management.persistence.repository.RefreshTokenRepository;
import com.example.self_management.persistence.repository.UserRepository;
import com.example.self_management.utils.RequestUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    public void register(User requestUser) {
        UserEntity user = new UserEntity();
        boolean exist = userRepository.findByUsername(requestUser.getUsername()).isPresent();
        if (exist) {
            throw new UserAlreadyExistsException("Username '" + requestUser.getUsername() + "' already exists!");
        }
        user.setName(requestUser.getName());
        user.setEmail(requestUser.getEmail());
        user.setCreatedAt(requestUser.getCreatedAt());
        user.setLocation(requestUser.getLocation());
        user.setUsername(requestUser.getUsername());
        user.setPassword(passwordEncoder.encode(requestUser.getPassword()));
        userRepository.save(user);
    }

    public JwtResponse login(LoginRequest loginRequest,  HttpServletRequest httpRequest) {

        UserEntity user = userRepository.findByUsername(loginRequest.getUsername()).orElseThrow(() -> new RuntimeException("User not found"));
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        // 4️⃣ Capture device info
        String userAgent = httpRequest.getHeader("User-Agent");
        String ipAddress = RequestUtils.getClientIp(httpRequest);

        // 3️⃣ Generate tokens
        String accessToken = jwtService.generateAccessToken(user.getId());
        RefreshTokenEntity refreshTokenEntity = refreshTokenService.createRefreshToken(user, userAgent, ipAddress, loginRequest.getDeviceId());
        return new JwtResponse(accessToken, refreshTokenEntity.getRefreshToken());
    }

    public JwtResponse refreshToken(String token) {
        RefreshTokenEntity refreshToken = refreshTokenService.verifyRefreshToken(token);
        String accessToken = jwtService.generateAccessToken(refreshToken.getUserEntity().getId());
        return new JwtResponse(accessToken, token);
    }

    public void logout(String refreshToken) {
        refreshTokenService.logoutUser(refreshToken);
    }

    public void logoutAll(String refreshToken) {
        refreshTokenService.logoutAllDevice(refreshToken);
    }


}
