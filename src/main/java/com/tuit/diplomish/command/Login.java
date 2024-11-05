package com.tuit.diplomish.command;

import com.tuit.diplomish.command.kernel.TelegramSendMessage;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.generics.TelegramClient;

@Service("LOGIN")
public class Login extends TelegramSendMessage {

    private final TelegramClient telegramClient;

    public Login(TelegramClient telegramClient) {
        super(telegramClient);
        this.telegramClient = telegramClient;
    }

    @Override
    public void execute(Update update) {
        StringBuilder makeResponse = new StringBuilder();
        makeResponse.append(update.getMessage().getFrom().getFirstName() + " ");
        makeResponse.append(update.getMessage().getFrom().getLastName() + " ");
        makeResponse.append(update.getMessage().getFrom().getUserName() + " \n");
        makeResponse.append("telegramdaki nastroyka qiligan tili " + update.getMessage().getFrom().getLanguageCode());
        SendMessage sendMessage = new SendMessage(update.getMessage().getChatId() + "",makeResponse.toString());
        responseToMessage(sendMessage);
    }
}
