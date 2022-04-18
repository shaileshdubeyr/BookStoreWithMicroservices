package cart.carts.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CartAspect {
    /**
     *
     * @param jp for passing parameter
     * @Purpose show message of database operation
     * @return no return type
     */
    @Before("execution(* cart.carts.service.CartService.saveBooksToCart(..))")
    public void savingData(JoinPoint jp){
        System.out.println("Saving data into the database");
    }

    /**
     *
     * @param jp for passing parameter
     * @Purpose show message of database operation
     * @return no return type
     */
    @After("execution(* cart.carts.service.CartService.saveBooksToCart(..))")
    public void afterSavingData(JoinPoint jp){
        System.out.println("Saving success");
    }

    @AfterReturning("execution(* cart.carts.service.CartService.saveBooksToCart(..))")
    public void afterReturningStatus(JoinPoint jp){
        System.out.println("Saving success returning status");
    }
}
