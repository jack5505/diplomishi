package com.tuit.diplomish.command;

import com.tuit.diplomish.command.kernel.TelegramSendMessage;
import com.tuit.diplomish.ui.ResponseStrategy;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.generics.TelegramClient;

@Service
public class Start extends TelegramSendMessage {
    private final TelegramClient telegramClient;
    private final ResponseStrategy<ReplyKeyboardMarkup> responseStrategy;

    public Start(TelegramClient telegramClient, ResponseStrategy<ReplyKeyboardMarkup> responseStrategy) {
        super(telegramClient);
        this.telegramClient = telegramClient;
        this.responseStrategy = responseStrategy;
    }

    @Override
    public void execute(Update update) {
        SendMessage sendMessage = new SendMessage(update.getMessage().getChatId() + "", update.getMessage().getText());
        sendMessage.setReplyMarkup(responseStrategy.makeResponse());
        responseToMessage(sendMessage);
    }
}
