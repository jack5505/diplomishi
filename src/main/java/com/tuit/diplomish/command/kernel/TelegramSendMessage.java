package com.tuit.diplomish.command.kernel;

import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

@RequiredArgsConstructor
public abstract class TelegramSendMessage implements BotCommand {

    private final TelegramClient telegramClient;

    protected void responseToMessage(SendMessage sendMessage){
        try {
            telegramClient.execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
