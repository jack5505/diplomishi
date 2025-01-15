package com.tuit.diplomish.dao.service.impl;

import com.tuit.diplomish.dao.entity.QuestionsEntity;
import com.tuit.diplomish.dao.entity.UserEntity;
import com.tuit.diplomish.dao.repository.QuestionRepository;
import com.tuit.diplomish.dao.service.QuestionService;
import com.tuit.diplomish.dao.service.UserService;
import com.tuit.diplomish.exceptions.BadRequestAlertExceptions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final UserService userService;
    private final Integer totalSizeQuestion = 20;

    @Override
    public QuestionsEntity save(QuestionsEntity entity) {
        return null;
    }

    @Override
    public void delete(QuestionsEntity entity) {

    }

    @Override
    public Optional<QuestionsEntity> findById(Object id) {
        return questionRepository.findById((Long) id);
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

    @Override
    public List<QuestionsEntity> listQuestions(Long userId) {
        UserEntity byUserId = userService.findByUserId(userId)
                .orElseThrow(BadRequestAlertExceptions::dataNotFound);
        UserEntity userEntity = userService.findByUserIdAndId(byUserId.getUserId(), byUserId.getId())
                .orElseThrow(BadRequestAlertExceptions::dataNotFound);
        return new ArrayList<>(userEntity.getQuestionsEntity());
    }

    @Override
    public List<QuestionsEntity> listQuestionRandom() {
        List<QuestionsEntity> all = questionRepository.findAll();
        List<QuestionsEntity> readyQuestion = new ArrayList<>();
        int temp = totalSizeQuestion;
        for(int i = random(all.size()); i < all.size() && temp >= 1 ; i ++,temp --)
        {
            if(i == all.size() - 1){
                readyQuestion.add(all.get(i));
                i = 0;
                continue;
            }
            readyQuestion.add(all.get(i));
        }
        return readyQuestion;
    }

    private   Integer random(int size){
        return new Random().nextInt(50);
    }


}
