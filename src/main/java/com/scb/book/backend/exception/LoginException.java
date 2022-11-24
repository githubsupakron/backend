package com.scb.book.backend.exception;

public class LoginException extends BaseException{

    public LoginException(String errorCode) {
        super("Login."+errorCode);
    }

    public static LoginException requestNull(){
        return new LoginException("request.null");
    }

    public static LoginException usernameNull(){
        return new LoginException("username.null");
    }

    public static LoginException passwordNull(){
        return new LoginException("password.null");
    }


}
