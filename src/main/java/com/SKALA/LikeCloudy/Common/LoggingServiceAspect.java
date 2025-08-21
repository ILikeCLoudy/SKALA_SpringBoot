package com.SKALA.LikeCloudy.Common;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Aspect
@Component
@Order(2) // TracingAspect보다 나중에
public class LoggingServiceAspect {

    @Pointcut("execution(* com.SKALA.LikeCloudy.Service..*(..))")
    public void serviceMethods() {}

    @Before("serviceMethods()")
    public void logBefore(JoinPoint jp) {
        // 민감정보 마스킹 예시
        Object[] args = Arrays.stream(jp.getArgs())
                .map(a -> (a instanceof String s && s.toLowerCase().contains("password")) ? "****" : a)
                .toArray();

        log.info("▶ Service ENTER: {}.{}({})",
                jp.getSignature().getDeclaringTypeName(),
                jp.getSignature().getName(),
                Arrays.toString(args));
    }

    @AfterReturning(pointcut = "serviceMethods()", returning = "result")
    public void logAfter(JoinPoint jp, Object result) {
        String out = (result != null) ? result.toString() : "null";
        if (out.length() > 200) out = out.substring(0, 200) + "...";
        log.info("◀ Service EXIT: {}.{} -> {}",
                jp.getSignature().getDeclaringTypeName(),
                jp.getSignature().getName(),
                out);
    }

    @AfterThrowing(pointcut = "serviceMethods()", throwing = "ex")
    public void logAfterThrowing(JoinPoint jp, Throwable ex) {
        log.error("✖ Service EX: {}.{} threw {}",
                jp.getSignature().getDeclaringTypeName(),
                jp.getSignature().getName(),
                ex.toString());
    }
}