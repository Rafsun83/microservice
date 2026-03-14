package com.example.self_management.persistence.repository;

import com.example.self_management.persistence.entity.RefreshTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshTokenEntity, Long> {
    Optional<RefreshTokenEntity> findByToken(String token);
//    void deleteByUserEntity(UserEntity userEntity);
//    List<RefreshTokenEntity> findByDeviceIdAndIsActiveTrue(String deviceId);
    int countByDeviceIdAndIsActiveTrue(String deviceId);

    //Same things of findByDeviceIdAndIsActiveTrue
    @Modifying
    @Query("UPDATE RefreshTokenEntity r SET r.isActive = false WHERE r.userEntity.id = :userId AND r.isActive = true")
    void deactivateAllActiveTokens(@Param("userId") Long userId);

}
