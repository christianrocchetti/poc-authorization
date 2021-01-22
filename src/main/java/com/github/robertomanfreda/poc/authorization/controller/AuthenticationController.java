package com.github.robertomanfreda.poc.authorization.controller;

import com.github.robertomanfreda.poc.authorization.aop.annotation.Authorizing;
import com.github.robertomanfreda.poc.authorization.model.request.LoginRequest;
import com.github.robertomanfreda.poc.authorization.model.response.LoginResponse;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Data
@RequestMapping("/auth")
@RequiredArgsConstructor
@RestController
public class AuthenticationController {

    @Authorizing
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest loginRequest,
                                               BindingResult bindingResult) {

        // TODO use this technique for every endpoints (?AOP? - ?ControllerAdvice?)
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();

            for (ObjectError error : bindingResult.getAllErrors()) {
                String fieldError = ((FieldError) error).getField();
                String errorMessage = error.getDefaultMessage();

                errors.put(fieldError, errorMessage);
            }

            LoginResponse response = LoginResponse.builder()
                    .httpStatus(HttpStatus.UNPROCESSABLE_ENTITY.value())
                    .errors(errors)
                    .build();

            return new ResponseEntity<>(response, Objects.requireNonNull(HttpStatus.resolve(response.getHttpStatus())));
        }

        LoginResponse response = LoginResponse.builder()
                .httpStatus(HttpStatus.OK.value())
                .sessionID(UUID.randomUUID().toString())
                .build();

        return new ResponseEntity<>(response, Objects.requireNonNull(HttpStatus.resolve(response.getHttpStatus())));
    }
}
