package com.tuit.diplomish.dao.repository;

import com.tuit.diplomish.dao.entity.AnswerToEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<AnswerToEntity,Long> {

}
