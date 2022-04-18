package com.user.users.exception;

/**
 * @author shailesh dubey
 * @Version 16.0.2
 * @Since 24-03-2022
 */
public class UserException extends  RuntimeException {
    public enum ExceptionType{
        USER_ALREADY_EXISTS,
        EMAIL_NOT_FOUND,
        PASSWORD_INVALID,
        ALREADY_VERIFIED,
        INVALID_DATA,
        NOT_VALID_USER,
        NO_USER_REGISTERED
    }

    public UserException.ExceptionType type;
    public UserException(String message, UserException.ExceptionType type){
        super(message);
        this.type = type;
    }
}
