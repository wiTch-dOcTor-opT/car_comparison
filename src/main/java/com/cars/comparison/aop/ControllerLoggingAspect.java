package com.cars.comparison.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.After;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ControllerLoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(ControllerLoggingAspect.class);

    private ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Before("execution(* com.cars.comparison.controller.*.*(..))")
    public void logControllerMethodInvocation(JoinPoint joinPoint) {
        startTime.set(System.currentTimeMillis());
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        Object[] args = joinPoint.getArgs();
        logger.debug("Method '{}' in class '{}' invoked with arguments: {}", methodName, className, args);
    }

    @After("execution(* com.cars.comparison.controller.*.*(..))")
    public void logMethodExecutionTime(JoinPoint joinPoint) {
        long executionTime = System.currentTimeMillis() - startTime.get();
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        logger.debug("Method '{}' in class '{}' takes execution time: {} ms", methodName, className, executionTime);
    }
}
