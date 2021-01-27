package com.poc.authorization.aop;

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

// TODO implement a Logging aspect that logs the following details:
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

    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping) " +
            "|| @annotation(org.springframework.web.bind.annotation.GetMapping)" +
            "|| @annotation(org.springframework.web.bind.annotation.PostMapping)" +
            "|| @annotation(org.springframework.web.bind.annotation.PatchMapping)" +
            "|| @annotation(org.springframework.web.bind.annotation.PutMapping)" +
            "|| @annotation(org.springframework.web.bind.annotation.DeleteMapping)"
    )
    public void authLogging() {
    }

    @Before(value = "authLogging()")
    public void beforeLogging(JoinPoint pjp) {
        startTime = System.nanoTime();
    }


    @AfterReturning(value = "authLogging()")
    public void afterLogging(JoinPoint pjp) {

        Optional<String> ip = Optional.ofNullable(httpServletRequest.getHeader("X-FORWARDED-FOR"));
        if (ip.isEmpty()) {
            ip = Optional.of(httpServletRequest.getRemoteAddr());
        }

        log.info("ip: {} elapsedTime: {} ns", ip.get(), System.nanoTime() - startTime);
    }


}
