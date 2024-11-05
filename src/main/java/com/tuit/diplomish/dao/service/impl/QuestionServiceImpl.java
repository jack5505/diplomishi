package com.tuit.diplomish.dao.service.impl;

import com.tuit.diplomish.dao.entity.QuestionsEntity;
import com.tuit.diplomish.dao.repository.QuestionRepository;
import com.tuit.diplomish.dao.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

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
}
