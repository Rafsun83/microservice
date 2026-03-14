package com.example.self_management.mapper;


import com.example.self_management.model.domain.User;
import com.example.self_management.model.dto.task.CreateTaskRequest;
import com.example.self_management.model.dto.user.CreateUserRequest;
import com.example.self_management.persistence.entity.TaskEntity;
import com.example.self_management.persistence.entity.UserEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class UserMapper {

    public User entityToDomain(UserEntity userEntity){
        User user = new User();
        BeanUtils.copyProperties(userEntity, user);
        return user;
    }

    public UserEntity createUserRequestToEntity(CreateUserRequest createUserRequest){
        UserEntity userEntity = new UserEntity();
        userEntity.setCreatedAt(new Date());
        BeanUtils.copyProperties(createUserRequest, userEntity);
        return userEntity;
    }
}
