package com.book.books.excption;

/**
 * @author shailesh dubey
 * @Version 16.0.2
 * @Since 24-03-2022
 */
public class BookException extends  RuntimeException {
    public enum ExceptionType{
        BOOK_ALREADY_EXISTS,
        BOOK_NOT_FOUND,
        BOOK_INVALID,
        ALREADY_VERIFIED,
        INVALID_DATA,
        NOT_VALID_BOOK,
        NO_BOOK_REGISTERED
    }

    public ExceptionType type;
    public BookException(String message, ExceptionType type){
        super(message);
        this.type = type;
    }
}
