package com.tuit.diplomish.command.adminquestionadd;

import com.tuit.diplomish.command.AdminAddQuestion;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface AdminState {
    void handle(AdminAddQuestion context, Update update);
}
