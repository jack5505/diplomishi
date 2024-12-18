package com.tuit.diplomish.dao.service;

import com.tuit.diplomish.dao.entity.UserEntity;
import com.tuit.diplomish.dao.service.kernel.CrudService;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserService extends CrudService<UserEntity> {

    UserEntity createUser(String username, String phone,Long userId,String firstName,String lastName);

    Optional<UserEntity> findByUserId(Long userId);


    Optional<UserEntity> findByUserIdAndId(Long userId, Long id);

}
