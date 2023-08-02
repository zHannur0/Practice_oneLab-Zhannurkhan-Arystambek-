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
    public class ControllersLoggerAspect {
        private static final Logger logger = LoggerFactory.getLogger(ControllersLoggerAspect.class);

        HttpServletRequest request;

        HttpServletResponse response;

        @Autowired
        public ControllersLoggerAspect(HttpServletRequest request, HttpServletResponse response) {
            this.request = request;
            this.response = response;
        }


        @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
        public void controllerLoggers() {
        }

        @Before("controllerLoggers()")
        public void beforePlaylists(JoinPoint joinPoint) {
            long startTime = System.currentTimeMillis();
            logger.info("\n Send request + \n IP address: " +
                    request.getLocalAddr() + "\n Requested URL: " +
                    request.getRequestURL() + "\n User: " +
                    request.getRemoteUser());

            request.setAttribute("startTime", startTime);
        }

        @AfterReturning(pointcut = "controllerLoggers()", returning = "result")
        public void afterPlaylists(Object result) {
            long endTime = System.currentTimeMillis();
            long startTime = (Long) request.getAttribute("startTime");
            long duration = endTime - startTime;

            String status = response.getStatus() == 200 ? "Success!" : "Fail!";
            logger.info("\n " + status + "\n Status: " + response.getStatus() + "\n Result: " + result.toString()
                    + "\n Duration: " + duration + " ms");
        }


        }

