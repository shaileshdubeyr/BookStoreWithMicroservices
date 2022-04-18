package com.book.books.excption;

import com.book.books.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author shailesh dubey
 * @Version 11.0.2
 * @Since 13-04-2022
 */
@ControllerAdvice
public class BookExceptionHandler {
    //method to throw exception user not found
    @ExceptionHandler(BookException.class)
    public ResponseEntity<ResponseDTO> userNotFound(BookException message){
        ResponseDTO responseDTO = new ResponseDTO("exception whilw processing rest request", message.getMessage(),"token not verified");
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.BAD_REQUEST);
    }
}
