package com.tuit.diplomish.command;

import com.tuit.diplomish.command.kernel.TelegramSendMessage;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.generics.TelegramClient;

import java.util.Objects;

@Service("DEFAULT")
public class DefaultAnswer extends TelegramSendMessage {
    private final TelegramClient telegramClient;

    public DefaultAnswer(TelegramClient telegramClient) {
        super(telegramClient);
        this.telegramClient = telegramClient;
    }

    @Override
    public void execute(Update update)
    {
        final User from = update.getMessage().getFrom();
        final String response = String.format(""" 
                Iltimos 🤗 %s faqat shu qomandasini yuriting  /start
                """, Objects.isNull(from.getUserName()) ? from.getFirstName() + " " + from.getLastName() : from.getUserName());
        SendMessage sendMessage = new SendMessage(update.getMessage().getChatId() + "", response);
        responseToMessage(sendMessage);
    }
}
