package com.tuit.diplomish.command;

import com.tuit.diplomish.command.kernel.TelegramSendMessage;
import com.tuit.diplomish.dao.service.UserService;
import com.tuit.diplomish.ui.ResponseStrategy;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.generics.TelegramClient;

@Service("REGISTER")
public class Register  extends TelegramSendMessage {

    private final ResponseStrategy<ReplyKeyboardMarkup> responseStrategy;
    private final TelegramClient telegramClient;


    public Register(ResponseStrategy<ReplyKeyboardMarkup> responseStrategy,
                    TelegramClient telegramClient)
    {
        super(telegramClient);
        this.responseStrategy = responseStrategy;
        this.telegramClient = telegramClient;
    }


    @Override
    public void execute(Update update) {
        SendMessage sendMessage = new SendMessage(update.getMessage().getChatId()+ "","Iltimos registratsiyadan o`ting? ");
        sendMessage.setReplyMarkup(responseStrategy.sharePhoneNumberToRegister());
        responseToMessage(sendMessage);
    }
}
