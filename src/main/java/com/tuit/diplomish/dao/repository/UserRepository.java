package com.tuit.diplomish.dao.repository;

import com.tuit.diplomish.dao.entity.UserEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository  extends JpaRepository<UserEntity,Long> {

    Optional<UserEntity> findByUserId(Long userId);

    @EntityGraph(attributePaths = "questionsEntity")
    Optional<UserEntity> findByUserIdAndId(Long userId, Long id);
}
