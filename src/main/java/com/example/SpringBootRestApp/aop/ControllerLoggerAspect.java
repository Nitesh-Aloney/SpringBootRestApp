package com.example.SpringBootRestApp.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.logging.Logger;

@Aspect
@Component
public class ControllerLoggerAspect {

    private Logger log = Logger.getLogger(getClass().getName());

    @Pointcut("execution(* com.example.SpringBootRestApp.controller.*.*(*))")
    public void controllerPointCut(){}

    @Before("controllerPointCut()")
    public void controllersBeforeLogger(JoinPoint joinPoint){
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Object[] args = joinPoint.getArgs();

        log.info("[Before] "+signature+" -> arguments passed : "+ Arrays.toString(args));
    }

    @AfterReturning(value = "controllerPointCut()", returning = "result")
    public void controllersAfterLogger(JoinPoint joinPoint, Object result){
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();

        log.info("[After Returning] "+signature+" -> returned : "+ result);
    }

    @AfterThrowing(value = "controllerPointCut()", throwing = "excep")
    public void controllersAfterLogger(JoinPoint joinPoint, Exception excep){
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();

        log.info("[After Throwing] "+signature+" -> throws : "+ excep.getMessage());
    }

}
