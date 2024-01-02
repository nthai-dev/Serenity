package com.edu.serenitydemo.factory;


import com.edu.serenitydemo.constants.ResponseStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseWrapper<T> {

    private String statusCode;
    private String message;
    private T data;

    public ResponseWrapper(ResponseStatusEnum responseStatusEnum) {
        this.statusCode = responseStatusEnum.code();
        this.message = responseStatusEnum.message();
    }

    public ResponseWrapper(ResponseStatusEnum responseStatusEnum, T data) {
        this.statusCode = responseStatusEnum.code();
        this.message = responseStatusEnum.message();
        this.data = data;
    }
}
