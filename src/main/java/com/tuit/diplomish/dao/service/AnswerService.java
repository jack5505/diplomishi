package com.tuit.diplomish.dao.service;

import com.tuit.diplomish.dao.entity.AnswerToEntity;
import com.tuit.diplomish.dao.service.kernel.CrudService;

public interface AnswerService extends CrudService<AnswerToEntity> {
    AnswerToEntity addAnswer(String text,Long questionId,Boolean right);
}
