package com.tuit.diplomish.exceptions;

public class BadRequestAlertExceptions extends RuntimeException {
    private String message;

    public BadRequestAlertExceptions(String message) {
        this.message = message;
    }

    public static BadRequestAlertExceptions dataNotFound(){
        return new BadRequestAlertExceptions("data not found");
    }
    public static BadRequestAlertExceptions dataNotFound(String message){
        return new BadRequestAlertExceptions(message);
    }
}
