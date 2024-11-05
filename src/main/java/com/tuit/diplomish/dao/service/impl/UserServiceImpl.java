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
        return null;
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
}
