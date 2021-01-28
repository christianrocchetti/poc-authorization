package com.poc.authorization.controller;

import com.poc.authorization.aop.annotation.Authorizing;
import com.poc.authorization.model.request.LoginRequest;
import com.poc.authorization.model.response.LoginResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Objects;
import java.util.UUID;

@Data
@RequestMapping("/auth")
@RequiredArgsConstructor
@RestController
public class AuthenticationController {

    @Authorizing
    @Operation(description = "")
    @PostMapping(value = "/login", produces = "application/json")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest loginRequest) {

        // DONE TODO use this technique for every endpoints (?AOP? - ?ControllerAdvice?)

        LoginResponse response = LoginResponse.builder()
                .httpStatus(HttpStatus.OK.value())
                .sessionID(UUID.randomUUID().toString())
                .build();

        return new ResponseEntity<>(response, Objects.requireNonNull(HttpStatus.resolve(response.getHttpStatus())));
    }
}
