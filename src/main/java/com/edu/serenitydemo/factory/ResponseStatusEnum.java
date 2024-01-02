package com.edu.serenitydemo.factory;

public enum ResponseStatusEnum {
    SUCCESS("success", "success");

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
