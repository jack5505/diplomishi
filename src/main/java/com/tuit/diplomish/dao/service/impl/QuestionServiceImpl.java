package com.tuit.diplomish.dao.service.impl;

import com.tuit.diplomish.dao.entity.QuestionsEntity;
import com.tuit.diplomish.dao.entity.UserEntity;
import com.tuit.diplomish.dao.repository.QuestionRepository;
import com.tuit.diplomish.dao.service.QuestionService;
import com.tuit.diplomish.dao.service.UserService;
import com.tuit.diplomish.exceptions.BadRequestAlertExceptions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final UserService userService;

    @Override
    public QuestionsEntity save(QuestionsEntity entity) {
        return null;
    }

    @Override
    public void delete(QuestionsEntity entity) {

    }

    @Override
    public Optional<QuestionsEntity> findById(Object id) {
        return Optional.empty();
    }

    @Override
    public void deleteById(Object id) {

    }

    @Override
    public QuestionsEntity addQuestion(String question, Long userEntityId)
    {
        UserEntity userEntity = userService.findByUserId(userEntityId)
                .orElseThrow(BadRequestAlertExceptions::dataNotFound);

        QuestionsEntity entity = new QuestionsEntity();
        entity.setContent(question);
        entity.setUser(userEntity);
        return questionRepository.save(entity);
    }
}
