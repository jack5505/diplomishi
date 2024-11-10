package com.tuit.diplomish.command;

import com.tuit.diplomish.command.kernel.TelegramSendMessage;
import com.tuit.diplomish.dao.entity.UserEntity;
import com.tuit.diplomish.dao.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.generics.TelegramClient;

@Service("LOGIN")
@Slf4j
public class Login extends TelegramSendMessage {

    private final TelegramClient telegramClient;
    private final UserService userService;
    private final SharePhoneRegister  sharePhoneRegister;

    public Login(TelegramClient telegramClient,
                 UserService userService,
                 SharePhoneRegister sharePhoneRegister) {
        super(telegramClient);
        this.telegramClient = telegramClient;
        this.userService = userService;
        this.sharePhoneRegister = sharePhoneRegister;
    }

    @Override
    public void execute(Update update) {
        final Long userId = update.getMessage().getFrom().getId();
        StringBuilder makeResponse = new StringBuilder();
        makeResponse.append(update.getMessage().getFrom().getFirstName() + " ");
        makeResponse.append(update.getMessage().getFrom().getLastName() + " ");
        makeResponse.append(update.getMessage().getFrom().getUserName() + " \n");
        makeResponse.append("telegramdaki nastroyka qiligan tili " + update.getMessage().getFrom().getLanguageCode());
        SendMessage sendMessage = new SendMessage(update.getMessage().getChatId() + "",makeResponse.toString());
        responseToMessage(sendMessage);
        UserEntity userEntity = this.userService.findByUserId(userId).orElseGet(() -> null);
        if(userEntity != null) {
                log.info("welcome to system again {}",userEntity.getPhoneNumber());
                sharePhoneRegister.execute(update);
        }
    }
}
