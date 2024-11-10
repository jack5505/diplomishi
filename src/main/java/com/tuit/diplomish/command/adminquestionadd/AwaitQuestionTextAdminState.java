package com.tuit.diplomish.command.adminquestionadd;

import com.tuit.diplomish.command.AdminAddQuestion;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 *  in this method user will type Questions content
 *  But TODO not think about each individual user so far
 */
@Service("awaitQuestionAdminState")
public class AwaitQuestionTextAdminState implements AdminState {


    private final AdminState awaitAdminState;

    public AwaitQuestionTextAdminState(@Lazy AdminState awaitAdminState) {
        this.awaitAdminState = awaitAdminState;
    }


    @Override
    public void handle(AdminAddQuestion context, Update update) {
        final String text = update.getMessage().getText();
        final Long addQuestionId = context.getQuestionService().addQuestion(text, update.getMessage().getFrom().getId()).getId();
        context.setCurrentQuestionId(addQuestionId);
        Long countQuestion = context.getQuestionSizeToAddTouser().get(update.getMessage().getFrom().getId());
        countQuestion --;
        context.setAnswerToQuestion(4);
        context.getQuestionSizeToAddTouser().put(update.getMessage().getFrom().getId(), countQuestion);
        context.setAwaitQuestionCountState(awaitAdminState);
        context.sendMessage(update.getMessage().getChatId() + "","Savol qabul qilindi ✅ \n Endi javoblarni kiriting oxirgi javob to`g`ri javob bo`lsin iltimos \n javoblar soni :" + context.getAnswerToQuestion());
    }
}
