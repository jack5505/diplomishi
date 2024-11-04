package com.tuit.diplomish.command;

import com.tuit.diplomish.command.kernel.TelegramSendMessage;
import com.tuit.diplomish.dao.UserDTO;
import com.tuit.diplomish.mapper.ContactToUserDtoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Contact;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.generics.TelegramClient;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class SharePhoneRegister extends TelegramSendMessage {
    private List<UserDTO> userDTOList = new ArrayList<>();
    private final ContactToUserDtoMapper mapper;
    private final TelegramClient telegramClient;

    public SharePhoneRegister(TelegramClient telegramClient,
                              ContactToUserDtoMapper mapper)
    {
        super(telegramClient);
        this.mapper = mapper;
        this.telegramClient = telegramClient;
    }

    @Override
    public void execute(Update update) {
        log.info(update.toString());
        Contact contact = update.getMessage().getContact();
        UserDTO userDto = mapper.toUserDto(contact);
        userDTOList.add(userDto);
        log.info(userDTOList.toString());
    }
}
