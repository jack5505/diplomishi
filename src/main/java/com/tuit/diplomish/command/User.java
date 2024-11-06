package com.tuit.diplomish.command;

import com.tuit.diplomish.command.kernel.TelegramSendMessage;
import com.tuit.diplomish.dao.service.AnswerService;
import com.tuit.diplomish.dao.service.QuestionService;
import com.tuit.diplomish.ui.FirstOptionResponseStrategy;
import com.tuit.diplomish.ui.MakeQuestionListUI;
import com.tuit.diplomish.ui.ResponseStrategy;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.generics.TelegramClient;

@Service("USER")
public class User extends TelegramSendMessage {

    private final TelegramClient telegramClient;
    private final ResponseStrategy responseStrategy;
    private final MakeQuestionListUI makeQuestionListUI;
    // this flag tells us asking question or Asnwer
    private Boolean questionOrAnswer = Boolean.TRUE;


    public User(TelegramClient telegramClient,
                FirstOptionResponseStrategy responseStrategy,
                QuestionService questionService,
                AnswerService answerService,
                MakeQuestionListUI makeQuestionListUI,
                SharePhoneRegister sharePhoneRegister) {
        super(telegramClient);
        this.telegramClient = telegramClient;
        this.responseStrategy = responseStrategy;
        this.makeQuestionListUI = makeQuestionListUI;
    }

    @Override
    public void execute(Update update)
    {
        responseToMessage(makeQuestionListUI.makeList(update.getMessage().getFrom().getId(),
                update.getMessage().getChatId()+""));
    }
}
