package com.example.spring6RestMvc.exception;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class CustomErrorController {

    @ExceptionHandler
    ResponseEntity handleJPViolations(TransactionSystemException exception) {
        ResponseEntity.BodyBuilder responseEntity = ResponseEntity.badRequest();

        if (exception.getCause().getCause() instanceof ConstraintViolationException constraintViolationException) {
            List errors = constraintViolationException.getConstraintViolations().stream()
                    .map( constraintViolation -> {
                        Map<String, String> errorMap = new HashMap<>();
                        errorMap.put(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage());
                        return errorMap;
                    }).toList();
            return responseEntity.body(errors);
        }

        return ResponseEntity.badRequest().build();

    }

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

        return ResponseEntity.badRequest().body(errorList);

    }
}
