package com.tuit.diplomish.dao.service;

import com.tuit.diplomish.dao.entity.UserEntity;
import com.tuit.diplomish.dao.service.kernel.CrudService;

import java.util.Optional;

public interface UserService extends CrudService<UserEntity> {

    UserEntity createUser(String username, String phone,Long userId,String firstName,String lastName);

    Optional<UserEntity> findByUserId(Long userId);


}
