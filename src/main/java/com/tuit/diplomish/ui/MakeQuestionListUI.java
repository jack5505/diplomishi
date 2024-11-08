package com.tuit.diplomish.ui;

import com.tuit.diplomish.command.SharePhoneRegister;
import com.tuit.diplomish.dao.entity.AnswerToEntity;
import com.tuit.diplomish.dao.entity.QuestionsEntity;
import com.tuit.diplomish.dao.service.AnswerService;
import com.tuit.diplomish.dao.service.QuestionService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardRow;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.*;
import java.util.stream.Collectors;

/**
 *  In this class we create UI part of question to show up to user
 *  who wants to see create questions for himself
 */
@Service
@RequiredArgsConstructor
@Getter
public class MakeQuestionListUI {



    @Getter
    @Setter
    @ToString
    public static class AskQuestion{
        String question;
        List<Answer> answers;

        public AskQuestion(String question, List<Answer> answers) {
            this.question = question;
            this.answers = answers;
        }

        public AskQuestion() {
        }
    }
    @Getter
    @Setter
    @ToString
    public static class Answer{
        String answer;
        Boolean correct;
        public Answer(String answer, Boolean correct) {
            this.answer = answer;
            this.correct = correct;
        }

        public Answer() {
        }
    }

}
