package nl.hsleiden.webshop.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private Logger logger = Logger.getLogger(getClass().getName());

    @Pointcut("execution(* nl.hsleiden.webshop.controller.*.*(..))")
    private void forControllerPackage() {}

    @Pointcut("execution(* nl.hsleiden.webshop.service.*.*(..))")
    private void forServicePackage() {}

    @Pointcut("execution(* nl.hsleiden.webshop.dao.*.*(..))")
    private void forDaoPackage() {}

    @Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
    private void forAppFlow() {}

    @Before("forAppFlow()")
    public void before(JoinPoint theJoinPoint) {
        String theMethod = theJoinPoint.getSignature().toShortString();
        logger.info("=====>> in @Before: calling method: " + theMethod);
    }

    @AfterReturning(
            pointcut="forAppFlow()",
            returning="result"
    )
    public void afterReturning(JoinPoint joinPoint, Object result) {
        String theMethod = joinPoint.getSignature().toShortString();
        logger.info("=====>> in @AfterReturning: from method: " + theMethod);
        logger.info("=====>> result: " + result);
    }

}