package com.tuit.diplomish.command;

import com.tuit.diplomish.command.kernel.BotCommand;
import com.tuit.diplomish.command.kernel.TelegramSendMessage;
import com.tuit.diplomish.dao.service.AnswerService;
import com.tuit.diplomish.dao.service.QuestionService;
import com.tuit.diplomish.ui.FirstOptionResponseStrategy;
import com.tuit.diplomish.ui.MakeQuestionListUI;
import com.tuit.diplomish.ui.ResponseStrategy;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.sql.ast.tree.expression.Star;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.message.Message;
import org.telegram.telegrambots.meta.generics.TelegramClient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service("USER")
@Getter
public class User extends TelegramSendMessage {

    private final TelegramClient telegramClient;
    private final ResponseStrategy responseStrategy;
    private final MakeQuestionListUI makeQuestionListUI;
    // this flag tells us asking question or Asnwer
    private Boolean questionOrAnswer = Boolean.TRUE;
    private Map<Long,Boolean> currentProcessUsers = new HashMap<>();
    private Map<Long,Information> dashboardInformation = new HashMap<>();
    private final BotCommand start;


    public User(TelegramClient telegramClient,
                FirstOptionResponseStrategy responseStrategy,
                QuestionService questionService,
                AnswerService answerService,
                MakeQuestionListUI makeQuestionListUI,
                Start sharePhoneRegister) {
        super(telegramClient);
        this.telegramClient = telegramClient;
        this.responseStrategy = responseStrategy;
        this.makeQuestionListUI = makeQuestionListUI;
        this.start = sharePhoneRegister;
    }

    @Override
    public void execute(Update update)
    {
        final Long userId = update.getMessage().getFrom().getId();
        responseToMessage(makeQuestionListUI.makeList(update.getMessage().getFrom().getId(),
                update.getMessage().getChatId() + ""));
        if(this.makeQuestionListUI.getNotGoOn()) {
            currentProcessUsers.putIfAbsent(userId, Boolean.TRUE);
        }
        if(currentProcessUsers.containsKey(userId)) {
            calculateRightAnswers(update.getMessage().getText(),userId);
        }
        if(makeQuestionListUI.getQuestionMap().get(userId) == null){
            this.currentProcessUsers.remove(userId);
            responseToMessage(finalResult(dashboardInformation.get(userId),update.getMessage().getChatId() + ""));
            this.start.execute(update);
            dashboardInformation.remove(userId);
        }

    }

    private void calculateRightAnswers(String answer,Long userId) {
        Integer current = this.makeQuestionListUI.getCurrentUserQuestion().get(userId);
        if(current != null && current >= 0) {
            MakeQuestionListUI.AskQuestion askQuestion = this.makeQuestionListUI.getQuestionMap().get(userId).get(current);
            dashboardInformation.putIfAbsent(userId,new Information());
            Information information = dashboardInformation.get(userId);
            writeDownInformationAboutResult(information,checkCorrectAnswer(askQuestion.getAnswers(),answer));
        }
    }



    private void writeDownInformationAboutResult(Information current,Boolean isStatusAnswer){
        if(isStatusAnswer){
            current.setCorrectAnswer(current.getCorrectAnswer() + 1);
        }else{
            current.setWrongAnswer(current.getWrongAnswer() + 1);
        }
    }

    private boolean checkCorrectAnswer(List<MakeQuestionListUI.Answer> answers, String answer){
        Optional<MakeQuestionListUI.Answer> first = answers.stream()
                .filter(answer1 -> answer1.getAnswer().equals(answer) && answer1.getCorrect())
                .findFirst();
        return first.isPresent();
    }

    private SendMessage finalResult(Information information,String chatId){
        final String result = String.format("CONGRULATIONS YOUR RESULT IS  correctAsnwers  %S ✅ wrongAsnwers %S ❌", information.getCorrectAnswer(), information.getWrongAnswer());
        SendMessage sendMessage = new SendMessage(chatId,result);
        return sendMessage;
    }

    @Override
    public void sendCustomMessageToIntendMenu(Update update) {
        SendMessage sendMessage = new SendMessage(update.getMessage().getChatId()+"","ILTIMOS SAVOLARGA JAVOB BERIB BO`LING!");
        responseToMessage(sendMessage);
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    static class Information{
       Integer correctAnswer = 0;
       Integer wrongAnswer = 0;
    }
}
