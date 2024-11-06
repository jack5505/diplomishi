package com.tuit.diplomish.dao.service.impl;

import com.tuit.diplomish.dao.entity.UserEntity;
import com.tuit.diplomish.dao.repository.UserRepository;
import com.tuit.diplomish.dao.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserEntity save(UserEntity entity) {
        return userRepository.save(entity);
    }

    @Override
    public void delete(UserEntity entity) {

    }

    @Override
    public Optional<UserEntity> findById(Object id) {
        return Optional.empty();
    }

    @Override
    public void deleteById(Object id) {

    }

    @Override
    public UserEntity createUser(String username, 
                                 String phone, 
                                 Long userId, 
                                 String firstName, 
                                 String lastName) 
    {
        return userRepository.save(UserEntity.builder()
                .userId(userId)
                .username(username)
                .firstName(firstName)
                .lastName(lastName)
                .phoneNumber(phone).build());

    }

    @Override
    public Optional<UserEntity> findByUserId(Long userId) {
        return userRepository.findByUserId(userId);
    }

    @Override
    public Optional<UserEntity> findByUserIdAndId(Long userId, Long id) {
        return userRepository.findByUserIdAndId(userId,id);
    }
}
