package com.tuit.diplomish.dao.repository;

import com.tuit.diplomish.dao.entity.AnswerToEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<AnswerToEntity,Long> {

    @EntityGraph(attributePaths = "questionsEntity")
    List<AnswerToEntity> findByQuestionsEntity_Id(Long id);
}
