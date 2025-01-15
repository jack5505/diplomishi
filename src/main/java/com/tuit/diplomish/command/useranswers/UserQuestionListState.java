package com.tuit.diplomish.command.useranswers;

import com.tuit.diplomish.command.User;
import com.tuit.diplomish.dto.PollQuestionDto;
import com.tuit.diplomish.ui.MakeQuestionListUI;
import com.tuit.diplomish.ui.MakeQuestionListUI.AskQuestion;
import com.tuit.diplomish.ui.ResponseStrategy;
import com.tuit.diplomish.utils.ApiConstants;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;


@Service("addQuestion")
public class UserQuestionListState implements UserAnswerState {

    private final ResponseStrategy<ReplyKeyboardMarkup> responseStrategy;
    private final UserAnswerState addAnswer;
    private Integer questionStart = 1;

    private final RestClient restClient;

    public UserQuestionListState(ResponseStrategy responseStrategy,
                                 @Lazy UserAnswerState addAnswer,
                                 RestClient restClient) {
        this.responseStrategy = responseStrategy;
        this.addAnswer = addAnswer;
        this.restClient = restClient;
    }

    @Override
    public void handle(User context, Update update)
    {
        sendPoll(update.getMessage().getChatId() + "", context);
        //Map<Long, List<AskQuestion>> questionMap = context.getQuestionMap();
        //SendMessage sendMessage = makeList(update.getMessage().getFrom().getId(), update.getMessage().getChatId() + "", questionMap, context);
        //context.sendMessage(sendMessage);
    }

    private SendMessage makeList(Long userId,
                                 String chatId,
                                 Map<Long,List<AskQuestion>> questionMap,
                                 User context)
    {
        if(questionMap.get(userId) == null || questionMap.get(userId).isEmpty())
        {
            questionMap.put(userId,context.getQuestionService().listQuestionRandom()
            //questionMap.put(userId,context.getQuestionService().listQuestions(userId)
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
        context.changeState(addAnswer);
        context.getCurrentProcessUsers().put(userId,true);
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

    private void sendPoll(String chatId, User context){
        try {

            context.getQuestionService().listQuestionRandom()
                    //questionMap.put(userId,context.getQuestionService().listQuestions(userId)
                    .stream()
                    .map(question -> {
                        PollQuestionDto questionRequest = new PollQuestionDto();
                        questionRequest.setChatId(chatId);
                        questionRequest.setQuestion(questionStart +") savol \n" +question.getContent());
                        questionRequest.setOptions(context.getAnswerService().listAnswersToQuestion(question.getId()).stream().map(i -> i.getAnswer()).toList());
                        questionRequest.setCorrectOptionId(1);
                        questionRequest.setIsAnonymous(false);
                        questionStart ++;
                        return questionRequest;
                    }).forEach(i ->{
                        try {
                            String body = restClient
                                    .post()
                                    .uri(ApiConstants.TELEGRAM_API + ApiConstants.botToken + ApiConstants.SEND_POLL)
                                    .body(i)
                                    .retrieve()
                                    .body(String.class);
                            System.out.println(body);
                        }catch (Exception e){
                            System.out.println(e.getMessage());
                        }
                    });

//            PollQuestionDto pollQuestionDto = new PollQuestionDto();
//            pollQuestionDto.setChatId(chatId);
//            pollQuestionDto.setQuestion("hell world");
//            pollQuestionDto.setCorrectOptionId(1);
//            pollQuestionDto.setIsAnonymous(false);
//            pollQuestionDto.setOptions(List.of("Bir","ikki","uch","tort"));
//
//            String body = restClient
//                    .post()
//                    .uri(ApiConstants.TELEGRAM_API +  ApiConstants.botToken + ApiConstants.SEND_POLL)
//                    .body(list)
//                    .retrieve()
//                    .body(String.class);

            // System.out.println(body);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

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
