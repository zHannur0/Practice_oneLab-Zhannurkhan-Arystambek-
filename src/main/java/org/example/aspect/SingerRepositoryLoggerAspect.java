package org.example.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SingerRepositoryLoggerAspect {

    private static final Logger logger = LoggerFactory.getLogger(SingerRepositoryLoggerAspect.class);

    @Pointcut("execution(* org.example.repository.SingerRepository.save(..))")
    public void savePointcut() {}

    @Before("savePointcut()")
    public void beforeSave(JoinPoint joinPoint) {
        logger.info("Saving singer...");
        logger.info("SingerDTO: {}", joinPoint.getArgs()[0].toString());
    }

    @AfterReturning(pointcut = "savePointcut()", returning = "result")
    public void afterSave(Object result) {
        logger.info("Singer saved successfully.");
    }
}