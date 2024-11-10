package com.tuit.diplomish.dao.service;

import com.tuit.diplomish.dao.entity.QuestionsEntity;
import com.tuit.diplomish.dao.service.kernel.CrudService;

import java.util.List;

public interface QuestionService extends CrudService<QuestionsEntity> {
    QuestionsEntity addQuestion(String question,Long UserEntityId);

    List<QuestionsEntity> listQuestions(Long userId);
}
