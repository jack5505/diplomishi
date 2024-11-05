package com.tuit.diplomish.dao.service.impl;

import com.tuit.diplomish.dao.entity.AnswerToEntity;
import com.tuit.diplomish.dao.entity.QuestionsEntity;
import com.tuit.diplomish.dao.repository.AnswerRepository;
import com.tuit.diplomish.dao.service.AnswerService;
import com.tuit.diplomish.dao.service.QuestionService;
import com.tuit.diplomish.exceptions.BadRequestAlertExceptions;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnswerServiceImpl implements AnswerService {
    private final AnswerRepository answerRepository;
    private final QuestionService questionService;
    @Override
    public AnswerToEntity save(AnswerToEntity entity) {
        return null;
    }

    @Override
    public void delete(AnswerToEntity entity) {

    }

    @Override
    public Optional<AnswerToEntity> findById(Object id) {
        return Optional.empty();
    }

    @Override
    public void deleteById(Object id) {

    }



    @Override
    public AnswerToEntity addAnswer(String text, Long questionId, Boolean right) {
        QuestionsEntity questionsEntity = questionService.findById(questionId).orElseThrow(BadRequestAlertExceptions::dataNotFound);
        return answerRepository.save(AnswerToEntity.builder()
                .questionsEntity(questionsEntity)
                .answer(text)
                .correctAnswer(right)
                .build());
    }

}
