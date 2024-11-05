package com.tuit.diplomish.command;

import com.tuit.diplomish.command.kernel.BotCommand;
import com.tuit.diplomish.command.kernel.TelegramSendMessage;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.generics.TelegramClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;


@Getter
@Setter
@Service("ADMIN_ADD_QUESTIONS")
public class AdminAddQuestion extends TelegramSendMessage {
    private final TelegramClient telegramClient;
    private  Integer questionsSizeToAdd;
    private List<String> questions = new ArrayList<>();
    List<List<String>> answer = new ArrayList<>();
    List<String> temp = new ArrayList<>();
    private boolean flagQuestions = false;
    private Integer answerToQuestion = 4;

    public AdminAddQuestion(TelegramClient telegramClient) {
        super(telegramClient);
        this.telegramClient = telegramClient;

    }

    @Override
    public void execute(Update update)
    {
        String text = update.getMessage().getText();
        SendMessage sendMessage = null;
        if(!flagQuestions && Objects.nonNull(questionsSizeToAdd) &&  questionsSizeToAdd >= 0){
            if(answerToQuestion == 4){

            }
        }
        else if(flagQuestions && Objects.nonNull(questionsSizeToAdd) && questionsSizeToAdd >= 1){
            questions.add(update.getMessage().getText());
            sendMessage = sendMessage(update.getMessage().getChatId()+"","endi 4 ta javob kiriting oxirida to`g`ri javobni 4 qilib kirtasiz");
            questionsSizeToAdd--;
            flagQuestions = false;
        }
        else if(checkIsDigit(text))
        {
            questionsSizeToAdd = Integer.parseInt(text);
            flagQuestions = true;
            sendMessage =
                    sendMessage(update.getMessage().getChatId() + "",
                            "Iltimos savolar kiriting  !! ");
        }else {
            sendMessage =
                    sendMessage(update.getMessage().getChatId() + "",
                            "Qoshadigan savoligizni sonini kiriting (iltimos faqat son)? ");
        }
        responseToMessage(sendMessage);
    }

    private SendMessage sendMessage(String chatId,String text){
        return new SendMessage(chatId, text);
    }

    private boolean checkIsDigit(String text){
        return (Pattern.matches("\\d+", text));
    }


}
