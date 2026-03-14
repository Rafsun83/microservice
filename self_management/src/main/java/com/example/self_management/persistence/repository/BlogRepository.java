package com.example.self_management.persistence.repository;


import com.example.self_management.model.domain.Blog;
import com.example.self_management.persistence.entity.BlogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends JpaRepository<BlogEntity,Long> {
}
