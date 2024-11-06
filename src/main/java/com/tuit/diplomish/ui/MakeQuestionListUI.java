package com.tuit.diplomish.ui;

import com.tuit.diplomish.dao.entity.AnswerToEntity;
import com.tuit.diplomish.dao.entity.QuestionsEntity;
import com.tuit.diplomish.dao.service.AnswerService;
import com.tuit.diplomish.dao.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardRow;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *  In this class we create UI part of question to show up to user
 *  who wants to see create questions for himself
 */
@Service
@RequiredArgsConstructor
public class MakeQuestionListUI {

    private final QuestionService questionService;
    private final AnswerService answerService;

    public InlineKeyboardMarkup makeList(Long userId)
    {
        List<QuestionsEntity> questionsEntities = questionService.listQuestions(userId);
        Long id = questionsEntities.get(0).getId();
        QuestionsEntity questionsEntity = questionsEntities.get(0);
        List<AnswerToEntity> answerToEntities = answerService.listAnswersToQuestion(id);
        InlineKeyboardButton question = new InlineKeyboardButton(questionsEntity.getContent());
        InlineKeyboardRow row = new InlineKeyboardRow();
        row.add(question);
        List<InlineKeyboardRow> rows = new ArrayList<>();
        rows.add(row);
        return new InlineKeyboardMarkup(rows);
    }

}
