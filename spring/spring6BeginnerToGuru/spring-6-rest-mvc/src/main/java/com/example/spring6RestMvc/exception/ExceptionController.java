package com.example.spring6RestMvc.exception;

import com.example.spring6RestMvc.entities.Beer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// @ControllerAdvice
//When uncommented then for any NotFoundException thrown in the Controller/Service/Data Layer the handleNotFound
//Exception will be thrown
@Slf4j
public class ExceptionController {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Beer> handleNotFoundException() {
        log.error("*** Activated Exception Handler by uncommenting the ControllerAdvice *****");
        return ResponseEntity.notFound().build();
    }

}
