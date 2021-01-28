package com.poc.authorization.model.request;

import com.poc.authorization.validator.annotation.UUID4;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class BaseRequest {

    @Schema(description = "UUID4 (Universally unique identifier version 4)",
            example = "069af93f-7e7c-4a00-a8ff-b1c2bf821a38", required = true)
    // DONE TODO implements a custom validator (with a custom message for BindingResult) that checks if this string
    @UUID4
    private String requestID;
}
