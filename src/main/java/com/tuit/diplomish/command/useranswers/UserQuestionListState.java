package com.tuit.diplomish.command.useranswers;

import com.tuit.diplomish.command.User;
import com.tuit.diplomish.ui.MakeQuestionListUI;
import com.tuit.diplomish.ui.MakeQuestionListUI.AskQuestion;
import com.tuit.diplomish.ui.ResponseStrategy;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;


@Service("addQuestion")
public class UserQuestionListState implements UserAnswerState {

    private final ResponseStrategy<ReplyKeyboardMarkup> responseStrategy;
    private final UserAnswerState addAnswer;

    public UserQuestionListState(ResponseStrategy responseStrategy,
                                 @Lazy UserAnswerState addAnswer) {
        this.responseStrategy = responseStrategy;
        this.addAnswer = addAnswer;
    }

    @Override
    public void handle(User context, Update update)
    {

        Map<Long, List<AskQuestion>> questionMap = context.getQuestionMap();
        SendMessage sendMessage = makeList(update.getMessage().getFrom().getId(), update.getMessage().getChatId() + "", questionMap, context);
        context.changeState(addAnswer);
        context.sendMessage(sendMessage);
    }

    private SendMessage makeList(Long userId,
                                 String chatId,
                                 Map<Long,List<AskQuestion>> questionMap,
                                 User context)
    {
        if(questionMap.get(userId) == null || questionMap.get(userId).isEmpty())
        {
            questionMap.put(userId,context.getQuestionService().listQuestions(userId)
                    .stream()
                    .map(question ->{
                        AskQuestion askQuestion = new AskQuestion();
                        askQuestion.setQuestion(question.getContent());
                        askQuestion.setAnswers(context.getAnswerService().listAnswersToQuestion(question.getId())
                                .stream()
                                .map(entity->new MakeQuestionListUI.Answer(entity.getAnswer(),entity.getCorrectAnswer()))
                                .toList());
                        return askQuestion;
                    }).toList());
            context.getCurrentUserQuestion().put(userId,questionMap.get(userId).size());
            if(questionMap.get(userId) == null || questionMap.get(userId).isEmpty()){
                return new SendMessage(chatId,"Savolar bo`m bo`sh savol kiriting");
            }
        }
        if(context.getCurrentUserQuestion().get(userId) == 0){
            context.getCurrentUserQuestion().put(userId,context.getCurrentUserQuestion().get(userId));
            questionMap.remove(userId);
            context.getCurrentUserQuestion().remove(userId);
            return new SendMessage(chatId,"congrulations you finished it");
        }
        AskQuestion askQuestion = questionMap.get(userId).get(context.getCurrentUserQuestion().get(userId) - 1);
        context.getCurrentUserQuestion().put(userId,context.getCurrentUserQuestion().get(userId) - 1);
        SendMessage sendMessage = new SendMessage(chatId, askQuestion.getQuestion());
        sendMessage.setReplyMarkup(responseStrategy.makeAnswers(changeAnswerPlace(askQuestion.getAnswers())));
        return sendMessage;
    }

    private List<MakeQuestionListUI.Answer> changeAnswerPlace(List<MakeQuestionListUI.Answer> answer)
    {
        List<MakeQuestionListUI.Answer> temp = new ArrayList<>(answer);
        Random random = new Random();
        List<MakeQuestionListUI.Answer> answers = new ArrayList<>();
        while (temp.size() > 1) {
            int size = temp.size();
            int i = random.nextInt(Math.max(size - 1, 1));
            answers.add(temp.get(i));
            temp.remove(i);
        }
        answers.add(temp.get(0));
        return answers;
    }
}
