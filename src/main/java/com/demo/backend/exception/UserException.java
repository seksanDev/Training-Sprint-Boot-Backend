package com.demo.backend.exception;

public class UserException extends BaseException {

    public UserException(String code) {
        super("User." + code);
    }

    // user.request.email.null
    public static UserException requestNull() {
        return new UserException("register.request.null");
    }

    // user.register.email.null
    public static UserException emailNull() {
        return new UserException("register.email.null");
    }

    public static UserException passwordNull() {
        return new UserException("register.password.null");
    }

    public static UserException nameNull() {
        return new UserException("register.name.null");
    }

    // CREATE
    public static UserException createEmailNull() {
        return new UserException("register.email.null");
    }

    public static UserException createPasswordNull() {
        return new UserException("register.password.null");
    }

    public static UserException createNameNull() {
        return new UserException("register.name.null");
    }

    public static UserException createEmailDuplicated() {
        return new UserException("register.email.duplicated");
    }
    // Login

    public static UserException loginFailEmailNotfound() {
        return new UserException("login.fail");
    }

    public static UserException loginFailPasswordIncorrert() {
        return new UserException("login.fail");
    }

    // Update
    public static UserException notFound() {
        return new UserException("user.not.found");
    }

    // Token
    public static UserException unauthorized() {
        return new UserException("unauthorized.not.found");
    }
}
