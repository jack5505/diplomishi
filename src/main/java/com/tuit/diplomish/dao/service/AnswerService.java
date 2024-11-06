package com.tuit.diplomish.dao.service;

import com.tuit.diplomish.dao.entity.AnswerToEntity;
import com.tuit.diplomish.dao.service.kernel.CrudService;

import java.util.List;

public interface AnswerService extends CrudService<AnswerToEntity> {

    AnswerToEntity addAnswer(String text,Long questionId,Boolean right);

    List<AnswerToEntity> listAnswersToQuestion(Long questionId);
}
