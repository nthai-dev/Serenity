package com.edu.serenitydemo.factory;

import com.edu.serenitydemo.constants.ResponseStatusEnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import static com.edu.serenitydemo.constants.ResponseStatusEnum.SUCCESS;


@Component
public class ResponseFactory {

    public ResponseEntity<ResponseWrapper<Object>> success() {
        ResponseWrapper<Object> responseObject = new ResponseWrapper<>(SUCCESS);
        return ResponseEntity.ok(responseObject);
    }

    public <T> ResponseEntity<ResponseWrapper<T>> success(T data) {
        ResponseWrapper<T> responseObject = new ResponseWrapper<>(SUCCESS, data);
        return ResponseEntity.ok(responseObject);
    }

    public ResponseEntity<ResponseWrapper<Object>> error(HttpStatus httpStatus, ResponseStatusEnum responseStatusEnum) {
        ResponseWrapper<Object> responseObject = new ResponseWrapper<>(responseStatusEnum);

        return new ResponseEntity<>(responseObject, httpStatus);
    }

    public <T> ResponseEntity<ResponseWrapper<T>> error(HttpStatus httpStatus, ResponseStatusEnum responseStatusEnum, T data) {
        ResponseWrapper<T> responseObject = new ResponseWrapper<>(responseStatusEnum, data);

        return new ResponseEntity<>(responseObject, httpStatus);
    }

}
