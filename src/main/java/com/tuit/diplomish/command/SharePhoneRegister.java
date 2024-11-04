package com.tuit.diplomish.command;

import com.tuit.diplomish.command.kernel.TelegramSendMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.generics.TelegramClient;

@Service
@Slf4j
public class SharePhoneRegister extends TelegramSendMessage {

    private final TelegramClient telegramClient;

    public SharePhoneRegister(TelegramClient telegramClient) {
        super(telegramClient);
        this.telegramClient = telegramClient;
    }

    @Override
    public void execute(Update update) {
        log.info(update.toString());
    }
}
