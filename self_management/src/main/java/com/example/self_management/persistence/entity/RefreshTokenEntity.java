package com.example.self_management.persistence.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "refresh_tokens")
public class RefreshTokenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String token;

    private LocalDateTime expiryDate;

    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName = "id")
    private UserEntity userEntity;

    //Modified column for device tracking
    private String deviceId;
    @Column(length = 1000)
    private String userAgent;
    private String ipAddress;
    private boolean isActive;
    private String deviceName;
    private LocalDateTime createdAt;


    public RefreshTokenEntity() {}

    public RefreshTokenEntity(String token, UserEntity userEntity, LocalDateTime expiryDate, boolean isActive, String deviceId, String userAgent, String ipAddress, LocalDateTime createdAt, String deviceName) {
        this.token = token;
        this.userEntity = userEntity;
        this.expiryDate = expiryDate;

        //Modified column for device tracking
        this.isActive = isActive;
        this.deviceId = deviceId;
        this.userAgent = userAgent;
        this.ipAddress = ipAddress;
        this.createdAt = createdAt;
        this.deviceName = deviceName;

    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getRefreshToken() {
        return token;
    }
    public void setRefreshToken(String refreshToken) {
        this.token = refreshToken;
    }
    public UserEntity getUserEntity() {
        return userEntity;
    }
    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
    public LocalDateTime getExpiresAt() {
        return expiryDate;
    }
    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiryDate = expiresAt;
    }

    //Modified column for device tracking
    public String getDeviceId() {
        return deviceId;
    }
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
    public String getUserAgent() {
        return userAgent;
    }
    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }
    public String getIpAddress() {
        return ipAddress;
    }
    public void setIpAddress(String idAddress) {
        this.ipAddress = idAddress;
    }
    public boolean getIsActive() {
        return isActive;
    }
    public void setIsActive(boolean active) {
        isActive = active;
    }
    public String getDeviceName() {
        return deviceName;
    }
    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

}
