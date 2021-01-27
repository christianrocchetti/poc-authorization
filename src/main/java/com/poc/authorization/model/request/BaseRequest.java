package com.poc.authorization.model.request;

import com.poc.authorization.validator.annotation.UUID4;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class BaseRequest {

    // DONE TODO implements a custom validator (with a custom message for BindingResult) that checks if this string
    @UUID4
    private String requestID;
}
