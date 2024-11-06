package com.tuit.diplomish.command;

import com.tuit.diplomish.command.kernel.TelegramSendMessage;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.generics.TelegramClient;

@Service("USER")
public class User extends TelegramSendMessage {

    private final TelegramClient telegramClient;

    public User(TelegramClient telegramClient) {
        super(telegramClient);
        this.telegramClient = telegramClient;
    }

    @Override
    public void execute(Update update) {

    }
}
