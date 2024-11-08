package com.tuit.diplomish.command.useranswers;

import com.tuit.diplomish.command.AdminAddQuestion;
import com.tuit.diplomish.command.User;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface UserAnswerState {
    void handle(User context, Update update);
}
