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
    public class PlaylistControllerLoggerAspect {
        private static final Logger logger = LoggerFactory.getLogger(PlaylistControllerLoggerAspect.class);

        HttpServletRequest request;

        HttpServletResponse response;

        @Autowired
        public PlaylistControllerLoggerAspect(HttpServletRequest request, HttpServletResponse response) {
            this.request = request;
            this.response = response;
        }

        //Get all playlist
        @Pointcut("execution(* org.example.controller.PlaylistController.getPlaylists())")
        public void getPlaylistsPointcut(){

        }

        @Before("getPlaylistsPointcut()")
        public void beforeGetPlaylists(JoinPoint joinPoint) {
            logger.info("Get playlists! Send request + \n IP address: " +
                    request.getLocalAddr() + "\n Requested URL: " +
                    request.getRequestURL() + "\n User: " +
                    request.getRemoteUser());
        }

        @AfterReturning(pointcut = "getPlaylistsPointcut()", returning = "result")
        public void afterGetPlaylists(Object result) {
            String status = response.getStatus() == 200 ? "Success!" : "Fail!";
            logger.info(status + "\n Status: " +
                    response.getStatus() + "\n Result: " +
                    result.toString());
        }

        //Get playlist by id
        @Pointcut("execution(* org.example.controller.PlaylistController.getPlaylist(..))")
        public void getPlaylistPointcut(){

        }

        @Before("getPlaylistPointcut()")
        public void beforeGetPlaylist(JoinPoint joinPoint) {
            logger.info("Get playlist! Send request + \n IP address: " +
                    request.getLocalAddr() + "\n Requested URL: " +
                    request.getRequestURL() + "\n User: " +
                    request.getRemoteUser());
        }

        @AfterReturning(pointcut = "getPlaylistPointcut()", returning = "result")
        public void afterGetPlaylist(Object result) {
            String status = response.getStatus() == 200 ? "Success!" : "Fail!";
            logger.info(status + "\n Status: " +
                    response.getStatus() + "\n Result: " +
                    result.toString());
        }

        //Create playlist
        @Pointcut("execution(* org.example.controller.PlaylistController.createPlaylist(..))")
        public void createPlaylistPointcut(){

        }

        @Before("createPlaylistPointcut()")
        public void beforeCreatePlaylist(JoinPoint joinPoint) {
            logger.info("Create playlist! Send request + \n IP address: " +
                    request.getLocalAddr() + "\n Requested URL: " +
                    request.getRequestURL() + "\n User: " +
                    request.getRemoteUser());
        }

        @AfterReturning(pointcut = "createPlaylistPointcut()", returning = "result")
        public void afterCreatePlaylist(Object result) {
            String status = response.getStatus() == 200 ? "Success!" : "Fail!";
            logger.info(status + "\n Status: " +
                    response.getStatus() + "\n Result: " +
                    result.toString());
        }

        //Update playlist
        @Pointcut("execution(* org.example.controller.PlaylistController.updatePlaylist(..))")
        public void updatePlaylistPointcut(){

        }

        @Before("updatePlaylistPointcut()")
        public void beforeUpdatePlaylist(JoinPoint joinPoint) {
            logger.info("Update playlist! Send request + \n IP address: " +
                    request.getLocalAddr() + "\n Requested URL: " +
                    request.getRequestURL() + "\n User: " +
                    request.getRemoteUser());
        }

        @AfterReturning(pointcut = "updatePlaylistPointcut()", returning = "result")
        public void afterUpdatePlaylist(Object result) {
            String status = response.getStatus() == 200 ? "Success!" : "Fail!";
            logger.info(status + "\n Status: " +
                    response.getStatus() + "\n Result: " +
                    result.toString());
        }

        //Delete playlist
        @Pointcut("execution(* org.example.controller.PlaylistController.deletePlaylist(..))")
        public void deletePlaylistPointcut(){

        }

        @Before("deletePlaylistPointcut()")
        public void beforeDeletePlaylist(JoinPoint joinPoint) {
            logger.info("Delete playlist! Send request + \n IP address: " +
                    request.getLocalAddr() + "\n Requested URL: " +
                    request.getRequestURL() + "\n User: " +
                    request.getRemoteUser());
        }

        @AfterReturning(pointcut = "deletePlaylistPointcut()", returning = "result")
        public void afterDeletePlaylist(Object result) {
            String status = response.getStatus() == 200 ? "Success!" : "Fail!";
            logger.info(status + "\n Status: " +
                    response.getStatus());
        }
    }
