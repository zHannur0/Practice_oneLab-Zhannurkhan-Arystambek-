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
public class SongControllerLoggerAspect {
    private static final Logger logger = LoggerFactory.getLogger(SongControllerLoggerAspect.class);

    HttpServletRequest request;

    HttpServletResponse response;

    @Autowired
    public SongControllerLoggerAspect(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }


    //Get all song
    @Pointcut("execution(* org.example.controller.SongController.getSongs())")
    public void getSongsPointcut(){

    }

    @Before("getSongsPointcut()")
    public void beforeGetSongs(JoinPoint joinPoint) {
        logger.info("Get songs! Send request + \n IP address: " +
                request.getLocalAddr() + "\n Requested URL: " +
                request.getRequestURL() + "\n User: " +
                request.getRemoteUser());
    }

    @AfterReturning(pointcut = "getSongsPointcut()", returning = "result")
    public void afterGetSongs(Object result) {
        String status = response.getStatus() == 200 ? "Success!" : "Fail!";
        logger.info(status + "\n Status: " +
                response.getStatus() + "\n Result: " +
                result.toString());
    }

    //Get song by id
    @Pointcut("execution(* org.example.controller.SongController.getSong(..))")
    public void getSongPointcut(){

    }

    @Before("getSongPointcut()")
    public void beforeGetSong(JoinPoint joinPoint) {
        logger.info("Get song! Send request + \n IP address: " +
                request.getLocalAddr() + "\n Requested URL: " +
                request.getRequestURL() + "\n User: " +
                request.getRemoteUser());
    }

    @AfterReturning(pointcut = "getSongPointcut()", returning = "result")
    public void afterGetSong(Object result) {
        String status = response.getStatus() == 200 ? "Success!" : "Fail!";
        logger.info(status + "\n Status: " +
                response.getStatus() + "\n Result: " +
                result.toString());
    }

    //Create song
    @Pointcut("execution(* org.example.controller.SongController.createSong(..))")
    public void createSongPointcut(){

    }

    @Before("createSongPointcut()")
    public void beforeCreateSong(JoinPoint joinPoint) {
        logger.info("Create song! Send request + \n IP address: " +
                request.getLocalAddr() + "\n Requested URL: " +
                request.getRequestURL() + "\n User: " +
                request.getRemoteUser());;
    }

    @AfterReturning(pointcut = "createSongPointcut()", returning = "result")
    public void afterCreateSong(Object result) {
        String status = response.getStatus() == 200 ? "Success!" : "Fail!";
        logger.info(status + "\n Status: " +
                response.getStatus() + "\n Result: " +
                result.toString());
    }

    //Update song
    @Pointcut("execution(* org.example.controller.SongController.updateSong(..))")
    public void updateSongPointcut(){

    }

    @Before("updateSongPointcut()")
    public void beforeUpdateSong(JoinPoint joinPoint) {
        logger.info("Update song! Send request + \n IP address: " +
                request.getLocalAddr() + "\n Requested URL: " +
                request.getRequestURL() + "\n User: " +
                request.getRemoteUser());
    }

    @AfterReturning(pointcut = "updateSongPointcut()", returning = "result")
    public void afterUpdateSong(Object result) {
        String status = response.getStatus() == 200 ? "Success!" : "Fail!";
        logger.info(status + "\n Status: " +
                response.getStatus() + "\n Result: " +
                result.toString());
    }

    //Delete song
    @Pointcut("execution(* org.example.controller.SongController.deleteSong(..))")
    public void deleteSongPointcut(){

    }

    @Before("deleteSongPointcut()")
    public void beforeDeleteSong(JoinPoint joinPoint) {
        logger.info("Delete song! Send request + \n IP address: " +
                request.getLocalAddr() + "\n Requested URL: " +
                request.getRequestURL() + "\n User: " +
                request.getRemoteUser());
    }

    @AfterReturning(pointcut = "deleteSongPointcut()", returning = "result")
    public void afterDeleteSong(Object result) {
        String status = response.getStatus() == 200 ? "Success!" : "Fail!";
        logger.info(status + "\n Status: " +
                response.getStatus());
    }
}
