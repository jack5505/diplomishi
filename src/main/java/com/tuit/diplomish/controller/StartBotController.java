package com.tuit.diplomish.controller;


import com.tuit.diplomish.command.*;
import com.tuit.diplomish.command.kernel.BotCommand;
import com.tuit.diplomish.command.kernel.MenuOperationServiceFactory;
import com.tuit.diplomish.common.Text;
import com.tuit.diplomish.config.RegisterBot;
import com.tuit.diplomish.ui.ResponseStrategy;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class StartBotController implements LongPollingSingleThreadUpdateConsumer {

    private final String botToken = "8019508424:AAH5MVR8-EcsSyCof8uRUablHGhEuvpprLk";

    private final RegisterBot registerBot;

    private final MenuOperationServiceFactory factory;

    @PostConstruct
    public void init() {
        try {
            registerBot.registerBot(botToken,this);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void consume(Update update)
    {
        if(update.hasMessage() && update.getMessage().hasText())
        {
            log.info("text from userName: {} userId:{}",
                    update.getMessage().getFrom().getUserName(),
                    update.getMessage().getFrom().getId());
            factory.getService(Text.getByText(update.getMessage().getText())
                    .orElseGet(() -> Text.DEFAULT)).execute(update);
        }
    }


}
