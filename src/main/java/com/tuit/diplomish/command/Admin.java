package com.tuit.diplomish.command;

import com.tuit.diplomish.command.kernel.TelegramSendMessage;
import com.tuit.diplomish.ui.FirstOptionResponseStrategy;
import com.tuit.diplomish.ui.ResponseStrategy;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.generics.TelegramClient;

@Service("ADMIN")
public class Admin extends TelegramSendMessage {
    private final TelegramClient telegramClient;
    private final ResponseStrategy<ReplyKeyboardMarkup> responseStrategy;

    public Admin(TelegramClient telegramClient, ResponseStrategy<ReplyKeyboardMarkup> responseStrategy) {
        super(telegramClient);
        this.responseStrategy = responseStrategy;
        this.telegramClient = telegramClient;
    }

    @Override
    public void execute(Update update) {
        SendMessage sendMessage = new SendMessage(update.getMessage().getChatId() + "","Admin menyusi...");
        sendMessage.setReplyMarkup(responseStrategy.adminMenuAddQuestions());
        responseToMessage(sendMessage);
    }
}
