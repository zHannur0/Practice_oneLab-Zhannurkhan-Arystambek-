package org.example.aspect;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.example.kafka.KafkaSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Aspect
@Component
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class KafkaLoggerAspect {
    private static final Logger logger = LoggerFactory.getLogger(KafkaLoggerAspect.class);

    KafkaSender kafkaSender;

    @Autowired
    public KafkaLoggerAspect(KafkaSender kafkaSender) {
        this.kafkaSender = kafkaSender;
    }

    @Pointcut("execution(* org.example.kafka.KafkaSender.listen())")
    public void kafkaListen(){

    }

    @Before("kafkaListen()")
    public void beforeGetPlaylists() {
        logger.info("");
    }

    @AfterReturning(pointcut = "kafkaListen()", returning = "result")
    public void afterGetPlaylists(Object result) {
        logger.info("Result: {}" + result.toString());
    }
}
