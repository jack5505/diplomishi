package com.tuit.diplomish.command.kernel;

import com.tuit.diplomish.common.Text;

public interface MenuOperationServiceFactory {
    BotCommand getService(Text text);
}
