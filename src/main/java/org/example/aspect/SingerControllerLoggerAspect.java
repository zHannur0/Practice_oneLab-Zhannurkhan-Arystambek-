package org.example.aspect;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Aspect
@Component
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class SingerControllerLoggerAspect {

    private static final Logger logger = LoggerFactory.getLogger(SingerControllerLoggerAspect.class);

    HttpServletRequest request;

    HttpServletResponse response;

    @Autowired
    public SingerControllerLoggerAspect(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }
    //Get all singers
    @Pointcut("execution(* org.example.controller.SingerController.getSingers())")
    public void getSingersPointcut(){

    }

    @Before("getSingersPointcut()")
    public void beforeGetSingers(JoinPoint joinPoint) {
        logger.info("Get singers! Send request + \n IP address: " +
                request.getLocalAddr() + "\n Requested URL: " +
                request.getRequestURL() + "\n User: " +
                request.getRemoteUser());
    }

    @AfterReturning(pointcut = "getSingersPointcut()", returning = "result")
    public void afterGetSingers(Object result) {
        String status = response.getStatus() == 200 ? "Success!" : "Fail!";
        logger.info(status + "\n Status: " +
                response.getStatus() + "\n Result: " +
                result.toString());
    }

    //Get singer by id
    @Pointcut("execution(* org.example.controller.SingerController.getSinger(..))")
    public void getSingerPointcut(){

    }

    @Before("getSingerPointcut()")
    public void beforeGetSinger(JoinPoint joinPoint) {
        logger.info("Get singer! Send request + \n IP address: " +
                request.getLocalAddr() + "\n Requested URL: " +
                request.getRequestURL() + "\n User: " +
                request.getRemoteUser());
    }

    @AfterReturning(pointcut = "getSingerPointcut()", returning = "result")
    public void afterGetSinger(Object result) {
        String status = response.getStatus() == 200 ? "Success!" : "Fail!";
        logger.info(status + "\n Status: " +
                response.getStatus() + "\n Result: " +
                result.toString());
    }

    //Create singer
    @Pointcut("execution(* org.example.controller.SingerController.createSinger(..))")
    public void createSingerPointcut(){

    }

    @Before("createSingerPointcut()")
    public void beforeCreateSinger(JoinPoint joinPoint) {
        logger.info("Create singer! Send request + \n IP address: " +
                request.getLocalAddr() + "\n Requested URL: " +
                request.getRequestURL() + "\n User: " +
                request.getRemoteUser());;
    }

    @AfterReturning(pointcut = "createSingerPointcut()", returning = "result")
    public void afterCreateSinger(Object result) {
        String status = response.getStatus() == 200 ? "Success!" : "Fail!";
        logger.info(status + "\n Status: " +
                response.getStatus() + "\n Result: " +
                result.toString());
    }

    //Update singer
    @Pointcut("execution(* org.example.controller.SingerController.updateSinger(..))")
    public void updateSingerPointcut(){

    }

    @Before("updateSingerPointcut()")
    public void beforeUpdateSinger(JoinPoint joinPoint) {
        logger.info("Update singer! Send request + \n IP address: " +
                request.getLocalAddr() + "\n Requested URL: " +
                request.getRequestURL() + "\n User: " +
                request.getRemoteUser());
    }

    @AfterReturning(pointcut = "updateSingerPointcut()", returning = "result")
    public void afterUpdateSinger(Object result) {
        String status = response.getStatus() == 200 ? "Success!" : "Fail!";
        logger.info(status + "\n Status: " +
                response.getStatus() + "\n Result: " +
                result.toString());
    }

    //Delete singer
    @Pointcut("execution(* org.example.controller.SingerController.deleteSinger(..))")
    public void deleteSingerPointcut(){

    }

    @Before("deleteSingerPointcut()")
    public void beforeDeleteSinger(JoinPoint joinPoint) {
        logger.info("Delete singer! Send request + \n IP address: " +
                request.getLocalAddr() + "\n Requested URL: " +
                request.getRequestURL() + "\n User: " +
                request.getRemoteUser());
    }

    @AfterReturning(pointcut = "deleteSingerPointcut()", returning = "result")
    public void afterDeleteSinger(Object result) {
        String status = response.getStatus() == 200 ? "Success!" : "Fail!";
        logger.info(status + "\n Status: " +
                response.getStatus());
    }



}
