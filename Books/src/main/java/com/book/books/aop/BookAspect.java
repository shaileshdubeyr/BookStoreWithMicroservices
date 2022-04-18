package com.book.books.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @author shailesh dubey
 * @Version 16.0.2
 * @Since 29-03-2022
 */
@Aspect
@Component
public class BookAspect {
    /**
     *
     * @param jp for passing parameter
     * @Purpose show message of database operation
     * @return no return type
     */
    @Before("execution(* com.book.books.service.BookService.saveBooks(..))")
    public void savingData(JoinPoint jp){
        System.out.println("Saving data into the database");
    }

    /**
     *
     * @param jp for passing parameter
     * @Purpose show message of database operation
     * @return no return type
     */
    @After("execution(* com.book.books.service.BookService.saveBooks(..))")
    public void afterSavingData(JoinPoint jp){
        System.out.println("Saving success");
    }

    @AfterReturning("execution(* com.book.books.service.BookService.saveBooks(..))")
    public void afterReturningStatus(JoinPoint jp){
        System.out.println("Saving success returning status");
    }
}
