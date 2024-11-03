package com.tuit.diplomish.ui;

import com.tuit.diplomish.common.Text;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardRow;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@Service("FIRST_RESPONSE")
public class FirstOptionResponseStrategy  implements ResponseStrategy<ReplyKeyboardMarkup>{
    @Override
    public ReplyKeyboardMarkup makeResponse() {
        KeyboardButton button = new KeyboardButton(Text.REGISTER);
        KeyboardRow row = new KeyboardRow();
        row.add(button);
        KeyboardButton login = new KeyboardButton(Text.LOGIN);
        KeyboardRow row2 = new KeyboardRow();
        row2.add(login);
        List<KeyboardRow> rows = new ArrayList<>();
        rows.add(row);
        rows.add(row2);
        return  new ReplyKeyboardMarkup(rows);
    }


//    @Override
//    public InlineKeyboardMarkup makeResponse() {
//        List<InlineKeyboardButton> rinline1 = new ArrayList<>();
//        List<InlineKeyboardButton> rinline2 = new ArrayList<>();
//        InlineKeyboardButton register = new InlineKeyboardButton("Register");
//        rinline1.add(register);
//        InlineKeyboardButton logIn = new InlineKeyboardButton("LogIn");
//        rinline2.add(logIn);
//        List<List<InlineKeyboardButton>> row = new ArrayList<>();
//        row.add(rinline1);
//        row.add(rinline2);
//        List<InlineKeyboardRow> rows = new ArrayList<>();
//        return new InlineKeyboardMarkup(rows);
//    }

}
