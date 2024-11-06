package com.tuit.diplomish.ui;

import java.util.List;

public interface ResponseStrategy<T> {

    T makeResponse();

    T sharePhoneNumberToRegister();

    T chooseOption();

    T adminMenuAddQuestions();

     T makeAnswers(List<String> answers);

}
