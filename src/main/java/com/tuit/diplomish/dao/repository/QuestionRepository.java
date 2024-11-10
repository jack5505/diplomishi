package com.tuit.diplomish.dao.repository;

import com.tuit.diplomish.dao.entity.QuestionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<QuestionsEntity,Long> {
}
