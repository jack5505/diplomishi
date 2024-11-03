package com.tuit.diplomish.controller;


import com.tuit.diplomish.config.RegisterBot;
import jakarta.annotation.PostConstruct;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

import java.util.EnumMap;
import java.util.Objects;
import java.util.function.Supplier;

@Slf4j
@RequiredArgsConstructor
@Service
public class StartBotController implements LongPollingSingleThreadUpdateConsumer {

    private final String botToken = "8019508424:AAH5MVR8-EcsSyCof8uRUablHGhEuvpprLk";

    private final TelegramClient telegramClient;

    private final RegisterBot registerBot;

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

            responseToMessage(update);
        }
    }

    private void responseToMessage(Update update)
    {
        SendMessage sendMessage = new SendMessage(update.getMessage().getChatId() + "", update.getMessage().getText());
        try{
            telegramClient.execute(sendMessage);
        }catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
