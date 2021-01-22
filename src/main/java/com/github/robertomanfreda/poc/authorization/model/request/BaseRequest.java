package com.github.robertomanfreda.poc.authorization.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class BaseRequest {

    @NotEmpty
    @NotNull
    // TODO implements a custom validator (with a custom message for BindingResult) that checks if this string
    //  is an UUID4
    private String requestID;
}
