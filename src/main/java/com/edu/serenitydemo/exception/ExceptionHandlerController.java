package com.edu.serenitydemo.exception;


import com.edu.serenitydemo.constants.ResponseStatusEnum;
import com.edu.serenitydemo.factory.ResponseFactory;
import com.edu.serenitydemo.factory.ResponseWrapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


@ControllerAdvice
@AllArgsConstructor
@Slf4j
public class ExceptionHandlerController {
    private final ResponseFactory responseFactory;


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<ResponseWrapper<Object>> handleValidateException(MethodArgumentNotValidException e) {
        log.error(e.getMessage());
        List<FieldError> fieldErrors = e.getFieldErrors();
        if (CollectionUtils.isNotEmpty(fieldErrors)) {
            Map<String, Set<String>> fieldErrorMap = fieldErrors.stream()
                    .filter(fieldError -> StringUtils.isNotEmpty(fieldError.getDefaultMessage()))
                    .collect(Collectors.groupingBy(FieldError::getField,
                            HashMap::new,
                            Collectors.mapping(FieldError::getDefaultMessage, Collectors.toSet())));
            log.error(fieldErrorMap.toString());
            return responseFactory.error(HttpStatus.BAD_REQUEST,
                    ResponseStatusEnum.VALIDATE_ERROR,
                    fieldErrorMap);
        }
        log.error(e.getMessage());

        return responseFactory.error(HttpStatus.BAD_REQUEST, ResponseStatusEnum.VALIDATE_ERROR, e.getLocalizedMessage());
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseBody
    public ResponseEntity<ResponseWrapper<Object>> handleValidateException(BadRequestException e) {
        log.error(e.getMessage());
        return responseFactory.error(
                HttpStatus.BAD_REQUEST,
                e.getStatusEnum());
    }

}
