package com.tuit.diplomish.command;


import com.tuit.diplomish.command.adminquestionadd.AdminState;
import com.tuit.diplomish.command.kernel.TelegramSendMessage;
import com.tuit.diplomish.dao.service.AnswerService;
import com.tuit.diplomish.dao.service.QuestionService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.generics.TelegramClient;

import java.util.*;
import java.util.regex.Pattern;


@Getter
@Setter
@Service("ADMIN_ADD_QUESTIONS")
public class AdminAddQuestion extends TelegramSendMessage {
    private final TelegramClient telegramClient;
    private Map<Long,Long> questionSizeToAddTouser = new HashMap<>();
    private List<String> questions = new ArrayList<>();
    List<String> temp = new ArrayList<>();
    private boolean flagQuestions = false;
    private Integer answerToQuestion = 4;
    private final QuestionService questionService;
    private final AnswerService answerService;
    //TODO later we will think about ading to this questionId from different users
    private Long currentQuestionId;



    @Autowired
    private AdminState awaitQuestionCountState;

    private final SharePhoneRegister start;



    public AdminAddQuestion(TelegramClient telegramClient,
                            QuestionService questionService,
                            AnswerService answerService,
                            SharePhoneRegister start
                            ) {
        super(telegramClient);
        this.telegramClient = telegramClient;
        this.questionService = questionService;
        this.answerService = answerService;
        this.start = start;

    }

    @Override
    public void execute(Update update)
    {
       awaitQuestionCountState.handle(this,update);
    }

    public void changeMenu(Update update){
        start.execute(update);
    }

    public void sendMessage(String chatId,String text){
         responseToMessage(new SendMessage(chatId,text));
    }

    public boolean checkIsDigit(String text){
        return (Pattern.matches("\\d+", text));
    }






}
