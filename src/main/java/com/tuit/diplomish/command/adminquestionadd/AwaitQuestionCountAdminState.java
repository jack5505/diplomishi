package com.tuit.diplomish.command.adminquestionadd;

import com.tuit.diplomish.command.AdminAddQuestion;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;


/**
 *  In this method user will type size of questions
 *  and check if this user type size of question but not finished it
 */
@Service("awaitQuestionCountState")
public class AwaitQuestionCountAdminState implements AdminState{


    private final AdminState awaitQuestionAdminState;

    public AwaitQuestionCountAdminState(@Lazy AdminState awaitQuestionAdminState) {
        this.awaitQuestionAdminState = awaitQuestionAdminState;
    }

    @Override
    public void handle(AdminAddQuestion context, Update update)
    {
        if(context.checkIsDigit(update.getMessage().getText()))
        {
            final Long sizeQuestion = Long.parseLong(update.getMessage().getText());
            final Long id = update.getMessage().getFrom().getId();
            final Long addQuestionSizeToThisUser = context.getQuestionSizeToAddTouser().getOrDefault(id, 0L);
            if(addQuestionSizeToThisUser > 0){
                context.setAwaitQuestionCountState(awaitQuestionAdminState);
                context.sendMessage(update.getMessage().getChatId()+"","Iltimos savolarni to`liq kiriting to`liq kiritilmagan savolar bor");
                return;
            }
            context.getQuestionSizeToAddTouser().put(id, sizeQuestion);
            context.setAwaitQuestionCountState(awaitQuestionAdminState);
            context.sendMessage(update.getMessage().getChatId()+"","Iltimos savolarni kiriting!!");
        }else{
            context.sendMessage(update.getMessage().getChatId()+"","Iltimos savolarni sonini kiriting ");
        }
    }
}
