package com.example.GorkyQuest.Exception;

public class UserAuthorizationException extends RuntimeException{
    public UserAuthorizationException(String message){
        super(message);
    }
}
