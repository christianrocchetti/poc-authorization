package com.poc.authorization.aop;

import com.poc.authorization.stub.Base64Stub;
import com.poc.authorization.utils.HeadersHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

import static com.poc.authorization.utils.HeadersHelper.TokenType.BASE_64;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class AuthorizationAspect {

    private final HttpServletRequest httpServletRequest;

    @Pointcut("execution(@com.poc.authorization.aop.annotation.Authorizing * *.*(..))")
    public void authPointcut() {
        // All methods marked using @Authorizing pointcut
    }

    @Around(value = "authPointcut()")
    public Object authorizing(ProceedingJoinPoint pjp) throws Throwable {

        // Extract authentication token, in this case we're using a simple base64 token
        Optional<String> authorization = Optional.ofNullable(httpServletRequest.getHeader("Authorization"));

        if (authorization.isPresent()) {
            // Remove 'Basic ' keyword from the header
            String base64Token = HeadersHelper.extractToken(authorization.get(), BASE_64);

            // Do your logic and check if the user exists
            if (Base64Stub.get().contains(base64Token)) {
                return pjp.proceed(); // --> The user exists, return the control to the method caller
            }
        }

        // The Authorization is not present or the user doesn't exists, return 401 UNAUTHORIZED
        // skipping the pjp.proceed()
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }


}
