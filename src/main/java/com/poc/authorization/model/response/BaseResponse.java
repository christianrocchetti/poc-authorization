package com.poc.authorization.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class BaseResponse {
    private Integer httpStatus;
    private Map<String, String> errors;
}
