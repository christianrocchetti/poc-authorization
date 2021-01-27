package com.poc.authorization.aop;

import com.poc.authorization.model.request.BaseRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

// DONE TODO implement a Logging aspect that logs the following details:
//      - requestId         (The unique IDentifier for the request)
//      - elapsedTime       (The total elapsed time for the request)
//      - ipAddress         (The IP address)
//      This aspect should be activated on every REST endpoints
@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class LoggingAspect {

    private final HttpServletRequest httpServletRequest;
    private long startTime;

    @Pointcut("@within(org.springframework.web.bind.annotation.RestController) && args(baseRequest,..)")
    public void authLogging(BaseRequest baseRequest) {
    }


    @Before(value = "authLogging(baseRequest)")
    public void beforeLogging(JoinPoint pjp, BaseRequest baseRequest) {
        startTime = System.nanoTime();
    }


    @AfterReturning(value = "authLogging(baseRequest)")
    public void afterLogging(JoinPoint pjp, BaseRequest baseRequest) {

        Optional<String> ip = Optional.ofNullable(httpServletRequest.getHeader("X-FORWARDED-FOR"));
        if (ip.isEmpty()) {
            ip = Optional.of(httpServletRequest.getRemoteAddr());
        }

        log.info("ip: {} elapsedTime: {} ns requestID {}",
                ip.get(), System.nanoTime() - startTime, baseRequest.getRequestID());
    }


}
