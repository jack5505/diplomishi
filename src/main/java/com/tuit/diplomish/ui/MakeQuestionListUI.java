package com.tuit.diplomish.ui;

import com.tuit.diplomish.command.SharePhoneRegister;
import com.tuit.diplomish.dao.entity.AnswerToEntity;
import com.tuit.diplomish.dao.entity.QuestionsEntity;
import com.tuit.diplomish.dao.service.AnswerService;
import com.tuit.diplomish.dao.service.QuestionService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardRow;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.*;
import java.util.stream.Collectors;

/**
 *  In this class we create UI part of question to show up to user
 *  who wants to see create questions for himself
 */
@Service
@RequiredArgsConstructor
@Getter
public class MakeQuestionListUI {

    private final QuestionService questionService;
    private final AnswerService answerService;
    private final ResponseStrategy<ReplyKeyboardMarkup> responseStrategy;
    private Map<Long,List<AskQuestion>> questionMap = new LinkedHashMap<>();
    private Map<Long,Integer> currentUserQuestion = new HashMap<>();
    private Boolean notGoOn = true;

    public SendMessage makeList(Long userId,String chatId)
    {
        notGoOn = true;
        if(questionMap.get(userId) == null || questionMap.get(userId).isEmpty())
        {
            questionMap.put(userId,questionService.listQuestions(userId)
                    .stream()
                    .map(question ->{
                        AskQuestion askQuestion = new AskQuestion();
                        askQuestion.setQuestion(question.getContent());
                        askQuestion.setAnswers(answerService.listAnswersToQuestion(question.getId())
                                .stream()
                                .map(entity->new Answer(entity.getAnswer(),entity.getCorrectAnswer()))
                                .toList());
                        return askQuestion;
                    }).toList());
            currentUserQuestion.put(userId,questionMap.get(userId).size());
            if(questionMap.get(userId) == null || questionMap.get(userId).isEmpty()){
                notGoOn = false;
                return new SendMessage(chatId,"Savolar bo`m bo`sh savol kiriting");
            }
        }
        if(currentUserQuestion.get(userId) == 0){
            AskQuestion askQuestion = questionMap.get(userId).get(currentUserQuestion.get(userId));
            currentUserQuestion.put(userId,currentUserQuestion.get(userId));
            questionMap.remove(userId);
            currentUserQuestion.remove(userId);
            return new SendMessage(chatId,"congrulations you finished it");
        }
        AskQuestion askQuestion = questionMap.get(userId).get(currentUserQuestion.get(userId) - 1);
        currentUserQuestion.put(userId,currentUserQuestion.get(userId) - 1);
        SendMessage sendMessage = new SendMessage(chatId, askQuestion.getQuestion());
        sendMessage.setReplyMarkup(responseStrategy.makeAnswers(changeAnswerPlace(askQuestion.getAnswers())));
        return sendMessage;

    }
    private List<Answer> changeAnswerPlace(List<Answer> answer)
    {
        List<Answer> temp = new ArrayList<>(answer);
        Random random = new Random();
        List<Answer> answers = new ArrayList<>();
        while (temp.size() > 1) {
            int size = temp.size();
            int i = random.nextInt(Math.max(size - 1, 1));
            answers.add(temp.get(i));
            temp.remove(i);
        }
        answers.add(temp.get(0));
        return answers;
    }

    @Getter
    @Setter
    public static class AskQuestion{
        String question;
        List<Answer> answers;

        public AskQuestion(String question, List<Answer> answers) {
            this.question = question;
            this.answers = answers;
        }

        public AskQuestion() {
        }
    }
    @Getter
    @Setter
    public static class Answer{
        String answer;
        Boolean correct;
        public Answer(String answer, Boolean correct) {
            this.answer = answer;
            this.correct = correct;
        }

        public Answer() {
        }
    }

}
