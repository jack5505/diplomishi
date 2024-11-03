package com.tuit.diplomish.ui;

public interface ResponseStrategy<T> {

    T makeResponse();

    T sharePhoneNumberToRegister();

}
