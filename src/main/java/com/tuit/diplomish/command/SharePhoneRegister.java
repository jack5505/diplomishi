package com.tuit.diplomish.command;

import com.tuit.diplomish.command.kernel.TelegramSendMessage;
import com.tuit.diplomish.dao.UserDTO;
import com.tuit.diplomish.dao.service.UserService;
import com.tuit.diplomish.mapper.ContactToUserDtoMapper;
import com.tuit.diplomish.ui.ResponseStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Contact;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.generics.TelegramClient;

import java.util.ArrayList;
import java.util.List;

@Service("PHONE")
@Slf4j
public class SharePhoneRegister extends TelegramSendMessage {
    private List<UserDTO> userDTOList = new ArrayList<>();
    private final ContactToUserDtoMapper mapper;
    private final TelegramClient telegramClient;
    private final ResponseStrategy<ReplyKeyboardMarkup> responseStrategy;
    private final UserService userService;

    public SharePhoneRegister(TelegramClient telegramClient,
                              ContactToUserDtoMapper mapper,
                              ResponseStrategy<ReplyKeyboardMarkup> responseStrategy,
                              UserService userService)
    {
        super(telegramClient);
        this.mapper = mapper;
        this.telegramClient = telegramClient;
        this.responseStrategy = responseStrategy;
        this.userService = userService;
    }

    /**
     *  In this method we should register new user no matter who is this admin or simple user
     *  In this first to check this user if this user not exist then write down to database
     *  otherwise create and let them choose admin or simple user.
     *  Every time when you enter to Register it will give you choose two options no matter
     *  your was in our system or not it will keep going to give you two options
     *
     * @param update
     */

    @Override
    public void execute(Update update)
    {

        final Contact contact = update.getMessage().getContact();
        final User from = update.getMessage().getFrom();
        log.info(update.toString());
        if(contact != null) {
            saveData(contact, update.getMessage().getFrom().getUserName(),from.getId());
        }
        log.info(userDTOList.toString());
        //
        final String welcome_page = String.format("""
                👨🏼‍💻 WELCOME TO OUR BOT!!!  %s  %s  🥳🤩🤗
                """, from.getLastName(), from.getFirstName());
        SendMessage sendMessage = new SendMessage(update.getMessage().getChatId() + "",welcome_page);
        sendMessage.setReplyMarkup(responseStrategy.chooseOption());
        responseToMessage(sendMessage);
    }
    private void saveData(Contact contact,String userName,Long userId)
    {
        userService.findByUserId(userId)
                .orElseGet(() -> userService.createUser(userName,contact.getPhoneNumber(),
                                    contact.getUserId(),contact.getFirstName(), contact.getLastName()));
    }
}
