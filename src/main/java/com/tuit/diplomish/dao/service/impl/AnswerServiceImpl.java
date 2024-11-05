package com.tuit.diplomish.dao.service.impl;

import com.tuit.diplomish.dao.entity.AnswerToEntity;
import com.tuit.diplomish.dao.repository.AnswerRepository;
import com.tuit.diplomish.dao.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnswerServiceImpl implements AnswerService {
    private final AnswerRepository answerRepository;
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
}
