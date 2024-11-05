package com.tuit.diplomish.ui;

import com.tuit.diplomish.common.Text;
import org.jetbrains.annotations.NotNull;
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
        KeyboardButton button = new KeyboardButton(Text.REGISTER.getText());
        KeyboardRow row = new KeyboardRow();
        row.add(button);
        KeyboardButton login = new KeyboardButton(Text.LOGIN.getText());
        KeyboardRow row2 = new KeyboardRow();
        row2.add(login);
        return  getReplyKeyboardMarkup(row,row2);
    }

    @Override
    public ReplyKeyboardMarkup sharePhoneNumberToRegister() {
        KeyboardButton button = new KeyboardButton(Text.PHONE.getText());
        button.setRequestContact(true);
        KeyboardRow row = new KeyboardRow();
        row.add(button);
        return getReplyKeyboardMarkup(row);
    }

    @Override
    public ReplyKeyboardMarkup chooseOption() {
        KeyboardButton admin = new KeyboardButton(Text.ADMIN.getText());
        KeyboardRow row = new KeyboardRow();
        row.add(admin);
        KeyboardButton user = new KeyboardButton(Text.USER.getText());
        KeyboardRow row1 = new KeyboardRow();
        row1.add(user);
        return getReplyKeyboardMarkup(row,row1);
    }

    @Override
    public ReplyKeyboardMarkup adminMenuAddQuestions() {
        KeyboardButton button = new KeyboardButton(Text.ADMIN_ADD_QUESTIONS.getText());
        KeyboardRow row = new KeyboardRow();
        row.add(button);
        return getReplyKeyboardMarkup(row);
    }

    @NotNull
    private static ReplyKeyboardMarkup getReplyKeyboardMarkup(KeyboardRow ... row) {
        List<KeyboardRow> rows = new ArrayList<>();
        for(int i = 0 ;i < row.length; i++){
            rows.add(row[i]);
        }
        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup(rows);
        markup.setResizeKeyboard(true);
        return markup;
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
