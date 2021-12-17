package com.example.SpringBootRestApp.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.logging.Logger;

@Aspect
@Component
public class DAOLoggerAspect {

    private Logger log = Logger.getLogger((getClass().getName()));

    @Pointcut("execution(* com.example.SpringBootRestApp.controller.*.*(*))")
    public void daoPointcut(){}

    @Before("daoPointcut()")
    public void daoBeforeLogger(JoinPoint joinPoint){
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Object[] args = joinPoint.getArgs();

        log.info("[Before] "+signature+" -> arguments passed : "+ Arrays.toString(args));
    }

    @AfterReturning(value = "daoPointcut()", returning = "result")
    public void daoAfterLogger(JoinPoint joinPoint, Object result){
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();

        log.info("[After Returning] "+signature+" -> returned : "+ result);
    }

    @AfterThrowing(value = "daoPointcut()", throwing = "excep")
    public void daoAfterLogger(JoinPoint joinPoint, Exception excep){
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();

        log.warning("[After Throwing] "+signature+" -> throws : "+ excep.getMessage());
    }

}
