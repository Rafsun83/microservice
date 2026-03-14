package com.example.self_management.service;

import com.example.self_management.mapper.UserMapper;
import com.example.self_management.model.domain.User;
import com.example.self_management.model.dto.user.CreateUserRequest;
import com.example.self_management.persistence.entity.UserEntity;
import com.example.self_management.persistence.repository.UserRepository;
import com.example.self_management.utils.SecurityUtil;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<User> getAllUsers(Pageable pageable){
        List<UserEntity> userEntityList = userRepository.findAll(pageable).getContent();
        return userEntityList.stream().map(userMapper::entityToDomain).toList();
    }

    public User getUserDetails(){
        Long userId = SecurityUtil.getLoggedUserId();
        assert userId != null;
        UserEntity userDetails = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        return userMapper.entityToDomain(userDetails);
    }

    public Long createUser(CreateUserRequest createUserRequest){
        var entityToSave = userMapper.createUserRequestToEntity(createUserRequest);
        var saveUser = userRepository.save(entityToSave);
        return saveUser.getId();
    }
}
