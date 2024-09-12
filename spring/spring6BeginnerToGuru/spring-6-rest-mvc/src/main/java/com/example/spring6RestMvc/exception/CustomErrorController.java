package com.example.spring6RestMvc.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class CustomErrorController {
    /**
     * An example of a customer error body
     *
     * @param exception  The MethodArgumentNotValidException instance that contains the error validation messages
     *
     * @return  The error message for each field error
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<List<Map<String, String>>> handleBindErrors(MethodArgumentNotValidException exception) {

        // The map will render to Jackson quite nicely
        List<Map<String, String>> errorList = exception.getFieldErrors().stream().map(fieldError -> {
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
            return errorMap;
        }).toList();

        log.error("Charles Stockman " + errorList.toString());
        log.error("Charles Stockman " + errorList.size());

        return ResponseEntity.badRequest().body(errorList);

    }
}
