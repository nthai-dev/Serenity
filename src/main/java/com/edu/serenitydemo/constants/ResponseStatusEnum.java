package com.edu.serenitydemo.constants;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;

public enum ResponseStatusEnum {
    SUCCESS("success", "success"),
    VALIDATE_ERROR("validate_error", "Validate request body fail"),
    BAD_REQUEST_ERROR("invalid_request", "Validate request body fail"),
    CUSTOMER_NOT_FOUND("not_found", "Customer [%s] is not found");
    private String code;
    private String message;

    ResponseStatusEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String code() {
        return code;
    }

    public String message() {
        return message;
    }
}
