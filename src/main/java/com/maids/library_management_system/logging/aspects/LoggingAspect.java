package com.maids.library_management_system.logging.aspects;

import com.maids.library_management_system.logging.entities.LogEntry;
import com.maids.library_management_system.logging.repositories.LogEntryRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@RequiredArgsConstructor
public class LoggingAspect {

    private final LogEntryRepository repository;


    @Before("@annotation(com.maids.library_management_system.logging.annotations.Loggable)")
    public void logBeforeMethodCalls(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String logString = String.format("[%s] %s", methodName, Arrays.toString(joinPoint.getArgs()));
        LogEntry entry = LogEntry.builder()
                .content("Log Before: " + logString)
                .build();
        repository.save(entry);
    }

    @After("@annotation(com.maids.library_management_system.logging.annotations.Loggable)")
    public void logAfterMethodCalls(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String logString = String.format("[%s] %s", methodName, Arrays.toString(joinPoint.getArgs()));
        LogEntry entry = LogEntry.builder()
                .content("Log After: " + logString)
                .build();
        repository.save(entry);
    }

    @AfterThrowing(pointcut = "@annotation(com.maids.library_management_system.logging.annotations.Loggable)", throwing = "ex")
    public void logExceptions(JoinPoint joinPoint, Throwable ex) {
        String methodName = joinPoint.getSignature().getName();
        String logString = String.format("[%s] %s threw an exception: %s", methodName, Arrays.toString(joinPoint.getArgs()), ex.getMessage());
        LogEntry entry = LogEntry.builder()
                .content("Log Exception: " + logString)
                .build();
        repository.save(entry);
    }

}
