package com.edu.serenitydemo.exception;

import com.edu.serenitydemo.constants.ResponseStatusEnum;

import java.util.Objects;

public class BadRequestException extends RuntimeException {

    private ResponseStatusEnum statusEnum;
    private Object[] params;

    public BadRequestException(ResponseStatusEnum statusEnum, Object... paramArgs) {
        super(String.format(statusEnum.message(), (Object) paramArgs));
        this.statusEnum = statusEnum;
        this.params = paramArgs;
    }

    public ResponseStatusEnum getStatusEnum() {
        return statusEnum;
    }

    public Object[] getParams() {
        return params;
    }

    public String getMessage() {
        return String.format(this.statusEnum.message(), (Object) this.params);
    }
}
