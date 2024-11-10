package com.tuit.diplomish.command.adminquestionadd;

import com.tuit.diplomish.command.AdminAddQuestion;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 *  in this section you should write down here responses to question
 */

@Service("awaitAdminState")
public class AwaitAnswerAdminState implements AdminState {


    private final AdminState awaitQuestionCountState;


    private final AdminState awaitQuestionAdminState;

    public AwaitAnswerAdminState(@Lazy AdminState awaitQuestionCountState,
                                 @Lazy AdminState awaitQuestionAdminState)
    {
        this.awaitQuestionCountState = awaitQuestionCountState;
        this.awaitQuestionAdminState = awaitQuestionAdminState;
    }

    @Override
    public void handle(AdminAddQuestion context, Update update) {
        final String answer = update.getMessage().getText();
        final Long chatId = update.getMessage().getChatId();
        final Long questionId = context.getCurrentQuestionId();
        context.getAnswerService().addAnswer(answer,questionId,context.getAnswerToQuestion() - 1 == 0);
        context.setAnswerToQuestion(context.getAnswerToQuestion() - 1);
        if(context.getAnswerToQuestion() != 0)
            context.sendMessage(chatId + "","Javob qoshildi! kegingi javoblarni kiriting");
        if(context.getAnswerToQuestion() == 0){
            Long totalQuestion = context.getQuestionSizeToAddTouser().get(update.getMessage().getFrom().getId());
            totalQuestion --;
            if(totalQuestion <= -1){
                context.setAwaitQuestionCountState(awaitQuestionCountState);
                context.getQuestionSizeToAddTouser().remove(update.getMessage().getFrom().getId());
                context.changeMenu(update);
                context.sendMessage(chatId + "","Javob qoshildi! Hammasi tugatildi savol saqlandi bazaga ✅");
                return;
            }
            context.sendMessage(chatId+"",String.format("Javoblar saqlandi! Keginsi savolni kirtsagiz bo`ladi (qolgan savolar soni %s )",totalQuestion + 1));
            context.setAwaitQuestionCountState(awaitQuestionAdminState);
        }
    }
}
