package com.poc.authorization.controller;

import com.poc.authorization.model.response.LoginResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


@ControllerAdvice(annotations = {RestController.class})
public class ValidationExceptionHandler {

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<LoginResponse> handleBindingErrors(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
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
}

