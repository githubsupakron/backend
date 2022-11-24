package com.scb.book.backend.exception;

public class UserException extends BaseException {

    public UserException(String code) {
        super("user." + code);
    }


    public static UserException requestNull() {
        return new UserException("register.request.null");
    }


    // CREATE

    public static UserException createUserDuplicated() {
        return new UserException("create.user.duplicated");
    }

    public static UserException createPasswordNull() {
        return new UserException("create.password.null");
    }

    public static UserException createUsernameNull() {
        return new UserException("create.username.null");
    }
    public static UserException createNameNull() {
        return new UserException("create.name.null");
    }

    // LOGIN

    public static UserException loginFailUserNotFound() {
        return new UserException("login.fail");
    }

    public static UserException loginFailPasswordIncorrect() {
        return new UserException("login.password.fail");
    }

}