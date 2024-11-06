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
    private final QuestionService questionService;
    private final AnswerService answerService;

    public User(TelegramClient telegramClient,
                MakeQuestionListUI makeQuestionListUI,
                FirstOptionResponseStrategy responseStrategy,
                QuestionService questionService,
                AnswerService answerService) {
        super(telegramClient);
        this.telegramClient = telegramClient;
        this.responseStrategy = responseStrategy;
        this.questionService = questionService;
        this.answerService =  answerService;
    }

    @Override
    public void execute(Update update)
    {

        SendMessage sendMessage = new SendMessage(update.getMessage().getChatId() + "","Savollar");
//        sendMessage.setReplyMarkup(this.makeQuestionListUI
//                .makeList(update.getMessage().getFrom().getId()));
        responseToMessage(sendMessage);
    }
}
