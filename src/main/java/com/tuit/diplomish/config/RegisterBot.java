package com.tuit.diplomish.config;


import com.tuit.diplomish.controller.StartBotController;
import jakarta.annotation.PostConstruct;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;
import org.telegram.telegrambots.longpolling.TelegramBotsLongPollingApplication;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


@Configuration
@Validated
public class RegisterBot {

    @NotNull
    @Value("${bot.token}")
    private String token;

    @NotNull
    @Value("${bot.name}")
    private String botName;

    @PostConstruct
    public void initMethod() {
        try {
            var telegramBotsLongPollingApplication = new TelegramBotsLongPollingApplication();
            telegramBotsLongPollingApplication.registerBot(token,new StartBotController());
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }






}
