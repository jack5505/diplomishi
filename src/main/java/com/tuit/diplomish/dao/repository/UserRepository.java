package com.tuit.diplomish.dao.repository;

import com.tuit.diplomish.dao.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<UserEntity,Long> {
}
