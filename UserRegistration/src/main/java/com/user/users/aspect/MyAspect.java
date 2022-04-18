package com.user.users.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @author shailesh dubey
 * @Version 11.0.2
 * @Since 12-04-2022
 */
@Aspect
@Component
public class MyAspect {
    /**
     *
     * @param jp for passing parameter
     * @Purpose show message of database operation
     * @return no return type
     */
    @Before("execution(* com.user.users.services.UserService.saveUser(..))")
    public void savingData(JoinPoint jp){
        System.out.println("Saving data into the database");
    }

    /**
     *
     * @param jp for passing parameter
     * @Purpose show message of database operation
     * @return no return type
     */
    @After("execution(* com.user.users.services.UserService.saveUser(..))")
    public void afterSavingData(JoinPoint jp){
        System.out.println("Saving success");
    }

    @AfterReturning("execution(* com.user.users.services.UserService.saveUser(..))")
    public void afterReturningStatus(JoinPoint jp){
        System.out.println("Saving success returning status");
    }
}
