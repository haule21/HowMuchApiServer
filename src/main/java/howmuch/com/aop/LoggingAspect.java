package howmuch.com.aop;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Before("execution(* howmuch.com.controller.rest.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        log.info("Before: " + joinPoint.getSignature().getName());
    }

    @After("execution(* howmuch.com.controller.rest.*.*(..))")
    public void logAfter(JoinPoint joinPoint) {
        log.info("After: " + joinPoint.getSignature().getName());
    }

//    @AfterReturning(pointcut = "execution(* howmuch.com.controller.rest.*.*(..))", returning = "result")
//    public void logAfterReturning(JoinPoint joinPoint, Object result) {
//        log.info("AfterReturning: " + joinPoint.getSignature().getName() + " result: " + result);
//    }

    @AfterThrowing(pointcut = "execution(* howmuch.com.controller.rest.*.*(..))", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
        log.error("AfterThrowing: " + joinPoint.getSignature().getName() + " exception: " + e.getMessage());
    }

//    @Around("execution(* howmuch.com.controller.rest.*.*(..))")
//    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
//        log.info("Around before: " + joinPoint.getSignature().getName());
//        Object result = joinPoint.proceed();
//        log.info("Around after: " + joinPoint.getSignature().getName());
//        return result;
//    }

}