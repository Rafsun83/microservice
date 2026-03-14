package com.example.self_management.service;

import com.example.self_management.persistence.entity.RefreshTokenEntity;
import com.example.self_management.persistence.entity.UserEntity;
import com.example.self_management.persistence.repository.RefreshTokenRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class RefreshTokenService {

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    public RefreshTokenEntity createRefreshToken(UserEntity userEntity, String userAgent, String ipAddress, String deviceId) {

        //2️⃣ Device limit (max 3)
        int activeSessions =
                refreshTokenRepository.countByDeviceIdAndIsActiveTrue(deviceId);
        if (activeSessions >= 3) {
            throw new RuntimeException("Maximum device limit reached");
        }

        RefreshTokenEntity refreshToken = new RefreshTokenEntity();
        refreshToken.setUserEntity(userEntity);
        refreshToken.setRefreshToken(UUID.randomUUID().toString());
        refreshToken.setExpiresAt(LocalDateTime.now().plusDays(7));

        refreshToken.setUserAgent(userAgent);
        refreshToken.setIpAddress(ipAddress);
        refreshToken.setIsActive(true);
        refreshToken.setCreatedAt(LocalDateTime.now());
        refreshToken.setDeviceId(deviceId);
        return refreshTokenRepository.save(refreshToken);
    }

    public RefreshTokenEntity verifyRefreshToken(String token) {
        RefreshTokenEntity refreshToken = refreshTokenRepository.findByToken(token).orElseThrow(() -> new RuntimeException("Invalid refresh token"));
        if(refreshToken.getExpiresAt().isBefore(LocalDateTime.now())){
            refreshTokenRepository.delete(refreshToken);
            throw new RuntimeException("Expired refresh token");
        }
        return refreshToken;
    }

    //During delete JPA says I can't delete without transaction so, that's why used (@Transactional) Annotation.
    @Transactional
    public void logoutUser(String token) {
        RefreshTokenEntity session = refreshTokenRepository
                .findByToken(token)
                .orElseThrow();

        session.setIsActive(false);
        refreshTokenRepository.save(session);
    }

    @Transactional
    public void logoutAllDevice(String token){
        RefreshTokenEntity user = refreshTokenRepository
                .findByToken(token)
                .orElseThrow();
//        var sessions =
//                refreshTokenRepository.findByDeviceIdAndIsActiveTrue(user.getDeviceId());
//        for (RefreshTokenEntity session : sessions) {
//            session.setIsActive(false);
//        }
        refreshTokenRepository.deactivateAllActiveTokens(user.getUserEntity().getId());
//        refreshTokenRepository.saveAll(sessions);
    }
}
