package com.tuit.diplomish.command;

import com.tuit.diplomish.command.kernel.BotCommand;
import com.tuit.diplomish.command.kernel.TelegramSendMessage;
import com.tuit.diplomish.command.useranswers.UserAnswerState;
import com.tuit.diplomish.command.useranswers.UserQuestionListState;
import com.tuit.diplomish.dao.service.AnswerService;
import com.tuit.diplomish.dao.service.QuestionService;
import com.tuit.diplomish.ui.FirstOptionResponseStrategy;
import com.tuit.diplomish.ui.MakeQuestionListUI;
import com.tuit.diplomish.ui.ResponseStrategy;
import lombok.Getter;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.generics.TelegramClient;

import java.util.*;

@Service("USER")
@Getter
public class User extends TelegramSendMessage {

    private final TelegramClient telegramClient;
    private final ResponseStrategy responseStrategy;
    private final MakeQuestionListUI makeQuestionListUI;
    // this flag tells us asking question or Asnwer
    private Boolean questionOrAnswer = Boolean.TRUE;
    // MAIN
    // bu user haqida malumot beradi test topshirib o`tiribidi yoki yo`q
    private Map<Long,Boolean> currentProcessUsers = new HashMap<>();
    // bu user topshirmoqchi bo`lgan savolarni olib yuriydi
    private Map<Long,List<MakeQuestionListUI.AskQuestion>> questionMap = new LinkedHashMap<>();
    // MAIN
    private final BotCommand start;
    private final QuestionService questionService;
    private final AnswerService answerService;

    private Map<Long,Integer> currentUserQuestion = new HashMap<>();
    private UserAnswerState currentState;

    public User(TelegramClient telegramClient,
                FirstOptionResponseStrategy responseStrategy,
                QuestionService questionService,
                AnswerService answerService,
                MakeQuestionListUI makeQuestionListUI,
                UserQuestionListState userQuestionListState,
                Start sharePhoneRegister) {
        super(telegramClient);
        this.telegramClient = telegramClient;
        this.responseStrategy = responseStrategy;
        this.makeQuestionListUI = makeQuestionListUI;
        this.start = sharePhoneRegister;
        this.questionService = questionService;
        this.answerService = answerService;
        this.currentState = userQuestionListState;
    }

    @Override
    public void execute(Update update)
    {
        this.currentState.handle(this,update);
    }


    @Override
    public void sendCustomMessageToIntendMenu(Update update) {
        SendMessage sendMessage = new SendMessage(update.getMessage().getChatId()+"","ILTIMOS SAVOLARGA JAVOB BERIB BO`LING!");
        responseToMessage(sendMessage);
    }

    public void sendMessage(String chatId,String text){
        responseToMessage(new SendMessage(chatId,text));
    }
    public void sendMessage(SendMessage sendMessage){
        responseToMessage(sendMessage);
    }

    public void changeState(UserAnswerState userAnswerState){
        this.currentState = userAnswerState;
    }

    public void changeToMenu(Update update){
        start.execute(update);
    }



}
