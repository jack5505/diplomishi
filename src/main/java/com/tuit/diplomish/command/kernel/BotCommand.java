package com.tuit.diplomish.command.kernel;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface BotCommand  {
    void execute(Update update);
}
