package com.tuit.diplomish.command;

import com.tuit.diplomish.command.kernel.TelegramSendMessage;
import com.tuit.diplomish.dao.UserDTO;
import com.tuit.diplomish.mapper.ContactToUserDtoMapper;
import com.tuit.diplomish.ui.ResponseStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Contact;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.generics.TelegramClient;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class SharePhoneRegister extends TelegramSendMessage {
    private List<UserDTO> userDTOList = new ArrayList<>();
    private final ContactToUserDtoMapper mapper;
    private final TelegramClient telegramClient;
    private final ResponseStrategy<ReplyKeyboardMarkup> responseStrategy;

    public SharePhoneRegister(TelegramClient telegramClient,
                              ContactToUserDtoMapper mapper,
                              ResponseStrategy<ReplyKeyboardMarkup> responseStrategy)
    {
        super(telegramClient);
        this.mapper = mapper;
        this.telegramClient = telegramClient;
        this.responseStrategy = responseStrategy;
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

        log.info(update.toString());
        Contact contact = update.getMessage().getContact();
        UserDTO userDto = mapper.toUserDto(contact);
        userDTOList.add(userDto);
        log.info(userDTOList.toString());
        //
        final String welcome_page = String.format("""
                👨🏼‍💻 WELCOME TO OUR BOT!!!  %s  %s  🥳🤩🤗
                """, userDto.getLastName(),userDto.getFirstName());
        SendMessage sendMessage = new SendMessage(update.getMessage().getChatId() + "",welcome_page);
        sendMessage.setReplyMarkup(responseStrategy.chooseOption());
        responseToMessage(sendMessage);
    }
}
