package com.tuit.diplomish.command.useranswers;

import com.tuit.diplomish.command.User;
import com.tuit.diplomish.ui.MakeQuestionListUI;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;


import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 */
@Service("addAnswer")
public class UserAnswerCountState implements UserAnswerState {


    private final UserAnswerState addQuestion;
    private Map<Long,Dashboard> information = new LinkedHashMap<>();


    public UserAnswerCountState(UserAnswerState addQuestion) {
        this.addQuestion = addQuestion;
    }


    @Override
    public void handle(User context, Update update) {
        final String msg = update.getMessage().getText();
        final Long userId = update.getMessage().getFrom().getId();
        final Integer leftQuestion = context.getCurrentUserQuestion().get(userId);
        MakeQuestionListUI.AskQuestion askQuestion = context.getQuestionMap().get(userId).get(leftQuestion );
        makeCountAnswers(msg,askQuestion,userId);
        if(leftQuestion == 0) {
            Dashboard dashboard = information.get(userId);
            context.sendMessage(information(dashboard,update.getMessage().getChatId() + ""));
            context.changeState(addQuestion);
            clearAllData(context, userId);
            context.changeToMenu(update);
            return;
        }
        context.changeState(addQuestion);
        addQuestion.handle(context, update);
    }

    /**
     * Clear all data that not needed one anymore to answer or to get question from
     * @param context
     * @param userId
     */

    private void clearAllData(User context, Long userId) {
        context.getCurrentProcessUsers().remove(userId);
        context.getQuestionMap().remove(userId);
        information.remove(userId);
    }

    private void makeCountAnswers(String anser, MakeQuestionListUI.AskQuestion question,Long userId)
    {
        boolean present = question.getAnswers().stream()
                .filter(i -> i.getAnswer().equals(anser) && i.getCorrect()).findAny().isPresent();

        information.putIfAbsent(userId,new Dashboard());
        Dashboard dashboard = information.get(userId);
        if(present){
            dashboard.setRightAnswers(dashboard.getRightAnswers() + 1);
        }else{
            dashboard.setWrongAnswers(dashboard.getWrongAnswers() + 1);
        }
    }
    private SendMessage information(Dashboard information,String chatId){
        final String result = String.format("Tabriklaymiz siz to`g`ri javob %sta ✅ noto`g`ri %sta ❌",information.getRightAnswers(),information.getWrongAnswers());
        SendMessage sendMessage = new SendMessage(chatId, result);
        return sendMessage;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class Dashboard{
        private Integer rightAnswers = 0;
        private Integer wrongAnswers = 0;
    }
}
