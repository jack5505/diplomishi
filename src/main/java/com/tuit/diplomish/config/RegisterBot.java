package com.tuit.diplomish.config;


import com.tuit.diplomish.controller.StartBotController;
import jakarta.annotation.PostConstruct;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.longpolling.BotSession;
import org.telegram.telegrambots.longpolling.TelegramBotsLongPollingApplication;
import org.telegram.telegrambots.longpolling.interfaces.LongPollingUpdateConsumer;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;


@Configuration
@Validated
public class RegisterBot extends TelegramBotsLongPollingApplication {


    @NotNull
    @Value("${bot.token}")
    private String token;

    @NotNull
    @Value("${bot.name}")
    private String botName;

    @Bean
    public TelegramClient telegramClient(){
        return new OkHttpTelegramClient(token);
    }


    @Override
    public BotSession registerBot(String botToken, LongPollingUpdateConsumer updatesConsumer) throws TelegramApiException {
        return super.registerBot(botToken, updatesConsumer);
    }


}
