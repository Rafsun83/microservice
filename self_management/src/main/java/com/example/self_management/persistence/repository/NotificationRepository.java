package com.example.self_management.persistence.repository;

import com.example.self_management.persistence.entity.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {
//    List<NotificationEntity> findByUserIdOrderByCreatedAt(Long userId);
//    long countByUserIdAndIsReadFalse(Long userId);

    List<NotificationEntity> findByUserIdOrderByCreatedAtDesc(Long userId);
    List<NotificationEntity> findByUserIdAndIsReadFalse(Long userId);
}
